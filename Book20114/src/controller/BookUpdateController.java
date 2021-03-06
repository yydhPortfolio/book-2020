package controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.BookDAO;
import book.BookVO;
@WebServlet("/BookUpdate.do")
public class BookUpdateController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(req, resp);
	}
	
	public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// GET방식이든 POST방식이든 여기서 처리
		
		// 한글 인코딩 처리
		req.setCharacterEncoding("utf-8");
		
		// 파라미터 가져오기
		int bcode = Integer.parseInt(req.getParameter("bcode")); // BookUpdate.do를 로드할때 보내준 bcode를 변수에 저장해준다.
		String btitle = req.getParameter("btitle"); // BookUpdate.do를 로드할때 보내준 btitle를 변수에 저장해준다.
		String bwriter = req.getParameter("bwriter"); // BookUpdate.do를 로드할때 보내준 bwriter를 변수에 저장해준다.
		int bpub = Integer.parseInt(req.getParameter("bpub")); // BookUpdate.do를 로드할때 보내준 bpub를 변수에 저장해준다.
		int bprice = Integer.parseInt(req.getParameter("bprice")); // BookUpdate.do를 로드할때 보내준 bprice를 변수에 저장해준다.
		Date bdate = Date.valueOf(req.getParameter("bdate"));// BookUpdate.do를 로드할때 보내준 bdate를 변수에 저장해준다.
		
		// vo 객체 생성 -> 담기 -> DAO에서 메서드 처리
		BookDAO instance = BookDAO.getInstance(); // BookDAO의 instance를 가져온다.
		String msg = null; // 실행 후 alert에 띄울 문구를 저장할 변수 설정
		BookVO vo = new BookVO(bcode, btitle, bwriter, bpub, bprice, bdate); // 새로 선언한 BookVO vo에 책의 정보들을 넣어준다.
		int result = 0; // 실행된 row의 개수를 반환받을 변수 선언
		result = instance.updateBook(vo); // DB에서 책 한권의 정보를 수정해주는 BookDAO의 객체 updateBook()을 실행시켜 반환되는 성공한 row의 개수를 result에 저장해준다.
		
		if(result > 0) { // 실행 된 row가 하나라도 있다면 실행된것이기 때문에
			msg = "책 수정 완료"; // 삭제완료 문구를 저장
		} else { // 실행된 row가 하나도 없다면
			msg = "책 수정 오류"; // 오류문구 저장
		}
		req.setAttribute("msg", msg); // JSP 페이지 사이에서 주고받거나 공유 할 수 있는 메소드 setAttribute를 이용하여 msg란 이름으로 msg를 저장한다.
		
		// 화면 출력 -> jsp 의 역할 (view 역할 -> view 페이지로)
		RequestDispatcher rd = req.getRequestDispatcher("/BookList.do");
		rd.forward(req, resp); // 요청 (호출)
	}
}
