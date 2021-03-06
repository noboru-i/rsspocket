<c:import url="/include/layout.jsp">
	<c:param name="title" value=""/>
	<c:param name="content">

	<c:if test="${param.error == 'no_login'}">
		<div class="alert alert-error">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			ログインしてください。
		</div>
	</c:if>


<div class="hero-unit" style="position: relative;">
	<a href="https://github.com/noboru-i/rsspocket">
	<img style="position: absolute; top: 0; right: 0; border: 0; z-index: 1031;" src="/img/forkme_right_darkblue_121621.png" alt="Fork me on GitHub">
	</a>

	<h1>RocketSS（α版）</h1>
	<p>新着のRSS　→　Pocketに自動保存</p>
	<c:if test="${!loggedIn}">
		<p><a href="${loginUrl}" class="btn btn-primary btn-large">Googleのアカウントでログイン</a></p>
	</c:if>
	<p><a href="/home" class="btn btn-info btn-large">詳しくはこちら <i class="icon-chevron-right icon-white"></i></a></p>
	<div style="margin-top: 40px;">
	<p>このページは開発途中です。<br />
	当サイトの御利用につき、何らかのトラブルや損失・損害等につきましては一切責任を問わないものとします。</p>
	<p>バグ・要望などは募集中です。<br />
	<a href="https://github.com/noboru-i/rsspocket">GitHub</a>にてIssueを登録して頂くか、
	<a href="https://twitter.com/noboru_i">Twitter</a>にて御連絡ください。<br />
	<a href="https://twitter.com/share" class="twitter-share-button" data-url="http://rsspocket.appspot.com/" data-text="@noboru_i" data-lang="ja" data-size="large" data-count="none">ツイート</a>
	</p>
	</div>
</div>

	</c:param>
	<c:param name="script">

<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+'://platform.twitter.com/widgets.js';fjs.parentNode.insertBefore(js,fjs);}}(document, 'script', 'twitter-wjs');</script>

	</c:param>
</c:import>
