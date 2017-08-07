package com.mycompany.myapp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mycompany.myapp.dto.Board;
import com.mycompany.myapp.dto.BoardComment;
import com.mycompany.myapp.dto.Member;
import com.mycompany.myapp.service.BoardService;

@Controller
@SessionAttributes({ "member" })
public class BoardController {
	private static final Logger LOGGER = LoggerFactory.getLogger(BoardController.class);
	private Member member;
	
	public void session(Model model) {
		member = new Member();		
		member.setMemail("admin");
		member.setMname("관리자2");
		model.addAttribute("member", member);
	}

	@Resource(name = "boardServiceImpl")
	private BoardService service;

	@Autowired
	private ServletContext servletContext;

	/*
	 * 
	 * boardList
	 * 
	 */
	@RequestMapping("/board/boardList")
	public String boardList(@RequestParam(defaultValue = "1") int pageNo, Model model) {
		// 한 페이지를 구성하는 행 수
		int rowsPerPage = 10;
		// 한 그룹을 구성하는 페이지 수
		int pagesPerGroup = 5;
		// 총 행 수
		int totalRows = service.boardTotalRows(); // DB 쿼리해야함
		// 총 페이지 수
		int totalPageNo = (totalRows / rowsPerPage) + ((totalRows % rowsPerPage != 0) ? 1 : 0);
		// 총 그룹 수
		int totalGroupNo = (totalPageNo / pagesPerGroup) + ((totalPageNo % pagesPerGroup != 0) ? 1 : 0);
		// 현재 그룹 번호
		int groupNo = (pageNo - 1) / pagesPerGroup + 1;
		// 현재 그룹의 시작 페이지 번호
		int startPageNo = (groupNo - 1) * pagesPerGroup + 1;
		// 현재 그룹의 마지막 페이지 번호
		int endPageNo = startPageNo + pagesPerGroup - 1;
		if (groupNo == totalGroupNo) {
			endPageNo = totalPageNo;
		}
		// 현재 페이지의 데이터 가져오기
		List<Board> list = service.boardListPage(pageNo, rowsPerPage);
		// view 로 넘겨줄 데이터
		model.addAttribute("list", list);
		model.addAttribute("pagesPerGroup", pagesPerGroup);
		model.addAttribute("totalPageNo", totalPageNo);
		model.addAttribute("totalGroupNo", totalGroupNo);
		model.addAttribute("groupNo", groupNo);
		model.addAttribute("startPageNo", startPageNo);
		model.addAttribute("endPageNo", endPageNo);
		model.addAttribute("pageNo", pageNo);

		session(model);
		// view 이름 리턴
		return "board/boardList";
	}

	/*
	 * 
	 * boardWrite
	 * 
	 */
	@RequestMapping(value = "/board/boardWrite", method = RequestMethod.GET)
	public String boardWriteGet() {
		LOGGER.info("실행됨");
		return "board/boardWrite";
	}

	@RequestMapping(value = "/board/boardWrite", method = RequestMethod.POST)
	public String boardWritePost(Board Board, HttpServletRequest req) throws IllegalStateException, IOException {

		// 파일의 정보 얻기
		String fileName = Board.getBattach().getOriginalFilename();
		String contentType = Board.getBattach().getContentType();
		// long fileSize = board.getBattach().getSize();
		// 파일을 서버 하드 디스크에 저장
		// 실제 경로를 대입해줌
		String realPath = servletContext.getRealPath("/WEB-INF/upload/" + fileName);
		File file = new File(realPath);
		System.out.println("fileName:" + fileName);
		if (fileName != "") {
			boolean tt = file.mkdirs();
			System.out.println(tt);
			Board.getBattach().transferTo(file);

			// 파일 데이터 저장
			Board.setBoriginalfilename(fileName);
			String savedFileName = new Date().getTime() + "-" + Board.getBoriginalfilename();
			Board.setBsavedfilename(savedFileName);
			Board.setBfilecontent(contentType);
		} else {
			Board.setBoriginalfilename("");
			Board.setBsavedfilename("");
			Board.setBfilecontent("");
		}
		// 서비스 객체로 요청 처리
		service.boardWrite(Board);

		return "redirect:/board/boardList";
	}

	/*
	 * 
	 * boardDetail
	 * 
	 */
	@RequestMapping("/board/boardDetail")
	public String boardDetail(int bno, int pageNo, Model model) {
		Board board = null;
		
		if (member != null) {
			board = service.getBoard(bno, member.getMemail());
		} else {
			board = service.getBoard(bno);
		}
		String content = board.getBcontent();
		content = content.replace("<", "&lt;");
		content = content.replace(">", "&gt;");
		content = content.replace("  ", "&nbsp;&nbsp;");
		content = content.replace("\n", "<br/>");
		board.setBcontent(content);

		List<BoardComment> list = service.boardCommentList(bno);

		// view 로 넘겨줄 데이터
		model.addAttribute("list", list);
		model.addAttribute("board", board);
		model.addAttribute("pageNo", pageNo);

		session(model);

		return "board/boardDetail";
	}

