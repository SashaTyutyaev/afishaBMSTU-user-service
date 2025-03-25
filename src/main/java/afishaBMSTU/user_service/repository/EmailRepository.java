package afishaBMSTU.user_service.repository;

import afishaBMSTU.user_service.model.email.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {

    @Query("select e.user.id from Email e where e.email like :email")
    Optional<Long> findUserIdByEmail(@Param("email") String email);
}
