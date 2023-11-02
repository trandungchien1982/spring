package demo.applyconfigs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@RestController
public class EnvsController {

    @Autowired
    Environment env;

    @GetMapping(path="/env/list")
    public List<String> listEnvs() {
        List<String> results = new LinkedList<>();

        // Add active profiles
        results.addAll(Arrays.asList(env.getActiveProfiles()));

        // Add default profiles
        results.addAll(Arrays.asList(env.getDefaultProfiles()));

        results.add("server.port = " + env.getProperty("server.port"));
        results.add("test.first.data = " + env.getProperty("test.first.data"));
        results.add("test.second.data = " + env.getProperty("test.second.data"));
        results.add("test.3rd.data = " + env.getProperty("test.3rd.data", "[NotFound:test.3rd.data]"));

        results.add("spring.datasource.url = " + env.getProperty("spring.datasource.url"));
        results.add("spring.datasource.username = " + env.getProperty("spring.datasource.username"));
        results.add("spring.datasource.password = " + env.getProperty("spring.datasource.password"));

        return results;
    }

}
