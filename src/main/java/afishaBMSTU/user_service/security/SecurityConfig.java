package afishaBMSTU.user_service.security;

import afishaBMSTU.auth_lib.security.BaseAuthTokenFilter;
import afishaBMSTU.auth_lib.security.BaseSecurityConfig;
import afishaBMSTU.auth_lib.security.dto.UserInfoDto;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import java.util.UUID;

@Configuration
@EnableMethodSecurity
public class SecurityConfig extends BaseSecurityConfig {

    public SecurityConfig(BaseAuthTokenFilter<UserInfoDto<UUID>> authTokenFilter) {
        super(authTokenFilter);
    }
}
