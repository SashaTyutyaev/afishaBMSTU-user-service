package afishaBMSTU.user_service.repository;

import afishaBMSTU.user_service.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u " +
            "where u.id in ?1")
    Page<User> findAllByIdsPageable(List<Long> ids, Pageable pageable);

    Optional<User> findByExternalId(UUID externalId);
}