package nl.novi.eindopdrachtcommonhero.services;

import nl.novi.eindopdrachtcommonhero.controllers.dto.UserRequest;
import nl.novi.eindopdrachtcommonhero.controllers.dto.UserData;
import nl.novi.eindopdrachtcommonhero.exceptions.RecordNotFoundException;
import nl.novi.eindopdrachtcommonhero.exceptions.UserNotFoundException;
import nl.novi.eindopdrachtcommonhero.models.Authority;
import nl.novi.eindopdrachtcommonhero.models.FileUploadResponse;
import nl.novi.eindopdrachtcommonhero.models.User;
import nl.novi.eindopdrachtcommonhero.repositories.FileUploadRepository;
import nl.novi.eindopdrachtcommonhero.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final FileUploadRepository uploadRepository;

    @Autowired
    public UserService(UserRepository userRepository, FileUploadRepository uploadRepository) {
        this.userRepository = userRepository;
        this.uploadRepository = uploadRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(String username) {
        return this.userRepository.findById(username)
                .orElseThrow(UserNotFoundException::new);
    }


    public boolean userExists(String username) {
        return userRepository.existsById(username);
    }

    public void deleteUser(String username) {
        if (!userRepository.existsById(username)) {
            throw new UserNotFoundException();
        }
        userRepository.deleteById(username);
    }

    public String createUser(UserData userData) {

        User newUser = userRepository.save(toUser(userData));

        return newUser.getUsername();

    }

    public UserData updateUser(String username, UserRequest newUser) {
        if (!userRepository.existsById(username)) throw new RecordNotFoundException();

        User user = this.getUser(username);

        user.setPassword(newUser.password);
        user.setUsername(newUser.username);
        user.setName(newUser.name);
        user.setCity(newUser.city);
        user.setEmail(newUser.email);

        this.userRepository.save(user);
        return this.createUserDto(user);
    }

    public User toUser(UserData userData) {

        var user = new User();

        user.setUsername(userData.getUsername());
        user.setPassword(userData.getPassword());
        user.setEmail(userData.getEmail());
        user.setName(userData.getName());
        user.setCity(userData.getCity());

        return user;
    }

    public UserData createUserDto(User user) {
        return new UserData(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getName(),
                user.getCity()
        );
    }

    public void assignPhotoToUser(String name, String username) {

        Optional<User> optionalUser = userRepository.findById(username);

        Optional<FileUploadResponse> fileUploadResponse = uploadRepository.findByFileName(name);

        if (optionalUser.isPresent() && fileUploadResponse.isPresent()) {

            FileUploadResponse photo = fileUploadResponse.get();

            User user = optionalUser.get();

            user.setFile(photo);

            userRepository.save(user);
        }
    }
    public void addAuthority(String username, String authority) {

        if (!userRepository.existsById(username)) throw new UserNotFoundException();
        User user = userRepository.findById(username).get();
        user.addAuthority(new Authority(username, authority));
        userRepository.save(user);
    }
    public Set<Authority> getAuthorities(String username) {
        if (!userRepository.existsById(username)) throw new UserNotFoundException();
        User user = userRepository.findById(username).get();
        UserData userData = createUserDto(user);
        return userData.getAuthorities();
    }

    public void removeAuthority(String username, String authority) {
        if (!userRepository.existsById(username)) throw new UserNotFoundException();
        User user = userRepository.findById(username).get();
        Authority authorityToRemove = user.getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();
        user.removeAuthority(authorityToRemove);
        userRepository.save(user);
    }
}