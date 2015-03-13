$('#file-upload').closest('li').addClass("active");

$("input[type=file]").filestyle();

$("#reset").click(function() {
	location.reload();
});

$(document).bind('dragover', function(e) {
	var dropZone = $('#dropzone'), timeout = window.dropZoneTimeout;
	if (!timeout) {
		dropZone.addClass('in');
	} else {
		clearTimeout(timeout);
	}
	var found = false, node = e.target;
	do {
		if (node === dropZone[0]) {
			found = true;
			break;
		}
		node = node.parentNode;
	} while (node != null);
	if (found) {
		dropZone.addClass('hover');
	} else {
		dropZone.removeClass('hover');
	}
	window.dropZoneTimeout = setTimeout(function() {
		window.dropZoneTimeout = null;
		dropZone.removeClass('in hover');
	}, 100);
});

$(function() {
	$('#fileupload').fileupload({
		dataType : 'json',
		dropZone : $('#dropzone'),
		filesContainer : '#fileList',
		done : function(e, data) {
			handleMessage(data.result.messageType, data.result.messageContent);
		},
		progressall : function(e, data) {
			var progress = parseInt(data.loaded / data.total * 100, 10);
			$('#progressBar').css('width', progress + '%');
			$('#progressBar').attr('aria-valuenow',progress);
			$('#progressBarText').html(progress + '% Complete');
		},
		add : function(e, data) {
			data.context = $('#submitUpload').click(function() {
				data.submit();
			});

			var fileString = 'Added the following files: ';
			$.each(data.originalFiles, function(index, file) {
				fileString = fileString + file.name + ' ';
			});
			handleMessage('INFO', fileString);
		}
	});
});

function handleMessage(messageType, messageContent, removeTimeoutInMs) {
	$("#form-message").removeClass(
			"alert-success alert-error alert-warn alert-info");
	if (messageType == "SUCCESS") {
		$("#form-message").addClass("alert-success");
		$("#form-message-header").html("Success!");
	} else if (messageType == "ERROR") {
		$("#form-message").addClass("alert-danger");
		$("#form-message-header").html("Error!");
	} else if (messageType == "WARN") {
		$("#form-message").addClass("alert-warning");
		$("#form-message-header").html("Warning!");
	} else {
		$("#form-message").addClass("alert-info");
		$("#form-message-header").html("Information:");
	}

	$("#form-message-text").html(messageContent);

	$("#form-message").show("slow");

	if (removeTimeoutInMs != null && removeTimeoutInMs != undefined) {
		setTimeout(function() {
			$("#form-message").hide("slow")
		}, removeTimeoutInMs)
	}
}