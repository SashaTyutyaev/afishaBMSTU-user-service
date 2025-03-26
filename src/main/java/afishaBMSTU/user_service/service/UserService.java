package afishaBMSTU.user_service.service;


import afishaBMSTU.user_service.dto.UserCreationRequestDto;
import afishaBMSTU.user_service.dto.UserFullDto;
import afishaBMSTU.user_service.dto.UserUpdateDto;

import java.util.UUID;

public interface UserService {
    UserFullDto getUser(Long userId);
    UserFullDto updateUser(UserUpdateDto userUpdateDto, Long userId);
    void deleteUser(Long id, UUID externalId);
    void createUserAfterSignUp(UserCreationRequestDto userCreationRequestDto);
}
