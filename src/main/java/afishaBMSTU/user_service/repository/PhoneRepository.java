package afishaBMSTU.user_service.repository;

import afishaBMSTU.user_service.model.phone.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

    @Query("select p.user.id from Phone p where p.phone like :number")
    Optional<Long> findUserIdByPhone(@Param("number") String phoneNumber);
}
