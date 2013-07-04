<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta property="og:url" content="http://rsspocket.appspot.com/">
	<meta property="og:title" content="RocketSS（α）" />
	<meta property="og:description" content="新着のRSS　→　Pocketに自動保存" />

	<link href="/css/bootstrap.css" rel="stylesheet">
	<link href="/css/responsive.css" rel="stylesheet">
	<link href="/css/rsspocket.css" rel="stylesheet">

	<title>
		<c:if test="${empty param.title}">
			RocketSS（α） - RSSを自動でPocketに保存
		</c:if>
		<c:if test="${!empty param.title}">
			${param.title} | RocketSS（α）
		</c:if>
	</title>

	<jsp:include page="/include/track.jsp" />
</head>
<body>

<jsp:include page="/include/navbar.jsp" />

<div class="container">
	${param.content}
</div>

<script src="/js/libs.js"></script>
<script src="/js/app.js"></script>
${param.script}
</body>
</html>
