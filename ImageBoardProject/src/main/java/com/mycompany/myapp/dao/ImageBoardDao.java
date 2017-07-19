package com.mycompany.myapp.dao;

import java.util.List;

import com.mycompany.myapp.dto.Exam12Member;
import com.mycompany.myapp.dto.ImageBoard;

public interface ImageBoardDao {
	
	
	// Member	
	public String memberInsert(Exam12Member member);
	public List<Exam12Member	> memberSelectPage(int pageNum, int rowsPerPage);
	public int memberCountAll();
	public Exam12Member memberSelectByMid(String mid);
	public void memberUpdate(Exam12Member member);
	public void memberDelete(String mid);
	
	// ImageBoard
	public int imageBoardInsert(ImageBoard imageboard);
	public List<ImageBoard> imageBoardSelectPage(int pageNo, int rowsPerPage);
	public int imageBoardCountAll();
	public ImageBoard imageBoardSelectByBno(int bno);
	public void imageBoardUpdateBhitcount(int bno, int bhitcount);
	public void imageBoardDelete(int bno);
	public void imageBoardUpdate(ImageBoard board);
	public void imageBoardUpdateBlikecount(int bno, int blikecount);
//	public List<Exam12ImageBoard> imageBoardSelectPage(int pageNo, int rowsPerPage, String btitle);
	


}
