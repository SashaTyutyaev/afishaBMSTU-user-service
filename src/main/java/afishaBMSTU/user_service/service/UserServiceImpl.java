package afishaBMSTU.user_service.service;

import afishaBMSTU.user_service.dto.EmailDto;
import afishaBMSTU.user_service.dto.PhoneDto;
import afishaBMSTU.user_service.dto.UserCreationRequestDto;
import afishaBMSTU.user_service.dto.UserFullDto;
import afishaBMSTU.user_service.dto.UserUpdateDto;
import afishaBMSTU.user_service.exceptions.NotFoundException;
import afishaBMSTU.user_service.kafka.KafkaSender;
import afishaBMSTU.user_service.mapper.EmailMapper;
import afishaBMSTU.user_service.mapper.PhoneMapper;
import afishaBMSTU.user_service.mapper.UserMapper;
import afishaBMSTU.user_service.model.user.User;
import afishaBMSTU.user_service.repository.UserRepository;
import afishaBMSTU.user_service.security.SecurityContextService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final EmailMapper emailMapper;
    private final PhoneMapper phoneMapper;
    private final UserRepository userRepository;
    private final SecurityContextService securityContextService;
    private final KafkaSender kafkaSender;

    @Override
    @Transactional(readOnly = true)
    public UserFullDto getUser(Long id) {
        User user = getUserById(id);
        UserFullDto userFullDto = userMapper.toUserFullDto(user);

        setContactInfoToUser(user, userFullDto);

        return userFullDto;
    }

    @Override
    @Transactional
    public UserFullDto updateUser(UserUpdateDto userUpdateDto, Long userId) {
        User user = getUserById(userId);

        String groupName = userUpdateDto.getGroupName();
        String name = userUpdateDto.getName();
        String surname = userUpdateDto.getSurname();

        if (groupName != null) {
            user.setGroupName(groupName);
        } else if (name != null) {
            user.setName(name);
        } else if (surname != null) {
            user.setSurname(surname);
        }

        userRepository.save(user);
        log.info("Successfully updated user {}", user.getId());
        return userMapper.toUserFullDto(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id, UUID externalId) {
        userRepository.deleteById(id);
        securityContextService.clearContextForUser(id);
        kafkaSender.send(externalId);
        log.info("User deleted: {}", id);
    }

    @Override
    @Transactional
    public void createUserAfterSignUp(UserCreationRequestDto userCreationRequestDto) {
        User user = userMapper.toUser(userCreationRequestDto);
        userRepository.save(user);
        log.info("Successfully saved user after signUp");
    }

    private void setContactInfoToUser(User user, UserFullDto userFullDto) {
        Set<EmailDto> emailDtos = user.getEmails().stream()
                .map(emailMapper::toEmailDto)
                .collect(Collectors.toSet());

        Set<PhoneDto> phoneDtos = user.getPhones().stream()
                .map(phoneMapper::toPhoneDto)
                .collect(Collectors.toSet());

        userFullDto.setEmails(emailDtos);
        userFullDto.setPhones(phoneDtos);
    }

    private User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> {
            log.error("User with id {} not found", id);
            return new NotFoundException("The user with id " + id + " not found");
        });
    }
}
