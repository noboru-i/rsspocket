<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<link href="/css/bootstrap.min.css" rel="stylesheet">
	<link href="/css/bootstrap-responsive.min.css" rel="stylesheet">
	<link href="/css/rsspocket.css" rel="stylesheet">

	<title>Rss</title>
</head>
<body>

<jsp:include page="/include/navbar.jsp" />

<div class="container">
	<div class="row">
		<div class="span3 bs-docs-sidebar">
			<ul class="nav nav-list bs-docs-sidenav">
			<c:forEach var="userRss" items="${userRssList}" varStatus="status">
				<li><a href="javascript:void(0);" data-url="${userRss.rssFeed.model.url}" onClick="Rss.openFeed(this)">${userRss.rssFeed.model.title}</a></li>
			</c:forEach>
			</ul>
		</div>
		<div class="span9">
			<table class="table feedList">
			</table>
		</div>
	</div>

	<!-- Modal -->
	<div id="deleteConfirm" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<h3 id="deleteModalLabel">注意</h3>
		</div>
		<div class="modal-body">
			<p>削除します。よろしいですか？</p>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
			<button class="btn btn-primary" data-dismiss="modal" aria-hidden="true" onClick="Rss.delete($(this).data('id'))">実行</button>
		</div>
	</div>
</div>

<script src="//code.jquery.com/jquery.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/app.js"></script>
</body>
</html>
