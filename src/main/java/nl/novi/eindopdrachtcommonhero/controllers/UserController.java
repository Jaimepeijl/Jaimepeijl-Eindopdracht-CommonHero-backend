package nl.novi.eindopdrachtcommonhero.controllers;

import nl.novi.eindopdrachtcommonhero.controllers.dto.UserRequest;
import nl.novi.eindopdrachtcommonhero.controllers.dto.UserData;
import nl.novi.eindopdrachtcommonhero.exceptions.BadRequestException;
import nl.novi.eindopdrachtcommonhero.exceptions.UserNotFoundException;
import nl.novi.eindopdrachtcommonhero.models.FileUploadResponse;
import nl.novi.eindopdrachtcommonhero.models.User;
import nl.novi.eindopdrachtcommonhero.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/gebruikers")
public class UserController {

    private final UserService userService;
    private final PhotoController photoController;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PhotoController photoController, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.photoController = photoController;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    @Transactional
    public List<User> getUsers(){
        List<User> users;

        users  = userService.getUsers();

        return users;
    }
    @GetMapping("/{username}")
    private void getUser(@PathVariable String username){
        try {
            this.userService.getUser(username);
        } catch (UserNotFoundException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @PostMapping("/signup")
    private User registerUser(@RequestBody UserData userData){

        User user = new User();
        user.setUsername(userData.getUsername());
//        user.setPassword(passwordEncoder.encode(userData.getPassword()));
        user.setPassword(userData.getPassword());
        user.setEmail(userData.getEmail());
        user.setName(userData.getName());
        user.setCity(userData.getCity());

        userService.addAuthority(user.getUsername(), "ROLE_USER");

//        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}")
//                .buildAndExpand(user.getUsername()).toUri();

        return userService.createUser(userData);
    }

    @PutMapping()
    private UserData updateUser(@RequestBody UserRequest userRequest){
        try{
            return userService.updateUser(userRequest.username, userRequest);
        } catch(UserNotFoundException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/gebruikers/del/{username}")
    private void deleteUser(@PathVariable String username){
        try{
            this.userService.deleteUser(username);
        } catch (UserNotFoundException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{username}/photo")
    public void assignPhotoToUser(@PathVariable("username") String username, @RequestBody MultipartFile file){
        FileUploadResponse photo = photoController.singleFileUpload(file);

        userService.assignPhotoToUser(photo.getFileName(), username);
    }

    @GetMapping(value = "/{username}/authorities")
    public ResponseEntity<Object> getUserAuthorities(@PathVariable("username") String username) {
        return ResponseEntity.ok().body(userService.getAuthorities(username));
    }

    @PostMapping(value = "/{username}/authorities")
    public ResponseEntity<Object> addUserAuthority(@PathVariable("username") String username, @RequestBody Map<String, Object> fields) {
        try {
            String authorityName = (String) fields.get("authority");
            userService.addAuthority(username, authorityName);
            return ResponseEntity.noContent().build();
        }
        catch (Exception ex) {
            throw new BadRequestException();
        }
    }

    @DeleteMapping(value = "/{username}/authorities/{authority}")
    public ResponseEntity<Object> deleteUserAuthority(@PathVariable("username") String username, @PathVariable("authority") String authority) {
        userService.removeAuthority(username, authority);
        return ResponseEntity.noContent().build();
    }
}
