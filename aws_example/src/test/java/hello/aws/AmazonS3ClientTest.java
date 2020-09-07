package hello.aws;

import com.amazonaws.services.s3.model.S3Object;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.io.InputStream;

import hello.aws.model.DocumentData;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
@ContextConfiguration(value = {"classpath:/AmazonS3ClientTest-context.xml"})
@PropertySource(value = "classpath:/application-test.properties")
@RunWith(SpringJUnit4ClassRunner.class)
public class AmazonS3ClientTest {

    @Autowired
    private AmazonS3Client amazonS3Client;

    @Test
    public void isAvailable() {
        assertTrue("AWS S3 data store is not available", amazonS3Client.isAvailable());
    }

    @Test
    public void addDocumentTest() throws IOException {
        String file = "abc.pdf";
        DocumentData data = new DocumentData("application/pdf", file);

        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("sample.pdf");

        assertNotNull(inputStream);

        DocumentData documentData = amazonS3Client.addDocument(inputStream, data);
        assertNotNull(documentData);

        // get underlying files and assert that is is compressed/encrypted!
        S3Object object = amazonS3Client.getS3Object(file);

        byte[] bytes = IOUtils.toByteArray(object.getObjectContent());
        assertTrue(bytes != null && bytes.length > 0);

        amazonS3Client.deleteDocument(file);
    }

    @Test
    public void containsDocumentTest() throws IOException {
        String file = "abc.pdf";
        DocumentData data = new DocumentData("application/pdf", file);

        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("sample.pdf");
        assertNotNull(inputStream);

        DocumentData documentData = amazonS3Client.addDocument(inputStream, data);
        assertNotNull(documentData);

        boolean exists = amazonS3Client.containsDocument(file);

        assertTrue(exists);

        amazonS3Client.deleteDocument(file);
    }
}
