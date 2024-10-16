package gt.core.MovieManagement.persistence.repository;

import gt.core.MovieManagement.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserCrudRepository extends JpaRepository<User, Long> {
    List<User> getByNameContaining(String name);
    Optional<User> getByUsername(String username);

    @Modifying
    @Transactional
    int deleteByUsername(String username);
}