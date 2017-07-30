select count(*) from board;
select * from dba_users;
insert into board (
			bno, btitle, BCONTENT, BWRITER, BDATE,
			BPASSWORD, BHITCOUNT, BLIKECOUNT,
			BORIGINALFILENAME, BSAVEDFILENAME,
			BFILECONTENT)
		values	(
			board_bno_seq.nextval, '1',
			'1',
			'1', sysdate,
			'1', 0, 0,
			' ',
			' ',
			' ');
select bccomment, bcwriter, bcdate from boardcomment order by bcno desc;
select bccomment, bcwriter, bcdate from boardcomment where bno = 3 order by bcno desc;

select count(*) from boardcomment where bno = 13 order by bcno desc;

	   
select * from ( select rownum as r,(select count(*) from boardcomment where bno = 13) as bb, bno, boriginalfilename,
btitle, bwriter,
bdate, bhitcount, blikecount
from ( select  bno,
boriginalfilename, btitle, bwriter, bdate, bhitcount,
blikecount from
board order by bno desc
)) ;


select  bno, (select count(*) from boardcomment where bno= board.bno) as count  from boardcomment;



select * from ( select rownum as r, (select count(*) from boardcomment where bno =board.bno) as count,  bno, boriginalfilename,
		btitle, bwriter,
		bdate, bhitcount, blikecount
		from ( select  bno,
		boriginalfilename, btitle, bwriter, bdate, bhitcount,
		blikecount from
		board order by bno desc
		) where rownum <=10) where r >1;    
 
 select distinct a.*, (select count(*) from BOARDCOMMENT where bno = a.bno )as count
    from ( 
    select rownum as r, bno, boriginalfilename,
		btitle, bwriter,
		bdate, bhitcount, blikecount
		from ( select  bno,
		boriginalfilename, btitle, bwriter, bdate, bhitcount,
		blikecount from
		board order by bno desc
		) where rownum <=10
    ) a, boardcomment b where A.bno = B.bno(+) and  r >=1 order by a.bno desc;    
 
SELECT distinct a.*,
       (select count(*) from BOARDCOMMENT where bno =a.bno )as count
FROM   board a,
       boardcomment b
WHERE  A.bno = B.bno(+)
order by a.bno desc;

 
    