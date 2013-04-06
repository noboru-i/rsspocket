<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<link href="/css/bootstrap.min.css" rel="stylesheet">
	<link href="/css/bootstrap-responsive.min.css" rel="stylesheet">
	<link href="/css/rsspocket.css" rel="stylesheet">

	<title>Index</title>
<title>reader Fileupload</title>
</head>
<body>

<jsp:include page="/include/navbar.jsp" />

<div class="container">
	<h1>Hello ${user.email}</h1>
	<hr>
	<form action="/reader/fileupload" method="post" enctype="multipart/form-data">
		<input type="file" name="xmlfile">
		<input type="submit" value="send">
	</form>
	<hr>
	<table border="1">
		<c:forEach var="rssFeed" items="${rssFeedList}" varStatus="status">
			<tr id="like_${status.index}">
				<td>
					<label class="checkbox">
						<input type="checkbox" id="regist_${status.index}" name="regist" checked="checked"> 登録
					</label>
				</td>
				<td id="url_${status.index}">${rssFeed.url}</td>
				<td>
					<c:forEach var="tag" items="${rssFeed.tags}" varStatus="tagStatus">
						<input type="text" class="tag_${status.index}" value="${tag}"><br>
					</c:forEach>
				</td>
				<td>
					<a href="javascript:void(0);" class="btn btn-success" onClick="Rss.add(${status.index})">
						<i class="icon-plus-sign icon-white"></i> 登録
					</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>


<script src="//code.jquery.com/jquery.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/app.js"></script>

</body>
</html>
