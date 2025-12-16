package bg.softuni.planner.repository;

import bg.softuni.planner.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsernameOrEmail(String username, String email);

    User findByUsername(String username);

    Optional<User> findUserByUsername(String username);

    Optional<User> findById(Long id);
}
