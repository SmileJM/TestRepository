package com.mycompany.myapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dto.Exam12Board;
import com.mycompany.myapp.dto.Exam12ImageBoard;
import com.mycompany.myapp.dto.Exam12Member;

@Component
public class Exam12DaoImpl2 implements Exam12Dao {
	private static final Logger LOGGER = LoggerFactory.getLogger(Exam12DaoImpl2.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	Connection conn;

	@Override
	public int boardInsert(Exam12Board board) {
		int bno = -1;
		// SQL 작성
		final String sql = "insert into board "
				+ "(bno, BTITLE, BCONTENT, BWRITER, BDATE, BPASSWORD, BHITCOUNT, BORIGINALFILENAME, BSAVEDFILENAME, BFILECONTENT) "
				+ "values " + "(board_bno_seq.nextval, ?, ?, ?, sysdate, ?, 0, ?, ?, ?) ";
		PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pstmt = conn.prepareStatement(sql, new String[] { "bno" });

				pstmt.setString(1, board.getBtitle());
				pstmt.setString(2, board.getBcontent());
				pstmt.setString(3, board.getBwriter());
				pstmt.setString(4, board.getBpassword());
				pstmt.setString(5, board.getBoriginalfilename());
				pstmt.setString(6, board.getBsavedfilename());
				pstmt.setString(7, board.getBfilecontent());
				return pstmt;
			}
		};
		// bno 를 키홀더에
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(psc, keyHolder);
		bno = keyHolder.getKey().intValue();

