
package hello.aws;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
@Configuration
@ImportResource(value = "classpath*:AmazonS3Config-context.xml")
public class AmazonS3Configuration {

}
