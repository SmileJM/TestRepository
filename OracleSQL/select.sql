/*
SELECT 문
*/

-- bwriter 가 '다'인 모든 컬럼 가져오기
select *
from board
where bwriter='다';

-- bwriter 가 '다'인 bno, btitle, bwriter 가져오기
select bno, btitle, bwriter
from board
where bwriter='다';

-- bno 가 1~10 인 게시물 가져오기
select bno, btitle, bwriter
from board
where bno>=1 and bno<=10;

select bno, btitle, bwriter
from board
where bno between 1 and 10;
-- bwriter 가 '다'를 포함하는 게시물의 bno, btitle, bwriter 가져오기
select bno, btitle, bwriter
from board
where bwriter like '%다%';
-- 게시물의 제목 중에 '자바'가 포함되어 있는 게시물의 번호, 제목, 글쓴이 가져오기
select bno, btitle, bwriter
from board
where btitle like '%자바%';
-- 게시물의 제목 또는 내용 중에 '자바'가 포함되어 있는 게시물의 번호, 제목, 글쓴이, 내용가져오기
select bno, btitle, bwriter, bcontent
from board
where btitle like '%자바%' or  bcontent like '%자바%';
-- 글쓴이가 감자바, 길길동, 라즈베리인 게시물의번호, 제목, 글쓴이 가져오기
select bno, btitle, bwriter
from board
where bwriter = '감자바' or bwriter = '김길동' or bwriter = '라즈베리';

select bno, btitle, bwriter
from board
where bwriter in('감자바', '김길동', '라즈베리');

-- 첨부 파일이 없는 게시물을 가져오기
select *
from board
where BORIGINALFILENAME is null;
-- 게시물을 작성한 사람의 이름을 가져오기(중복 제거)
select distinct bwriter
from board;

-- 게시물의 날짜가 2016년인 게시글 가져오기
select *
from board
--where '2016.01.01' <= bdate and '2016.12.31' >= bdate;
 where bdate between '2016.01.01'and '2016.12.31';
-- where bdate <= to_date('16/12/31', 'YY/MM/DD');

-- bno > 번호, btitle > 제목, bwriter > 글쓴이로 컬럼 이름 변경해서 가져오기
select bno as 번호, btitle as 제목, bwriter as 글쓴이
from board;

/*
  정렬
*/
-- 게시물의 번호를 기준으로 오름차순으로 가져오기
select *
from board
order by bno asc;

-- 2017년도에 작성한 게시물의 번호를 기준으로 내림차순으로 가져오기
select * 
from board
where bdate between '2017.01.01' and '2017.12.31'
order by bno asc;

-- 글쓴이를 기준으로 1차 정렬, 작성일을 기준 2차정렬
select * 
from board
order by bwriter asc, bdate desc;

/*
페이징 처리
*/
-- 저장되어 있는 순서대로 행 번호 매기기
select rownum, bno, btitle, bwriter, bdate, bhitcount
from board;
-- 정렬 후 행번호 매기기
select rownum, bno, btitle, bwriter, bdate, bhitcount
from (select bno, btitle, bwriter, bdate, bhitcount from board order by bno desc);
-- 특정 행 번호 이하 가져오기(Top N을 가져오기, 위에서부터 N개)
select *
from (
  select rownum as r, bno, btitle, bwriter, bdate, bhitcount
  from (select bno, btitle, bwriter, bdate, bhitcount from board order by bno desc)
  where rownum<=(7*15)
)
where r >= ((7-1)*15+1);
-- 시작 행번호 끝 행번호 사이의 게시물 가져오기

-- 전체 행수 구하기
select count(*)from board;
select count(bno)from board;
select count(BORIGINALFILENAME)from board;
select count(*)from board where bpassword <> 12345;

select * 
from (
  select rownum as r, mid, mname, mage, maddress
  from (select mid, mname, mage, maddress from member)
  where rownum <=20
)
where r >= 11;
