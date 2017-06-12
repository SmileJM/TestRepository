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
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.myapp.dto.Exam12Board;
import com.mycompany.myapp.dto.Exam12ImageBoard;
import com.mycompany.myapp.dto.Exam12Member;
import com.mycompany.myapp.service.Exam12Service;

@Controller
public class Exam12DBController {
	private static final Logger LOGGER = LoggerFactory.getLogger(Exam12DBController.class);

	@Resource(name="exam12ServiceImpl3")
	private Exam12Service service;

	@Autowired
	private ServletContext servletContext;

	@RequestMapping("/jdbc/exam01")
	public String exam01() {
		Exam12Board board = new Exam12Board();
		board.setBtitle("제목");
		board.setBcontent("내용");
		board.setBwriter("다");
		board.setBpassword("12345");
		board.setBoriginalfilename("d.png");
		board.setBsavedfilename("d444555.png");
		board.setBfilecontent("image/png");

		service.boardWrite(board);
		return "redirect:/";
	}

	@RequestMapping(value = "/jdbc/exam02", method = RequestMethod.GET)
	public String exam02Get() {
		LOGGER.info("실행됨");
		return "jdbc/exam02";
	}

	@RequestMapping(value = "/jdbc/exam02", method = RequestMethod.POST)
	public String exam02Post(Exam12Board board) throws IllegalStateException, IOException {
		// board 라는 Command 객체 사용

		// 첨부 파일에 대한 정보를 컬럼값으로 설정
		board.setBoriginalfilename(board.getBattach().getOriginalFilename());
		board.setBfilecontent(board.getBattach().getContentType());
		String fileName = new Date().getTime() + "-" + board.getBoriginalfilename();
		board.setBsavedfilename(fileName);

		// 첨부 파일을 서버 로컬 시스템에 저장
		String realPath = servletContext.getRealPath("/WEB-INF/upload/");
		File file = new File(realPath + fileName);
		board.getBattach().transferTo(file);

		// 서비스 객체로 요청 처리
		service.boardWrite(board);

		return "redirect:/jdbc/exam05";
	}

	@RequestMapping(value = "/jdbc/exam03", method = RequestMethod.GET)
	public String exam03Get() {
		LOGGER.info("실행됨");
		return "jdbc/exam03";
	}

	@RequestMapping(value = "/jdbc/exam03", method = RequestMethod.POST)
	public String exam03Post(Exam12Member member) throws IllegalStateException, IOException {
		// board 라는 Command 객체 사용

		// 첨부 파일에 대한 정보를 컬럼값으로 설정
		member.setMoriginalfilename(member.getMattach().getOriginalFilename());
		member.setMfilecontent(member.getMattach().getContentType());
		String fileName = new Date().getTime() + "-" + member.getMoriginalfilename();
		member.setMsavedfilename(fileName);

		// 첨부 파일을 서버 로컬 시스템에 저장
		String realPath = servletContext.getRealPath("/WEB-INF/upload/");
		File file = new File(realPath + fileName);
		member.getMattach().transferTo(file);

		// 서비스 객체로 요청 처리
		service.memberJoin(member);

		return "redirect:/";
	}

	@RequestMapping("/jdbc/exam04")
	public String exam04(Model model) {
		List<Exam12Board> list = service.boardListAll();
		model.addAttribute("list", list);

		return "jdbc/exam04";
	}

	@RequestMapping("/jdbc/exam05")
	public String exam05(@RequestParam(defaultValue = "1") int pageNo, Model model) {
		// 한 페이지를 구성하는 행 수
		int rowsPerPage = 10;
		// 한 그룹을 구성하는 페이지 수
		int pagesPerGroup = 7;
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
		List<Exam12Board> list = service.boardListPage(pageNo, rowsPerPage);
		// view 로 넘겨줄 데이터
		model.addAttribute("list", list);
		model.addAttribute("pagesPerGroup", pagesPerGroup);
		model.addAttribute("totalPageNo", totalPageNo);
		model.addAttribute("totalGroupNo", totalGroupNo);
		model.addAttribute("groupNo", groupNo);
		model.addAttribute("startPageNo", startPageNo);
		model.addAttribute("endPageNo", endPageNo);
		model.addAttribute("pageNo", pageNo);
		// view 이름 리턴
		return "jdbc/exam05";
	}

