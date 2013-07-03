<c:import url="/include/layout.jsp">
	<c:param name="title" value="Rss"/>
	<c:param name="content">

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

	</c:param>
</c:import>
