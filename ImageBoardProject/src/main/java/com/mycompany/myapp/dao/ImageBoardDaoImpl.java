package com.mycompany.myapp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dto.Exam12Member;
import com.mycompany.myapp.dto.ImageBoard;

@Component
public class ImageBoardDaoImpl implements ImageBoardDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(ImageBoardDaoImpl.class);

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

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
	public int imageBoardInsert(ImageBoard imageboard) {
		sqlSessionTemplate.insert("imageBoard.insert", imageboard);
		int bno = imageboard.getBno();
		LOGGER.info(bno + "");
		return bno;
	}

	@Override
	public List<ImageBoard> imageBoardSelectPage(int pageNo, int rowsPerPage) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("endNum", pageNo * rowsPerPage);
		map.put("startNum", (pageNo - 1) * rowsPerPage + 1);
		List<ImageBoard> list = sqlSessionTemplate.selectList("imageBoard.selectPage", map);
		return list;
	}

	@Override
	public int imageBoardCountAll() {
		int count = sqlSessionTemplate.selectOne("imageBoard.countAll");
		return count;
	}

	@Override
	public ImageBoard imageBoardSelectByBno(int bno) {
		ImageBoard board = sqlSessionTemplate.selectOne("imageBoard.selectByBno", bno);
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
	public void imageBoardUpdate(ImageBoard board) {
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
