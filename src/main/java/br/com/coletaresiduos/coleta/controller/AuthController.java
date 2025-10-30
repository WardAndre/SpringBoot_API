package br.com.coletaresiduos.coleta.controller;

import br.com.coletaresiduos.coleta.dto.LoginDTO;
import br.com.coletaresiduos.coleta.dto.TokenDTO;
import br.com.coletaresiduos.coleta.dto.UserDisplayDTO;
import br.com.coletaresiduos.coleta.dto.UserRegisterDTO;
import br.com.coletaresiduos.coleta.model.User;
import br.com.coletaresiduos.coleta.service.TokenService;
import br.com.coletaresiduos.coleta.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(
            @RequestBody
            @Valid
            LoginDTO loginDto
    ){
        UsernamePasswordAuthenticationToken usernamePassword =
                new UsernamePasswordAuthenticationToken(
                        loginDto.email(),
                        loginDto.password());

        Authentication auth = authenticationManager.authenticate(usernamePassword);

        String token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new TokenDTO(token));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDisplayDTO register(@RequestBody @Valid UserRegisterDTO userRegisterDTO){

        UserDisplayDTO savedUser = null;
        savedUser = userService.saveUser(userRegisterDTO);

        return savedUser;

    }

}
