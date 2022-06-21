package nl.novi.eindopdrachtcommonhero.controllers;

import nl.novi.eindopdrachtcommonhero.controllers.dto.UserRequest;
import nl.novi.eindopdrachtcommonhero.dtos.UserData;
import nl.novi.eindopdrachtcommonhero.exceptions.UserNotFoundException;
import nl.novi.eindopdrachtcommonhero.models.FileUploadResponse;
import nl.novi.eindopdrachtcommonhero.models.User;
import nl.novi.eindopdrachtcommonhero.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/users")
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

    @PostMapping
    private UserData createUser(@RequestBody UserRequest userRequest){

        User user = new User(userRequest.username, userRequest.password, userRequest.enabled,
                userRequest.apikey, userRequest.email, userRequest.name, userRequest.city);
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    private void getUser(@PathVariable Long id){
        try {
            this.userService.getUser(id);
        } catch (UserNotFoundException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping()
    private UserData updateUser(@RequestBody UserRequest userRequest){
        try{
            return userService.updateUser(userRequest.id, userRequest);
        } catch(UserNotFoundException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    private void deleteUser(@PathVariable Long id){
        try{
            this.userService.deleteUser(id);
        } catch (UserNotFoundException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{id}/photo")
    public void assignPhotoToUser(@PathVariable("id") Long id, @RequestBody MultipartFile file){
        FileUploadResponse photo = photoController.singleFileUpload(file);

        userService.assignPhotoToUser(photo.getFileName(), id);
    }
}
