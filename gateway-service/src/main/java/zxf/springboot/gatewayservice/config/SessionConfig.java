package zxf.springboot.gatewayservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.mongo.JacksonMongoSessionConverter;
import org.springframework.session.data.mongo.config.annotation.web.reactive.EnableMongoWebSession;

@Configuration
@EnableMongoWebSession
public class SessionConfig {
    @Bean
    public JacksonMongoSessionConverter jacksonMongoSessionConverter() {
        System.out.println("SessionConfig::jacksonMongoSessionConverter");
        return new JacksonMongoSessionConverter();
    }
}