	@RequestMapping("/jdbc/exam05Detail")
	public String exam05Detail(int bno, Model model) {
		Exam12Board board = service.getBoard(bno);
		model.addAttribute("board", board);
		return "jdbc/exam05Detail";
	}

	@RequestMapping("/jdbc/exam05CheckBpassword")
	public String exam05CheckBpassword(int bno, String bpassword, Model model) {
		String result = service.boardCheckBpassword(bno, bpassword);
		model.addAttribute("result", result);
		return "jdbc/exam05CheckBpassword";
	}

	@RequestMapping(value = "/jdbc/exam05Update", method = RequestMethod.GET)
	public String exam05UpdateGet(int bno, Model model) {
		Exam12Board board = service.getBoard(bno);
		model.addAttribute("board", board);
		return "jdbc/exam05Update";
	}

	@RequestMapping(value = "/jdbc/exam05Update", method = RequestMethod.POST)
	public String exam05UpdatePost(Exam12Board board) throws IllegalStateException, IOException {
		// 첨부 파일이 변경되었는지 검사
		if (!board.getBattach().isEmpty()) {
			// 첨부 파일에 대한 정보를 컬럼값으로 설정
			board.setBoriginalfilename(board.getBattach().getOriginalFilename());
			board.setBfilecontent(board.getBattach().getContentType());
			String fileName = new Date().getTime() + "-" + board.getBoriginalfilename();
			board.setBsavedfilename(fileName);

			// 첨부 파일을 서버 로컬 시스템에 저장
			String realPath = servletContext.getRealPath("/WEB-INF/upload/");
			File file = new File(realPath + fileName);
			board.getBattach().transferTo(file);
		}
		// 게시물 수정 처리
		service.boardUpdate(board);
		return "redirect:/jdbc/exam05Detail?bno=" + board.getBno();
	}

	@RequestMapping("/jdbc/exam05Delete")
	public String exam05Delete(int bno) {
		service.boardDelete(bno);
		return "redirect:/jdbc/exam05";
	}

	@RequestMapping("/jdbc/exam06")
	public String exam06(@RequestParam(defaultValue = "1") int pageNo, Model model) {
		// 한 페이지를 구성하는 행 수
		int rowsPerPage = 10;
		// 한 그룹을 구성하는 페이지 수
		int pagesPerGroup = 7;
		// 총 행 수
		int totalRows = service.memberTotalRows(); // DB 쿼리해야함
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
		List<Exam12Member> list = service.memberListPage(pageNo, rowsPerPage);
		// view 로 넘겨줄 데이터
		model.addAttribute("list", list);
		model.addAttribute("pagesPerGroup", pagesPerGroup);
		model.addAttribute("totalPageNo", totalPageNo);
		model.addAttribute("totalGroupNo", totalGroupNo);
		model.addAttribute("groupNo", groupNo);
		model.addAttribute("startPageNo", startPageNo);
		model.addAttribute("endPageNo", endPageNo);
		model.addAttribute("pageNo", pageNo);
		// view 이름 리턴

		return "jdbc/exam06";
	}

	@RequestMapping("/jdbc/exam06Detail")
	public String exam06Detail(String mid, Model model) {
		Exam12Member member = service.getMember(mid);
		model.addAttribute("member", member);
		return "jdbc/exam06Detail";
	}

