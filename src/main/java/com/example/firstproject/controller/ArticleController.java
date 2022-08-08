package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j // 로깅을 위한 어노테이션
public class ArticleController {

    // 객체를 만들지 않아도 됨 -> spring boot가 미리 생성해놓은 객체를 가져다가 자동 연결
    @Autowired
    private ArticleRepository articleRepository;

    // get 형식
    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "/articles/new";
    }

    // post 형식으로 이 주소로 던져짐
    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){ // 입력한 데이터가 인자로 던져짐
        //로깅으로 대체
        //System.out.println(form.toString());
        log.info(form.toString());

        // 1. dto를 entity로 변환
        Article article=form.toEntity();
        //System.out.println(article.toString());
        log.info(article.toString());

        // 2. repository를 통해 entity를 db안에 저장하게 함
        Article saved =articleRepository.save(article);
        //System.out.println(saved.toString());
        log.info(saved.toString());

        return "";
    }
}
