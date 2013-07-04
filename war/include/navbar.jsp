<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<a class="brand" href="/">RocketSS（α版）</a>
			<div class="nav-collapse collapse">
				<ul class="nav">
					<li><a href="/">Top</a></li>
					<li><a href="/home">Home</a></li>
					<li><a href="/rss">Rss</a></li>
					<li><a href="/reader/fileupload">Upload</a></li>
				</ul>
				<c:choose>
					<c:when test="${login}">
						<div class="navbar-text pull-right">
							${user.email}&nbsp;|&nbsp;
							<a href="${logoutUrl}">ログアウト</a>
						</div>
					</c:when>
				</c:choose>
			</div>
		</div>
	</div>
</div>
