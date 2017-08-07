insert into member (MEMAIL, MNAME) values ('admin', '包府磊');
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

insert into member(memail, mname) values ('admin', '包府磊');
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
