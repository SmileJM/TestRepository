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