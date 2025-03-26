package afishaBMSTU.user_service.service.contactInfo;

import afishaBMSTU.user_service.dto.UserContactInfoUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MainUserContactInfoService {

    @Qualifier("emailService")
    private final UserContactInfoService emailService;

    @Qualifier("phoneService")
    private final UserContactInfoService phoneService;

    public void addNewItem(Long userId, UserContactInfoUpdateDto updateDto) {
        String email = updateDto.getEmail();
        String phone = updateDto.getPhone();

        if (email != null) {
            emailService.addNewItem(userId, email);
        }
        if (phone != null) {
            phoneService.addNewItem(userId, phone);
        }
    }

    public void deleteItem(Long itemId, Long userId, String type) {
        switch (type) {
            case "phone":
                phoneService.deleteItem(itemId, userId);
                return;
            case "email":
                emailService.deleteItem(itemId, userId);
        }
    }

    public void updateItem(Long itemId, Long userId, UserContactInfoUpdateDto updateDto) {
        String email = updateDto.getEmail();
        String phone = updateDto.getPhone();

        if (email != null) {
            emailService.updateItem(itemId, userId, email);
        }
        if (phone != null) {
            phoneService.updateItem(itemId, userId, phone);
        }
    }
}