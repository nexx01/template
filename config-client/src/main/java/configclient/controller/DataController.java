package configclient.controller;

import configclient.service.DataTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DataController {

    @Autowired
    DataTestService service;

    @GetMapping("/data")
    public String data() {
        return service.data();
    }
}