	@RequestMapping("/jdbc/exam06CheckMpassword")
	public String exam06CheckMpassword(String mid, String mpassword, Model model) {
		String result = service.memberCheckBpassword(mid, mpassword);
		model.addAttribute("result", result);
		return "jdbc/exam06CheckMpassword";
				
	}

	@RequestMapping(value = "/jdbc/exam06Update", method = RequestMethod.GET )
	public String exam06UpdateGet(String mid, Model model) {
		
		Exam12Member member = service.getMember(mid);
		model.addAttribute("member", member);
		return "jdbc/exam06Update";
	}
	@RequestMapping(value = "/jdbc/exam06Update", method = RequestMethod.POST)
	public String exam06UpdatePost(Exam12Member member) throws IllegalStateException, IOException {
		if (!member.getMattach().isEmpty()) {
			// 첨부 파일에 대한 정보를 컬럼값으로 설정
			member.setMoriginalfilename(member.getMattach().getOriginalFilename());
			member.setMfilecontent(member.getMattach().getContentType());
			String fileName = new Date().getTime() + "-" + member.getMoriginalfilename();
			member.setMsavedfilename(fileName);

			// 첨부 파일을 서버 로컬 시스템에 저장
			String realPath = servletContext.getRealPath("/WEB-INF/upload/");
			File file = new File(realPath + fileName);
			member.getMattach().transferTo(file);
		}
		// 게시물 수정 처리
		service.memberUpdate(member);
		return "redirect:/jdbc/exam06Detail?mid=" + member.getMid();
	}
	@RequestMapping("/jdbc/exam06Delete")
	public String exam06Delete(String mid) throws IOException {
		service.memberDelete(mid);
		
		return "redirect:/jdbc/exam06";
	}
	
	/*  -------------------------------------------------------------------
								ImageBoard
	-------------------------------------------------------------------  */
	@RequestMapping("/jdbc/exam07List")
	public String exam07(@RequestParam(defaultValue = "1") int pageNo, Model model) {
		// 한 페이지를 구성하는 행 수
		int rowsPerPage = 10;
		// 한 그룹을 구성하는 페이지 수
		int pagesPerGroup = 5;
		// 총 행 수
		int totalRows = service.imageBoardTotalRows(); // DB 쿼리해야함
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
		List<Exam12ImageBoard> list = service.imageBoardListPage(pageNo, rowsPerPage);
		// view 로 넘겨줄 데이터
		model.addAttribute("list", list);
		model.addAttribute("pagesPerGroup", pagesPerGroup);
		model.addAttribute("totalPageNo", totalPageNo);
		model.addAttribute("totalGroupNo", totalGroupNo);
		model.addAttribute("groupNo", groupNo);
		model.addAttribute("startPageNo", startPageNo);
		model.addAttribute("endPageNo", endPageNo);
		model.addAttribute("pageNo", pageNo);
		// view 이름 리턴
		return "jdbc/exam07List";
	}
	
	@RequestMapping(value = "/jdbc/exam07Write", method = RequestMethod.GET)
	public String exam07WriteGet() {
		LOGGER.info("실행됨");
		return "jdbc/exam07Write";
	}

	@RequestMapping(value = "/jdbc/exam07Write", method = RequestMethod.POST)
	public String exam07WritePost(Exam12ImageBoard board, HttpServletRequest req) throws IllegalStateException, IOException {

        // 파일의 정보 얻기
        String fileName = board.getBattach().getOriginalFilename();
        String contentType = board.getBattach().getContentType();
        long fileSize = board.getBattach().getSize();
        // 파일을 서버 하드 디스크에 저장
        // 실제 경로를 대입해줌
        String realPath = servletContext.getRealPath("/WEB-INF/upload/" + fileName);
        File file = new File(realPath);
        board.getBattach().transferTo(file);

        // 파일 데이터 저장
        board.setBoriginalfilename(fileName);
		String savedFileName = new Date().getTime() + "-" + board.getBoriginalfilename();
        board.setBsavedfilename(savedFileName);
        board.setBfilecontent(contentType);
        
		// 서비스 객체로 요청 처리
		service.imageBoardWrite(board);
		String no = req.getParameter("type");
		return "redirect:/jdbc/exam07List?type="+no;
	}
	
