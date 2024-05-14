package org.zerock.b01.repository.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.b01.dto.BoardDTO;
import org.zerock.b01.service.BoardService;

@Log4j2
@SpringBootTest
public class BoardServiceTests {

    @Autowired
    private BoardService boardService;

    @Test
    public void testRegister(){

        BoardDTO boardDTO = BoardDTO.builder()
                .title("boardDTO test...")
                .content("boardDTO test...")
                .writer("user00")
                .build();
        Long bno = boardService.register(boardDTO);
        log.info("bno: "+bno);
    }
}
