package afishaBMSTU.user_service.security;

import afishaBMSTU.auth_lib.security.BaseAuthTokenFilter;
import afishaBMSTU.auth_lib.security.BaseSecurityConfig;
import afishaBMSTU.auth_lib.security.internal.InternalTokenFilter;
import afishaBMSTU.auth_lib.security.internal.InternalTokenService;
import afishaBMSTU.user_service.model.user.CustomUserDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig extends BaseSecurityConfig {

    public SecurityConfig(BaseAuthTokenFilter<CustomUserDetails> authTokenFilter) {
        super(authTokenFilter);
    }

    @Bean
    public InternalTokenService internalTokenService() {
        return new InternalTokenService();
    }

    @Bean
    public InternalTokenFilter internalTokenFilter() {
        return new InternalTokenFilter(internalTokenService());
    }

    @Override
    protected void configureHttpSecurity(HttpSecurity http) throws Exception {
        http.addFilterBefore(internalTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/actuator/**").permitAll()
                );
    }
}
