package com.mycompany.myapp.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycompany.myapp.dto.Exam12Board;
import com.mycompany.myapp.dto.Exam12Member;
import com.mycompany.myapp.service.Exam12Service;

@Controller
public class Exam12JdbcController {
	private static final Logger LOGGER = LoggerFactory.getLogger(Exam12JdbcController.class);

	@Autowired
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

		service.write(board);
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
		service.write(board);

		return "redirect:/";
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
		service.join(member);

		return "redirect:/";
	}
}
