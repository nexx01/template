package configclient.service;

import configclient.entity.Book;
import configclient.feign.ServiceBConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Autowired
    ServiceBConnector serviceBConnector;

    public List<Book> getAllBookksList() {
        return serviceBConnector.getAllBooks();
    }

}
