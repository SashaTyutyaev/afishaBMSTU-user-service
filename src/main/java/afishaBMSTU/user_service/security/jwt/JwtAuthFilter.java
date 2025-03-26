package afishaBMSTU.user_service.security.jwt;

import afishaBMSTU.auth_lib.security.BaseAuthTokenFilter;
import afishaBMSTU.user_service.dto.JwtTokenDataDto;
import afishaBMSTU.user_service.model.user.CustomUserDetails;
import afishaBMSTU.user_service.security.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends BaseAuthTokenFilter<CustomUserDetails> {

    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected List<String> parseRoles(CustomUserDetails userInfo) {
        return userInfo.getRoles();
    }

    @Override
    protected CustomUserDetails retrieveUserInfo(String token) {
        JwtTokenDataDto jwtTokenDataDto = jwtService.extractUserInfo(token);
        return customUserDetailsService.loadUser(jwtTokenDataDto.getExternalId(), jwtTokenDataDto.getRoles());
    }

}
