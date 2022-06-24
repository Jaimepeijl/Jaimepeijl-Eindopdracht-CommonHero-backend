package nl.novi.eindopdrachtcommonhero.repositories;

import nl.novi.eindopdrachtcommonhero.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findById(String username);
}
