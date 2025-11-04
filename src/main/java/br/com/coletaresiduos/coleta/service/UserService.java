package br.com.coletaresiduos.coleta.service;

import br.com.coletaresiduos.coleta.dto.UserDisplayDTO;
import br.com.coletaresiduos.coleta.dto.UserRegisterDTO;
import br.com.coletaresiduos.coleta.exception.UserNotFoundException;
import br.com.coletaresiduos.coleta.model.User;
import br.com.coletaresiduos.coleta.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public UserDisplayDTO getById(Long id){
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()){
            return new UserDisplayDTO(userOptional.get());
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    public List<UserDisplayDTO> getAll(){
        return userRepository
                .findAll()
                .stream()
                .map(UserDisplayDTO::new)
                .toList();
    }

    public void delete(Long id){
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()){
            userRepository.delete(userOptional.get());
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    public User update(User user){
        Optional<User> userOptional = userRepository.findById(user.getUserId());

        if (userOptional.isPresent()){
            return userRepository.save(user);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

}
