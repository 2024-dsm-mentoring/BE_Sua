package org.example.be_sua;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class BeSuaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeSuaApplication.class, args);
    }

}
