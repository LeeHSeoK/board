package org.zerock.b01.Controller;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.b01.dto.ReplyDTO;

import java.util.HashMap;
import java.util.Map;

//들어온 JSON값이 @RequsetMapping을 통해서 해당경로로 들어오는데 사용하기위해선 replyDTO모양으로 만들어져야하고
//해당 내용을 변환해 주는게 @RequestBody이다.
@RestController
@RequestMapping("/replies")
@Log4j2
public class ReplyController {
//consumes = MediaType.APPLICATION_JSON_VALUE JSON형식을 받겠다.
    @PostMapping(value="/",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Long> register(@Valid @RequestBody ReplyDTO replyDTO,
                                                        BindingResult bindingResult) throws BindException {
        log.info(replyDTO.toString());
        if(bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("rno",10L);

        return resultMap;
    }
}
