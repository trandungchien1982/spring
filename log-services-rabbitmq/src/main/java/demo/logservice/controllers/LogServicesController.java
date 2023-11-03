package demo.logservice.controllers;

import demo.logservice.producer.RabbitMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(path="/logs/add-1-log")
    public String add1Item() {
        rabbitMQProducer.sendMessage("=== Try to send 1 log from log-services");
        rabbitMQProducer.sendMessage("log-service, Item New :: SEND from tdchien ... ");
        return "OK-1-log";
    }

    @GetMapping(path="/logs/add-5-logs")
    public String add5Logs() {
        rabbitMQProducer.sendMessage("=== Try to send 5 logs from log-services");
        for (int i = 0; i < 5; i++) {
            rabbitMQProducer.sendMessage("log-service, Item " + i + " :: SEND from tdchien ... ");
        }

        return "OK-5-logs";
    }

    @GetMapping(path="/logs/add-50-logs")
    public String add50Logs() {
        rabbitMQProducer.sendMessage("=== Try to send 50 logs from log-services");
        for (int i = 0; i < 50; i++) {
            rabbitMQProducer.sendMessage("log-service, Item " + i + " :: SEND from tdchien ... ");
        }

        return "OK-50-logs";
    }

    @GetMapping(path="/logs/add-10K-logs")
    public String add200Logs() {
        rabbitMQProducer.sendMessage("=== Try to send 10K logs from log-services");
        for (int i = 0; i < 10000; i++) {
            rabbitMQProducer.sendMessage("log-service, Item " + i + " :: SEND from tdchien ... ");
        }

        return "OK-1000-logs";
    }
}
