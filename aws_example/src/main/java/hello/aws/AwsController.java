
package hello.aws;

import com.amazonaws.services.s3.model.S3Object;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;

import hello.aws.model.DocumentData;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
@RestController
@RequestMapping(path = "/s3")
public class AwsController {

    private static Log LOG = LogFactory.getLog(AwsController.class);

    @Autowired
    private AmazonS3Client amazonS3Client;

    @GetMapping("/get/{filename}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String filename) throws IOException {
        S3Object s3Object = amazonS3Client.getS3Object(filename);

        return ResponseEntity.ok()
            .contentType(contentType(filename))
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
            .body(IOUtils.toByteArray(s3Object.getObjectContent()));
    }

    @PostMapping("/add/{filename}")
    public String addSampleFile(@PathVariable String filename) {

        DocumentData data = new DocumentData("application/pdf", filename);
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("sample.pdf");

        amazonS3Client.addDocument(inputStream, data);
        return "Upload Successfully. -> KeyName = " + filename;
    }

    @GetMapping("/contains/{filename}")
    public String containFile(@PathVariable String filename) throws IOException {
        boolean exists = amazonS3Client.containsDocument(filename);
        return filename + " exists : " + exists;
    }

    @GetMapping("/bucket/{bucketname}")
    public String bucketExists(@PathVariable String bucketname) throws IOException {
        boolean exists = amazonS3Client.doesBucketExists(bucketname);
        return bucketname + " exists : " + exists+", "+amazonS3Client.getBucketName()+" exists : "+amazonS3Client.doesBucketExists(amazonS3Client.getBucketName());
    }

    @DeleteMapping("/delete/{filename}")
    public String deleteFile(@PathVariable String filename) throws IOException {
        boolean exists = amazonS3Client.containsDocument(filename);
        if (exists) {
            amazonS3Client.deleteDocument(filename);
            return filename + " deleted";
        }
        return filename + " doesn't exists";
    }

    private MediaType contentType(String keyname) {
        String[] arr = keyname.split("\\.");
        String type = arr[arr.length - 1].toLowerCase();
        switch (type) {
            case "txt":
                return MediaType.TEXT_PLAIN;
            case "png":
                return MediaType.IMAGE_PNG;
            case "jpg":
                return MediaType.IMAGE_JPEG;
            case "pdf":
                return MediaType.APPLICATION_PDF;
            default:
                return MediaType.APPLICATION_OCTET_STREAM;
        }
    }
}
