$('#table-interface').closest('li').addClass("active");

$(document).ajaxError(function(event, jqxhr, settings, thrownError) {
	var el = $('<div></div>');
	el.html(jqxhr.responseText);
	$('#alertModalBody').text($('h1', el).html());
	$('#alertModalLabel').text('Error!')
	jQuery('#cData').click();
	$('#alert-modal-container').modal('show');
});

function tableInterfaceDefault(tableInterfaceObject) {
	$(function() {
		$("#grid").jqGrid(tableInterfaceObject);
		
		$("#grid").jqGrid('navGrid', '#pager', {
			autowidth : true,
			edit : false,
			add : false,
			del : false,
			search : false
		});

		$("#grid").navButtonAdd('#pager', {
			caption : "Add",
			buttonicon : "ui-icon-plus",
			onClickButton : function(){return addRow(tableInterfaceObject.colModel);},
			position : "last",
			title : "",
			cursor : "pointer",
			id : "addButton"
		});

		$("#grid").navButtonAdd('#pager', {
			caption : "Edit",
			buttonicon : "ui-icon-pencil",
			onClickButton :  function(){return editRow(tableInterfaceObject.colModel);},
			position : "last",
			title : "",
			cursor : "pointer",
			id : "editButton"
		});

		$("#grid").navButtonAdd('#pager', {
			caption : "Delete",
			buttonicon : "ui-icon-trash",
			onClickButton : deleteRow,
			position : "last",
			title : "",
			cursor : "pointer",
			id : "deleteButton"
		});

		// Toolbar Search
		$("#grid").jqGrid('filterToolbar', {
			stringResult : true,
			searchOnEnter : true,
			defaultSearch : "cn"
		});
	});
}

function addRow(tableColModel) {

	// Get the currently selected row
	$('#grid').jqGrid(
			'editGridRow',
			'new',
			{
				url : addUrl,
				editData : {},
				serializeEditData : function(data) {
					data.id = 0;
					return $.param(data);
				},
				recreateForm : true,
				beforeShowForm : function(form) {
					$('#pData').hide();
					$('#nData').hide();
					
					var names = isShowOnAdd(tableColModel, form);
					$.each(names, function(k,v){
						$('#tr_'+v, form).show();
					});
				},
				beforeInitData : function(form) {
				},
				closeAfterAdd : true,
				reloadAfterSubmit : true,
				afterSubmit : function(response, postdata) {
					var result = eval('(' + response.responseText + ')');
					var errors = "";

					if (result.success == false) {
						return [ true, '', '' ];
					} else {
						handleMessage("SUCCESS",
								"Entry has been added successfully!", 5000);
					}
					// only used for adding new records
					var newId = null;

					return [ result.success, errors, newId ];
				}
			});
} // end of addRow

function editRow(tableColModel) {

	// Get the currently selected row
	var row = $('#grid').jqGrid('getGridParam', 'selrow');

	if (row != null) {

		$('#grid').jqGrid('editGridRow', row, {
			url : editUrl,
			editData : {},
			recreateForm : true,
			beforeShowForm : function(form) {
				$('#pData').hide();
				$('#nData').hide();
				
				var names = isShowOnAdd(tableColModel, form);
				$.each(names, function(k,v){
					$('#tr_'+v, form).hide();
				});
			},
			beforeInitData : function(form) {
			},
			closeAfterEdit : true,
			reloadAfterSubmit : true,
			afterSubmit : function(response, postdata) {
				var result = eval('(' + response.responseText + ')');
				var errors = "";

				if (result.success == false) {
					for (var i = 0; i < result.message.length; i++) {
						errors += result.message[i] + "<br/>";
					}
				} else {
					$('#msgbox').text('Entry has been edited successfully');
					$('#msgbox').dialog({
						title : 'Success',
						modal : true,
						buttons : {
							"Ok" : function() {
								$(this).dialog("close");
							}
						}
					});
				}
				// only used for adding new records
				var newId = null;

				return [ result.success, errors, newId ];
			}
		});
	} else {
		handleMessage("ERROR", "You must select a record first!", 5000);
	}
}

function deleteRow(obj, args) {
	// Get the currently selected row
	var row = $('#grid').jqGrid('getGridParam', 'selrow');

	// A pop-up dialog will appear to confirm the selected action
	if (row != null)
		$('#grid').jqGrid(
				'delGridRow',
				row,
				{
					url : deleteUrl,
					recreateForm : true,
					beforeShowForm : function(form) {
						// Change title
						$(".delmsg")
								.replaceWith(
										'<span style="white-space: pre;">'
												+ 'Delete selected record?'
												+ '</span>');
						// hide arrows
						$('#pData').hide();
						$('#nData').hide();
					},
					reloadAfterSubmit : true,
					closeAfterDelete : true,
					serializeDelData : function(postdata) {
						var rowdata = $('#grid').getRowData(postdata.id);
						// append postdata with any information
						return {
							id : postdata.id,
							oper : postdata.oper,
							username : rowdata.username
						};
					},
					afterSubmit : function(response, postdata) {
						var result = eval('(' + response.responseText + ')');
						var errors = "";

						if (result.success == false) {
							for (var i = 0; i < result.message.length; i++) {
								errors += result.message[i] + "<br/>";
							}
						} else {
							$('#msgbox').text(
									'Entry has been deleted successfully');
							$('#msgbox').dialog({
								title : 'Success',
								modal : true,
								buttons : {
									"Ok" : function() {
										$(this).dialog("close");
									}
								}
							});
						}
						// only used for adding new records
						var newId = null;

						return [ result.success, errors, newId ];
					}
				});
	else {
		handleMessage("ERROR", "You must select a record first!", 5000);
	}
}

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

function isShowOnAdd(tableColModels, form){
	var names = [];
	$.each(tableColModels, function(k, v){
		if(v.visibleOnAdd === true){
			names.push(v.name);
		}
	});
	
	return names;
}