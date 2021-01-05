<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="book.BookVO"%>
<%@page import="book.BookDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <!-- 자바서버 페이지 표준 태그 라이브러리인 jstl을 이용하여 jsp코딩을한다. -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="header.jsp"%> <!-- header페이지를 selectBook.jsp에 포함시킨다. (book페이지의 메뉴를 포함시킨다) -->
<c:if test="${!empty msg}"> <!-- Attribute중 msg가 비어있는지 확인을 한다. -->
	<script>
		alert('${msg}'); /* 비어있지 않다면 alert으로 msg변수 안에있는 문구를 띄운다 */
	</script>
</c:if>

<br>

<table border="1">
	<tr align="center">
		<th width="200">도서코드</th>
		<th width="200">책이름</th>
		<th width="200">저자</th>
		<th width="200">출판사</th>
		<th width="200">가격</th>
		<th width="200">출간날짜</th>
		<th width="200">삭제</th>
	</tr>
	<c:forEach var="book" items="${bookList}"> <!-- Attribute중 bookList를 들고와서 book이란 이름으로 foreach를 해준다. -->
		<tr align="center">
			<td width="200"><a href="updateBook.jsp?bcode=${book.bcode}">${book.bcode}</a></td> <!-- a태그를 넣어서 클릭시 updateBook.jsp로 넘어가고 bcode를 넘겨준다 -->
			<td width="200">${book.btitle}</td> <!-- book에 들어있는 btitle을 불러온다 -->
			<td width="200">${book.bwriter}</td> <!-- book에 들어있는 bwriter을 불러온다 -->
			<td width="200">
				<c:choose>
					<c:when test="${book.bpub == 1001}"> <!-- book에 들어있는 bpub을 불러와서 그 값이 1001이면 양영디지털로 설정한다 -->
					양영디지털
					</c:when>
					<c:when test="${book.bpub == 1002}"> <!-- book에 들어있는 bpub을 불러와서 그 값이 1002이면 북스미디어로 설정한다 -->
					북스미디어
					</c:when>
					<c:when test="${book.bpub == 1003}"> <!-- book에 들어있는 bpub을 불러와서 그 값이 1003이면 한빛아카데미로 설정한다 -->
					한빛아카데미
					</c:when>
					<c:when test="${book.bpub == 1004}"> <!-- book에 들어있는 bpub을 불러와서 그 값이 1004이면 이지스로 설정한다 -->
					이지스
					</c:when>
				</c:choose>
			</td>
			<td width="200"><fmt:formatNumber type="number" maxFractionDigits="3" value="${book.bprice}" /></td> <!-- book에 들어있는 bprice을 불러온다  maxFractionDigits="3"를 이용해 3자리수마다 콤마(,)를 찍어준다. -->
			<td width="200"><fmt:formatDate value="${book.bdate}" pattern="yyyy년MM월dd일"/></td> <!-- book에 들어있는 bdate을 불러온다 pattern="yyyy년MM월dd일"을 이용해 날짜 패턴을 년월일로 바꿔준다. -->
			<td width="200"><a href="deleteBook.jsp?bcode=${book.bcode}">삭제</a></td> <!-- a태그를 넣어서 클릭시 deleteBook.jsp로 넘어가고 bcode를 넘겨준다 -->
		</tr>
	</c:forEach>
</table>
<jsp:include page="footer.jsp" /> <!-- footer페이지를 selectBook.jsp에 포함시킨다. -->