package by.bsuir.publisher.client.discussion.config;

import by.bsuir.publisher.client.discussion.DiscussionClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class DiscussionConfig {

    @Autowired
    private Environment env;

    @Bean
    RestClient restClient() {
        String defaultUrl = "http://localhost:24130/api/v1.0";
        return RestClient.builder()
                .baseUrl(env.getProperty("rest-client.base-url", defaultUrl))
                .build();
    }

    @Bean
    DiscussionClient discussionClient(RestClient restClient) {
        HttpServiceProxyFactory httpServiceProxyFactory =
                HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient))
                        .build();

        return httpServiceProxyFactory.createClient(DiscussionClient.class);
    }
}
