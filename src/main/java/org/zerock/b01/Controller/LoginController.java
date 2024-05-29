package org.zerock.b01.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.zerock.b01.dto.LoginDTO;
import org.zerock.b01.dto.SignUpDTO;
import org.zerock.b01.service.LoginService;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;


    @GetMapping("/user/login")
    public void login() {

    }

    @PostMapping("/user/login")
    public String logincheck(LoginDTO loginDTO){
        if(loginService.login(loginDTO)){
            return "redirect:/board/list";
        }
        return "/user/login";

    }

    @GetMapping("/user/signup")
    public void signup() {

    }

    @PostMapping("/user/signup")
    public String signupcheck(SignUpDTO signUpDTO){

//        System.out.println(signUpDTO.toString());
        if(loginService.register(signUpDTO)){
            return "/user/login";
        }
        else{
            return "/user/signup";
        }
    }
}
