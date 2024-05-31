package org.zerock.b01.repository.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.b01.domain.User;
import org.zerock.b01.dto.SignUpDTO;
import org.zerock.b01.service.LoginService;

import java.util.stream.LongStream;

@SpringBootTest
@Log4j2
public class LoginServiceTests {

    @Autowired
    private LoginService loginService;

    @Test
    public void signup() {
        LongStream.rangeClosed(1,10L).forEach(i->{
          SignUpDTO signUpDTO = SignUpDTO.builder()
                .id("user"+i)
                .name("name"+i)
                .password("12341234")
                .build();
        loginService.register(signUpDTO);
        });
    }
}
