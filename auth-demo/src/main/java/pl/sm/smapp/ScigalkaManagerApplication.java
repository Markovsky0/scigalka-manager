package pl.sm.smapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import pl.sm.smapp.configuration.RSAKeyProperties;

@SpringBootApplication
@EnableConfigurationProperties(RSAKeyProperties.class)
public class ScigalkaManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScigalkaManagerApplication.class, args);
    }

}
