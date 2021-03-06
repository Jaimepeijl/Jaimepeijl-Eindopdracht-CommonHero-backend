package nl.novi.eindopdrachtcommonhero.services;

import nl.novi.eindopdrachtcommonhero.controllers.dto.UserRequest;
import nl.novi.eindopdrachtcommonhero.controllers.dto.UserData;
import nl.novi.eindopdrachtcommonhero.exceptions.BadRequestException;
import nl.novi.eindopdrachtcommonhero.exceptions.UserNotFoundException;
import nl.novi.eindopdrachtcommonhero.models.Authority;
import nl.novi.eindopdrachtcommonhero.models.FileUploadResponse;
import nl.novi.eindopdrachtcommonhero.models.User;
import nl.novi.eindopdrachtcommonhero.repositories.FileUploadRepository;
import nl.novi.eindopdrachtcommonhero.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FileUploadRepository uploadRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(String username) {
        if (!userExists(username)){
            throw new UserNotFoundException();
        }
        return userRepository.findById(username).get();
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
        try{
            User user = new User();
            user.setId(userData.getId());
            user.setUsername(userData.getUsername());
            user.setPassword(passwordEncoder.encode(userData.getPassword()));
            user.setEmail(userData.getEmail());
            user.setName(userData.getName());
            user.setCity(userData.getCity());
            userRepository.save(user);
            addAuthority(userData.getUsername(), "ROLE_USER");
            return user.getUsername();

        } catch (Exception ex) {
            throw new BadRequestException("Kan gebruiker niet aanmaken");
        }}

    public void updateUser(String username, UserRequest newUser) {

        if (!userRepository.existsById(username)) throw new UserNotFoundException();

        User user = this.getUser(username);
        if(newUser.name != null){
                user.setName(newUser.name);}
        if(newUser.city != null){
        user.setCity(newUser.city);}
        if(newUser.email != null){
        user.setEmail(newUser.email);}

        this.userRepository.save(user);
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

    public UserData createUserData(User user) {
        return new UserData(

                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getName(),
                user.getCity(),
                user.getFile(),
                user.getAuthorities()
        );
    }

    public void assignPhotoToUser(String fileName, String username) {

        Optional<User> optionalUser = userRepository.findById(username);

        Optional<FileUploadResponse> fileUploadResponse = uploadRepository.findByFileName(fileName);

        if (optionalUser.isPresent() && fileUploadResponse.isPresent()) {

            FileUploadResponse photo = fileUploadResponse.get();

            User user = optionalUser.get();

            user.setFile(photo);

            userRepository.save(user);
        }
    }
    public void addAuthority(String username, String authority) {

        if (!userRepository.existsById(username)) throw new UserNotFoundException(username);
        User user = getUser(username);
        user.addAuthority(new Authority(username, authority));
        userRepository.save(user);
    }
    public Set<Authority> getAuthorities(String username) {
        if (!userRepository.existsById(username)) throw new UserNotFoundException(username);
        User user = getUser(username);
        return user.getAuthorities();
    }

    public void removeAuthority(String username, String authority) {
        if (!userRepository.existsById(username)) throw new UserNotFoundException(username);
        User user = userRepository.findById(username).get();
        Authority authorityToRemove = user.getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();
        user.removeAuthority(authorityToRemove);
        userRepository.save(user);
    }
}