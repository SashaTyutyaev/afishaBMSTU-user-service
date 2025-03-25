package afishaBMSTU.user_service.service.user.managing;

import afishaBMSTU.user_service.exceptions.IncorrectParameterException;
import afishaBMSTU.user_service.exceptions.NotFoundException;
import afishaBMSTU.user_service.model.phone.Phone;
import afishaBMSTU.user_service.model.user.User;
import afishaBMSTU.user_service.repository.PhoneRepository;
import afishaBMSTU.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("phoneService")
@RequiredArgsConstructor
@Slf4j
public class PhoneService implements UserManagingService {

    private final PhoneRepository phoneRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void addNewItem(Long userId, String item) {
        isPhoneAvailable(item);

        User user = getUserById(userId);
        Phone phone = Phone.builder()
                .user(user)
                .phone(item)
                .build();

        user.getPhones().add(phone);
        userRepository.save(user);

        log.info("Successfully added new number to user {}", userId);
    }

    @Override
    @Transactional
    public void deleteItem(Long itemId, Long userId) {
        User user = getUserById(userId);
        Phone phone = getPhoneById(itemId);

        user.getPhones().remove(phone);
        userRepository.save(user);

        log.info("Successfully deleted number of user {}", userId);
    }

    @Override
    @Transactional
    public void updateItem(Long itemId, Long userId, String item) {
        isPhoneAvailable(item);

        User user = getUserById(userId);
        Phone existedPhone = getPhoneById(itemId);

        existedPhone.setPhone(item);
        user.getPhones().add(existedPhone);
        userRepository.save(user);

        log.info("Successfully updated number for user {}", userId);
    }

    private void isPhoneAvailable(String phoneNumber) {
        if (phoneRepository.findUserIdByPhone(phoneNumber).isPresent()) {
            throw new IncorrectParameterException("Phone is already using");
        }
    }

    private Phone getPhoneById(Long id) {
        return phoneRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Phone not found"));
    }

    private User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }
}