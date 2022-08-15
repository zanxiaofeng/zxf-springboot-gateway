package zxf.springboot.authservice.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.mongo.JacksonMongoSessionConverter;
import org.springframework.session.data.mongo.config.annotation.web.http.EnableMongoHttpSession;

@Configuration
@EnableMongoHttpSession
public class SessionConfig {
    @ConditionalOnProperty(prefix = "zxf", name = "session.jackson", havingValue = "true")
    public JacksonMongoSessionConverter jacksonMongoSessionConverter() {
        System.out.println("SessionConfig::jacksonMongoSessionConverter");
        return new JacksonMongoSessionConverter();
    }
}