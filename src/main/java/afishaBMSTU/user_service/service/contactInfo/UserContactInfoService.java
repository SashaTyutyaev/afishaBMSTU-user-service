package afishaBMSTU.user_service.service.contactInfo;

public interface UserContactInfoService {
    void addNewItem(Long userId, String item);
    void deleteItem(Long itemId, Long userId);
    void updateItem(Long itemId, Long userId, String item);
}