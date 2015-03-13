<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<title>Clinical Administration UI</title>
<script
	src="http://ajax.googleapis.com/ajax/libs/dojo/1.10.0/dojo/dojo.js"
	data-dojo-config="async:true, parseOnLoad:true" type="text/javascript"
	charset="utf-8"></script>
<script>
	require([ "dojo/parser", "dijit/layout/ContentPane",]);
</script>

<link rel="stylesheet" type="text/css"
	href='http://ajax.googleapis.com/ajax/libs/dojo/1.9.3/dijit/themes/tundra/tundra.css' />

<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/qmr.css"/>" />
</head>
<body>
		<!-- HEADER -->
		<header class="refguide-header">
			<div class="container">
				<div class="refguide-logo">
					<a href="<c:url value="home"/>"><p class="hide-text">Premier Connect</p></a>
				</div>
				<div class="mod-app-brand refguide-title">Clinical Registry Administration</div>
				<div class="refguide-logout">Welcome <c:out value="${userInfo.id.userId}"/> | <a href="<c:url value="logout"/>">Logout</a></div>
			</div>
		</header>
		<!-- /HEADER -->

		<!-- NAV -->
		<nav class="refguide-nav">
			<div class="container">
				<div class="refguide-nav-items">
					<div class="refguide-nav-items-wrapper">
						<a id="file-upload" class="headerMenuItem refguide-nav-item-active">File Upload</a>
						<a id="table-interface" class="headerMenuItem">Table Interface</a>
					</div>
				</div>
			</div>
		</nav>
		<!-- /NAV -->

		<!-- MAIN CONTENT -->
		<section class="refguide-main">
			<div class="container">
				<div data-dojo-type="dijit/layout/ContentPane" id="center" class="tundra" href="file-upload"></div>
			</div>
		</section>
		<!-- /MAIN CONTENT -->

		<!-- FOOTER -->
		<footer class="refguide-footer">
			<div class="container">
				<address>
					&copy; Copyright 2014 Premier Inc. | 13034 Ballantyne Corporate
					Place, Charlotte, NC 28277 | 877.777.1552 | <a
						href="https://premierconnect.premierinc.com/wps/myportal/mypremier/home/actions/actionsfeed/termsandconditions">Terms
						and Conditions</a>
				</address>
			</div>
		</footer>
		<!-- /FOOTER -->
</body>
</html>