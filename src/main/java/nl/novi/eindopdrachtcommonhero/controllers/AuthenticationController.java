package nl.novi.eindopdrachtcommonhero.controllers;

import nl.novi.eindopdrachtcommonhero.payload.AuthenticationRequest;
import nl.novi.eindopdrachtcommonhero.payload.AuthenticationResponse;
import nl.novi.eindopdrachtcommonhero.services.UserAuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/authenticate")
public class AuthenticationController {

    @Autowired
    UserAuthService userAuthService;

    @PostMapping()
    public ResponseEntity<Object> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest){
        AuthenticationResponse authenticationResponse = userAuthService.authenticateUser(authenticationRequest);
        return ResponseEntity.ok(authenticationResponse);
    }
}