		LOGGER.info("bno: " + bno);
		return bno;
	}

	@Override
	public List<Exam12Board> boardSelectAll() {
		String sql = "select bno, btitle, bwriter, bdate, bhitcount from board order by bno desc ";

		RowMapper<Exam12Board> rowMapper = new RowMapper<Exam12Board>() {
			@Override
			public Exam12Board mapRow(ResultSet rs, int rowNum) throws SQLException {
				Exam12Board board = new Exam12Board();
				board.setBno(rs.getInt("bno"));
				board.setBtitle(rs.getString("btitle"));
				board.setBwriter(rs.getString(3));
				board.setBdate(rs.getDate("bdate"));
				board.setBhitcount(rs.getInt("bhitcount"));
				return board;
			}
		};
		List<Exam12Board> list = jdbcTemplate.query(sql, rowMapper);

		return list;
	}

	@Override
	public List<Exam12Board> boardSelectPage(int pageNum, int rowsPerPage) {

		// SQL 작성
		String sql = "select * ";
		sql += "from ( ";
		sql += "	select rownum as r, bno, btitle, bwriter, bdate, bhitcount ";
		sql += "	from ( ";
		sql += "		select bno, btitle, bwriter, bdate, bhitcount from board order by bno desc ";
		sql += "	) ";
		sql += "	where rownum<=? ";
		sql += ") ";
		sql += "where r >=? ";

		Object[] args = { pageNum * rowsPerPage, (pageNum - 1) * rowsPerPage + 1 };
		RowMapper<Exam12Board> rowMapper = new RowMapper<Exam12Board>() {
			@Override
			public Exam12Board mapRow(ResultSet rs, int rowNum) throws SQLException {
				Exam12Board board = new Exam12Board();
				board.setBno(rs.getInt("bno"));
				board.setBtitle(rs.getString("btitle"));
				board.setBwriter(rs.getString(3));
				board.setBdate(rs.getDate("bdate"));
				board.setBhitcount(rs.getInt("bhitcount"));
				return board;
			}
		};
		List<Exam12Board> list = jdbcTemplate.query(sql, args, rowMapper);
		return list;
	}

	@Override
	public int boardCountAll() {
		String sql = "select count(*) from board";
		int count = jdbcTemplate.queryForObject(sql, Integer.class);
		return count;
	}

	@Override
	public Exam12Board boardSelectByBno(int bno) {
		String sql = "select * from board where bno=? ";
		RowMapper<Exam12Board> rowMapper = new RowMapper<Exam12Board>() {
			@Override
			public Exam12Board mapRow(ResultSet rs, int rowNum) throws SQLException {
				Exam12Board board = new Exam12Board();
				board.setBno(rs.getInt("bno"));
				board.setBtitle(rs.getString("btitle"));
				board.setBwriter(rs.getString("bwriter"));
				board.setBdate(rs.getDate("bdate"));
				board.setBhitcount(rs.getInt("bhitcount"));
				board.setBcontent(rs.getString("bcontent"));
				board.setBpassword(rs.getString("bpassword"));
				board.setBoriginalfilename(rs.getString("boriginalfilename"));
				board.setBsavedfilename(rs.getString("bsavedfilename"));
				board.setBfilecontent(rs.getString("bfilecontent"));
				return board;
			}
		};
		Exam12Board board = jdbcTemplate.queryForObject(sql, rowMapper, bno);
		return board;
	}

	@Override
	public void boardUpdateBhitcount(int bno, int bhitcount) {
		String sql = "update board set bhitcount=? where bno=? ";
		jdbcTemplate.update(sql, bhitcount, bno);
	}

	@Override
	public void boardUpdate(Exam12Board board) {
		String sql;
		if (board.getBoriginalfilename() != null) {
			sql = "update board set btitle=?, bcontent=?, bpassword=?, bdate=sysdate, boriginalfilename=?, bsavedfilename=?, bfilecontent=? where bno=? ";
			jdbcTemplate.update(sql, board.getBtitle(), board.getBcontent(), board.getBpassword(),
					board.getBoriginalfilename(), board.getBsavedfilename(), board.getBfilecontent(), board.getBno());
		} else {
			sql = "update board set btitle=?, bcontent=?, bpassword=?, bdate=sysdate where bno=? ";
			jdbcTemplate.update(sql, board.getBtitle(), board.getBcontent(), board.getBpassword(), board.getBno());
		}
	}

	@Override
	public void boardDelete(int bno) {
		String sql = "delete from board where bno=? ";
		jdbcTemplate.update(sql, bno);
	}

	/*************************************************************************************
	 * 
	 * 
	 * 
	 * Member
	 * 
	 * 
	 * 
	 *************************************************************************************/
	@Override
	public String memberInsert(Exam12Member member) {
		String sql = "insert into member "
				+ "(MID, MNAME, MPASSWORD, MDATE, MTEL, MEMAIL, MAGE, MADDRESS, MORIGINALFILENAME, MSAVEDFILENAME, MFILECONTENT) "
				+ "values "
				// 매개변수화된 SQL 문
				+ "(?, ?, ?, sysdate, ?, ?, ?, ?, ?, ?, ?) ";
		jdbcTemplate.update(sql, member.getMid(), member.getMname(), member.getMpassword(), member.getMtel(),
				member.getMemail(), member.getMage(), member.getMaddress(), member.getMoriginalfilename(),
				member.getMsavedfilename(), member.getMfilecontent());
		return member.getMid();
	}

	@Override
	public List<Exam12Member> memberSelectPage(int pageNum, int rowsPerPage) {
		String sql = "select * ";
		sql += "from ( ";
		sql += "	select rownum as r, mid, mname, mage, maddress ";
		sql += "	from (select mid, mname, mage, maddress from member)  ";
		sql += "	where rownum<=? ";
		sql += "	order by mid asc ";
		sql += ") ";
		sql += "where r>=?";

		RowMapper<Exam12Member> rowMapper = new RowMapper<Exam12Member>() {
			@Override
			public Exam12Member mapRow(ResultSet rs, int arg1) throws SQLException {
				Exam12Member member = new Exam12Member();
				member.setMid(rs.getString("mid"));
				member.setMname(rs.getString("mname"));
				member.setMage(rs.getInt("mage"));
				member.setMaddress(rs.getString("maddress"));
				return member;
			}
		};
		List<Exam12Member> list = jdbcTemplate.query(sql, rowMapper, pageNum * rowsPerPage,
				(pageNum - 1) * rowsPerPage + 1);

		return list;
	}

	@Override
	public int memberCountAll() {
		String sql = "select count(*) from member ";
		int count = jdbcTemplate.queryForObject(sql, Integer.class);

		return count;
	}

	@Override
	public Exam12Member memberSelectByMid(String mid) {
		String sql = "select * from member where mid=? ";

		RowMapper<Exam12Member> rowMapper = new RowMapper<Exam12Member>() {
			@Override
			public Exam12Member mapRow(ResultSet rs, int arg1) throws SQLException {
				Exam12Member member = new Exam12Member();
				member.setMid(rs.getString("mid"));
				member.setMname(rs.getString("mname"));
				member.setMpassword(rs.getString("mpassword"));
				member.setMdate(rs.getDate("mdate"));
				member.setMtel(rs.getString("mtel"));
				member.setMemail(rs.getString("memail"));
				member.setMage(rs.getInt("mage"));
				member.setMaddress(rs.getString("maddress"));
				member.setMoriginalfilename(rs.getString("moriginalfilename"));
				member.setMsavedfilename(rs.getString("msavedfilename"));
				member.setMfilecontent(rs.getString("mfilecontent"));
				return member;
			}
		};
		Exam12Member member = jdbcTemplate.queryForObject(sql, rowMapper, mid);
		return member;
	}

	@Override
	public void memberUpdate(Exam12Member member) {
		String sql;
		if (member.getMoriginalfilename() != null) {
			sql = "update member set mname=?, mpassword=?, mdate=sysdate, mtel=?, memail=?, mage=?, maddress=?, moriginalfilename=?, msavedfilename=?, mfilecontent=? ";
			sql += "where mid=? ";
			jdbcTemplate.update(sql, member.getMname(), member.getMpassword(), member.getMtel(), member.getMemail(),
					member.getMage(), member.getMaddress(), member.getMoriginalfilename(), member.getMsavedfilename(),
					member.getMfilecontent(), member.getMid());
		} else {
			sql = "update member set mname=?, mpassword=?, mdate=sysdate, mtel=?, memail=?, mage=?, maddress=? ";
			sql += "where mid=? ";
			jdbcTemplate.update(sql, member.getMname(), member.getMpassword(), member.getMtel(), member.getMemail(),
					member.getMage(), member.getMaddress(), member.getMid());
		}
	}

	@Override
	public void memberDelete(String mid) {
		String sql = "delete from member where mid=? ";
		jdbcTemplate.update(sql, mid);
	}

	/*************************************************************************************
	 * 
	 * 
	 * 
	 * ImageBoard
	 * 
	 * 
	 * 
	 *************************************************************************************/
	@Override
	public int imageBoardInsert(Exam12ImageBoard imageboard) {
		int bno = -1;

		final String sql = "insert into imageboard "
				+ "(bno, BTITLE, BCONTENT, BWRITER, BDATE, BPASSWORD, BHITCOUNT, BLIKECOUNT, BORIGINALFILENAME, BSAVEDFILENAME, BFILECONTENT) "
				+ "values " + "(board_bno_seq.nextval, ?, ?, ?, sysdate, ?, 0, 0, ?, ?, ?) ";

		PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pstmt = conn.prepareStatement(sql, new String[] { "bno" });

				pstmt.setString(1, imageboard.getBtitle());
				pstmt.setString(2, imageboard.getBcontent());
				pstmt.setString(3, imageboard.getBwriter());
				pstmt.setString(4, imageboard.getBpassword());
				pstmt.setString(5, imageboard.getBoriginalfilename());
				pstmt.setString(6, imageboard.getBsavedfilename());
				pstmt.setString(7, imageboard.getBfilecontent());
				return pstmt;
			}
		};
		KeyHolder keyHolder = new GeneratedKeyHolder();
		bno = jdbcTemplate.update(psc, keyHolder);
		return bno;
	}

	@Override
	public List<Exam12ImageBoard> imageBoardSelectPage(int pageNo, int rowsPerPage) {
		new ArrayList<>();

		String sql = "select * ";
		sql += "from ( ";
		sql += "	select rownum as r, bno, boriginalfilename, btitle, bwriter, bdate, bhitcount, blikecount ";
		sql += "	from ( ";
		sql += "		select bno, boriginalfilename, btitle, bwriter, bdate, bhitcount, blikecount from imageboard order by bno desc ";
		sql += "	) ";
		sql += "	where rownum<=? ";
		sql += ") ";
		sql += "where r >=? ";

		RowMapper<Exam12ImageBoard> rowMapper = new RowMapper<Exam12ImageBoard>() {
			@Override
			public Exam12ImageBoard mapRow(ResultSet rs, int rowNum) throws SQLException {
				Exam12ImageBoard board = new Exam12ImageBoard();

				board.setBno(rs.getInt("bno"));
				board.setBoriginalfilename(rs.getString("boriginalfilename"));
				board.setBtitle(rs.getString("btitle"));
				board.setBwriter(rs.getString("bwriter"));
				board.setBdate(rs.getDate("bdate"));
				board.setBhitcount(rs.getInt("bhitcount"));
				board.setBlikecount(rs.getInt("blikecount"));
				return board;
			}
		};
		List<Exam12ImageBoard> list = jdbcTemplate.query(sql, rowMapper, (pageNo * rowsPerPage),
				(pageNo - 1) * rowsPerPage + 1);
		return list;
	}

	@Override
	public int imageBoardCountAll() {
		String sql = "select count(*) from imageBoard ";
		int count = jdbcTemplate.queryForObject(sql, Integer.class);
		return count;
	}

	@Override
	public Exam12ImageBoard imageBoardSelectByBno(int bno) {
		String sql = "select * from imageboard where bno=?";
		RowMapper<Exam12ImageBoard> rowMapper = new RowMapper<Exam12ImageBoard>() {
			@Override
			public Exam12ImageBoard mapRow(ResultSet rs, int rowNum) throws SQLException {
				Exam12ImageBoard board = new Exam12ImageBoard();
				board.setBno(rs.getInt("bno"));
				board.setBtitle(rs.getString("btitle"));
				board.setBwriter(rs.getString("bwriter"));
				board.setBdate(rs.getDate("bdate"));
				board.setBpassword(rs.getString("bpassword"));
				board.setBhitcount(rs.getInt("bhitcount"));
				board.setBcontent(rs.getString("bcontent"));
				board.setBlikecount(rs.getInt("blikecount"));
				board.setBoriginalfilename(rs.getString("boriginalfilename"));
				board.setBsavedfilename(rs.getString("bsavedfilename"));
				board.setBfilecontent(rs.getString("bfilecontent"));
				return board;
			}
		};
		Exam12ImageBoard board = jdbcTemplate.queryForObject(sql, rowMapper, bno);
		return board;
	}

	@Override
	public void imageBoardUpdateBhitcount(int bno, int bhitcount) {
		String sql = "update imageboard set bhitcount=? where bno=? ";
		jdbcTemplate.update(sql, bhitcount, bno);
	}

	@Override
	public void imageBoardDelete(int bno) {
		String sql = "delete from imageboard where bno=? ";
		jdbcTemplate.update(sql, bno);
	}

	@Override
	public void imageBoardUpdate(Exam12ImageBoard board) {
		String sql;
		if (board.getBoriginalfilename() != null) {
			sql = "update imageboard set btitle=?, bcontent=?, bpassword=?, bdate=sysdate, boriginalfilename=?, bsavedfilename=?, bfilecontent=? where bno=? ";
			jdbcTemplate.update(sql, board.getBtitle(), board.getBcontent(), board.getBpassword(),
					board.getBoriginalfilename(), board.getBsavedfilename(), board.getBfilecontent(), board.getBno());
		} else {
			sql = "update imageboard set btitle=?, bcontent=?, bpassword=?, bdate=sysdate where bno=? ";
			jdbcTemplate.update(sql, board.getBtitle(), board.getBcontent(), board.getBpassword(), board.getBno());
		}
	}

	@Override
	public void imageBoardUpdateBlikecount(int bno, int blikecount) {
		String sql = "update imageboard set blikecount=? where bno=? ";
		jdbcTemplate.update(sql, blikecount, bno);
	}

	@Override
	public List<Exam12ImageBoard> imageBoardSelectPage(int pageNo, int rowsPerPage, String btitle) {

		String sql = "select * ";
		sql += "from ( ";
		sql += "	select rownum as r, bno, boriginalfilename, btitle, bwriter, bdate, bhitcount, blikecount ";
		sql += "	from ( ";
		sql += "		select bno, boriginalfilename, btitle, bwriter, bdate, bhitcount, blikecount from imageboard order by bno desc ";
		sql += "	) ";
		sql += "	where rownum<=? and btitle like '%?%' ";
		sql += ") ";
		sql += "where r >=? ";

		RowMapper<Exam12ImageBoard> rowMapper = new RowMapper<Exam12ImageBoard>() {
			@Override
			public Exam12ImageBoard mapRow(ResultSet rs, int rowNum) throws SQLException {
				Exam12ImageBoard board = new Exam12ImageBoard();
				board.setBno(rs.getInt("bno"));
				board.setBoriginalfilename(rs.getString("boriginalfilename"));
				board.setBtitle(rs.getString("btitle"));
				board.setBwriter(rs.getString("bwriter"));
				board.setBdate(rs.getDate("bdate"));
				board.setBhitcount(rs.getInt("bhitcount"));
				board.setBlikecount(rs.getInt("blikecount"));
				return board;
			}
		};
		List<Exam12ImageBoard> list = jdbcTemplate.query(sql, rowMapper, pageNo * rowsPerPage,
				(pageNo - 1) * rowsPerPage + 1);
		return list;
	}

	public static void main(String[] args) {
		Exam12DaoImpl2 test = new Exam12DaoImpl2();
		// for (int i = 1; i <= 100; i++) {
		// Exam12Board board = new Exam12Board();
		// board.setBtitle("제목" + i);
		// board.setBcontent("내용" + i);
		// board.setBwriter("홍길동");
		// board.setBpassword("12345");
		// board.setBoriginalfilename("a.png");
		// board.setBsavedfilename("a444555.png");
		// board.setBfilecontent("image/png");
		// int bno = test.boardInsert(board);
		// LOGGER.info("추가된 행의 bno: " + bno);
		// }
		// List<Exam12Board> list = test.boardSelectPage(2, 10);
		// for (Exam12Board data : list) {
		// LOGGER.info(data.getBtitle());
		// }

		// for(int i=1; i<=100; i++) {
		// Exam12Member member = new Exam12Member();
		// member.setMid("smile" + i);
		// member.setMname("홍길동" + i);
		// member.setMpassword("12345");
		// member.setMtel("010-1234-5678");
		// member.setMemail(i+"daum@naver.com");
		// member.setMage(23);
		// member.setMaddress("서울");
		// member.setMoriginalfilename("a.png");
		// member.setMsavedfilename("a.png");
		// member.setMfilecontent("image/png");
		// test.memberInsert(member);
		// }
		for (int i = 1; i < 10; i++) {
			Exam12ImageBoard board = new Exam12ImageBoard();
			board.setBtitle("김소현" + i);
			board.setBoriginalfilename("sohyun00" + i + ".jpg");
			board.setBcontent("김소현");
			board.setBpassword("123");
			board.setBwriter("smile");
			test.imageBoardInsert(board);

		}
		// for(int i=1; i<10; i++) {
		// Exam12ImageBoard board = new Exam12ImageBoard();
		// board.setBtitle("음식"+i);
		// board.setBoriginalfilename("food00"+i+".jpg");
		// board.setBcontent("음식");
		// board.setBpassword("123");
		// board.setBwriter("sky");
		// test.imageBoardInsert(board);
		// }
	}
}