	@RequestMapping("/jdbc/exam07Detail")
	public String exam07Detail(int bno, Model model) {
		Exam12ImageBoard board = service.getImageBoard(bno);
		model.addAttribute("board", board);
		return "jdbc/exam07Detail";
	}
	@RequestMapping("/jdbc/exam07CheckBpassword")
	public String exam07CheckBpassword(int bno, String bpassword, Model model) {
		String result = service.imageBoardCheckBpassword(bno, bpassword);
		model.addAttribute("result", result);
		return "jdbc/exam07CheckBpassword";
	}
	@RequestMapping("/jdbc/exam07Delete")
	public String exam07Delete(int bno, HttpServletRequest req)  {
		service.imageBoardDelete(bno);
		String no = req.getParameter("type");
		return "redirect:/jdbc/exam07List?type="+no;
	}
	
	@RequestMapping(value = "/jdbc/exam07Update", method = RequestMethod.GET)
	public String exam07UpdateGet(int bno, Model model) {
		Exam12ImageBoard board = service.getImageBoard(bno);
		model.addAttribute("board", board);
		return "jdbc/exam07Update";
	}
	@RequestMapping("/jdbc/exam07Like")
	public String exam07Like(int bno, Model model) {
		Exam12ImageBoard board = service.getImageBoardLike(bno);
		System.out.println("like");
		model.addAttribute("board", board);
		return "jdbc/exam07Detail";
	}

	@RequestMapping(value = "/jdbc/exam07Update", method = RequestMethod.POST)
	public String exam07UpdatePost(Exam12ImageBoard board, HttpServletRequest req) throws IllegalStateException, IOException {
		// 첨부 파일이 변경되었는지 검사
		if (!board.getBattach().isEmpty()) {    
			// 첨부 파일을 서버 로컬 시스템에 저장
			String realPath = servletContext.getRealPath("/WEB-INF/upload/");
			String fileName = board.getBattach().getOriginalFilename();
			String savedFileName = new Date().getTime() + "-" + fileName;
			File file = new File(realPath + fileName);
			board.getBattach().transferTo(file);
			
			board.setBoriginalfilename(fileName);
			board.setBfilecontent(board.getBattach().getContentType());
			board.setBsavedfilename(savedFileName);
		}
		// 게시물 수정 처리
		service.imageBoardUpdate(board);
		String no = req.getParameter("type");
		return "redirect:/jdbc/exam07Detail?type="+no +"&bno=" + board.getBno();
	}
	@RequestMapping("/jdbc/exam07Image")
	public void download(HttpServletResponse response, @RequestHeader("User-Agent") String userAgent, int bno) throws IOException {       // 응답 HTTP 헤더행을 추가(3가지는 다 넣어주는게 좋음)
        // 1 파일 이름(옵션)
		Exam12ImageBoard board = service.getImageBoardImg(bno);	
		
		String fileName = board.getBoriginalfilename();
		System.out.println(fileName);
        String encodingFileName;
        if (userAgent.contains("MSIE") || userAgent.contains("Trident") || userAgent.contains("Edge")) {
            encodingFileName = URLEncoder.encode(fileName, "UTF-8");
            // fileName을 UTF-8로 인코딩한 바이트 배열을 16진수로 출력함         
        } else {
//            encodingFileName = new String(fileName.getBytes(), "UTF-8");
            encodingFileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        }

        response.addHeader("Content-Disposition", "attachment; filename=\"" + encodingFileName + "\"");
        // Disposition: 위치
        
        // 2 파일 종류(필수)
        response.addHeader("Content-type", "image/jpeg");
        // "image/jpeg" >  MIME
        
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
}
