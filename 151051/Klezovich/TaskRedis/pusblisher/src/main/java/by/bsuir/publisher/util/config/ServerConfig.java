package by.bsuir.publisher.util.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "server")
@Getter
@Setter
public class ServerConfig {
    private int port;
    private Servlet servlet;

    @Getter
    @Setter
    public static class Servlet {
        private String contextPath;
    }
}
