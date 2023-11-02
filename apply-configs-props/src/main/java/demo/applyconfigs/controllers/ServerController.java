package demo.applyconfigs.controllers;

import demo.applyconfigs.configs.ServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
public class ServerController {

    @Autowired
    ServerConfig serverConfig;

    @GetMapping(path="/server/list")
    public List<String> listServerProperties() {
        List<String> results = new LinkedList<>();

        results.add("server.address.ip = " + serverConfig.getAddress().getIp());
        results.add("server.address.name = " + serverConfig.getAddress().getName());
        results.add("server.address.street = " + serverConfig.getAddress().getStreet());
        results.add("server.resource_path.* = " + serverConfig.getResourcesPath());

        for (String item: serverConfig.getListNewFolders()) {
            results.add("server.list_new_folders.[idx] = " + item);
        }

        for (String item: serverConfig.getSetOfNames()) {
            results.add("server.set_of_names.[idx] = " + item);
        }

        for (String item: serverConfig.getArrDates()) {
            results.add("server.arr_Dates.[idx] = " + item);
        }

        return results;
    }

}
