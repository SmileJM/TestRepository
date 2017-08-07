package com.mycompany.myapp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mycompany.myapp.dto.Board;
import com.mycompany.myapp.dto.BoardComment;
import com.mycompany.myapp.dto.Hitcount;
import com.mycompany.myapp.dto.Likecount;
import com.mycompany.myapp.dto.Member;

@Component
public class BoardDaoImpl implements BoardDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(BoardDaoImpl.class);

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	/*************************************************************************************
	 * 
	 * Board
	 * 
	 *************************************************************************************/
	@Override
	public int boardInsert(Board board) {
		sqlSessionTemplate.insert("board.insert", board);
		int bno = board.getBno();
		LOGGER.info(bno + "");
		return bno;
	}

	@Override
	public List<Board> boardSelectPage(int pageNo, int rowsPerPage) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("endNum", pageNo * rowsPerPage);
		map.put("startNum", (pageNo - 1) * rowsPerPage );
		List<Board> list = sqlSessionTemplate.selectList("board.selectPage", map);
		return list;
	}

	@Override
	public int boardCountAll() {
		int count = sqlSessionTemplate.selectOne("board.countAll");
		return count;
	}

	@Override
	public Board boardSelectByBno(int bno) {
		Board Board = sqlSessionTemplate.selectOne("board.selectByBno", bno);
		return Board;
	}

	@Override
	public int boardUpdateBhitcount(int bno, String memail, int bhitcount) {
		Map<String, String> map = new HashMap<>();
		map.put("bno", String.valueOf(bno));
		map.put("memail", memail);
		map.put("bhitcount", String.valueOf(bhitcount));

		int result = sqlSessionTemplate.update("board.updateBhitcount", map);				

		if(result ==1){
			Hitcount hitcount = new Hitcount();
			hitcount.setBno(bno);
			hitcount.setMemail(memail);
			System.out.println(bno + memail);
			
			sqlSessionTemplate.insert("hitcount.insert", hitcount);
		}
		return result;
	}

	@Override
	public void boardDelete(int bno) {
		sqlSessionTemplate.delete("board.delete", bno);
	}

	@Override
	public void boardUpdate(Board board) {
		sqlSessionTemplate.update("board.update", board);
	}

	@Override
	public void boardUpdateBlikecount(int bno, String memail, int blikecount) {
		Map<String, String> map = new HashMap<>();
		map.put("bno", String.valueOf(bno));		
		map.put("blikecount", String.valueOf(blikecount));
		map.put("memail", memail);
		System.out.println("update");
		int result = sqlSessionTemplate.update("board.updateBlikecount", map);
		System.out.println(result);
		System.out.println("insert");
		if(result == 1){
			System.out.println(bno + memail);
			Likecount likecount = new Likecount();
			likecount.setBno(bno);
			likecount.setMemail(memail);
			sqlSessionTemplate.insert("likecount.insert", likecount);
		} else {
			
		}
	}
	/*************************************************************************************
	 * 
	 * BoardComment
	 * 
	 *************************************************************************************/

	@Override
	public int boardCommentInsert(BoardComment comment) {
		sqlSessionTemplate.insert("boardcomment.insert", comment);
		int bcno = comment.getBcno();
		LOGGER.info(bcno + "");
		return bcno;
	}

	@Override
	public List<BoardComment> boardCommentList(int bno) {	
		List<BoardComment> list = sqlSessionTemplate.selectList("boardcomment.selectcommentlist", bno);
		return list;
	}
	
}
