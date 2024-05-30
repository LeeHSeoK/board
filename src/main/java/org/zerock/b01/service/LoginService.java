package org.zerock.b01.service;

import org.zerock.b01.dto.LoginDTO;
import org.zerock.b01.dto.SignUpDTO;

public interface LoginService {
    boolean login(LoginDTO loginDTO);
    boolean register(SignUpDTO signUpDTO);
    void modify(SignUpDTO signUpDTO);
    SignUpDTO searchOne(String id);
}
