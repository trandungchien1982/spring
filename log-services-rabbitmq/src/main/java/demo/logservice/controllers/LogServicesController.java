package demo.logservice.controllers;

import demo.logservice.producer.RabbitMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@RestController
public class LogServicesController {

    @Autowired
    RabbitMQProducer rabbitMQProducer;

    List<String> listAllLogs = new LinkedList<>();

    @GetMapping(path="/logs/all")
    public List<String> allLogs() {
        return listAllLogs;
    }

    @GetMapping(path="/logs/clear")
    public List<String> clearLogs() {
        listAllLogs.clear();
        return listAllLogs;
    }

    @GetMapping(path="/logs/add-1-item")
    public String add1Item() {
        rabbitMQProducer.sendMessage("Item :: SEND from tdchien ... ");
        return "OK";
    }
}
