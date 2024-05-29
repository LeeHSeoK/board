package org.zerock.b01.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.b01.domain.User;
import org.zerock.b01.dto.LoginDTO;
import org.zerock.b01.dto.SignUpDTO;
import org.zerock.b01.repository.LoginRepository;


import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class LoginServiceImpl implements LoginService {

    private final ModelMapper modelMapper;
    private final LoginRepository loginRepository;

    public boolean login(LoginDTO loginDTO){
        Optional<User> result = loginRepository.findById(loginDTO.getId());
        User user = result.orElseThrow();

        if(result.isPresent() && user.getPassword().equals(loginDTO.getPassword())){
            return true;
        }
        return false;
    }

    public boolean register(SignUpDTO signUpDTO){
        Optional<User> result = loginRepository.findById(signUpDTO.getId());
        if(result.isPresent()){
            return false;
        }
        User user = User.builder()
                .id(signUpDTO.getId())
                .name(signUpDTO.getName())
                .password(signUpDTO.getPassword())
                .build();

        loginRepository.save(user);
        return true;

    }

}
