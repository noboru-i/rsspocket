<c:import url="/include/layout.jsp">
	<c:param name="title" value="Rss"/>
	<c:param name="content">

<div class="hero-unit">
	<h1>使い方</h1>
	<table class="table" style="margin-top: 40px;">
		<tr>
			<td>1. Googleのアカウント認証を行なってください</td>
			<td><i class="icon-arrow-right"></i></td>
			<td>
				<c:if test="${!loggedIn}">
					<a href="${loginUrl}" class="btn btn-primary btn-large">
						Googleのアカウントでログイン
					</a>
				</c:if>
				<c:if test="${loggedIn}">
					<a href="#" class="btn btn-primary btn-large disabled">
						Google認証済み
					</a>
				</c:if>
			</td>
		</tr>

		<tr>
			<td>2. Pocketのアカウント認証を行なってください</td>
			<td><i class="icon-arrow-right"></i></td>
			<td>
				<c:if test="${empty userInfo.pocketAccessToken}">
					<a href="/pocket/auth" class="btn btn-primary btn-large">
						Pocketでログイン
					</a>
				</c:if>
				<c:if test="${!empty userInfo.pocketAccessToken}">
					<a href="#" class="btn btn-primary btn-large disabled">
						Pocket認証済み
					</a>
				</c:if>
			</td>
		</tr>
		
		<tr>
			<td>3. RSSフィードの登録を行なってください</td>
			<td><i class="icon-arrow-right"></i></td>
			<td>
				<a href="/rss?add=1" class="btn btn-primary btn-large">追加</a><span style="margin: 0 10px;">or</span>
				<a href="/reader/fileupload" class="btn btn-primary btn-large">ファイルアップロード</a>
			</td>
		</tr>
	</table>
	
	<h2 style="margin-top: 60px;">あとは</h2>
	<table class="table">
		<tr>
			<td>登録したRSSに更新がある度に、Pocketに追加されます</td>
			<td>
				<a href="/rss" class="btn btn-info btn-large">登録したRSSを編集する</a>
			</td>
		</tr>
		
		<tr>
			<td>Androidをお持ちなら「Read It Now」が便利です</td>
			<td>
				<a href="https://play.google.com/store/apps/details?id=hm.orz.chaos114.android.readitnow&hl=ja" target="_blank" class="btn btn-info btn-large">Google playでDL</a>
			</td>
		</tr>
	</table>
</div>

	</c:param>
</c:import>
