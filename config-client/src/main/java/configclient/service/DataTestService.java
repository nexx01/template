package configclient.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DataTestService {

    public static final Logger log = LoggerFactory.getLogger(DataTestService.class);

    @Autowired
    private RestTemplate template;

    //Здесь просто при помощи RestTemplate мы используем метод getForObject() в котором передаем адрес и необходимый
    // нам URL. Также определили Fallback метод в случае какого-либо сбоя со стороны вызывающего сервиса. Этот метод
    // никакой смысловой логики не несет, он просто показывает наглядный пример взаимодействия.
    @HystrixCommand(fallbackMethod = "failed")
    public String data() {
        String response = template.getForObject("http://localhost:8084/data", String.class);
        log.info(response);
        return response;
    }

    public String failed() {
        String error = "Service is not available noe.Please try later";
        log.info(error);
        return error;
    }

}
