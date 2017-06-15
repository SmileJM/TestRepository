package com.mycompany.myapp.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dao.Exam12Dao;
import com.mycompany.myapp.dto.Exam12Board;
import com.mycompany.myapp.dto.Exam12ImageBoard;
import com.mycompany.myapp.dto.Exam12Member;

@Component
public class Exam12ServiceImpl implements Exam12Service {

	@Resource(name="exam12DaoImpl")	
	private Exam12Dao dao;

	@Override
	public void boardWrite(Exam12Board board) {
		dao.boardInsert(board);
	}

	@Override
	public List<Exam12Board> boardListAll() {
		List<Exam12Board> list = dao.boardSelectAll();
		return list;
	}

	@Override
	public List<Exam12Board> boardListPage(int pageNo, int rowsPerPage) {
		List<Exam12Board> list = dao.boardSelectPage(pageNo, rowsPerPage);
		return list;
	}

	@Override
	public int boardTotalRows() {
		int totalRows = dao.boardCountAll();
		return totalRows;
	}

	@Override
	public Exam12Board getBoard(int bno) {
		Exam12Board board = dao.boardSelectByBno(bno);
		board.setBhitcount(board.getBhitcount() + 1);
		dao.boardUpdateBhitcount(bno, board.getBhitcount());
		return board;
	}

	@Override
	public String boardCheckBpassword(int bno, String bpassword) {
		String result = "fail";
		Exam12Board board = dao.boardSelectByBno(bno);
		if (board.getBpassword().equals(bpassword)) {
			result = "success";
		}
		return result;
	}

	@Override
	public void boardUpdate(Exam12Board board) {
		dao.boardUpdate(board);

	}

	@Override
	public void boardDelete(int bno) {
		dao.boardDelete(bno);

	}

	@Override
	public void memberJoin(Exam12Member member) {
		dao.memberInsert(member);
	}

	@Override
	public List<Exam12Member> memberListPage(int pageNo, int rowsPerPage) {
		List<Exam12Member> list = dao.memberSelectPage(pageNo, rowsPerPage);
		return list;
	}

	@Override
	public int memberTotalRows() {
		int count = dao.memberCountAll();
		return count;
	}

	@Override
	public Exam12Member getMember(String mid) {
		Exam12Member member = dao.memberSelectByMid(mid);
		return member;
	}

	@Override
	public String memberCheckBpassword(String mid, String mpassword) {
		String result = "fail";
		Exam12Member member = dao.memberSelectByMid(mid);
		if (member.getMpassword().equals(mpassword)) {
			result = "success";
		}
		return result;
	}

	@Override
	public void memberUpdate(Exam12Member member) {
		dao.memberUpdate(member);
		
	}

	@Override
	public void memberDelete(String mid) {
		dao.memberDelete(mid);
		
	}
	/////////////////////////////////////////////////////////

	@Override
	public void imageBoardWrite(Exam12ImageBoard imageboard) {
		dao.imageBoardInsert(imageboard);
		
	}

	@Override
	public List<Exam12ImageBoard> imageBoardListPage(int pageNo, int rowsPerPage) {
		List<Exam12ImageBoard> list = dao.imageBoardSelectPage(pageNo, rowsPerPage);
		return list;
	}

	@Override
	public int imageBoardTotalRows() {
		int count = dao.imageBoardCountAll();
		return count;
	}

	@Override
	public Exam12ImageBoard getImageBoard(int bno) {
		Exam12ImageBoard board = dao.imageBoardSelectByBno(bno);
		board.setBhitcount(board.getBhitcount() + 1);
		dao.imageBoardUpdateBhitcount(bno, board.getBhitcount());
		return board;
	}

	@Override
	public Exam12ImageBoard getImageBoardImg(int bno) {
		Exam12ImageBoard board = dao.imageBoardSelectByBno(bno);
		return board;
	}

	@Override
	public String imageBoardCheckBpassword(int bno, String bpassword) {
		String result = "fail";
		Exam12ImageBoard board = dao.imageBoardSelectByBno(bno);
		if (board.getBpassword().equals(bpassword)) {
			result = "success";
		}
		return result;
	}

	@Override
	public void imageBoardDelete(int bno) {
		dao.imageBoardDelete(bno);		
	}

	@Override
	public void imageBoardUpdate(Exam12ImageBoard board) {
		dao.imageBoardUpdate(board);		
	}

	@Override
	public Exam12ImageBoard getImageBoardLike(int bno) {
		Exam12ImageBoard board = dao.imageBoardSelectByBno(bno);
		board.setBlikecount(board.getBlikecount() + 1);
		dao.imageBoardUpdateBlikecount(bno, board.getBlikecount());
		return board;
	}

//	@Override
//	public List<Exam12ImageBoard> getImageBoardSearch(int pageNo, int rowsPerPage, String btitle) {
//		List<Exam12ImageBoard> list = dao.imageBoardSelectPage(pageNo, rowsPerPage, btitle);
//		return list;
//	}


	

}
