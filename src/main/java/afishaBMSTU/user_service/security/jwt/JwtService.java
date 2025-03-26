package afishaBMSTU.user_service.security.jwt;

import afishaBMSTU.auth_lib.security.BaseJwtService;
import afishaBMSTU.user_service.dto.JwtTokenDataDto;
import org.springframework.stereotype.Service;

@Service
public class JwtService extends BaseJwtService<JwtTokenDataDto> {

    @Override
    protected Class<JwtTokenDataDto> getDataType() {
        return JwtTokenDataDto.class;
    }

}
