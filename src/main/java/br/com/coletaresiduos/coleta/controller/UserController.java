package br.com.coletaresiduos.coleta.controller;

import br.com.coletaresiduos.coleta.dto.UserDisplayDTO;
import br.com.coletaresiduos.coleta.dto.UserRegisterDTO;
import br.com.coletaresiduos.coleta.model.User;
import br.com.coletaresiduos.coleta.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDisplayDTO> getAll(){
        return userService.getAll();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDisplayDTO> getById(@PathVariable Long userId){
        return ResponseEntity.ok(userService.getById(userId));
    }

    @DeleteMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long userId){
        userService.delete(userId);
    }

    @PutMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public User update(@RequestBody User user){
        return userService.update(user);
    }

}
