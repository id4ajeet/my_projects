
package hello.aws;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.*;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.event.ProgressEvent;
import com.amazonaws.event.ProgressListener;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.internal.MD5DigestCalculatingInputStream;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;
import com.amazonaws.util.CountingInputStream;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import hello.aws.model.DocumentData;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class AmazonS3Client implements ProgressListener {
    private static Log LOG = LogFactory.getLog(AmazonS3Client.class);

    public static final String DEFAULT_REGION = "eu-west-1";
    public static final Pattern EXISTING_ENDPOINT_PATTERN = Pattern.compile("^(.+\\.)?s3[.-]([a-z0-9-]+)\\.");
    /**
     * Amazon AWS SDK for Java
     */
    private AmazonS3 s3;
    private int corePoolSize = 1;
    private int maximumPoolSize = 10;
    private long keepAliveTime = 120L; //keep alive time in seconds
    private String bucketName;
    private String awsAccessKey;
    private String awsSecretKey;
    private boolean connected = false;
    private int maximumClientConnections = -1;//-1 means not configured, will get the default value
    private String regionEndpoint = DEFAULT_REGION;
    /**
     * A transfer manager for uploading big files asynchronously
     */
    private TransferManager tm;
    private AWSCredentialsProviderChain awsCredentialsProviderChain;
    private ClientConfiguration awsClientConfiguration;

    @PostConstruct
    public void afterPropertiesSet() {

        Assert.notNull(bucketName, "No S3 bucket configured");

        this.loadClientConfig();
        this.loadCredentialProvidersChain();
        this.connect();
    }

    /**
     * load DefaultCredentialProvidersChain along with StaticCredentialProvider
     */
    protected void loadCredentialProvidersChain() {
        if (getAwsCredentialsProviderChain() != null) {
            LOG.info("AWSCredentialsProvider's are already configured ");
            return;
        }

        List<AWSCredentialsProvider> providers = new ArrayList<>();
        addStaticCredentialProvider(providers);
        addDefaultCredentialProvider(providers);

        LOG.info("AWSCredentialsProvider configured are : " + providers);
        AWSCredentialsProviderChain chain = new AWSCredentialsProviderChain(providers);
        setAwsCredentialsProviderChain(chain);
    }

    /**
     * Method to add DefaultCredentialProviders to provider's chain
     */
    protected void addDefaultCredentialProvider(List<AWSCredentialsProvider> providers) {
        providers.add(new EnvironmentVariableCredentialsProvider());
        providers.add(new SystemPropertiesCredentialsProvider());
        providers.add(new ProfileCredentialsProvider());
        providers.add(new EC2ContainerCredentialsProviderWrapper());
    }

    /**
     * Method to add AWSStaticCredentialsProvider to provider's chain
     */
    protected void addStaticCredentialProvider(List<AWSCredentialsProvider> providers) {
        if (StringUtils.isNotEmpty(getAwsAccessKey()) && StringUtils.isNotEmpty(getAwsSecretKey())) {
            AWSCredentials credentials = new BasicAWSCredentials(getAwsAccessKey(), getAwsSecretKey());
            providers.add(new AWSStaticCredentialsProvider(credentials));
        }
    }

    /**
     * Method to provide ClientConfiguration
     */
    protected void loadClientConfig() {
        if (getAwsClientConfiguration() != null) {
            LOG.info("AwsClientConfiguration is already configured ");
            return;
        }

        if (getMaximumClientConnections() > 0) {
            ClientConfiguration config = new ClientConfiguration();
            config.setMaxConnections(getMaximumClientConnections());
            setAwsClientConfiguration(config);
        }
    }

    @PreDestroy
    public void disconnect() {
        if (this.tm != null) {
            this.tm.shutdownNow();
        }
    }

    private synchronized boolean connect() {

        try {
            // To communicate with S3, create a class that implements an
            // S3Service.
            // We will use the REST/HTTP implementation based on HttpClient, as
            // this is the most robust implementation.

            s3 = createS3Client();

            ThreadPoolExecutor executor = createThreadPoolExecutor();

            tm = TransferManagerBuilder
                .standard()
                .withS3Client(s3)
                .withExecutorFactory(() -> executor)
                .build();

            if (!doesBucketExists(getBucketName())) {
                LOG.warn("Creating Bucket '" + getBucketName() + "' in region " + this.getRegionEndpoint());
                s3.createBucket(new CreateBucketRequest(getBucketName(), getRegionEndpoint()));
            }

            connected = true;

        } catch (Exception e) {
            LOG.error("Could not connect to Amazon S3 API Endpoint", e);
            return false;
        }
        return true;

    }

    public boolean doesBucketExists(String bucketName) {
        LOG.info("Checking Bucket '" + bucketName + "' in region " + this.getRegionEndpoint());
        return s3.doesBucketExist(bucketName);
    }

    protected ThreadPoolExecutor createThreadPoolExecutor() {
        BlockingQueue<Runnable> linkedBlockingDeque = new LinkedBlockingDeque<Runnable>(50);

        return new ThreadPoolExecutor(getCorePoolSize(), getMaximumPoolSize(), getKeepAliveTime(), TimeUnit.SECONDS, linkedBlockingDeque,
            new ThreadPoolExecutor.CallerRunsPolicy());
    }

    private AmazonS3 createS3Client() {
        AmazonS3ClientBuilder s3ClientBuilder = AmazonS3ClientBuilder
            .standard()
            .withCredentials(getAwsCredentialsProviderChain());

        if (getAwsClientConfiguration() != null) {
            s3ClientBuilder.setClientConfiguration(getAwsClientConfiguration());
        }

        s3ClientBuilder.setRegion(this.getRegionEndpoint());
        return s3ClientBuilder.build();
    }

    public boolean isAvailable() {

        if (!connected) {
            return false;
        }
        // A good test to see if your S3Service can connect to S3 is to list
        // all the buckets you own. If a bucket listing produces no
        // exceptions, all is well.

        try {

            // Note This operation needs a different permission:
            // http://mikeferrier.com/2011/10/27/granting-access-to-a-single-s3-bucket-using-amazon-iam/
            List<Bucket> buckets = s3.listBuckets();
            LOG.info("S3 can connect and has " + buckets.size() + " buckets.");

        } catch (AmazonClientException ace) {
            LOG.error("Could not list S3 buckets. Data store not available.", ace);
            return false;
        }

        return true;
    }

    /**
     * Makes an entry in the logs is uploads are failing.
     */
    @Override
    public void progressChanged(ProgressEvent progressEvent) {

        switch (progressEvent.getEventType()) {
            case TRANSFER_COMPLETED_EVENT:
                LOG.debug("Completed upload of " + progressEvent.getBytesTransferred() + " bytes to Amazon S3");
                break;
            case TRANSFER_PART_FAILED_EVENT:
            case TRANSFER_FAILED_EVENT:
                LOG.error("Failed to upload document to Amazon S3. Check the S3 log for more info.");
                // We could wait for the exception from the Upload object
                break;
            default:
                break;
        }
    }

    protected DocumentData transferToS3(InputStream document, DocumentData item) {

        if (s3 == null) {
            throw new RuntimeException("No connection to S3 Service.");
        }

        try {

            CountingInputStream countBytesStream = new CountingInputStream(document);

            MD5DigestCalculatingInputStream checksumStream = new MD5DigestCalculatingInputStream(countBytesStream);

            ObjectMetadata metaData = new ObjectMetadata();
            // Documents can't change so cache aggressively
            metaData.setCacheControl("max-age=2592000");
            // metaData.setContentLength(doc.length);
            metaData.setContentType(item.getType());

            if (item.getType().equals("application/pdf")) {
                metaData.setContentDisposition("attachment");
            }
            //name now changed to batchId/uniqueName
            PutObjectRequest por = new PutObjectRequest(getBucketName(), item.getName(), checksumStream, metaData).withGeneralProgressListener(this);

            Upload myUpload = tm.upload(por);
            myUpload.waitForUploadResult();

            item.setSize(countBytesStream.getByteCount());
            item.setChecksum(new String(Hex.encodeHex(checksumStream.getMd5Digest())));

        } catch (AmazonServiceException ase) {
            LOG.error("Request to store document was rejected by Amazon S3. HTTP Status Code: " + ase.getStatusCode()
                + " AWS Error Code: " + ase.getErrorCode() + " Error Type: " + ase.getErrorType() + " Request ID: "
                + ase.getRequestId(), ase);
            throw new RuntimeException(ase);
        } catch (AmazonClientException ace) {
            LOG.error("Amazon S3 Client encountered a serious internal error while trying to communicate with S3.", ace);
            throw new RuntimeException(ace);
        } catch (InterruptedException e) {
            LOG.error("Amazon S3 Client got interrupted during upload. Document data store item name:" + item.getName(), e);
        }

        return item;

    }

    public DocumentData addDocument(InputStream document, DocumentData item) {
        return this.transferToS3(document, item);
    }

    public void deleteDocument(String name) {
        try {
            DeleteObjectRequest dor = new DeleteObjectRequest(getBucketName(), name);
            s3.deleteObject(dor);

        } catch (AmazonClientException ace) {
            LOG.error("Could not delete document from S3 data store.", ace);
        }
    }

    public S3Object getS3Object(String name) {
        GetObjectRequest request = new GetObjectRequest(getBucketName(), name);
        return s3.getObject(request);
    }

    public boolean containsDocument(String name) {

        // Try and get the document
        try {

            GetObjectMetadataRequest gor = new GetObjectMetadataRequest(getBucketName(), name);

            ObjectMetadata data = getAmazonS3Client().getObjectMetadata(gor);

            return (data != null && data.getContentLength() > 0l);

        } catch (AmazonClientException ace) {
            // Not an exception
            return false;
        }
    }

    public URL getLink(String name, Date expiryDate) {
        URL url = null;
        try {
            url = s3.generatePresignedUrl(getBucketName(), name, expiryDate);
        } catch (AmazonClientException ace) {
            LOG.error("Could not get CDN link from S3 data store.", ace);
        }
        return url;
    }

    /**
     * Get access to the underlying storage. Sometimes useful for batch operations that can be better implemented using
     * the native API instead of via the DataStore interface.
     *
     * @return the s3 client so that tests can access the underlying data storage.
     */
    public AmazonS3 getAmazonS3Client() {
        return s3;
    }

    /**
     * @return the bucketName
     */
    public String getBucketName() {
        return bucketName;
    }

    /**
     * @param bucketName the bucketName to set
     */
    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    /**
     * @return the awsAccessKey
     */
    public String getAwsAccessKey() {
        return awsAccessKey;
    }

    /**
     * @param awsAccessKey the awsAccessKey to set
     */
    public void setAwsAccessKey(String awsAccessKey) {
        this.awsAccessKey = awsAccessKey;
    }

    /**
     * @return the awsSecretKey
     */
    public String getAwsSecretKey() {
        return awsSecretKey;
    }

    /**
     * @param awsSecretKey the awsSecretKey to set
     */
    public void setAwsSecretKey(String awsSecretKey) {
        this.awsSecretKey = awsSecretKey;
    }

    /**
     * @return the regionEndpoint
     */
    public String getRegionEndpoint() {
        return regionEndpoint;
    }

    /**
     * Old S3 Client used regionEndpoint, but new builder can work based on region So for backward compatibility, if old
     * way client is configured with url - region will be extracted otherwise same property will be used for supplying
     * region.
     *
     * @param regionEndpoint the regionEndpoint to set
     */
    public void setRegionEndpoint(String regionEndpoint) {
        this.regionEndpoint = extractRegion(regionEndpoint);
    }

    public String extractRegion(String regionEndpoint) {
        if (StringUtils.isEmpty(regionEndpoint)) {
            LOG.error("Region cannot extracted from " + regionEndpoint);
            return regionEndpoint;
        }

        String host = URI.create(regionEndpoint).getHost();
        if (StringUtils.isNotEmpty(host)) {
            Matcher matcher = EXISTING_ENDPOINT_PATTERN.matcher(host);
            if (matcher.find()) {
                return matcher.group(2);
            }
        }
        return regionEndpoint;
    }

    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public void setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public long getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime(long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public int getMaximumClientConnections() {
        return maximumClientConnections;
    }

    public void setMaximumClientConnections(int maximumClientConnections) {
        this.maximumClientConnections = maximumClientConnections;
    }

    public AWSCredentialsProviderChain getAwsCredentialsProviderChain() {
        return awsCredentialsProviderChain;
    }

    public void setAwsCredentialsProviderChain(AWSCredentialsProviderChain awsCredentialsProviderChain) {
        this.awsCredentialsProviderChain = awsCredentialsProviderChain;
    }

    public ClientConfiguration getAwsClientConfiguration() {
        return awsClientConfiguration;
    }

    public void setAwsClientConfiguration(ClientConfiguration awsClientConfiguration) {
        this.awsClientConfiguration = awsClientConfiguration;
    }

}
