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
</head>
<body>

	<%@ include file="../common/header.jsp"%>

	<div class="container-fluid">

		<div class="row clearfix">

			<%@ include file="../common/file-upload-menu.jsp"%>

			<div class="col-md-10 column">

				<%@ include file="../common/message.jsp"%>

				<fieldset>
					<legend>NPI Facility Transmission Upload</legend>
					<div class="row clearfix">
						<div class="col-sm-10">
							<input id="fileupload" type="file" class="upload" name="file" data-url="file-upload">
						</div>
						<div class="col-sm-2">
							<button id="submitUpload" class="btn btn-success">
								Upload <span class="glyphicon glyphicon-circle-arrow-up"></span>
							</button>
							<button id="reset" class="btn btn-warning">
								Reset <span class="glyphicon glyphicon-refresh"></span>
							</button>
						</div>
					</div>
					<div class="row clearfix">
						<div class="col-sm-12" style="padding-top: 5px;">
							<div class="progress">
								<div id="progressBar" class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%">
									<span id="progressBarText">0% Complete</span>
								</div>
							</div>
						</div>
					</div>
					<div class="row clearfix">
						<div class="col-sm-12">
							<div id="dropzone" class="fade"><strong>Drop files here!</strong></div>
						</div>
					</div>
				</fieldset>

				<%@ include file="../common/alert-modal.jsp"%>

			</div>
		</div>

		<%@ include file="../common/footer.jsp"%>
	</div>



	<script src="<c:url value="/resources/external-js/jquery-1.11.1.min.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/resources/external-js/jquery-ui/jquery-ui.min.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>"></script>
	<script src="<c:url value="/resources/external-js/jquery-file-upload/js/vendor/jquery.ui.widget.js"/>"></script>
	<script src="<c:url value="/resources/external-js/jquery-file-upload/js/jquery.iframe-transport.js"/>"></script>
	<script src="<c:url value="/resources/external-js/jquery-file-upload/js/jquery.fileupload.js"/>"></script>
	<script src="<c:url value="/resources/external-js/jquery-filestyle/jquery.filestyle.min.js"/>"></script>
	<script src="<c:url value="/resources/js/file-uploads/fileUpload.js"/>" type="text/javascript"></script>
</body>
</html>