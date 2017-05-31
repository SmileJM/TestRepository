package com.mycompany.myapp.dao;

import java.util.List;

import com.mycompany.myapp.dto.Exam12Board;
import com.mycompany.myapp.dto.Exam12Member;

public interface Exam12Dao {
	public int boardInsert(Exam12Board board);	
	// 리턴 받아야 할 값이 여러개이기 때문에 컬렉션으로 받음
	public List<Exam12Board	> boardSelectAll();
	public List<Exam12Board	> boardSelectPage(int pageNum, int rowsPerPage);
	public int boardCountAll();
	
	public String memberInsert(Exam12Member member);
	public List<Exam12Member	> memberSelectPage(int pageNum, int rowsPerPage);
	public int memberCountAll();
}
