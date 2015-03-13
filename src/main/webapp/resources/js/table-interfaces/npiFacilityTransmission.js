var recordsUrl = "/admin-ui/npi-facility-transmission/records";
var addUrl = "/admin-ui/npi-facility-transmission/create";
var editUrl = "/admin-ui/npi-facility-transmission/update";
var deleteUrl = "/admin-ui/npi-facility-transmission/delete";

var npiGridData = {
				url : recordsUrl,
				datatype : 'json',
				mtype : 'GET',
				colNames : [ 'Id', 'Client Id', 'Facility Id', 'Initiative Id',
						'Measure Set Id', 'Ms Measure Id', 'Entity Type',  'Entity Code',
						'NPI Num', 'Eff Date', 'Exp Date', 'Transmit Ind' ],
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
					name : 'clientId',
					index : 'clientId',
					width : 150,
					visibleOnAdd: true,
					editable : true,
					editrules : {
						required : true
					},
					editoptions : {
						size : 15
					}
				}, {
					name : 'facilityId',
					index : 'facilityId',
					width : 150,
					visibleOnAdd: true,
					editable : true,
					editrules : {
						required : true
					},
					editoptions : {
						size : 15
					}
				}, {
					name : 'initiativeId',
					index : 'initiativeId',
					width : 150,
					visibleOnAdd: true,
					editable : true,
					formatter : 'integer',
					formatoptions : {
						thousandsSeparator : ""
					},
					editrules : {
						required : true
					}
				}, {
					name : 'measureSetId',
					index : 'measureSetId',
					width : 150,
					visibleOnAdd: true,
					editable : true,
					formatter : 'integer',
					formatoptions : {
						thousandsSeparator : ""
					},
					editrules : {
						required : true
					}
				}, {
					name : 'msMeasureId',
					index : 'msMeasureId',
					width : 150,
					visibleOnAdd: true,
					editable : true,
					formatter : 'integer',
					formatoptions : {
						thousandsSeparator : ""
					},
					editrules : {
						required : true
					}
				}, {
					name : 'entityType',
					index : 'entityType',
					width : 150,
					visibleOnAdd: true,
					editable : true,
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
					visibleOnAdd: true,
					editable : true,
					editrules : {
						required : true
					},
					editoptions : {
						size : 10
					}
				}, {
					name : 'npiNum',
					index : 'npiNum',
					width : 150,
					visibleOnAdd: true,
					editable : true,
					editrules : {
						required : true
					},
					editoptions : {
						size : 10
					}
				}, {
					name : 'effDate',
					index : 'effDate',
					width : 150,
					visibleOnAdd: true,
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
				}, {
					name : 'transmitInd',
					index : 'transmitInd',
					width : 150,
					editable : true,
					formatter : 'integer',
					formatoptions : {
						thousandsSeparator : ""
					},
					edittype : 'select',
					editoptions : {
						value : "0:0;1:1;2:2"
					},
					editrules : {
						required : true
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
			};

tableInterfaceDefault(npiGridData);