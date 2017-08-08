package com.mycompany.myapp.service;

import java.util.List;

import com.mycompany.myapp.dto.Board;
import com.mycompany.myapp.dto.BoardComment;

public interface BoardService {
	// Board
	public void boardWrite(Board Board);	
	public List<Board> boardListPage(int pageNo, int rowsPerPage);
	public int boardTotalRows();
	public Board getBoard(int bno, String memail);
	public Board getBoard(int bno);
	public Board getBoardImg(int bno);
	public String boardCheckBpassword(int bno, String bpassword);
	public void boardDelete(int bno);
	public void boardUpdate(Board Board);
	public Board getBoardLike(int bno, String memail);
	public List<Board> boardSearch(String category, String bsearch);
	// public List<Exam12ImageBoard> getImageBoardSearch(int pageNo, int
	// rowsPerPage, String btitle);
	
	// BoardComment
	public void boardCommentWrite(BoardComment comment);
	public List<BoardComment>boardCommentList(int bcno);
	public String boardCommentCheckBpassword(int bcno, String bcpassword);
	public void boardCommentDelete(int bcno);
//	public void boardCommentUpdate(int bcno);
}
