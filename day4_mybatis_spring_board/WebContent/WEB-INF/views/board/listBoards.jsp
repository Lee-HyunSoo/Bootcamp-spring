<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<%
	request.setCharacterEncoding("UTF-8");
%>


<html>
<head>
<meta charset=UTF-8">
<title>게시글 출력창</title>
</head>
<body>
	<table border="1" align="center" width="80%">
		<tr align="center" bgcolor="lightgreen">
			<td><b>게시글 번호</b></td>
			<td><b>제목</b></td>
			<td><b>내용</b></td>
			<td><b>작성일자</b></td>
			<td><b>ID</b></td>
			<td><b>삭제</b></td>
		</tr>

		<c:forEach var="board" items="${boards}">
			<tr align="center">
				<td>${board.articleNo}</td>
				<td>${board.title}</td>
				<td>${board.content}</td>
				<td>${board.writeDate}</td>
				<td>${board.id}</td>
				<td><a href="${contextPath}/board/deleteBoard.do?articleNo=${board.articleNo}">게시글 삭제</a></td>
			</tr>
		</c:forEach>
	</table>
	<a href="${contextPath}/board/boardForm.do"><h1 style="text-align: center">게시글 작성</h1></a>
</body>
</html>
