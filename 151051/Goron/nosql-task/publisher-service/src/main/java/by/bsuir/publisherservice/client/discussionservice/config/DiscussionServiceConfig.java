package by.bsuir.publisherservice.client.discussionservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

import by.bsuir.publisherservice.client.discussionservice.DiscussionServiceClient;

@Configuration
public class DiscussionServiceConfig {
    @Autowired
    private Environment env;
    
    @Bean
    RestClient restClient(ObjectMapper objectMapper) {
        return RestClient.builder()
                         .baseUrl(env.getProperty("rest-client.base-url", "http://localhost:24130/api/v1.0"))
                         .build();
    }

    @Bean
    DiscussionServiceClient discussionServiceClient(RestClient restClient) {
    HttpServiceProxyFactory httpServiceProxyFactory =
        HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient))
                               .build();

        return httpServiceProxyFactory.createClient(DiscussionServiceClient.class);
    }

}