	@RequestMapping("/board/boardCheckBpassword")
	public String boardCheckBpassword(int bno, String bpassword, Model model) {
		String result = service.boardCheckBpassword(bno, bpassword);
		model.addAttribute("result", result);
		return "board/boardCheckBpassword";
	}

	@RequestMapping("/board/boardDelete")
	public String boardDelete(int bno, HttpServletRequest req) {
		service.boardDelete(bno);
		return "redirect:/board/boardList";
	}

	@RequestMapping(value = "/board/boardUpdate", method = RequestMethod.GET)
	public String boardUpdateGet(int bno, String memail, int pageNo, Model model) {
		Board board = service.getBoard(bno, memail);
		model.addAttribute("board", board);
		model.addAttribute("pageNo", pageNo);
		return "board/boardUpdate";
	}

	@RequestMapping("/board/boardLike")
	public String boardLike(int bno, int pageNo, String memail, Model model) {
		// public String boardLike(int bno, int pageNo, Model model) {
		Board board = service.getBoardLike(bno, memail);
		model.addAttribute("board", board);
		session(model);
		// return "redirect:/board/boardDetail?bno="+bno + "&pageNo=" +pageNo;
		return "redirect:/board/boardDetail?bno=" + bno + "&pageNo=" + pageNo + "&memail=" + memail;
	}

	@RequestMapping(value = "/board/boardUpdate", method = RequestMethod.POST)
	public String boardUpdatePost(Board Board, HttpServletRequest req, int pageNo)
			throws IllegalStateException, IOException {
		// 첨부 파일이 변경되었는지 검사
		if (!Board.getBattach().isEmpty()) {
			// 첨부 파일을 서버 로컬 시스템에 저장
			String realPath = servletContext.getRealPath("/WEB-INF/upload/");
			String fileName = Board.getBattach().getOriginalFilename();
			String savedFileName = new Date().getTime() + "-" + fileName;
			File file = new File(realPath + fileName);
			Board.getBattach().transferTo(file);

			Board.setBoriginalfilename(fileName);
			Board.setBfilecontent(Board.getBattach().getContentType());
			Board.setBsavedfilename(savedFileName);
		}
		// 게시물 수정 처리
		service.boardUpdate(Board);
		System.out.println(pageNo);
		return "redirect:/board/boardDetail?bno=" + Board.getBno() + "&pageNo=" + pageNo;
	}

	@RequestMapping("/board/boardImage")
	public void download(HttpServletResponse response, @RequestHeader("User-Agent") String userAgent, int bno)
			throws IOException { // 응답 HTTP 헤더행을 추가(3가지는 다 넣어주는게 좋음)
		// 1 파일 이름(옵션)
		Board Board = service.getBoardImg(bno);

		String fileName = Board.getBoriginalfilename();
		System.out.println(fileName);
		String encodingFileName;
		if (userAgent.contains("MSIE") || userAgent.contains("Trident") || userAgent.contains("Edge")) {
			encodingFileName = URLEncoder.encode(fileName, "UTF-8");
			// fileName을 UTF-8로 인코딩한 바이트 배열을 16진수로 출력함
		} else {
			// encodingFileName = new String(fileName.getBytes(), "UTF-8");
			encodingFileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
		}

		response.addHeader("Content-Disposition", "attachment; filename=\"" + encodingFileName + "\"");
		// Disposition: 위치

		// 2 파일 종류(필수)
		response.addHeader("Content-type", "image/jpeg");
		// "image/jpeg" > MIME

		// 3 파일 크기(옵션)m
		File file = new File(servletContext.getRealPath("/WEB-INF/upload/" + fileName));
		long fileSize = file.length();
		response.addHeader("Content-Length", String.valueOf(fileSize));

		// 응답 HTTP 본문에 파일 데이터 추가
		OutputStream os = response.getOutputStream();
		FileInputStream fis = new FileInputStream(file);
		FileCopyUtils.copy(fis, os);
		// spring 에서는 자바에서 했던 복잡한 방법으로 파일을 카피하는 방식이 아닌 FileCopyUtils 클래스 제공
		// fis 에서 읽고, os로 출력
		os.flush();
		fis.close();
		os.close();
	}

	/*
	 * 
	 * BoardComment
	 * 
	 */
	@RequestMapping(value = "/board/boardCommentWrite", method = RequestMethod.GET)
	public String boardCommentWrite() {
		LOGGER.info("실행됨");
		return "board/boardCommentWrite";
	}

	@RequestMapping(value = "/board/boardCommentWrite", method = RequestMethod.POST)
	public String boardCommentWrite(BoardComment comment, int pageNo) {
		service.boardCommentWrite(comment);
		LOGGER.info("getBccomment " + comment.getBccomment());
		LOGGER.info("getBcno " + comment.getBcno());
		LOGGER.info("getBcpassword " + comment.getBcpassword());
		LOGGER.info("getBno " + comment.getBno());
		LOGGER.info("getBcwriter " + comment.getBcwriter());

		return "redirect:/board/boardDetail?bno=" + comment.getBno() + "&pageNo=" + pageNo;
	}
}
