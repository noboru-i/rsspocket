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
	<table class="table table-striped">
		<tr>
			<th>一括</th>
			<th>URL</th>
			<th>タグ</th>
			<th>操作</th>
		</tr>
		<c:if test="${empty userRssList}" >
			<tr>
				<td colspan="4">データがありません。データを登録して下さい。</td>
			</tr>
		</c:if>
		<c:forEach var="userRss" items="${userRssList}" varStatus="status">
			<tr id="like_${status.index}">
				<td>
					<label class="checkbox">
						<input type="checkbox" id="edit_${status.index}" data-id="${status.index}" class="check_edit">
					</label>
				</td>
				<td id="url_${status.index}">${userRss.rssFeed.model.url}</td>
				<td>
					<c:forEach var="tag" items="${userRss.tags}" varStatus="tagStatus">
						<div class="input-append">
							<input type="text" class="tag_${status.index}" value="${tag}">
							<button class="btn" type="button" onClick="$('.tag_${status.index}', $(this).parent()).val('')">Clear</button>
						</div>
						<br>
					</c:forEach>
					<div class="input-append">
						<input type="text" class="tag_${status.index}" value="">
						<button class="btn" type="button" onClick="$('.tag_${status.index}', $(this).parent()).val('')">Clear</button>
					</div>
				</td>
				<td>
					<a href="javascript:void(0);" class="btn btn-info" onClick="Rss.edit(${status.index})">
						<i class="icon-plus-sign icon-white"></i> 編集
					</a>
					<a href="javascript:void(0);" class="btn btn-danger" onClick="Rss.delete(${status.index})">
						<i class="icon-minus-sign icon-white"></i> 削除
					</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<div class="form-actions">
		<a href="#bulkConfirm" role="button" class="btn btn-danger" data-toggle="modal" onClick="$('#bulkEditCount').text($('table .check_edit:checked').length)">チェックしたものを一括削除</a>
	</div>

	<!-- Modal -->
	<div id="bulkConfirm" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="bulkModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<h3 id="bulkModalLabel">注意</h3>
		</div>
		<div class="modal-body">
			<p><span id="bulkEditCount"></span>件削除します。よろしいですか？</p>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
			<button class="btn btn-primary" data-dismiss="modal" aria-hidden="true" onClick="Rss.builkAdd()">実行</button>
		</div>
	</div>
</div>

<script src="//code.jquery.com/jquery.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/app.js"></script>
</body>
</html>
