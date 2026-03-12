package zxf.springboot.gatewayservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationEntryPoint;
import zxf.springboot.gatewayservice.security.MyAuthenticationEntryPoint;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        // Disable form login, HTTP basic, logout, and CSRF
        http.formLogin(spec -> spec.disable())
                .httpBasic(spec -> spec.disable())
                .logout(spec -> spec.disable())
                .csrf(spec -> spec.disable());

        // Configure authorization
        http.authorizeExchange(spec -> {
            spec.pathMatchers(securityProperties.getAuthorize().getPermitAll()).permitAll();
            securityProperties.getAuthorize().getHasAnyAuthority().forEach(settings ->
                    spec.pathMatchers(settings[0])
                            .hasAnyAuthority(Arrays.copyOfRange(settings, 1, settings.length))
            );
            spec.anyExchange().authenticated();
        });

        // Configure exception handling
        http.exceptionHandling(spec ->
                spec.authenticationEntryPoint(authenticationEntryPoint())
        );

        return http.build();
    }

    @Bean
    public ServerAuthenticationEntryPoint authenticationEntryPoint() {
        return new MyAuthenticationEntryPoint(securityProperties.getAuthenticationEntryPoint());
    }

    @Configuration
    @ConfigurationProperties(prefix = "security")
    public static class SecurityProperties {
        private String authenticationEntryPoint;
        private Authorize authorize;

        public String getAuthenticationEntryPoint() {
            return authenticationEntryPoint;
        }

        public void setAuthenticationEntryPoint(String authenticationEntryPoint) {
            this.authenticationEntryPoint = authenticationEntryPoint;
        }

        public Authorize getAuthorize() {
            return authorize;
        }

        public void setAuthorize(Authorize authorize) {
            this.authorize = authorize;
        }


        public static class Authorize {
            private String[] permitAll;
            private List<String[]> hasAnyAuthority;

            public String[] getPermitAll() {
                return permitAll;
            }

            public void setPermitAll(String[] permitAll) {
                this.permitAll = permitAll;
            }

            public List<String[]> getHasAnyAuthority() {
                return hasAnyAuthority;
            }

            public void setHasAnyAuthority(List<String[]> hasAnyAuthority) {
                this.hasAnyAuthority = hasAnyAuthority;
            }
        }
    }
}
