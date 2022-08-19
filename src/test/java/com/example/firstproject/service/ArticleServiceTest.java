package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 해당 클래스는 스프링부트와 연동되어 테스팅
class ArticleServiceTest {

    @Autowired ArticleService articleService;

    @Test // 테스트를 위한 코드
    void index() {
        // 예상
        Article a = new Article (1L,"KIM", "1111");
        Article b = new Article (2L,"SKSKSKSK", "HELLO~~~" );
        Article c = new Article (3L,"Minjeong", "OHOHOHOHOH!!!");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a,b,c));

        // 실제
        List<Article> articles = articleService.index();

        // 비교
        assertEquals(expected.toString(), articles.toString());
    }

    // 존재하는 id 입력
    @Test
    void show_success() {
        // 예상
        Long id = 1L;
        Article expected = new Article (id,"KIM", "1111");

        // 실제
        Article article = articleService.show(id);

        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    // 존재하지 않는 id 입력
    @Test
    void show_fail() {
        // 예상
        Long id = -1L;
        Article expected = null;

        // 실제
        Article article = articleService.show(id);

        // 비교
        assertEquals(expected, article);
    }

    // title과 content만 있는 dto 입력
    @Test
    @Transactional
    void create_success() {
        // 예상
        String title ="lala";
        String content ="4444";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article (4L,"lala", "4444");

        // 실제
        Article article = articleService.create(dto);

        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    // id값이 포함된 dto 입력
    @Test
    @Transactional
    void create_fail() {
        // 예상
        Long id = 4L;
        String title ="lala";
        String content ="4444";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = null;

        // 실제
        Article article = articleService.create(dto);

        // 비교
        assertEquals(expected, article);
    }

    // 존재하는 id와 title와 content가 있는 dto 입력
    @Test
    @Transactional
    void update_success_all() {
    }

    // 존재하는 id와 title만 있는 dto 입력
    @Test
    @Transactional
    void update_success_part() {
    }

    // 존재하지 않는 id의 dto를 입력
    @Test
    @Transactional
    void update_fail_none() {
    }

    // id만 있는 dto 입력
    @Test
    @Transactional
    void update_fail_onlyID() {
    }


    @Test
    @Transactional
    void delete_success() {
    }

    @Test
    @Transactional
    void delete_fail() {
    }
}