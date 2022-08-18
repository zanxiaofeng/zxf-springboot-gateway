package zxf.springboot.gatewayservice.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import zxf.springboot.gatewayservice.service.E2ETokenService;

import java.util.Objects;

@Component
public class E2ETrustTokenFilter extends AbstractGatewayFilterFactory<E2ETrustTokenFilter.Config> {
    @Autowired
    private E2ETokenService e2ETokenService;

    public E2ETrustTokenFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            Mono<ServerWebExchange> newExchange = exchange.getSession().map(webSession -> {
                String accessToken = webSession.getAttribute(config.getAccessTokenKey());
                System.out.println("E2ETrustTokenFilter::apply, " + webSession.getId() + ", " + accessToken);

                ServerHttpRequest.Builder builder = exchange.getRequest().mutate();
                if (accessToken != null) {
                    builder.header(config.getE2eTokenHeader(), e2ETokenService.getE2EToken(accessToken));
                }
                return exchange.mutate().request(builder.build()).build();
            });
            return newExchange.flatMap(chain::filter);
        };
    }

    @Configuration
    @ConfigurationProperties(prefix = "e2etrust.filter")
    public static class Config {
        private static String accessTokenKey;
        private static String e2eTokenHeader;

        public String getAccessTokenKey() {
            return accessTokenKey;
        }

        public void setAccessTokenKey(String accessTokenKey) {
            this.accessTokenKey = accessTokenKey;
        }

        public String getE2eTokenHeader() {
            return e2eTokenHeader;
        }

        public void setE2eTokenHeader(String e2eTokenHeader) {
            this.e2eTokenHeader = e2eTokenHeader;
        }
    }
}
