package com.example.firstproject.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@ToString
@NoArgsConstructor // 디폴트 생성자 추가
@Entity //db가 해당 객체를 인식 가능
@Getter // 모든 get 추가
public class Article {

    @Id // 대표 값
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 id를 자동 생성 어노테이션
    private Long id;

    @Column
    private String title;

    @Column
    private String content;


}
