package afishaBMSTU.user_service.service.contactInfo;

import afishaBMSTU.user_service.exceptions.IncorrectParameterException;
import afishaBMSTU.user_service.exceptions.NotFoundException;
import afishaBMSTU.user_service.model.email.Email;
import afishaBMSTU.user_service.model.user.User;
import afishaBMSTU.user_service.repository.EmailRepository;
import afishaBMSTU.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("emailService")
@RequiredArgsConstructor
@Slf4j
public class EmailService implements UserContactInfoService {

    private final EmailRepository emailRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void addNewItem(Long userId, String item) {
        isEmailAvailable(item);

        User user = getUserById(userId);
        Email email = Email.builder()
                .user(user)
                .email(item)
                .build();

        user.getEmails().add(email);
        userRepository.save(user);

        log.info("Successfully added new Email to user {}", userId);
    }

    @Override
    @Transactional
    public void deleteItem(Long itemId, Long userId) {
        User user = getUserById(userId);
        Email email = getEmailById(itemId);

        user.getEmails().remove(email);
        userRepository.save(user);

        log.info("Successfully deleted Email of user {}", userId);
    }

    @Override
    @Transactional
    public void updateItem(Long itemId, Long userId, String item) {
        isEmailAvailable(item);

        User user = getUserById(userId);
        Email existedEmail = getEmailById(itemId);

        existedEmail.setEmail(item);
        user.getEmails().add(existedEmail);
        userRepository.save(user);

        log.info("Successfully updated Email for user {}", userId);
    }

    private void isEmailAvailable(String email) {
        if (emailRepository.findUserIdByEmail(email).isPresent()) {
            throw new IncorrectParameterException("Email is already using");
        }
    }

    private Email getEmailById(Long id) {
        return emailRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Email not found"));
    }

    private User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }
}
