var recordsUrl = "/admin-ui/job-execution/records";

$('#file-upload').closest('li').addClass("active");

$(document).ajaxError(function(event, jqxhr, settings, thrownError) {
	var el = $('<div></div>');
	el.html(jqxhr.responseText);
	$('#alertModalBody').text($('h1', el).html());
	$('#alertModalLabel').text('Error!')
	jQuery('#cData').click();
	$('#alert-modal-container').modal('show'); 
});

$(function() {
	$("#grid").jqGrid(
			{
				url : recordsUrl,
				datatype : 'json',
				mtype : 'GET',
				colNames : [ 'Job Execution Id', 'Start Time', 'End Time',
						'Status', 'Exit Code', 'Exit Message' ],
				colModel : [ {
					name : 'jobExecutionId',
					index : 'jobExecutionId',
					width : 150,
					editable : true,
					formatter : 'integer',
					formatoptions : {
						thousandsSeparator : ""
					}
				}, {
					name : 'startTime',
					index : 'startTime',
					width : 200,
					editable : true,
					formatter : 'date',
					formatoptions : {
						srcformat : 'Y-m-d H:i',
						newformat : 'm/d/Y H:i',
						defaultValue : null
					},
					editrules : {
						required : true
					},
					editoptions : {
						size : 12,
						maxlengh : 12,
						dataInit : function(element) {
							$(element).datetimepicker({
								format : 'm/d/Y H:i',
								step : 1
							});
						}
					},
					searchoptions : {
						sopt : [ 'cn' ],
						dataInit : function(element) {
							$(element).datetimepicker({
								format : 'm/d/Y H:i',
								step : 1,
								onClose: function(){
									$("#grid")[0].triggerToolbar();
								}
							});
						}
					}
				}, {
					name : 'endTime',
					index : 'endTime',
					width : 200,
					editable : true,
					formatter : 'date',
					formatoptions : {
						srcformat : 'Y-m-d H:i',
						newformat : 'm/d/Y H:i',
						defaultValue : null
					},
					editrules : {
						required : true
					},
					editoptions : {
						size : 12,
						maxlengh : 12,
						dataInit : function(element) {
							$(element).datetimepicker({
								format : 'm/d/Y H:i',
								step : 1
							});
						}
					},
					searchoptions : {
						sopt : [ 'cn' ],
						dataInit : function(element) {
							$(element).datetimepicker({
								format : 'm/d/Y H:i',
								step : 1,
								onClose: function(){
									$("#grid")[0].triggerToolbar();
								}
							});
						}
					}
				}, {
					name : 'status',
					index : 'status',
					width : 150,
					editable : true,
					editrules : {
						required : true
					},
					editoptions : {
						size : 10
					}
				}, {
					name : 'exitCode',
					index : 'exitCode',
					width : 150,
					editable : true,
					editrules : {
						required : true
					},
					editoptions : {
						size : 2500
					}
				}, {
					name : 'exitMessage',
					index : 'exitMessage',
					width : 200,
					editable : true,
					editrules : {
						required : true
					},
					editoptions : {
						size : 2500
					}
				} ],
				postData : {},
				rowNum : 25,
				rowList : [ 25, 50, 100, 200 ],
				height : '80%',
				autowidth : true,
				shrinkToFit : false,
				rownumbers : true,
				pager : '#pager',
				sortname : 'jobExecutionId',
				viewrecords : true,
				sortorder : "desc",
				caption : "Records",
				emptyrecords : "Empty records",
				loadonce : false,
				loadComplete : function() {
				},
				jsonReader : {
					root : "rows",
					page : "page",
					total : "total",
					records : "records",
					repeatitems : false,
					cell : "cell",
					id : "id"
				}
			});

	$("#grid").jqGrid('navGrid', '#pager', {
		autowidth : true,
		edit : false,
		add : false,
		del : false,
		search : false
	});

	// Toolbar Search
	$("#grid").jqGrid('filterToolbar', {
		stringResult : true,
		searchOnEnter : true,
		defaultSearch : "cn"
	});
});