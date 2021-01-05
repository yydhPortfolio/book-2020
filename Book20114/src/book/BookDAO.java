package book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BookDAO {
	private static BookDAO instance = new BookDAO(); // BookDAO의 instance를 만들어준다

	private BookDAO() { // 기본실행객체
	}

	public static BookDAO getInstance() { // getInstance 객체를 실행시키면 BookDAO의 instance를 리턴
		return instance;
	}

	Connection conn = null; // 데이터베이스와 연결하는 객체 Connection 초기화
	PreparedStatement pstmt = null; // PreparedStatement는 sql문을 입력받아 prepareStatement() 메소드를 이용해서 메소드에 sql문을 입력한뒤 문장을 컴파일
	ResultSet rs = null; // 테이터베이스에서 쿼리문을 실행하면서 발생한 결과값을 받는것

	private static ArrayList<BookVO> bookList = new ArrayList<BookVO>(); // 테이블에 있는 책 정보 전체를 받기위한 BookVO ArrayList를 만든다.

	public Connection getConnection() { // oracleDB에 연결시켜주는 객체
		String url = "jdbc:oracle:thin:@localhost:1521:xe"; // DB 주소
		String user = "hr"; // DB 유저이름
		String password = "1234"; // DB 비밀번호
		conn = null; // Connection객체를 초기화
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 동적으로 oracleDriver 이라는 클래스를 로드
			conn = DriverManager.getConnection(url, user, password); // 데이터베이스에 주소, 유저이름, 비밀번호를 입력시켜 DB에 연결
			System.out.println("오라클 접속 성공");
		} catch (Exception e) {
			// try 안에있는 코드의 오류를 잡아서
			e.printStackTrace(); // 에러 메세지의 발생 원인 찾아 단계별로 에러를 출력
		}

		return conn; // Connection된 객체 conn을 리턴해준다.
	}
	
	public ArrayList<BookVO> selectBooks() { // book_tbl 테이블에 있는 모든 책들의 값들을 ArrayList에 넣어서 리턴하는 객체
		bookList = new ArrayList<BookVO>(); // 테이블에 있는 책 전체를 받기위한 BookVO ArrayList를 만든다.

		try { // 오라클DB에 연동하는 객체들
			conn = instance.getConnection(); // 오라클DB 에 연결된 Connection 객체를 conn에 받아온다.
			String sql = "select * from BOOK_TBL ORDER BY bCode"; // 실행시킬 sql문을 sql에 넣어준다.
			pstmt = conn.prepareStatement(sql); // sql문을 오라클DB 에 연결된 Connection에 연결하여 실행
			rs = pstmt.executeQuery(); // 실행결과로 ResultSet객체의 값을 리턴받아 rs에 저장
			while (rs.next()) { // rs가 null이 나올때까지 while을 돈다. rs.next row 한줄을 가져온다
				BookVO vo = new BookVO(); // 책 한권의 정보들을 저장할 BookVO vo 를 생성한다.
				vo.setBcode(rs.getInt("bcode")); // vo안에 리턴받은 값들이 저장되어있는 rs안의 bcode를 가져와 vo의 bcode에 셋팅한다.
				vo.setBtitle(rs.getString("btitle")); // vo안에 리턴받은 값들이 저장되어있는 rs안의 btitle를 가져와 vo의 btitle에 셋팅한다.
				vo.setBwriter(rs.getString("bwriter")); // vo안에 리턴받은 값들이 저장되어있는 rs안의 bwriter를 가져와 vo의 bwriter에 셋팅한다.
				vo.setBpub(rs.getInt("bpub")); // vo안에 리턴받은 값들이 저장되어있는 rs안의 bpub를 가져와 vo의 bpub에 셋팅한다.
				vo.setBprice(rs.getInt("bprice")); // vo안에 리턴받은 값들이 저장되어있는 rs안의 bprice를 가져와 vo의 bprice에 셋팅한다.
				vo.setBdate(rs.getDate("bdate")); // vo안에 리턴받은 값들이 저장되어있는 rs안의 bdate를 가져와 vo의 bdate에 셋팅한다.

				bookList.add(vo); // 책 한권의 정보를 넣은 vo를 bookList에 삽입
				vo.toString(); // vo객체의 정보를 String으로 받는다.
			}
			System.out.println("출력 완료");

		} catch (Exception e) {
			// try 안에있는 코드의 오류를 잡아서
			System.out.println("selectMembers() 오류");
			e.printStackTrace(); // 에러 메세지의 발생 원인 찾아 단계별로 에러를 출력한다.
		} finally {
			// 오류 상관없이 무조건 실행하는부분
			close(rs, pstmt, conn); // 객체의 자원을 반환한다(객제생성한 순서의 반대로 닫아준다)
		}

		return bookList; // 책 정보 전체가 들어간 ArrayList인 bookList를 리턴
	}

	public int getBcode() { // Book_TBL테이블의 책코드중 가장 큰  책코드를 리턴해주는 객체
		int bCode = 0; // BOOK_TBL테이블의 책코드중 가장 큰  책코드를 담을 변수를 초기화해준다.
		try {
			conn = instance.getConnection(); // 오라클DB 에 연결된 Connection 객체를 conn에 받아온다.
			String sql = "SELECT MAX(bCode) FROM BOOK_TBL"; // 실행시킬 sql문을 sql에 넣어준다.
			pstmt = conn.prepareStatement(sql); // sql문을 오라클DB 에 연결된 Connection에 연결하여 실행시킨다.
			rs = pstmt.executeQuery(); // 실행결과로 ResultSet객체의 값을 리턴받아 rs에 저장한다.
			while (rs.next()) { // rs가 null이 나올때까지 while루프를 돈다.
				bCode = rs.getInt(1); // 실행결과를 bCode에 저장한다.
			}

		} catch (Exception e) {
			// try 안에있는 코드의 오류를 잡아서
			System.out.println("getBcode() 오류");
			e.printStackTrace(); // 에러 메세지의 발생 원인 찾아 단계별로 에러를 출력
		} finally {
			// 오류 상관없이 무조건 실행하는부분
			close(rs, pstmt, conn); // 객체의 자원을 반환한다(객제생성한 순서의 반대로 닫아준다)
		}

		return bCode; // Book_TBL테이블의 중 가장 큰 책코드를 담은 변수(bCode)를 리턴
	}
	
	public int insertBook(BookVO vo) { // 책 한권을 삽입할때 쓰는 객체 (BookVO vo를 받는다.)
		int row = -1; // 객체를 실행한 결과를 저장할 객체에 기본값을 설정
		try {
			row = 0; // row를 0으로 셋팅한다.
			conn = instance.getConnection(); // 오라클DB 에 연결된 Connection 객체를 conn에 받아온다.
			String sql = "INSERT INTO book_tbl VALUES(?, ?, ?, ?, ?, ?)"; // 실행시킬 sql문을 sql에 넣어준다.
			pstmt = conn.prepareStatement(sql); // sql문을 오라클DB 에 연결된 Connection에 연결하여 실행
			// sql 안에있는 ?에 넣을 값을 설정해준다. (앞은 앞에서 몇번째의 ?에 값을 집어넣을지, 뒤는 어떤 값을 집어넣을지 정한다.)
			pstmt.setInt(1, vo.getBcode()); // 앞에서 1번째의 ?에 insertBook을 사용할때 입력받은 BookVO vo 에 있는 bcode를 들고와 넣어준다.
			pstmt.setString(2, vo.getBtitle()); // 앞에서 2번째의 ?에 insertBook을 사용할때 입력받은 BookVO vo 에 있는 btitle를 들고와 넣어준다.
			pstmt.setString(3, vo.getBwriter()); // 앞에서 3번째의 ?에 insertBook을 사용할때 입력받은 BookVO vo 에 있는 bwriter를 들고와 넣어준다.
			pstmt.setInt(4, vo.getBpub()); // 앞에서 4번째의 ?에 insertBook을 사용할때 입력받은 BookVO vo 에 있는 bpub를 들고와 넣어준다.
			pstmt.setInt(5, vo.getBprice()); // 앞에서 5번째의 ?에 insertBook을 사용할때 입력받은 BookVO vo 에 있는 bprice를 들고와 넣어준다.
			pstmt.setDate(6, vo.getBdate()); // 앞에서 6번째의 ?에 insertBook을 사용할때 입력받은 BookVO vo 에 있는 bdate를 들고와 넣어준다.
			
			row = pstmt.executeUpdate(); // sql문을 실행한 결과 반환되는 int타입의 값을 row에 저장
			System.out.println("책 추가 완료");
			return row; // sql실행 결과를 리턴
		} catch (Exception e) {
			// try 안에있는 코드의 오류를 잡아서
			System.out.println("insertBook() 오류");
			e.printStackTrace(); // 에러 메세지의 발생 원인 찾아 단계별로 에러를 출력
		} finally {
			// 오류 상관없이 무조건 실행하는부분
			close(rs, pstmt, conn); // 객체의 자원을 반환한다(객제생성한 순서의 반대로 닫아준다)
		}
		return row; // row값을 리턴해준다
	}
	
	public BookVO selectABook(int bcode) { // 책 한권의 정보를 가져올때 쓰는 객체 bcode를 받는다.)
		BookVO book = new BookVO(); // 책 한권의 정보들을 저장할 BookVO vo 를 생성한다.
		try {
			conn = instance.getConnection(); // 오라클DB 에 연결된 Connection 객체를 conn에 받아온다.

			String sql = "SELECT * FROM book_tbl WHERE bcode = ?"; // 실행시킬 sql문을 sql에 넣어준다.
			pstmt = conn.prepareStatement(sql); // sql문을 오라클DB 에 연결된 Connection에 연결하여 실행시킨다.
			// sql문 안에있는 ?에 넣을 값을 설정해준다. (앞은 앞에서 몇번째의 ?에 값을 집어넣을지, 뒤는 어떤 값을 집어넣을지 정한다.)
			pstmt.setInt(1, bcode); // 앞에서 1번째의 ?에 selectABook를 사용할때 입력받은 bcode를 들고와 넣어준다.
			rs = pstmt.executeQuery(); // 순차적으로만 접근 가능한 리스트를 ResultSet에 넣어줌 
			if(rs.next()) { // rs가 null이 아니라 무언가가 들어가있다면
				book.setBcode(rs.getInt("bcode")); // vo안에 리턴받은 값들이 저장되어있는 rs안의 bcode를 가져와 vo의 bcode에 셋팅한다.
				book.setBtitle(rs.getString("btitle")); // vo안에 리턴받은 값들이 저장되어있는 rs안의 btitle를 가져와 vo의 btitle에 셋팅한다.
				book.setBwriter(rs.getString("bwriter")); // vo안에 리턴받은 값들이 저장되어있는 rs안의 bwriter를 가져와 vo의 bwriter에 셋팅한다.
				book.setBpub(rs.getInt("bpub")); // vo안에 리턴받은 값들이 저장되어있는 rs안의 bpub를 가져와 vo의 bpub에 셋팅한다.
				book.setBprice(rs.getInt("bprice")); // vo안에 리턴받은 값들이 저장되어있는 rs안의 bprice를 가져와 vo의 bprice에 셋팅한다.
				book.setBdate(rs.getDate("bdate")); // vo안에 리턴받은 값들이 저장되어있는 rs안의 bdate를 가져와 vo의 bdate에 셋팅한다.
			}
		} catch (Exception e) {
			// try 안에있는 코드의 오류를 잡아서
			System.out.println("selectABook() 오류");
			e.printStackTrace(); // 에러 메세지의 발생 원인 찾아 단계별로 에러를 출력한다.
		} finally {
			// 오류 상관없이 무조건 실행하는부분
			close(rs, pstmt, conn); // 객체의 자원을 반환한다(객제생성한 순서의 반대로 닫아준다)
		}
		
		return book; // 책 한권의 정보를 가져와서 저장한 book변수를 리턴시킨다
	}
	
	public int updateBook(BookVO vo) { // 멤버 한명의 정보를 수정할때 쓰는 객체 (BookVO vo를 받는다.)
		int row = 0; // 객체를 실행한 결과를 저장할 객체에 기본값을 지정한다.
		try {
			conn = instance.getConnection(); // 오라클DB 에 연결된 Connection 객체를 conn에 받아온다.
			String sql = "UPDATE book_tbl SET btitle=?, bwriter=?, bpub=?, bprice=?, bdate=? WHERE bcode = ?"; // 실행시킬 sql문을 sql에 넣어준다.
			pstmt = conn.prepareStatement(sql); // sql문을 오라클DB 에 연결된 Connection에 연결하여 실행시킨다.
			// sql 안에있는 ?에 넣을 값을 설정해준다. (앞은 앞에서 몇번째의 ?에 값을 집어넣을지, 뒤는 어떤 값을 집어넣을지 정한다.)
			pstmt.setString(1, vo.getBtitle()); // 앞에서 1번째의 ?에 updateBook을 사용할때 입력받은 BookVO vo 에 있는 btitle를 들고와 넣어준다.
			pstmt.setString(2, vo.getBwriter()); // 앞에서 2번째의 ?에 updateBook을 사용할때 입력받은 BookVO vo 에 있는 bwriter를 들고와 넣어준다.
			pstmt.setInt(3, vo.getBpub()); // 앞에서 3번째의 ?에 updateBook을 사용할때 입력받은 BookVO vo 에 있는 bpub를 들고와 넣어준다.
			pstmt.setInt(4, vo.getBprice()); // 앞에서 4번째의 ?에 updateBook을 사용할때 입력받은 BookVO vo 에 있는 bprice를 들고와 넣어준다.
			pstmt.setDate(5, vo.getBdate()); // 앞에서 5번째의 ?에 updateBook을 사용할때 입력받은 BookVO vo 에 있는 bdate를 들고와 넣어준다.
			pstmt.setInt(6, vo.getBcode()); // 앞에서 6번째의 ?에 updateBook을 사용할때 입력받은 BookVO vo 에 있는 bcode를 들고와 넣어준다.
			
			row = pstmt.executeUpdate(); // sql문을 실행한 결과 반환되는 int타입의 값을 row에 저장
			System.out.println("책 한권 수정 완료");
			return row; // sql실행 결과를 리턴
		} catch (Exception e) {
			// try 안에있는 코드의 오류를 잡아서
			System.out.println("updateBook() 오류");
			e.printStackTrace(); // 에러 메세지의 발생 원인 찾아 단계별로 에러를 출력
		} finally {
			// 오류 상관없이 무조건 실행하는부분
			close(rs, pstmt, conn); // 객체의 자원을 반환한다(객제생성한 순서의 반대로 닫아준다)
		}
		return row; // row값을 리턴
	}
	
	public int deleteBook(int bcode) { // 책 한권의 정보를 삭제할때 쓰는 객체 bcode를 받는다.)
		int row = 0; // 객체를 실행한 결과를 저장할 객체에 기본값을 지정한다.
		try {
			conn = instance.getConnection(); // 오라클DB 에 연결된 Connection 객체를 conn에 받아온다.
			String sql = "DELETE FROM book_tbl WHERE bcode = ?"; // 실행시킬 sql문을 sql에 넣어준다.
			pstmt = conn.prepareStatement(sql); // sql문을 오라클DB 에 연결된 Connection에 연결하여 실행시킨다.
			// sql 안에있는 ?에 넣을 값을 설정해준다. (앞은 앞에서 몇번째의 ?에 값을 집어넣을지, 뒤는 어떤 값을 집어넣을지 정한다.)
			pstmt.setInt(1, bcode); // 앞에서 1번째의 ?에 deleteBook을 실행할때 입력받은 bcode를 들고와 넣어준다.
			
			row = pstmt.executeUpdate(); // sql문을 실행한 결과 반환되는 int타입의 값을 row에 저장해준다.
			System.out.println("책 한권 삭제 완료");
			return row; // sql실행 결과를 리턴해준다.
		} catch (Exception e) {
			// try 안에있는 코드의 오류를 잡아서
			System.out.println("deleteBook() 오류");
			e.printStackTrace(); // 에러 메세지의 발생 원인 찾아 단계별로 에러를 출력한다.
		} finally {
			// 오류 상관없이 무조건 실행하는부분
			close(rs, pstmt, conn); // 객체의 자원을 반환한다(객제생성한 순서의 반대로 닫아준다)
		}
		return row; // row값을 리턴해준다
	}

	public void close(ResultSet rs, PreparedStatement pstmt, Connection conn) { // 객체의 자원을 반환하는 객체(객제생성한 순서의 반대로 닫아준다)
		if (rs != null) // ResultSet에 무언가가 들어가있다면
			try {
				rs.close(); // ResultSet rs를 닫아준다.
			} catch (Exception e) {
				// try 안에있는 코드의 오류를 잡아서
				e.printStackTrace(); // 에러 메세지의 발생 원인 찾아 단계별로 에러를 출력한다.
			}
		if (pstmt != null) // PreparedStatement에 무언가가 들어가있다면
			try {
				pstmt.close(); // PreparedStatement pstmt를 닫아준다.
			} catch (Exception e) {
				// try 안에있는 코드의 오류를 잡아서
				e.printStackTrace(); // 에러 메세지의 발생 원인 찾아 단계별로 에러를 출력한다.
			}
		if (conn != null) // Connection에 무언가가 들어가있다면
			try {
				conn.close(); // Connection conn를 닫아준다.
			} catch (Exception e) {
				// try 안에있는 코드의 오류를 잡아서
				e.printStackTrace(); // 에러 메세지의 발생 원인 찾아 단계별로 에러를 출력한다.
			}
	}
}
