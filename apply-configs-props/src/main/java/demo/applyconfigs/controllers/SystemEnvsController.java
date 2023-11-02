package demo.applyconfigs.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
public class SystemEnvsController {

    @Value("${value03.system.env.path}")
    String value03EnvPath;

    @Value("${value04.system.env.path}")
    String value04EnvPath;

    @GetMapping(path="/system-envs/list")
    public List<String> listSytemEnvsValues() {
        List<String> results = new LinkedList<>();

        results.add("value03EnvPath = " + value03EnvPath);
        results.add("value04EnvPath = " + value04EnvPath);

        return results;
    }

}
