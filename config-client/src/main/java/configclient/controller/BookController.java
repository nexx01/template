package configclient.controller;

import configclient.entity.Book;
import configclient.feign.ServiceFeignClient;
import configclient.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/")
@RestController
public class BookController {

    public static final Logger log = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private TestService testService;

    @RequestMapping(path = "/getAllBooksFromService")
    public List<Book> getDataByFeughnClient() {
        System.out.println("Controller");
        return ServiceFeignClient.FeignHolder.create().getAllBooks();
    }

    @GetMapping(path = "getAllBooksByFeignClient")
    public List<Book> getAllBooks() {
        log.info("Calling through Feign Client");
        return testService.getAllBookksList();
    }

}
