create table book_tbl(
bcode	number(5) primary key not null, -- 도서코드
btitle	varchar2(30), -- 책이름
bwriter	varchar2(30), -- 저자
bpub	number(4), -- 출판사코드
bprice	number(10) not null, -- 가격
bdate	date -- 출간날짜
);

INSERT INTO book_tbl VALUES(10100, '자바킹', '강길동', 1001, 12000, '20201102');
INSERT INTO book_tbl VALUES(10101, '알고리듬', '남길동', 1002, 18000, '20200505');
INSERT INTO book_tbl VALUES(10102, '스프링두', '서길동', 1003, 23000, '20190803');
INSERT INTO book_tbl VALUES(10103, '파이썬', '홍길동', 1004, 9000, '20191011');

select * from book_tbl;

DELETE FROM book_tbl WHERE bcode = 10105;