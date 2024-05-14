package org.zerock.b01.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //값만 주고 받을 거야 REST방식
public class SampleJSONController {
    @GetMapping("/helloJSON")
    public String[] helloJSON1234(){
        return new String[]{"AAA","BBB","CCC"};
    }
}
