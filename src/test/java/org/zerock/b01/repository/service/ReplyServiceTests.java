package org.zerock.b01.repository.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.b01.dto.ReplyDTO;
import org.zerock.b01.service.ReplyService;

import java.util.stream.LongStream;

@SpringBootTest
@Log4j2
public class ReplyServiceTests {

    @Autowired
    private ReplyService replyService;

    @Test
    public void testRegister(){
        ReplyDTO replyDTO = ReplyDTO.builder()
                .replyText("replyreply....")
                .name("re01")
                .bno(67L)
                .build();
        log.info(replyService.register(replyDTO)+"입니다.");
    }

    @Test
    public void 댓글왕창넣기() {
        LongStream.rangeClosed(1,100L).forEach(i->{
            ReplyDTO replyDTO = ReplyDTO.builder()
                    .bno(99L)
                    .replyText("replyreply....."+i)
                    .name("name"+i%10)
                    .id("user"+i%10)
                    .build();
            Long rno = replyService.register(replyDTO);
            log.info(rno);
        });
    }

}
