package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.BookDAO;
import book.BookVO;

@WebServlet("/BookList.do")
public class BookListController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// GET방식이든 POST방식이든 여기서 처리
		
		// 한글 인코딩 처리
		req.setCharacterEncoding("utf-8");
		
		// vo 객체 생성 -> 담기 -> DAO에서 메서드 처리
		BookDAO instance = BookDAO.getInstance(); // BookDAO의 instance를 가져온다.
		ArrayList<BookVO> bookList = instance.selectBooks();// BookDAO의 객체중 테이블 전체값을 ArrayList로 리턴해주는 selectBooks()를 실행시켜 테이블 전체값 ArrayList를 bookList란 BookVO ArrayList에 저장한다.

		req.setAttribute("bookList", bookList); // JSP 페이지 사이에서 주고받거나 공유 할 수 있는 메소드 setAttribute를 이용하여 bookList란 이름으로 bookList를 저장한다.
        String msg = (String)req.getAttribute("msg"); // JSP 페이지 사이에서 주고받거나 공유 할 수 있는 메소드 setAttribute를 이용하여 저장한 msg를 변수 msg에 저장한다.
        req.setAttribute("msg", msg); // JSP 페이지 사이에서 주고받거나 공유 할 수 있는 메소드를 이용하여 msg란 이름으로 msg를 저장한다.(페이지가 한번 더 넘어가기 때문에 다시 setAttribute를 해준다.)
        
        // 화면 출력 -> jsp 의 역할 (view 역할 -> view 페이지로)
		RequestDispatcher rd = req.getRequestDispatcher("selectBook.jsp");
		rd.forward(req, resp); // 요청 (호출)
	}
}
