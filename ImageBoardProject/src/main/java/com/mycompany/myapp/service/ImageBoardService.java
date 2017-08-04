package com.mycompany.myapp.service;

import java.util.List;

import com.mycompany.myapp.dto.BoardComment;
import com.mycompany.myapp.dto.Exam12Member;
import com.mycompany.myapp.dto.ImageBoard;

public interface ImageBoardService {
	
	
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
	
	// ImageBoard
	public void imageBoardWrite(ImageBoard imageboard);	
	public List<ImageBoard> imageBoardListPage(int pageNo, int rowsPerPage);
	public int imageBoardTotalRows();
	public ImageBoard getImageBoard(int bno);
	public ImageBoard getImageBoardImg(int bno);
	public String imageBoardCheckBpassword(int bno, String bpassword);
	public void imageBoardDelete(int bno);
	public void imageBoardUpdate(ImageBoard board);
	public ImageBoard getImageBoardLike(int bno);
	// public List<Exam12ImageBoard> getImageBoardSearch(int pageNo, int
	// rowsPerPage, String btitle);
	
	// BoardComment
		public void boardCommentWrite(BoardComment comment);
		public List<BoardComment>boardCommentList(int bcno);
}
