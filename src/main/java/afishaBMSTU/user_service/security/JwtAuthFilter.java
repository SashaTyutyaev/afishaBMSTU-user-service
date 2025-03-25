package afishaBMSTU.user_service.security;

import afishaBMSTU.auth_lib.security.BaseAuthTokenFilter;
import afishaBMSTU.auth_lib.security.dto.UserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends BaseAuthTokenFilter<UserInfoDto<UUID>> {

    private final JwtService jwtService;

    @Override
    protected List<String> parseRoles(UserInfoDto<UUID> userInfo) {
        return userInfo.getRoles();
    }

    @Override
    protected UserInfoDto<UUID> retrieveUserInfo(String token) {
        return jwtService.extractUserInfo(token);
    }

}
