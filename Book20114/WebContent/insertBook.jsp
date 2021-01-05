<%@page import="book.BookDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %> <!-- header페이지를 insertBook.jsp에 포함시킨다. (book페이지의 메뉴를 포함시킨다) -->
	<br>
	<%
		BookDAO instance = BookDAO.getInstance();
		int bCode = instance.getBcode();
	%> 
	<form action="BookInsert.do" method="post"> <!-- 정보를 넘길떄 post 방식으로 넘기고, 페이지는 BookInsert.do로 넘어간다. -->
		<table border="1">
			<tr>
				<th>도서코드</th>
				<td><input type="text" name="bcode" value="<%= bCode + 1 %>" readonly="readonly"></td> <!-- 정보를 넘길때 도서코드 값을 bcode란 이름으로 넘긴다. readonly를 써서 수정불가하게 만든다 -->
			</tr>
			<tr>
				<th>도서제목</th>
				<td><input type="text" name="btitle" placeholder="도서제목" required></td> <!-- 정보를 넘길때 도서제목 값을 btitle이란 이름으로 넘긴다. required를 써서 필수입력 input으로 만든다. -->
			</tr>
			<tr>
				<th>도서저자</th>
				<td><input type="text" name="bwriter" placeholder="도서저자" required></td> <!-- 정보를 넘길때 도서저자 값을 bwriter란 이름으로 넘긴다. required를 써서 필수입력 input으로 만든다. -->
			</tr>
			<tr>
				<th>출판사코드</th>
				<td>
					<select name="bpub" required> <!-- 정보를 넘길때 출판사코드 값을 bpub란 이름으로 넘긴다. select를 써서 여러가지선택지중 한가지를 선택할 수 있게 해준다. required를 써서 필수입력 input으로 만든다. -->
						<option value="1001">양영디지털</option> <!-- 선택시 1001값으로 bpub에 저장되어 넘어간다. -->
						<option value="1002">북스미디어</option> <!-- 선택시 1002값으로 bpub에 저장되어 넘어간다. -->
						<option value="1003">한빛아카데미</option> <!-- 선택시 1003값으로 bpub에 저장되어 넘어간다. -->
						<option value="1004">이지스</option> <!-- 선택시 1004값으로 bpub에 저장되어 넘어간다. -->
					</select>
				</td>
			</tr>
			<tr>
				<th>가격</th>
				<td><input type="number" name="bprice" placeholder="가격" required></td> <!-- 정보를 넘길때 가격 값을 bprice란 이름으로 넘긴다. required를 써서 필수입력 input으로 만든다. -->
			</tr>
			<tr>
				<th>출간날짜</th>
				<td><input type="date" name="bdate" required></td> <!-- 정보를 넘길때 출간날짜 값을 bdate란 이름으로 넘긴다. required를 써서 필수입력 input으로 만든다. -->
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="등록"> <!-- 클릭하면 BookInsert.do로 input에 입력되어있는 값들과 함께 넘어간다. -->
					<input type="reset" value="재작성"> <!-- 클릭하면 선택하거나 입력한 모든 값들이 초기화된다. (기본값이 있었던 것들은 기본값으로 돌아간다.) -->
				</td>
			</tr>
		</table>
	</form>
<jsp:include page="footer.jsp" /> <!-- footer페이지를 insertBook.jsp에 포함시킨다. -->