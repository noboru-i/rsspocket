<!DOCTYPE html>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<link href="/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link href="/css/bootstrap-responsive.min.css" rel="stylesheet">
	<link href="/css/rsspocket.css" rel="stylesheet">

	<title>Home</title>
</head>
<body>

<jsp:include page="/include/navbar.jsp" />

<div class="container">
	<h1>Hello ${f:h(user.email)} !!!</h1>
	<a href="${logoutUrl}" class="btn btn-primary btn-large">logout</a>
	
	<a href="/reader/fileupload" class="btn btn-primary btn-large">file upload</a>
	<a href="/pocket/auth" class="btn btn-primary btn-large">Login to Pocket</a>
</div>

</body>
</html>
