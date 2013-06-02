<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<link href="/css/bootstrap.min.css" rel="stylesheet">
	<link href="/css/bootstrap-responsive.min.css" rel="stylesheet">
	<link href="/css/rsspocket.css" rel="stylesheet">

	<title>Rss to Pocket</title>
	
	<jsp:include page="/include/track.jsp" />
</head>
<body>

<jsp:include page="/include/navbar.jsp" />

<div class="container">

	<c:if test="${error == 'no_login'}">
		<h1>ログインしてください。</h1>
	</c:if>
	<c:choose>
		<c:when test="${login}">
			<h1>Hello ${f:h(user.email)} !!!</h1>
			<a href="${logoutUrl}" class="btn btn-primary btn-large">logout</a>
			
			<a href="/home" class="btn btn-primary btn-large">home</a>
		</c:when>
		<c:otherwise>
			<a href="${loginUrl}" class="btn btn-primary btn-large">login</a>
		</c:otherwise>
	</c:choose>
	</div>
</div>

<script src="//code.jquery.com/jquery.js"></script>
<script src="/js/bootstrap.min.js"></script>

</body>
</html>
