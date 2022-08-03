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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/gebruikers")
public class UserController {


    private final UserService userService;
    private final PhotoController photoController;

    @Autowired
    public UserController(UserService userService, PhotoController photoController) {
        this.userService = userService;
        this.photoController = photoController;
    }

    @GetMapping
    @Transactional
    public List<User> getUsers(){
        List<User> users;

        users  = userService.getUsers();

        return users;
    }
    @GetMapping("/{username}")
    public ResponseEntity<UserData> getUser(@PathVariable String username){
        try {
            userService.createUserData(userService.getUser(username));
        } catch (UserNotFoundException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().body(userService.createUserData(userService.getUser(username)));
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> createUser(@RequestBody UserData userData){
        try{
            userService.createUser(userData);
        return new ResponseEntity<>("Gebruiker aangemaakt", HttpStatus.CREATED);}
        catch (BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{username}")
    public ResponseEntity<Object> updateUser(@PathVariable String username, @RequestBody UserRequest userRequest){
        try{
            userService.getUser(username);
            userService.updateUser(username, userRequest);
            return new ResponseEntity<>("Gebruiker aangepast", HttpStatus.OK);
        } catch(UserNotFoundException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/del/{username}")
    public void deleteUser(@PathVariable String username){
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

    @PostMapping(value = "/{username}/authorities/{authority}")
    public ResponseEntity<Object> addUserAuthority(@PathVariable("username") String username, @PathVariable("authority") String authority) {
        try {
            userService.addAuthority(username, authority);
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
