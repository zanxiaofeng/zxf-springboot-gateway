package zxf.springboot.gatewayservice.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.DefaultServerRedirectStrategy;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.security.web.server.ServerRedirectStrategy;
import org.springframework.util.Assert;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

public class MyAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {
    private final String location;

    public MyAuthenticationEntryPoint(String location) {
        Assert.notNull(location, "location cannot be null");
        this.location = location;
    }

    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException ex) {
        ServerRedirectStrategy redirectStrategy = new DefaultServerRedirectStrategy();
        return redirectStrategy.sendRedirect(exchange, URI.create(location + "?returnPage=" + exchange.getRequest().getPath()));
    }
}