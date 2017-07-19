package com.mycompany.myapp.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.mycompany.myapp.dao.ImageBoardDao;
import com.mycompany.myapp.dto.Exam12Member;
import com.mycompany.myapp.dto.ImageBoard;

@Component
public class ImageBoardServiceImpl implements ImageBoardService {

	@Resource(name = "imageBoardDaoImpl")
	private ImageBoardDao dao;

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
	public void imageBoardWrite(ImageBoard imageboard) {
		dao.imageBoardInsert(imageboard);
	}

	@Override
	public List<ImageBoard> imageBoardListPage(int pageNo, int rowsPerPage) {
		List<ImageBoard> list = dao.imageBoardSelectPage(pageNo, rowsPerPage);
		return list;
	}

	@Override
	public int imageBoardTotalRows() {
		int count = dao.imageBoardCountAll();
		return count;
	}

	@Override
	public ImageBoard getImageBoard(int bno) {
		ImageBoard board = dao.imageBoardSelectByBno(bno);
		board.setBhitcount(board.getBhitcount() + 1);
		dao.imageBoardUpdateBhitcount(bno, board.getBhitcount());
		return board;
	}

	@Override
	public ImageBoard getImageBoardImg(int bno) {
		ImageBoard board = dao.imageBoardSelectByBno(bno);
		return board;
	}

	@Override
	public String imageBoardCheckBpassword(int bno, String bpassword) {
		String result = "fail";
		ImageBoard board = dao.imageBoardSelectByBno(bno);
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
	public void imageBoardUpdate(ImageBoard board) {
		dao.imageBoardUpdate(board);
	}

	@Override
	public ImageBoard getImageBoardLike(int bno) {
		ImageBoard board = dao.imageBoardSelectByBno(bno);
		board.setBlikecount(board.getBlikecount() + 1);
		dao.imageBoardUpdateBlikecount(bno, board.getBlikecount());
		return board;
	}
}
