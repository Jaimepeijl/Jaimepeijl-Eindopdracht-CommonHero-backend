package nl.novi.eindopdrachtcommonhero.services;

import nl.novi.eindopdrachtcommonhero.controllers.dto.UserRequest;
import nl.novi.eindopdrachtcommonhero.dtos.UserData;
import nl.novi.eindopdrachtcommonhero.exceptions.RecordNotFoundException;
import nl.novi.eindopdrachtcommonhero.exceptions.UserNotFoundException;
import nl.novi.eindopdrachtcommonhero.models.User;
import nl.novi.eindopdrachtcommonhero.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUser(Long id) {
        return this.userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }


    public boolean userExists(Long id) {
        return userRepository.existsById(id);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)){
            throw new UserNotFoundException();
        }
        userRepository.deleteById(id);
    }

    public UserData createUser(User user) {

        userRepository.save(user);

        return this.createUserDto(user);

    }

    public UserData updateUser(Long id, UserRequest newUser) {
        if (!userRepository.existsById(id)) throw new RecordNotFoundException();

        User user = this.getUser(id);

        user.setPassword(newUser.password);
        user.setUsername(newUser.username);
        user.setName(newUser.name);
        user.setApikey(newUser.apikey);
        user.setCity(newUser.city);
        user.setEmail(newUser.email);
        user.setEnabled(newUser.enabled);

        this.userRepository.save(user);
        return this.createUserDto(user);
    }



    public User toUser(UserData userData) {

        var user = new User();

        user.setUsername(userData.getUsername());
        user.setPassword(userData.getPassword());
        user.setEnabled(userData.getEnabled());
        user.setApikey(userData.getApikey());
        user.setEmail(userData.getEmail());
        user.setName(userData.getName());
        user.setCity(userData.getCity());

        return user;
    }

    public UserData createUserDto(User user){
        return new UserData(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                user.getApikey(),
                user.getEmail(),
                user.getName(),
                user.getCity()
        );
    }
}
