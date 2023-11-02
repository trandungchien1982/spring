package demo.applyconfigs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
public class ValuesController {

    @Value("Value 01")
    String value01;

    @Value("${value02}")
    String value02;

    @Value("${value02.sub}")
    String value02IncludeSub;

    @GetMapping(path="/values/list")
    public List<String> listValues() {
        List<String> results = new LinkedList<>();

        results.add("value01 = " + value01);
        results.add("value02 = " + value02);
        results.add("value02.sub = " + value02IncludeSub);

        return results;
    }

}
