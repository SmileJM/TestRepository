package com.mycompany.myapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	public int boardInsert(Exam12Board board) {
		int bno = -1;
		try {
			// JDBC Driver 클래스 로딩
			Class.forName("oracle.jdbc.OracleDriver");

			// 연결 문자열 작성
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";

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
	public List<Exam12Board> boardSelectAll() {
		List<Exam12Board> list = new ArrayList<>();
		try {
			// JDBC Driver 클래스 로딩
			Class.forName("oracle.jdbc.OracleDriver");

			// 연결 문자열 작성
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";

			// 연결 객체 얻기
			conn = DriverManager.getConnection(url, "iotuser", "iot12345");
			LOGGER.info("연결 성공");

			// SQL 작성
			String sql = "select bno, btitle, bwriter, bdate, bhitcount from board order by bno desc";

			PreparedStatement pstmt = conn.prepareStatement(sql);

			// SQL 문을 전송해서 실행
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Exam12Board board = new Exam12Board();

				board.setBno(rs.getInt("bno"));
				board.setBtitle(rs.getString("btitle"));
				board.setBwriter(rs.getString(3));
				board.setBdate(rs.getDate("bdate"));
				board.setBhitcount(rs.getInt("bhitcount"));
				list.add(board);
			}
			rs.close();
			pstmt.close();
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
		return list;
	}

	@Override
	public List<Exam12Board> boardSelectPage(int pageNum, int rowsPerPage) {
		List<Exam12Board> list = new ArrayList<>();
		try {
			// JDBC Driver 클래스 로딩
			Class.forName("oracle.jdbc.OracleDriver");

			// 연결 문자열 작성
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";

			// 연결 객체 얻기
			conn = DriverManager.getConnection(url, "iotuser", "iot12345");
			LOGGER.info("연결 성공");

			// SQL 작성
			String sql = "select * ";
			sql += "from ( ";
			sql += "	select rownum as r, bno, btitle, bwriter, bdate, bhitcount ";
			sql += "	from ( ";
			sql += "		select bno, btitle, bwriter, bdate, bhitcount from board order by bno desc ";
			sql += "	) ";
			sql += "	where rownum<=? ";
			sql += ") ";
			sql += "where r >=? ";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (pageNum * rowsPerPage));
			pstmt.setInt(2, ((pageNum - 1) * rowsPerPage + 1));

			// SQL 문을 전송해서 실행
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Exam12Board board = new Exam12Board();

				board.setBno(rs.getInt("bno"));
				board.setBtitle(rs.getString("btitle"));
				board.setBwriter(rs.getString(4));
				board.setBdate(rs.getDate("bdate"));
				board.setBhitcount(rs.getInt("bhitcount"));
				list.add(board);
			}
			rs.close();
			pstmt.close();
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
		return list;
	}

	@Override
	public int boardCountAll() {
		int count = 0;
		try {
			// JDBC Driver 클래스 로딩
			Class.forName("oracle.jdbc.OracleDriver");

			// 연결 문자열 작성
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";

			// 연결 객체 얻기
			conn = DriverManager.getConnection(url, "iotuser", "iot12345");
			LOGGER.info("연결 성공");

			// SQL 작성
			String sql = "select count(*) from board";

			PreparedStatement pstmt = conn.prepareStatement(sql);

			// SQL 문을 전송해서 실행
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
			rs.close();
			pstmt.close();
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
		return count;
	}

	@Override
	public Exam12Board boardSelectByBno(int bno) {
		Exam12Board board = null;
		try {
			// JDBC Driver 클래스 로딩
			Class.forName("oracle.jdbc.OracleDriver");

			// 연결 문자열 작성
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";

			// 연결 객체 얻기
			conn = DriverManager.getConnection(url, "iotuser", "iot12345");
			LOGGER.info("연결 성공");

			// SQL 작성
			String sql = "select * from board where bno=?";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			// SQL 문을 전송해서 실행
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				board = new Exam12Board();
				board.setBno(rs.getInt("bno"));
				board.setBtitle(rs.getString("btitle"));
				board.setBwriter(rs.getString("bwriter"));
				board.setBdate(rs.getDate("bdate"));
				board.setBpassword(rs.getString("bpassword"));
				board.setBhitcount(rs.getInt("bhitcount"));
				board.setBcontent(rs.getString("bcontent"));
				board.setBoriginalfilename(rs.getString("boriginalfilename"));
				board.setBsavedfilename(rs.getString("bsavedfilename"));
				board.setBfilecontent(rs.getString("bfilecontent"));
			}
			rs.close();
			pstmt.close();
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
		return board;
	}

	@Override
	public void boardUpdateBhitcount(int bno, int bhitcount) {
		try {
			// JDBC Driver 클래스 로딩
			Class.forName("oracle.jdbc.OracleDriver");

			// 연결 문자열 작성
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";

			// 연결 객체 얻기
			conn = DriverManager.getConnection(url, "iotuser", "iot12345");
			LOGGER.info("연결 성공");

			// SQL 작성
			String sql = "update board set bhitcount=? where bno=? ";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bhitcount);
			pstmt.setInt(2, bno);
			// SQL 문을 전송해서 실행
			pstmt.executeUpdate();
			pstmt.close();
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
	}

	@Override
	public void boardUpdate(Exam12Board board) {
		try {
			// JDBC Driver 클래스 로딩
			Class.forName("oracle.jdbc.OracleDriver");

			// 연결 문자열 작성
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";

			// 연결 객체 얻기
			conn = DriverManager.getConnection(url, "iotuser", "iot12345");

			// SQL 작성
			String sql;
			if (board.getBoriginalfilename() != null) {
				sql = "update board set btitle=?, bcontent=?, bpassword=?, bdate=sysdate, boriginalfilename=?, bsavedfilename=?, bfilecontent=? where bno=? ";
			} else {
				sql = "update board set btitle=?, bcontent=?, bpassword=?, bdate=sysdate where bno=? ";
			}

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getBtitle());
			pstmt.setString(2, board.getBcontent());
			pstmt.setString(3, board.getBpassword());
			if (board.getBoriginalfilename() != null) {
				pstmt.setString(4, board.getBoriginalfilename());
				pstmt.setString(5, board.getBsavedfilename());
				pstmt.setString(6, board.getBfilecontent());
				pstmt.setInt(7, board.getBno());
			} else {
				pstmt.setInt(4, board.getBno());
			}
			// SQL 문을 전송해서 실행
			pstmt.executeUpdate();
			pstmt.close();
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

	}

	@Override
	public void boardDelete(int bno) {
		try {
			// JDBC Driver 클래스 로딩
			Class.forName("oracle.jdbc.OracleDriver");

			// 연결 문자열 작성
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";

			// 연결 객체 얻기
			conn = DriverManager.getConnection(url, "iotuser", "iot12345");

			// SQL 작성
			String sql = "delete from board where bno=? ";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);

			// SQL 문을 전송해서 실행
			pstmt.executeUpdate();
			pstmt.close();
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

	}

	// ---------------------------------------------------------------------------------------------
	@Override
	public String memberInsert(Exam12Member member) {
		String mid = null;
		try {
			// JDBC Driver 클래스 로딩
			Class.forName("oracle.jdbc.OracleDriver");
			// 연결 문자열 작성
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
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

	@Override
	public List<Exam12Member> memberSelectPage(int pageNum, int rowsPerPage) {
		List<Exam12Member> list = new ArrayList<>();
		try {
			// JDBC Driver 클래스 로딩
			Class.forName("oracle.jdbc.OracleDriver");
			
			// 연결 문자열 작성
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			// 연결 객체 얻기
			conn = DriverManager.getConnection(url, "iotuser", "iot12345");
			// SQL 작성
			String sql = "select * ";
			sql += "from ( ";
			sql += "	select rownum as r, mid, mname, mage, maddress ";
			sql += "	from (select mid, mname, mage, maddress from member)  ";
			sql += "	where rownum<=? ";
			sql += "	order by mid asc ";
			sql += ") ";
			sql += "where r>=?";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (pageNum * rowsPerPage));
			pstmt.setInt(2, ((pageNum - 1) * rowsPerPage + 1));
			// SQL 문을 전송해서 실행
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Exam12Member member = new Exam12Member();
				member.setMid(rs.getString("mid"));
				member.setMname(rs.getString("mname"));
				member.setMage(rs.getInt("mage"));
				member.setMaddress(rs.getString("maddress"));
				list.add(member);
			}
			rs.close();
			pstmt.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public int memberCountAll() {
		int count = 0;
		try {
			// JDBC Driver 클래스 로딩
			Class.forName("oracle.jdbc.OracleDriver");			
			// 연결 문자열 작성
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			// 연결 객체 얻기
			conn = DriverManager.getConnection(url, "iotuser", "iot12345");
			// SQL 작성
			String sql = "select count(*) from member ";

			PreparedStatement pstmt = conn.prepareStatement(sql);

			// SQL 문을 전송해서 실행
			ResultSet rs = pstmt.executeQuery();
			rs.next();

			count = rs.getInt(1);

			rs.close();
			pstmt.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	@Override
	public Exam12Member memberSelectByMid(String mid) {
		Exam12Member member = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");			
			LOGGER.info("드라이버 로드 성공");
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";			
			conn = DriverManager.getConnection(url, "iotuser", "iot12345");
			
			String sql = "select * from member where mid=? ";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				member = new Exam12Member();
				member.setMid(rs.getString("mid"));
				member.setMname(rs.getString("mname"));
				member.setMpassword(rs.getString("mpassword"));
				member.setMdate(rs.getDate("mdate"));
				member.setMtel(rs.getString("mtel"));
				member.setMemail(rs.getString("memail"));
				member.setMage(rs.getInt("mage"));
				member.setMaddress(rs.getString("maddress"));
				member.setMoriginalfilename(rs.getString("moriginalfilename"));
				member.setMsavedfilename(rs.getString("msavedfilename"));
				member.setMfilecontent(rs.getString("mfilecontent"));
			}
			rs.close();
			pstmt.close();
		} catch (ClassNotFoundException e) {		
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return member;
	}

	@Override
	public void memberUpdate(Exam12Member member) {
		try {
			Class.forName("oracle.jdbc.OracleDriver");			
			LOGGER.info("드라이버 로드 성공");
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";			
			conn = DriverManager.getConnection(url, "iotuser", "iot12345");
			
			String sql;
			if (member.getMoriginalfilename() != null) {
				sql = "update member set mname=?, mpassword=?, mdate=sysdate, mtel=?, memail=?, mage=?, maddress=?, moriginalfilename=?, msavedfilename=?, mfilecontent=? ";			
				sql += "where mid=? ";
			} else {
				sql = "update member set mname=?, mpassword=?, mdate=sysdate, mtel=?, memail=?, mage=?, maddress=? ";			
				sql += "where mid=? ";
			}
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMname());
			pstmt.setString(2, member.getMpassword());
			pstmt.setString(3, member.getMtel());
			pstmt.setString(4, member.getMemail());
			pstmt.setInt(5, member.getMage());
			pstmt.setString(6, member.getMaddress());
			if (member.getMoriginalfilename() != null) {
				pstmt.setString(7, member.getMoriginalfilename());
				pstmt.setString(8, member.getMsavedfilename());
				pstmt.setString(9, member.getMfilecontent());
				pstmt.setString(10, member.getMid());
			}else {
				pstmt.setString(7, member.getMid());
			}
			
			pstmt.executeUpdate();		
			pstmt.close();
			LOGGER.info("업데이트 성공");
		} catch (ClassNotFoundException e) {		
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void memberDelete(String mid) {
		try {
			Class.forName("oracle.jdbc.OracleDriver");			
			LOGGER.info("드라이버 로드 성공");
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";			
			conn = DriverManager.getConnection(url, "iotuser", "iot12345");
			
			String sql = "delete from member where mid=? ";
			
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			
			pstmt.executeUpdate();		
			pstmt.close();
			LOGGER.info("업데이트 성공");
		} catch (ClassNotFoundException e) {		
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}

	public static void main(String[] args) {
		Exam12DaoImpl test = new Exam12DaoImpl();
		// for (int i = 1; i <= 100; i++) {
		// Exam12Board board = new Exam12Board();
		// board.setBtitle("제목" + i);
		// board.setBcontent("내용" + i);
		// board.setBwriter("홍길동");
		// board.setBpassword("12345");
		// board.setBoriginalfilename("a.png");
		// board.setBsavedfilename("a444555.png");
		// board.setBfilecontent("image/png");
		// int bno = test.boardInsert(board);
		// LOGGER.info("추가된 행의 bno: " + bno);
		// }
		// List<Exam12Board> list = test.boardSelectPage(2, 10);
		// for (Exam12Board data : list) {
		// LOGGER.info(data.getBtitle());
		// }

//		 for(int i=1; i<=100; i++) {
//		 Exam12Member member = new Exam12Member();
//		 member.setMid("smile" + i);
//		 member.setMname("홍길동" + i);
//		 member.setMpassword("12345");
//		 member.setMtel("010-1234-5678");
//		 member.setMemail(i+"daum@naver.com");
//		 member.setMage(23);
//		 member.setMaddress("서울");
//		 member.setMoriginalfilename("a.png");
//		 member.setMsavedfilename("a.png");
//		 member.setMfilecontent("image/png");
//		 test.memberInsert(member);
//		 }
	}
}
