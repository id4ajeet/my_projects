package hello;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

    private static final Log LOG = LogFactory.getLog(Application.class);

    @Value("${app.ajeet.message:Sample_Msg}")
    private String msg;

    @RequestMapping("/")
    public String home() {
        LOG.info("!!!!!!!!!!Hello World!!!!!!!!!!");
        LOG.info("Message set to " + msg);
        return "!!!!!!!!!!Hello World!!!!!!!!!!\n" + "Message : " + msg + "\n\n";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
