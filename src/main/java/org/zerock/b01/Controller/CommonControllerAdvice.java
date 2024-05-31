//package org.zerock.b01.controller;
//
//import jakarta.servlet.http.HttpSession;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.zerock.b01.dto.SessionDTO;
//
//@ControllerAdvice
//@Log4j2
//@RequiredArgsConstructor
//public class CommonControllerAdvice {
//
//    @ModelAttribute("loginSession")
//    public SessionDTO addLoginSessionToModel(HttpSession session) {
//        return (SessionDTO) session.getAttribute("loginSession");
//    }
//}
