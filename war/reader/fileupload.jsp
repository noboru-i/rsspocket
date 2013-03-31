<!DOCTYPE html>
<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<link href="/css/bootstrap.min.css" rel="stylesheet" media="screen">

	<title>Index</title>
<title>reader Fileupload</title>
</head>
<body>
<div class="container">
	<h1>Hello reader Fileupload !!!</h1>
	<form action="/reader/fileupload" method="post" enctype="multipart/form-data">
		<input type="file" name="xmlfile">
		<input type="submit" value="send">
	</form>
	<table>
		<c:forEach var="url" items="${urls}" varStatus="status">
			<tr>
				<td>${url}</td>
			</tr>
		</c:forEach>
	</table>
</div>


<script src="//code.jquery.com/jquery.js"></script>
<script src="/js/bootstrap.min.js"></script>

</body>
</html>
