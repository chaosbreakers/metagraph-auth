package io.metagraph.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @author Ranger Tsao(https://github.com/boliza)
 */
@SpringBootApplication
@EnableAuthorizationServer
public class MetagraphAuth {

    public static void main(String[] args) {
        SpringApplication.run(MetagraphAuth.class, args);
    }

}
