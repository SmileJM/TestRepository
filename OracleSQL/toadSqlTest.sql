select count(*) from board;

insert into board (
			btitle, BCONTENT, BWRITER, BDATE,
			BPASSWORD, BHITCOUNT, BLIKECOUNT,
			BORIGINALFILENAME, BSAVEDFILENAME,
			BFILECONTENT, MEMAIL)
		values	(
			'1',
			'1',
			'1', current_timestamp,
			'1', 0, 0,
			'1',
			'1',
			'1',
      '2@a'
      );
      
select distinct a.*, ( select count(*) from boardcomment where bno =a.bno ) as count
		from ( select @ROWNUM := @ROWNUM + 1 as r, bno, boriginalfilename, btitle, bwriter,	bdate, bhitcount, blikecount
			from ( select  bno, boriginalfilename, btitle, bwriter, bdate, bhitcount, blikecount 
				from board order by bno desc
			) as c , (select @rownum:=0) TMP
      where @ROWNUM := @ROWNUM + 1 <=10 
		) a left join 
		boardcomment b  on
		r >=3 and a.bno = b.bno
		order by a.bno desc;
    
 
select distinct a.*, ( select count(*) from boardcomment where bno =a.bno ) as count
		from ( select bno, boriginalfilename, btitle, bwriter,	bdate, bhitcount, blikecount
			from ( select  bno, boriginalfilename, btitle, bwriter, bdate, bhitcount, blikecount 
				from board order by bno desc
			) as c 
		) a left join 
		boardcomment b  on
		a.bno = b.bno
		order by a.bno desc
    limit 0, 10;