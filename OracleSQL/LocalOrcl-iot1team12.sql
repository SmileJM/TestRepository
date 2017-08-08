insert into member (MEMAIL, MNAME) values ('admin', '관리자');
select bccomment as b from BOARDCOMMENT;
select bccomment, (select distinct mname from member a, boardcomment b where a.MEMAIL = b.BCWRITER) as bcwirter from boardcomment where bno = 1
order by bcno desc;	

select     bccomment,     (select mname from member a, boardcomment b where a.MEMAIL = b.BCWRITER),    bcdate    from boardcomment    where bno = 1    order by bcno desc;

SELECT distinct a.*,
       (select count(*) from BOARDCOMMENT where bno =a.bno )as count
FROM   board a,
       boardcomment b
WHERE  A.bno = B.bno(+)
order by a.bno desc;

insert into member(memail, mname) values ('admin', '관리자');
-- likecount
update board set blikecount= 3 where bno= 3 and 'admin' not in (select memail from likecount where bno=3);
insert into likecount(bno, memail) values ('3', 'admin');

select distinct mname from member a, boardcomment b where a.MEMAIL = b.BCWRITER;

select distinct mname from member a, boardcomment b;

		select 
			bccomment, 
			bcwriter,
			bcdate 
		from boardcomment 
		where bno = 9
		order by bcno asc	;
    
update board set bhitcount=5 where bno=1 and 'admin' not in (select memail from hitcount where bno=1);
insert into hitcount(bno, memail) values ('1', 'admin');
update board set bhitcount=5 where bno=1 and 'admin' not in (select memail from hitcount where bno=1);
insert into hitcount (    bno, memail   )   values (    1,    'admin'   );

insert into board (bno, btitle, bwriter, bpassword, bcontent, bdate) values (board_bno_seq.nextval, 'test', 'abc', '123', '테스트합니다', sysdate);


select * from board where btitle like '%테스%';
select * from board where bwriter = 'abc';
select * from board where bcontent like '%테스%';
select * from board where btitle like '%테스%';
select * from board where btitle like '%테스%' or bcontent like '%테스%';
