<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>간단한 도서 등록 프로젝트</title>
</head>
<body>
	<%
		int bcode = Integer.parseInt(request.getParameter("bcode"));  // deleteBook.jsp를 로드할때 같이 보내준 bcode 변수에 넣어줌
	%>
	<script>
		let result = confirm("정말 삭제하겠습니까?"); /* confirm창을 이용해 사용자에게 삭제 여부를 묻는다. */
		if (result) { /* 확인버튼을 누르면 결과값은 true이기 떄문데  */
			location.href = "BookDelete.do?bcode=<%= bcode %>"; /* BookDelete.do를 실행시키며 거기에 bcode를 보낸다 */
		} else { /* 취소버튼을 누르면 결과값은 false이기 때문에 */
			history.back(); /* 뒤로가기를 해준다. */
		}
	</script>
</body>
</html>