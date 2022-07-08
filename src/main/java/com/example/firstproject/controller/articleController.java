package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class articleController {

    // get 형식
    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "/articles/new";
    }

    // post 형식으로 이 주소로 던져짐
    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){ // 입력한 데이터가 인자로 던져짐
        System.out.println(form.toString());
        return "";
    }
}
