package org.zerock.b01.repository.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.b01.dto.BoardDTO;
import org.zerock.b01.dto.PageRequestDTO;
import org.zerock.b01.dto.PageResponseDTO;
import org.zerock.b01.service.BoardService;

import java.util.Arrays;
import java.util.UUID;

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
                .name("user00")
                .build();
        Long bno = boardService.register(boardDTO);
        log.info("bno: "+bno);
    }

    @Test
    public void testModify(){
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(99L)
                .title("update test...")
                .content("update test...")
                .build();
        boardService.modify(boardDTO);
        log.info(boardDTO.toString());
    }

    @Test
    public void testList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .type("tcw")
                .keyword("3")
                .page(1)
                .size(10)
                .build();
        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);
        log.info(responseDTO);
    }

    @Test
    public void testRegisterWithImages(){
        log.info(boardService.getClass().getName());

        BoardDTO boardDTO = BoardDTO.builder()
                .title("file test...")
                .content("file sample test...")
                .name("user1")
                .id("user1")
                .build();
        boardDTO.setFileNames(
                Arrays.asList(
                        UUID.randomUUID()+"_aaa.jpg",
                        UUID.randomUUID()+"_bbb.jpg",
                        UUID.randomUUID()+"_ccc.jpg"
                )
        );
        Long bno = boardService.register(boardDTO);
        log.info("bno: "+bno);
    }

    @Test
    public void testReadOne(){
        Long bno = 104L;

        BoardDTO boardDTO = boardService.readOne(bno);
        log.info(boardDTO);
        for(String fileName : boardDTO.getFileNames()){
            log.info(fileName);
        }
    }
}
