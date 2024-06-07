package org.zerock.b01.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.b01.dto.*;
import org.zerock.b01.service.BoardService;
import org.zerock.b01.service.LoginService;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final LoginService loginService;

    @GetMapping("list")
    public void list(PageRequestDTO pageRequestDTO, Model model, HttpSession session) {
        PageResponseDTO<BoardListReplyCountDTO> responseDTO = boardService.listWithReplyCount(pageRequestDTO);
        model.addAttribute("responseDTO", responseDTO);


    }

    @GetMapping("/register")
    public void registerGET(HttpSession session, Model model){
        String loginSession = (String) session.getAttribute("loginSession");
        SignUpDTO signUpDTO = loginService.searchOne(loginSession);
        model.addAttribute("userInfo", signUpDTO);
    }

    @PostMapping("/register")
    public String registerPOST(@Valid BoardDTO boardDTO, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/board/register";
        }

        Long bno = boardService.register(boardDTO);
        redirectAttributes.addFlashAttribute("result", bno);
        return "redirect:/board/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(@RequestParam("bno")Long bno, PageRequestDTO pageRequestDTO, Model model, HttpSession session){
        BoardDTO boardDTO = boardService.readOne(bno);
        model.addAttribute("dto", boardDTO);
        String loginSession = (String) session.getAttribute("loginSession");


        if(loginSession != null){
            SignUpDTO signUpDTO = loginService.searchOne(loginSession);
            model.addAttribute("userName", signUpDTO.getName());
            log.info(signUpDTO.toString()+"========================");
        }
        else{
            model.addAttribute("userName", "");
        }
    }

    @PostMapping("/modify")
    public String modify(PageRequestDTO pageRequestDTO,
                         @Valid BoardDTO boardDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            String link = pageRequestDTO.getLink();
            redirectAttributes.addFlashAttribute("errors",
                    bindingResult.getAllErrors());
            redirectAttributes.addAttribute("bno", boardDTO.getBno());
            return "redirect:/board/modify?"+link;
        }
        boardService.modify(boardDTO);
        redirectAttributes.addFlashAttribute("result", "modified");
        redirectAttributes.addAttribute("bno", boardDTO.getBno());
        return "redirect:/board/read";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("bno")Long bno, RedirectAttributes redirectAttributes){
        boardService.remove(bno);
        redirectAttributes.addFlashAttribute("result", "deleted");
        return "redirect:/board/list";
    }
}
