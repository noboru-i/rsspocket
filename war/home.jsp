<!DOCTYPE html>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<link href="/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link href="/css/bootstrap-responsive.min.css" rel="stylesheet">
	<link href="/css/rsspocket.css" rel="stylesheet">

	<title>Home | Rss to Pocket</title>

	<jsp:include page="/include/track.jsp" />
</head>
<body>

<jsp:include page="/include/navbar.jsp" />

<div class="container">
	<div class="hero-unit">
		<h1>ようこそ！</h1>
		<c:choose>
			<c:when test="${empty userInfo.pocketAccessToken}">
				<p>次は、Pocketのアカウント認証を行なってください。</p>
				<a href="/pocket/auth" class="btn btn-primary btn-large">Pocketでログイン</a>
			</c:when>
			<c:otherwise>
				<p>次は、RSSフィードの登録を行なってください。</p>
				<a href="/reader/fileupload" class="btn btn-primary btn-large">RSSフィードの追加</a>
			</c:otherwise>
		</c:choose>
	</div>
</div>

</body>
</html>
