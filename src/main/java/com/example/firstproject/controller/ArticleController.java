package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

        //리다이렉트
        return "redirect:/articles/"+saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        // PathVariable은 주소에 값을 받아옴
        log.info("id = "+id);

        // 1. id로 데이터를 가져옴
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 2. 가져온 데이터를 모델에 등록 -> view page에서 사용 가능
        model.addAttribute("article",articleEntity);

        // 3. 보여줄 페이지를 설정
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){
        // 1. 모든 Article을 가져옴
        // 타입 맞추는 방법
        // 1) Article 타입으로 캐스팅
        // 2) Iterable 타입으로 맞춰줌
        // 3) ArticleRepository에 ArrayList로 변경
        List<Article> articleEntityList = articleRepository.findAll();

        // 2. 가져온 Article 묶음을 뷰로 전달
        model.addAttribute("articleList", articleEntityList);

        //3. 뷰 페이지를 설정 (articles/index.mustache)
        return "articles/index";

    }
}
