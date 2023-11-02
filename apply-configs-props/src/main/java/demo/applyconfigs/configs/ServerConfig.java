package demo.applyconfigs.configs;

import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Copy from https://github.com/eugenp/tutorials/blob/master/spring-boot-modules/spring-boot-testing/src/main/java/com/baeldung/boot/configurationproperties/ServerConfig.java
 */
@Configuration
@ConfigurationProperties(prefix = "server")
@PropertySource("classpath:server.properties")
@Data
public class ServerConfig {

    // POJO
    private Address address;

    // Map
    private Map<String, String> resourcesPath;

    // List
    private List<String> listNewFolders;

    // Set
    private Set<String> setOfNames;

    // String[]
    private String[] arrDates;

    @Data
    public static class Address {
        private String ip;
        private String name;
        private String street;
    }

}