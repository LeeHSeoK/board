package org.zerock.b01.service;

import org.springframework.data.domain.PageRequest;
import org.zerock.b01.dto.BoardDTO;
import org.zerock.b01.dto.PageRequestDTO;
import org.zerock.b01.dto.PageResopnseDTO;

public interface BoardService {
    Long register(BoardDTO boardDTO);
    BoardDTO readOne(Long bno);
    void modify(BoardDTO boardDTO);
    void remove(Long bno);
    PageResopnseDTO<BoardDTO>list(PageRequestDTO pageRequestDTO);
}
