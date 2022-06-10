package nl.novi.eindopdrachtcommonhero.repositories;

import nl.novi.eindopdrachtcommonhero.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
