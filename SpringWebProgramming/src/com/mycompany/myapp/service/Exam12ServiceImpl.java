package com.mycompany.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dao.Exam12Dao;
import com.mycompany.myapp.dto.Exam12Board;
import com.mycompany.myapp.dto.Exam12Member;

@Component
public class Exam12ServiceImpl implements Exam12Service {

	@Autowired
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

}
