package org.zerock.b01.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.b01.dto.BoardDTO;
import org.zerock.b01.dto.PageRequestDTO;
import org.zerock.b01.dto.PageResponseDTO;
import org.zerock.b01.service.BoardService;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardSerivce;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        PageResponseDTO<BoardDTO> resopnseDTO = boardSerivce.list(pageRequestDTO);
        log.info(resopnseDTO);
        model.addAttribute("responseDTO",resopnseDTO);
    }

    @GetMapping("/register")
    public void register(){

    }

    @PostMapping("/register")   //@Valid DTO에 값이 잘들어갔는지에 대한 결과를 BindingResult에 담는다(정보)
    public String registerPOST(@Valid BoardDTO boardDTO, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){

            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors()); //한번 비워주는역할
            return "redirect:/board/register";
        }

        Long bno = boardSerivce.register(boardDTO);
        redirectAttributes.addFlashAttribute("result",bno);
        return "redirect:/board/list";
    }
}
