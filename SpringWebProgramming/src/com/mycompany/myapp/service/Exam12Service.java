package com.mycompany.myapp.service;

import java.util.List;

import com.mycompany.myapp.dto.Exam12Board;
import com.mycompany.myapp.dto.Exam12Member;

public interface Exam12Service {
	public void boardWrite(Exam12Board board);	
	public List<Exam12Board> boardListAll();
	public List<Exam12Board> boardListPage(int pageNo, int rowsPerPage);
	public int boardTotalRows();
	public Exam12Board getBoard(int bno);
	public String boardCheckBpassword(int bno, String bpassword);
	public void boardUpdate(Exam12Board board);
	public void boardDelete(int bno);
	
	// Member
	public void memberJoin(Exam12Member member);
	public List<Exam12Member> memberListPage(int pageNo, int rowsPerPage);
	public int memberTotalRows();
	// 상세보기
	public Exam12Member getMember(String mid);
	// 수정하기
	public String memberCheckBpassword(String mid, String mpassword);
	public void memberUpdate(Exam12Member member);
	// 삭제하기
	public void memberDelete(String mid);
	
	

	

}
