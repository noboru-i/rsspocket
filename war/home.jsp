<c:import url="/include/layout.jsp">
	<c:param name="title" value="Rss"/>
	<c:param name="content">

<div class="hero-unit">
	<h1>Rss to Pocket（α版）</h1>
	<table class="table" style="margin-top: 40px;">
		<tr>
			<td>Pocketのアカウント認証を行なってください。</td>
			<td>
				<a href="/pocket/auth" class="btn btn-primary btn-large ${empty userInfo.pocketAccessToken ? '' : 'disabled' }">
					${empty userInfo.pocketAccessToken ? 'Pocketでログイン' : 'Pocket認証済み' }
				</a>
			</td>
		</tr>
		
		<tr>
			<td>RSSフィードの登録を行なってください。</td>
			<td>
				<a href="/rss?add=1" class="btn btn-primary btn-large">追加</a><span style="margin: 0 10px;">or</span>
				<a href="/reader/fileupload" class="btn btn-primary btn-large">ファイルアップロード</a>
			</td>
		</tr>

		<tr>
			<td>登録したRSSに更新がある度に、Pocketに追加されます</td>
			<td>
				<a href="/rss" class="btn btn-primary btn-large">登録したRSSを編集する</a>
			</td>
		</tr>
	</table>
</div>

	</c:param>
</c:import>
