package zxf.springboot.authservice.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.mongo.JacksonMongoSessionConverter;
import org.springframework.session.data.mongo.config.annotation.web.http.EnableMongoHttpSession;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

@Configuration
@EnableMongoHttpSession
public class SessionConfig {
    @Bean
    public JacksonMongoSessionConverter jacksonMongoSessionConverter() {
        System.out.println("SessionConfig::jacksonMongoSessionConverter");
        return new JacksonMongoSessionConverter();
    }

    @Bean
    public CookieSerializer cookieSerializer(){
        DefaultCookieSerializer defaultCookieSerializer = new DefaultCookieSerializer();
        defaultCookieSerializer.setUseBase64Encoding(false);
        return defaultCookieSerializer;
    }
}