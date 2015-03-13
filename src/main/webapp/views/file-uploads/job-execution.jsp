<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<html>
<head>
<title>Clinical Administration UI</title>

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/qmr.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/bootstrap/css/bootstrap.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/bootstrap/css/bootstrap-theme.min.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/external-js/jquery-ui/jquery-ui.min.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/external-js/jquery-ui/jquery-ui.structure.min.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/external-js/jquery-ui/jquery-ui.theme.min.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/ui.jqgrid.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/external-js/jquery-datetimepicker/jquery.datetimepicker.css"/>" />

</head>
<body>
	<%@ include file="../common/header.jsp"%>

	<div class="container-fluid">

		<div class="row clearfix">

			<%@ include file="../common/file-upload-menu.jsp"%>

			<div class="col-md-10 column">

				<%@ include file="../common/message.jsp"%>


				<div id="jqgrid" style="width: 100%">
					<table id="grid"></table>
					<div id="pager"></div>
				</div>

				<%@ include file="../common/alert-modal.jsp"%>

			</div>
		</div>

		<%@ include file="../common/footer.jsp"%>
	</div>


	<script src="<c:url value="/resources/external-js/jquery-1.11.1.min.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/resources/external-js/jquery-ui/jquery-ui.min.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>"></script>
	<script src="<c:url value="/resources/external-js/jquery-jqgrid/js/i18n/grid.locale-en.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/resources/external-js/jquery-jqgrid/js/jquery.jqGrid.min.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/resources/external-js/jquery-datetimepicker/jquery.datetimepicker.js"/>"></script>
	<script src="<c:url value="/resources/js/file-uploads/job-execution.js"/>" type="text/javascript"></script>
</body>
</html>