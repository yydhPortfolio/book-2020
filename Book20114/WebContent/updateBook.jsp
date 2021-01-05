<%@page import="book.BookDAO"%>
<%@page import="book.BookVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%> <!-- header페이지를 updateBook.jsp에 포함시킨다. (book페이지의 메뉴를 포함시킨다) -->
<br>
<%
		request.setCharacterEncoding("utf-8"); // 한글 깨짐을 방지해준다.
		int bcode = Integer.parseInt(request.getParameter("bcode")); // upadateBook.jsp를 로드할때 보내준 bcode 변수에 저장해준다.

		BookDAO instance = BookDAO.getInstance(); // BookDAO의 instance를 가져온다.
		BookVO vo = instance.selectABook(bcode); // BookDAO 객체중 테이블에서 책 한권의 값을 리턴해주는 selectABook()를 실행시켜 책 한권의 값을 BookVO vo에 저장한다.
		
		if(vo.getBcode() == bcode){ // 만약 리턴받은 책의 bcode가 upadateBook.jsp를 로드할때 보내준 bcode 같다면 수정할 값을 입력받는다.
	%>
	<form action="BookUpdate.do" method="post"> <!-- 정보를 넘길떄 post 방식으로 넘기고, 페이지는BookUpdate.do로 넘어간다. -->
		<table border="1" style="width:500px">
			<tr>
				<th>도서코드</th>
				<td><input type="text" name="bcode" value="<%= vo.getBcode() %>" readonly="readonly"></td> <!-- 정보를 넘길때 도서코드 값을 bcode란 이름으로 넘긴다. (readonly로 설정한 값은 읽기만 가능할뿐 변경은 불가능하지만 form으로 값을 보낼 수 있다.) 기본값으로 사용자가 책등록을 할때 지정받은 도서번호를 넣는다. -->
			</tr>
			<tr>
				<th>도서제목</th>
				<td><input type="text" name="btitle" value="<%= vo.getBtitle() %>" required></td> <!-- 정보를 넘길때 도서제목 값을 btitle란 이름으로 넘긴다. 기본값으로 사용자가 책등록할때 입력한 도서제목을 넣는다. -->
			</tr>
			<tr>
				<th>도서저자</th>
				<td><input type="text" name="bwriter" value="<%= vo.getBwriter() %>" required></td> <!-- 정보를 넘길때 도서저자 값을 btitle란 이름으로 넘긴다. 기본값으로 사용자가 책등록할때 입력한 도서저자를 넣는다. -->
			</tr>
			<tr>
				<th>출판사코드</th>
				<td>
					<select name="bpub" required> <!-- 정보를 넘길때 출판사코드 값을 bpub란 이름으로 넘긴다. -->
						<option value="1001" <% if(vo.getBpub() == 1001) { %> selected="selected"<% } %> >양영디지털</option> <!-- 사용자가 책등록할때 양영디지털을 선택했다면 기본으로 선택을 해놓는다. -->
						<option value="1002" <% if(vo.getBpub() == 1002) { %> selected="selected"<% } %>>북스미디어</option> <!-- 사용자가 책등록할때 북스미디어를 선택했다면 기본으로 선택을 해놓는다. -->
						<option value="1003" <% if(vo.getBpub() == 1003) { %> selected="selected"<% } %>>한빛아카데미</option> <!-- 사용자가 책등록할때 한빛아카데미를 선택했다면 기본으로 선택을 해놓는다. -->
						<option value="1004" <% if(vo.getBpub() == 1004) { %> selected="selected"<% } %>>이지스</option> <!-- 사용자가 책등록할때 이지스를 선택했다면 기본으로 선택을 해놓는다. -->
					</select>
				</td>
			</tr>
			<tr>
				<th>가격</th>
				<td><input type="number" name="bprice" value="<%= vo.getBprice() %>" required></td> <!-- 정보를 넘길때 가격 값을 bprice란 이름으로 넘긴다. 기본값으로 사용자가 책등록할때 입력한 가격을 넣는다. -->
			</tr>
			<tr>
				<th>출간날짜</th>
				<td><input type="date" name="bdate" value="<%= vo.getBdate() %>" required></td> <!-- 정보를 넘길때 출간날짜 값을 bdate란 이름으로 넘긴다. 기본값으로 사용자가 책등록할때 입력한 출간날짜를 넣는다. -->
			</tr> 
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="수정"> <!-- 클릭하면 BookUpdate.do로 input에 입력되어있는 값들과 함께 넘어간다. -->
					<input type="reset" value="재작성"> <!-- 클릭하면 선택하거나 입력한 모든 값들이 초기화된다. (기본값이 있었던 것들은 기본값으로 돌아간다.) -->
				</td>
			</tr>
		</table>
	</form>
	<%
		}
	%>
<%@ include file="footer.jsp" %> <!-- footer페이지를 updateBook.jsp에 포함시킨다. -->