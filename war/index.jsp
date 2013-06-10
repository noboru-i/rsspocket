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

	<div class="hero-unit">
		<h1>Rss to Pocket</h1>
		<p>新着のRSSをPocketに自動保存</p>
		<a href="${loginUrl}" class="btn btn-primary btn-large">Googleのアカウントでログイン</a>
	</div>
</div>

<script src="//code.jquery.com/jquery.js"></script>
<script src="/js/bootstrap.min.js"></script>

</body>
</html>
