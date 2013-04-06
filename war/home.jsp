<!DOCTYPE html>
<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<link href="/css/bootstrap.min.css" rel="stylesheet" media="screen">

	<title>Home</title>
</head>
<body>

<div class="container">
	<h1>Hello ${f:h(user.email)} !!!</h1>
	<a href="${logoutUrl}" class="btn btn-primary btn-large">logout</a>
	
	<a href="/reader/fileupload" class="btn btn-primary btn-large">file upload</a>
	<a href="/pocket/auth" class="btn btn-primary btn-large">Login to Pocket</a>
</div>

</body>
</html>
