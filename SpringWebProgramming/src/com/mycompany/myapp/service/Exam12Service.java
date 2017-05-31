package com.mycompany.myapp.service;

import java.util.List;

import com.mycompany.myapp.dto.Exam12Board;
import com.mycompany.myapp.dto.Exam12Member;

public interface Exam12Service {
	public void boardWrite(Exam12Board board);	
	public List<Exam12Board> boardListAll();
	public List<Exam12Board> boardListPage(int pageNo, int rowsPerPage);
	public int boardTotalRows();
	
	public void memberJoin(Exam12Member member);
	public List<Exam12Member> memberListPage(int pageNo, int rowsPerPage);
	public int memberTotalRows();
}
