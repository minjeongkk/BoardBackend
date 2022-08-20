INSERT INTO article(id, title, content) VALUES (1, 'KIM', '1111');
INSERT INTO article(id, title, content) VALUES (2, 'SKSKSKSK', 'HELLO~~~');
INSERT INTO article(id, title, content) VALUES (3, 'Minjeong', 'OHOHOHOHOH!!!');

-- article 더미 데이터
INSERT INTO article(id, title, content) VALUES (4, 'movie', 'comment!');
INSERT INTO article(id, title, content) VALUES (5, 'food', 'comment plz');
INSERT INTO article(id, title, content) VALUES (6, 'hobby', 'comment gogo');

-- comment 더미 데이터
-- -- 4번 게시글의 댓글들
INSERT INTO comment(id, article_id, nickname, body) VALUES (1, 4, 'Park', 'spider man');
INSERT INTO comment(id, article_id, nickname, body) VALUES (2, 4, 'Kim', 'conan');
INSERT INTO comment(id, article_id, nickname, body) VALUES (3, 4, 'Choi', 'iron man');

-- -- 5번 게시글의 댓글들
INSERT INTO comment(id, article_id, nickname, body) VALUES (4, 5, 'Park', 'chicken');
INSERT INTO comment(id, article_id, nickname, body) VALUES (5, 5, 'Kim', 'rice');
INSERT INTO comment(id, article_id, nickname, body) VALUES (6, 5, 'Choi', 'kimchi');

-- -- 6번 게시글의 댓글들
INSERT INTO comment(id, article_id, nickname, body) VALUES (7, 6, 'Park', 'book');
INSERT INTO comment(id, article_id, nickname, body) VALUES (8, 6, 'Kim', 'soccer');
INSERT INTO comment(id, article_id, nickname, body) VALUES (9, 6, 'Choi', 'badminton');
