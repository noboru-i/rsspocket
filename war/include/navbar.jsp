<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<a class="brand" href="/">Rss to Pocket</a>
			<div class="nav-collapse collapse">
				<ul class="nav">
					<li class="active"><a href="/">Top</a></li>
					<li><a href="/home">Home</a></li>
					<li><a href="/rss">Rss</a></li>
				</ul>
				<c:choose>
					<c:when test="${login}">
						<div class="navbar-text pull-right">
							${user.email}&nbsp;|&nbsp;
							<a href="${logoutUrl}">ログアウト</a>
						</div>
					</c:when>
					<c:otherwise>
						<div class="navbar-form pull-right">
							<a href="${loginUrl}" class="btn btn-primary">Sign in with Google »</a>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</div>
