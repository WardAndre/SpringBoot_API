package br.com.coletaresiduos.coleta.controller;

import br.com.coletaresiduos.coleta.dto.UserDisplayDTO;
import br.com.coletaresiduos.coleta.dto.UserRegisterDTO;
import br.com.coletaresiduos.coleta.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDisplayDTO saveUser(@RequestBody @Valid UserRegisterDTO userRegisterDTO) {
        return userService.saveUser(userRegisterDTO);
    }

}
