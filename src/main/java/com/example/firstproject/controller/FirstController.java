package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// 컨트롤러 선언
@Controller
public class FirstController {
    @GetMapping("/hi") // 접속할 url주소
    public String niceToMeetYou(Model model){
        model.addAttribute("username", "민정");
        //templates/greetings.mustache를 찾아서 브라우저로 전송
        return "greetings";
    }

    @GetMapping("/bye") // 접속할 url주소
    public String seeYouNext(Model model){
        model.addAttribute("nickname", "민정");
        //templates/goodbye.mustache를 찾아서 브라우저로 전송
        return "goodbye";
    }
}
