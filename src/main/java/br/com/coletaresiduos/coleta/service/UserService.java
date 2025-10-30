package br.com.coletaresiduos.coleta.service;

import br.com.coletaresiduos.coleta.dto.UserDisplayDTO;
import br.com.coletaresiduos.coleta.dto.UserRegisterDTO;
import br.com.coletaresiduos.coleta.model.User;
import br.com.coletaresiduos.coleta.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDisplayDTO saveUser(UserRegisterDTO userRegisterDTO) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(userRegisterDTO.password());
        User user = new User();
        BeanUtils.copyProperties(userRegisterDTO, user);
        user.setPassword(encryptedPassword);
        User savedUser = userRepository.save(user);

        return new UserDisplayDTO(savedUser);
    }

}
