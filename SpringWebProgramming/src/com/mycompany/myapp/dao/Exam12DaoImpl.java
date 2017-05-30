package com.mycompany.myapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dto.Exam12Board;
import com.mycompany.myapp.dto.Exam12Member;

@Component
public class Exam12DaoImpl implements Exam12Dao {
	private static final Logger LOGGER = LoggerFactory.getLogger(Exam12DaoImpl.class);
	Connection conn;

	@Override
	public int insert1(Exam12Board board) {
		int bno = -1;
		try {
			// JDBC Driver 클래스 로딩
			Class.forName("oracle.jdbc.OracleDriver");

			// 연결 문자열 작성
			String url = "jdbc:oracle:thin:@192.168.3.22:1521:orcl";

			// 연결 객체 얻기
			conn = DriverManager.getConnection(url, "iotuser", "iot12345");
			LOGGER.info("연결 성공");

			// SQL 작성
			String sql = "insert into board ";
			sql += "(bno, BTITLE, BCONTENT, BWRITER, BDATE, BPASSWORD, BHITCOUNT, BORIGINALFILENAME, BSAVEDFILENAME, BFILECONTENT) ";
			sql += "values ";
			// 매개변수화된 SQL 문
			sql += "(board_bno_seq.nextval, ?, ?, ?, sysdate, ?, 0, ?, ?, ?) ";

			// SQL 문을 전송해서 실행, 완전한 SQL 문일 때만 사용 가능한
			// Statement stmt = conn.createStatement();
			// executeUpdate - DB의 상태를 변경해라
			// stmt.executeUpdate(sql);
			// stmt.close();

			// Statement.RETURN_GENERATED_KEYS - 구문실행 후 자동으로 생성된 키를 리턴해 달라
			// (mysql, mssql은 되지만 oracle은 안됨)
			// 테이블 정의시 컬럼의 속성으로 자동 증가를 지정할 수 있는 DB일 경우(MySQL, MSSQL)
			// PreparedStatement pstmt = conn.prepareStatement(sql,
			// Statement.RETURN_GENERATED_KEYS);
			// Oracle일 경우 Sequence 외부 객체로 자동 증가값을 얻기 때문에 다음과 같이 지정
			// oracle의 경우 conn.prepareStatement(sql, new String[] {"bno"});
			PreparedStatement pstmt = conn.prepareStatement(sql, new String[] { "bno" });

			pstmt.setString(1, board.getBtitle());
			pstmt.setString(2, board.getBcontent());
			pstmt.setString(3, board.getBwriter());
			pstmt.setString(4, board.getBpassword());
			pstmt.setString(5, board.getBoriginalfilename());
			pstmt.setString(6, board.getBsavedfilename());
			pstmt.setString(7, board.getBfilecontent());

			// SQL 문을 전송해서 실행
			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
			// 1번째 컬럼의 값 읽기
			bno = rs.getInt(1);

			pstmt.close();
			LOGGER.info("행 추가 성공");

			// 자동 커밋이 이루어짐

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 연결 끊기
			try {
				conn.close();
				LOGGER.info("연결 끊기");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return bno;
	}

	@Override
	public String insert2(Exam12Member member) {
		String mid = null;
		try {
			// JDBC Driver 클래스 로딩
			Class.forName("oracle.jdbc.OracleDriver");

			// 연결 문자열 작성
			String url = "jdbc:oracle:thin:@192.168.3.113:1521:orcl";

			// 연결 객체 얻기
			conn = DriverManager.getConnection(url, "iotuser", "iot12345");
			LOGGER.info("연결 성공");

			// SQL 작성
			String sql = "insert into member ";
			sql += "(MID, MNAME, MPASSWORD, MDATE, MTEL, MEMAIL, MAGE, MADDRESS, MORIGINALFILENAME, MSAVEDFILENAME, MFILECONTENT) ";
			sql += "values ";
			// 매개변수화된 SQL 문
			sql += "(?, ?, ?, sysdate, ?, ?, ?, ?, ?, ?, ?) ";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			
//			LOGGER.info(member.getMid());
//			LOGGER.info(member.getMname());
//			LOGGER.info(member.getMpassword());
//			LOGGER.info(member.getMtel());
//			LOGGER.info(member.getMemail());
//			LOGGER.info(member.getMage()+"");
//			LOGGER.info(member.getMaddress());
//			LOGGER.info(member.getMoriginalfilename());
//			LOGGER.info(member.getMsavedfilename());
//			LOGGER.info(member.getMfilecontent());
			pstmt.setString(1, member.getMid());
			pstmt.setString(2, member.getMname());
			pstmt.setString(3, member.getMpassword());
			pstmt.setString(4, member.getMtel());
			pstmt.setString(5, member.getMemail());
			pstmt.setInt(6, member.getMage());
			pstmt.setString(7, member.getMaddress());
			pstmt.setString(8, member.getMoriginalfilename());
			pstmt.setString(9, member.getMsavedfilename());
			pstmt.setString(10, member.getMfilecontent());

			// SQL 문을 전송해서 실행
			pstmt.executeUpdate();
			
			
			// 1번째 컬럼의 값 읽기
			mid = member.getMid();

			pstmt.close();
			LOGGER.info("행 추가 성공");

			// 자동 커밋이 이루어짐

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 연결 끊기
			try {
				conn.close();
				LOGGER.info("연결 끊기");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return mid;
	}

	public static void main(String[] args) {
		Exam12DaoImpl test = new Exam12DaoImpl();
		Exam12Board board = new Exam12Board();
		board.setBtitle("제목");
		board.setBcontent("내용");
		board.setBwriter("다");
		board.setBpassword("12345");
		board.setBoriginalfilename("d.png");
		board.setBsavedfilename("d444555.png");
		board.setBfilecontent("image/png");
		int bno = test.insert1(board);
		LOGGER.info("추가된 행의 bno: " + bno);
		Exam12Member member = new Exam12Member();
		String mid = test.insert2(member);
		LOGGER.info("추가된 행의 bno: " + mid);


	}

}
