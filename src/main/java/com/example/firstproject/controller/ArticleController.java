package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        // 수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 모델에 데이터를 등록
        model.addAttribute("article", articleEntity);

        // 뷰페이지 설정
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form){
        log.info(form.toString());

        // 1. DTO를 엔티티로 변환
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());

        // 2. 엔티티를 DB로 저장
        // 2-1. DB에서 기존 데이터를 가져옴
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        // 2-2. 기존 데이터가 있다면 값을 갱신
        if (target != null){
            articleRepository.save(articleEntity);
        }

        // 3. 수정 결과 페이지로 리다이렉트
        return "redirect:/articles/"+articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제 요청이 들어왔습니다!");

        // 1. 삭제 대상을 가져옴
        Article target = articleRepository.findById(id).orElse(null);

        // 2. 대상을 삭제
        if (target != null){
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제가 완료되었습니다!");
        }

        // 3. 결과 페이지를 리다이렉트
        return "redirect:/articles";
    }
}
