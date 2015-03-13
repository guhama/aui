var recordsUrl = "/admin-ui/npi-entity/records";
var addUrl = "/admin-ui/npi-entity/create";
var editUrl = "/admin-ui/npi-entity/update";
var deleteUrl = "/admin-ui/npi-entity/delete";

var npiGridData = {
				url : recordsUrl,
				datatype : 'json',
				mtype : 'GET',
				colNames : [ 'Id', 'Entity Type', 'Entity Code', 'NPI Num', 
				             'Eff Date', 'Exp Date'],
				colModel : [ {
					name : 'id',
					index : 'id',
					width : 1,
					editable : false,
					editoptions : {
						readonly : true,
						size : 250
					},
					hidden : true
				}, {
					name : 'entityType',
					index : 'entityType',
					width : 150,
					editable : true,
					visibleOnAdd: true,
					editrules : {
						required : true
					},
					editoptions : {
						size : 10
					}
				}, {
					name : 'entityCode',
					index : 'entityCode',
					width : 150,
					editable : true,
					visibleOnAdd: true,
					editrules : {
						required : true
					},
					editoptions : {
						size : 15
					}
				}, {
					name : 'npiNum',
					index : 'npiNum',
					width : 150,
					editable : true,
					visibleOnAdd: true,
					editrules : {
						required : true
					},
					editoptions : {
						size : 12
					}
				}, {
					name : 'effDate',
					index : 'effDate',
					width : 150,
					editable : true,
					visibleOnAdd: true,
					formatter : 'date',
					formatoptions : {
						srcformat : 'Y-m-d',
						newformat : 'm/d/Y',
						defaultValue : null
					},
					editrules : {
						required : true
					},
					editoptions : {
						size : 12,
						maxlengh : 12,
						dataInit : function(element) {
							$(element).datepicker({
								dateFormat : 'mm/dd/yy'
							})
						}
					},
					searchoptions : {
						sopt : [ 'cn' ],
						dataInit : function(elem) {
							$(elem).datepicker({
								dateFormat : 'mm/dd/yy',
								onClose : function(dateText, inst) {
									$("#grid")[0].triggerToolbar();
								}
							})
						}
					}
				}, {
					name : 'expDate',
					index : 'expDate',
					width : 150,
					editable : true,
					formatter : 'date',
					formatoptions : {
						srcformat : 'Y-m-d',
						newformat : 'm/d/Y',
						defaultValue : null
					},
					editrules : {
						required : true
					},
					editoptions : {
						size : 12,
						maxlengh : 12,
						dataInit : function(element) {
							$(element).datepicker({
								dateFormat : 'mm/dd/yy'
							})
						}
					},
					searchoptions : {
						sopt : [ 'cn' ],
						dataInit : function(elem) {
							$(elem).datepicker({
								dateFormat : 'mm/dd/yy',
								onClose : function(dateText, inst) {
									$("#grid")[0].triggerToolbar();
								}
							})
						}
					}
				}, ],
				postData : {},
				rowNum : 25,
				rowList : [ 25, 50, 100, 200 ],
				height : '80%',
				autowidth : true,
				shrinkToFit : false,
				rownumbers : true,
				pager : '#pager',
				sortname : 'id',
				viewrecords : true,
				sortorder : "asc",
				caption : "NPI Entity Records",
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
			};

tableInterfaceDefault(npiGridData);