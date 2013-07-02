<jsp:include page="/include/layout.jsp">
	<jsp:param name="title" value="Rss"/>
	<jsp:param name="content">
		<jsp:attribute name="value">

<div class="row">
	<div class="span3 bs-docs-sidebar">
		<button class="btn btn-success" data-toggle="modal" data-target="#addConfirm"><i class="icon-plus icon-white"></i></button>
		<div class="tabbable tabs-left">
			<ul id="rss-list" class="nav nav-tabs bs-docs-sidenav">
			<c:forEach var="userRss" items="${userRssList}" varStatus="status">
				<li><a href="javascript:void(0);" data-url="${userRss.rssFeed.model.url}" onClick="Rss.openFeed(this)">${userRss.rssFeed.model.title}</a></li>
			</c:forEach>
			</ul>
		</div>
	</div>
	<div class="span9">
		<div id="feed-detail" class="hide">
			<div class="bs-docs-example title-block">
				<a href="#" class="title" target="_blank"></a>
				<a href="#" class="delete-button btn btn-danger pull-right" data-toggle="modal" data-target="#deleteConfirm" onClick="$('#deleteConfirm .btn-primary').data('url', $(this).data('url'));">削除</a>
			</div>
			<table class="table feedList">
			</table>
		</div>
	</div>
</div>

<!-- Modal -->
<div id="addConfirm" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="addModalLabel" aria-hidden="true">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		<h3>追加するURLを入力</h3>
	</div>
	<div class="modal-body">
		<form class="form-horizontal">
			<div class="control-group">
				<label class="control-label" for="inputEmail">URL</label>
				<div class="controls">
					<input id="url_0" type="text" placeholder="URL">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputEmail">タグ</label>
				<div class="controls">
					<input class="tag_0" type="text" placeholder="tags">
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<input class="tag_0" type="text" placeholder="tags">
				</div>
			</div>
		</form>
	</div>
	<div class="modal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
		<button class="btn btn-primary" onClick="Rss.addByUrl($('#url_0').val())">Add</button>
	</div>
</div>
<div id="deleteConfirm" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel" aria-hidden="true">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		<h3 id="deleteModalLabel">注意</h3>
	</div>
	<div class="modal-body">
		<p>削除します。よろしいですか？</p>
	</div>
	<div class="modal-footer">
		<button class="btn" data-dismiss="modal">Close</button>
		<button class="btn btn-primary" data-dismiss="modal" onClick="Rss.delete($(this).data('url'))">実行</button>
	</div>
</div>

		</jsp:attribute>
	</jsp:param>
</jsp:include>
