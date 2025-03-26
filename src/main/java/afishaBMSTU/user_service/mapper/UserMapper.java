package afishaBMSTU.user_service.mapper;

import afishaBMSTU.user_service.dto.UserCreationRequestDto;
import afishaBMSTU.user_service.dto.UserFullDto;
import afishaBMSTU.user_service.model.email.Email;
import afishaBMSTU.user_service.model.phone.Phone;
import afishaBMSTU.user_service.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "emails", source = "emails", qualifiedByName = "mapEmails")
    @Mapping(target = "phones", source = "phones", qualifiedByName = "mapPhones")
    UserFullDto toUserFullDto(User user);

    User toUser(UserCreationRequestDto userCreationRequestDto);

    @Named("mapEmails")
    default Set<String> mapEmails(Set<Email> emails) {
        if (emails == null) {
            return null;
        }
        return emails.stream()
                .map(Email::getEmail)
                .collect(Collectors.toSet());
    }

    @Named("mapPhones")
    default Set<String> mapPhones(Set<Phone> phones) {
        if (phones == null) {
            return null;
        }
        return phones.stream()
                .map(Phone::getPhone)
                .collect(Collectors.toSet());
    }
}
