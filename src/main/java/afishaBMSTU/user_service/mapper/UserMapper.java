package afishaBMSTU.user_service.mapper;

import afishaBMSTU.user_service.dto.UserCreationRequestDto;
import afishaBMSTU.user_service.dto.UserFullDto;
import afishaBMSTU.user_service.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "emails", ignore = true)
    @Mapping(target = "phones", ignore = true)
    UserFullDto toUserFullDto(User user);

    User toUser(UserCreationRequestDto userCreationRequestDto);

}
