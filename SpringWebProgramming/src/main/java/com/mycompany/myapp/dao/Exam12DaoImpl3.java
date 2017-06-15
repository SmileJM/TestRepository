package com.mycompany.myapp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dto.Exam12Board;
import com.mycompany.myapp.dto.Exam12ImageBoard;
import com.mycompany.myapp.dto.Exam12Member;

@Component
public class Exam12DaoImpl3 implements Exam12Dao {
	private static final Logger LOGGER = LoggerFactory.getLogger(Exam12DaoImpl3.class);

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

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
		sqlSessionTemplate.insert("imageBoard.insert", imageboard);
		int bno = imageboard.getBno();
		LOGGER.info(bno + "");
		return bno;
	}

	@Override
	public List<Exam12ImageBoard> imageBoardSelectPage(int pageNo, int rowsPerPage) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("endNum", pageNo * rowsPerPage);
		map.put("startNum", (pageNo - 1) * rowsPerPage + 1);
		List<Exam12ImageBoard> list = sqlSessionTemplate.selectList("imageBoard.selectPage", map);
		return list;
	}

	@Override
	public int imageBoardCountAll() {
		int count = sqlSessionTemplate.selectOne("imageBoard.countAll");
		return count;
	}

	@Override
	public Exam12ImageBoard imageBoardSelectByBno(int bno) {
		Exam12ImageBoard board = sqlSessionTemplate.selectOne("imageBoard.selectByBno", bno);
		return board;
	}

	@Override
	public void imageBoardUpdateBhitcount(int bno, int bhitcount) {
		Map<String, Integer> map = new HashMap<>();
		map.put("bno", bno);
		map.put("bhitcount", bhitcount);
		sqlSessionTemplate.update("imageBoard.updateBhitcount", map);
	}

	@Override
	public void imageBoardDelete(int bno) {
		sqlSessionTemplate.delete("imageBoard.delete", bno);
	}

	@Override
	public void imageBoardUpdate(Exam12ImageBoard board) {
		sqlSessionTemplate.update("imageBoard.update", board);
	}

	@Override
	public void imageBoardUpdateBlikecount(int bno, int blikecount) {
		Map<String, Integer> map = new HashMap<>();
		map.put("bno", bno);
		map.put("blikecount", blikecount);
		sqlSessionTemplate.update("imageBoard.updateBlikecount", map);
	}
}
