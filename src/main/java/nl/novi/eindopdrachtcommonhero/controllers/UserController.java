package nl.novi.eindopdrachtcommonhero.controllers;

import nl.novi.eindopdrachtcommonhero.controllers.dto.UserRequest;
import nl.novi.eindopdrachtcommonhero.dtos.UserData;
import nl.novi.eindopdrachtcommonhero.exceptions.UserNotFoundException;
import nl.novi.eindopdrachtcommonhero.models.User;
import nl.novi.eindopdrachtcommonhero.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    private UserData createUser(@RequestBody UserRequest userRequest){

        User user = new User(userRequest.username, userRequest.password, userRequest.enabled,
                userRequest.apikey, userRequest.email, userRequest.name, userRequest.city);
        return this.userService.createUser(user);

    }

    @GetMapping("/{id}")
    private void getUser(@PathVariable Long id){
        userService.getUser(id);
    }

    @PutMapping()
    private UserData updateUser(@RequestBody UserRequest userRequest){

        return userService.updateUser(userRequest.id, userRequest);
    }

    @DeleteMapping("/{id}")
    private void deleteUser(@PathVariable Long id){
        try{
            this.userService.deleteUser(id);
        } catch (UserNotFoundException e){
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT);
        }

    }

}
