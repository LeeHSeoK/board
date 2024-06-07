package org.zerock.b01.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.b01.domain.Board;
import org.zerock.b01.domain.Reply;
import org.zerock.b01.dto.BoardListReplyCountDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardRepositroyTests {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void testInsert() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Board board = Board.builder()
                    .title("title...."+i)
                    .id("user"+(i%10))
                    .content("content..."+i)
                    .name("name"+(i%10))
                    .build();
            Board result = boardRepository.save(board);
            log.info("BNO: "+result.getBno());
        });

    }

    @Test
    public void testSelect() {
        Long bno = 10L;                         //findByID = select query문의 역할을 할듯?
        Optional<Board> result = boardRepository.findById(bno); //Optional 값이 있으면<>으로 가져오고 없으면 null일떄 오류가 발생하지 않음
//        result.ifPresent(board -> System.out.println(board));
        Board board = result.orElseThrow();
        log.info(board);
    }

    @Test
    public void testUpdate(){
        Long bno = 10L;
        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow();
        board.change("update...title","update...content","update..name");
        boardRepository.save(board);
    }

    @Test
    public void testDelete(){
        Long bno = 9L;
        boardRepository.deleteById(bno);
    }

    @Test
    public void testPaging(){
        //1 page. order by bno desc
        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());

        Page<Board> result = boardRepository.findAll(pageable);

        log.info("total count: "+result.getTotalElements());
        log.info("total pages: "+result.getTotalPages());
        log.info("page number: "+result.getNumber());
        log.info("page size: "+result.getSize());

        List<Board> list = result.getContent();
        list.forEach(board-> log.info(board));
//        Hibernate:
//        select
//        count(b1_0.bno)
//        from
//        board b1_0
    }

//    @Test
//    public void testSearch1(){
//        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());
//        boardRepository.search1(pageable);
//
//    }

    @Test
    public void testSearchAll(){
        String[] types = {"t","c","w"};
        String keyword = "3";
        Pageable pageable = PageRequest.of(1,10, Sort.by("bno").descending());
        Page<Board> result = boardRepository.searchAll(types,keyword,pageable);


        log.info(result.getTotalPages()); // total pages
        log.info(result.getSize()); // page size(한페이지)
        log.info(result.getNumber()); // page Number
        log.info(result.hasPrevious() +"__"+ result.hasNext()); // 이전페이지 유무(boolean) 다음페이지 유무(boolean)

        result.getContent().forEach(board -> log.info(board));

    }

    @Test
    public void testSearchReplyCount(){
        String[] types = {"t","c","w"};
        String keyword = "6";
        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());
        Page<BoardListReplyCountDTO> result = boardRepository.searchWithReplyCount(types, keyword, pageable);

        log.info(result.getTotalPages()); // total pages
        log.info(result.getSize()); // page size(한페이지)
        log.info(result.getNumber()); // page Number
        log.info(result.hasPrevious() +"__"+ result.hasNext()); // 이전페이지 유무(boolean) 다음페이지 유무(boolean)

        result.getContent().forEach(board -> log.info(board));
    }


}
