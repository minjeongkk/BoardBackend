package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article,Long> {
    // 상속 받아서 정의 없이 사용
    // 관리 대상 entity, 관리 대상 entity에서 대표값의 타입

    //CrudRepository를 오버라이딩
    @Override
    ArrayList<Article> findAll();
}
