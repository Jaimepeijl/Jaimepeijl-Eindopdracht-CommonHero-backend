package nl.novi.eindopdrachtcommonhero.controllers;

import nl.novi.eindopdrachtcommonhero.exceptions.BadRequestException;
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
        try {
            AuthenticationResponse authenticationResponse = userAuthService.authenticateUser(authenticationRequest);
            return ResponseEntity.ok(authenticationResponse);
        } catch (Exception ex){
            throw new BadRequestException("Verkeerde gebruikersnaam of wachtwoord");
        }
    }
}


