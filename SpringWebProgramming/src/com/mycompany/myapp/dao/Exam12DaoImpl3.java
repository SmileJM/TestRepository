package com.mycompany.myapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
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
public class Exam12DaoImpl3 implements Exam12Dao {
	private static final Logger LOGGER = LoggerFactory.getLogger(Exam12DaoImpl3.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	Connection conn;

	@Override
	public int boardInsert(Exam12Board board) {
		sqlSessionTemplate.insert("board.insert", board);
		// 매개변수 1 : namespace에 해당함
		// 매개변수 2 : board 객체
		LOGGER.info(board.getBno() + "");
		return board.getBno();
	}

	@Override
	public List<Exam12Board> boardSelectAll() {
		List<Exam12Board> list = sqlSessionTemplate.selectList("board.selectAll");
		return list;
	}

	@Override
	public List<Exam12Board> boardSelectPage(int pageNum, int rowsPerPage) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startNum", (pageNum - 1) * rowsPerPage + 1);
		map.put("endNum", pageNum * rowsPerPage);
		List<Exam12Board> list = sqlSessionTemplate.selectList("board.selectByPage", map);
		return list;
	}

	@Override
	public int boardCountAll() {
		int count = sqlSessionTemplate.selectOne("board.countAll");
		return count;
	}

	@Override
	public Exam12Board boardSelectByBno(int bno) {
		Exam12Board board = sqlSessionTemplate.selectOne("board.selectByBno", bno);
		return board;
	}

	@Override
	public void boardUpdateBhitcount(int bno, int bhitcount) {
		Map<String, Object> map = new HashMap<>();
		map.put("bno", bno);
		map.put("bhitcount", bhitcount);
		sqlSessionTemplate.update("board.updateBhitcount", map);
	}

	@Override
	public void boardUpdate(Exam12Board board) {
		sqlSessionTemplate.update("board.update", board);
	}

	@Override
	public void boardDelete(int bno) {
		sqlSessionTemplate.delete("board.delete", bno);
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
		sqlSessionTemplate.insert("member.insert", member);
		LOGGER.info(member.getMid());
		return member.getMid();
	}

	@Override
	public List<Exam12Member> memberSelectPage(int pageNum, int rowsPerPage) {
		Map<String, Integer> map = new HashMap<>();
		map.put("endNum", pageNum * rowsPerPage);
		map.put("startNum", (pageNum - 1) * rowsPerPage + 1);
		List<Exam12Member> list = sqlSessionTemplate.selectList("member.selectPage", map);

		return list;
	}

	@Override
	public int memberCountAll() {
		int count = sqlSessionTemplate.selectOne("member.countAll");
		return count;
	}

	@Override
	public Exam12Member memberSelectByMid(String mid) {
		Exam12Member member = sqlSessionTemplate.selectOne("member.selectByMid", mid);
		return member;
	}

	@Override
	public void memberUpdate(Exam12Member member) {
		sqlSessionTemplate.update("member.update", member);
	}

	@Override
	public void memberDelete(String mid) {
		sqlSessionTemplate.delete("member.delete", mid);
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
		Exam12DaoImpl3 test = new Exam12DaoImpl3();
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
