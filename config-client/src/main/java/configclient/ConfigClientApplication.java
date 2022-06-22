package configclient;

import configclient.feign.ServiceBConnector;
import configclient.feign.ServiceFeignClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(clients = ServiceBConnector.class )
@EnableEurekaClient
public class ConfigClientApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(ConfigClientApplication.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
