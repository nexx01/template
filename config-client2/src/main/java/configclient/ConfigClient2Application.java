package configclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ConfigClient2Application {

    public static void main(String[] args) {
        try {
            SpringApplication.run(ConfigClient2Application.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
