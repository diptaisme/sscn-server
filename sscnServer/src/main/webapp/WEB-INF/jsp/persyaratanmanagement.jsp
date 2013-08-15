<%@page language="java" session="true" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="userlogin" value="${sessionScope.userlogin}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>SSCN | Persyaratan</title>

<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">

<!-- Styles -->
<link href="/sscnServer/resources/css/bootstrap.css" rel="stylesheet">
<link href="/sscnServer/resources/css/bootstrap-responsive.css" rel="stylesheet">
<link href="/sscnServer/resources/css/bootstrap-overrides.css" rel="stylesheet">

<link href="/sscnServer/resources/css/ui-lightness/jquery.ui.all.css" rel="stylesheet">
<link href="/sscnServer/resources/js/plugins/datatables/DT_bootstrap.css" rel="stylesheet">
<link href="/sscnServer/resources/js/plugins/responsive-tables/responsive-tables.css"
	rel="stylesheet">

<link href="/sscnServer/resources/css/slate.css" rel="stylesheet">
<link href="/sscnServer/resources/css/slate-responsive.css" rel="stylesheet">

<!-- Javascript -->
<script src="/sscnServer/resources/js/jquery-1.7.2.min.js"></script>
<script src="/sscnServer/resources/js/jquery-ui-1.8.21.custom.min.js"></script>
<script src="/sscnServer/resources/js/jquery.ui.dialog.min.js"></script>
<script src="/sscnServer/resources/js/jquery.ui.autocomplete.js"></script>
<!--<script src="js/jquery-1.9.1.js"></script>
		<script src="js/jquery-ui-1.10.3.custom.min.js"></script>-->
<script src="/sscnServer/resources/js/jquery.ui.touch-punch.min.js"></script>
<script src="/sscnServer/resources/js/bootstrap.js"></script>

<script src="/sscnServer/resources/js/plugins/datatables/jquery.dataTables.js"></script>
<script src="/sscnServer/resources/js/plugins/datatables/DT_bootstrap.js"></script>
<script src="/sscnServer/resources/js/plugins/responsive-tables/responsive-tables.js"></script>

<script src="/sscnServer/resources/js/Slate.js"></script>

<script src="/sscnServer/resources/js/demos/demo.tables.js"></script>

<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
		<script src="../../../html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->

</head>

<body>

	<div id="header">

		<div class="container">

			<h1>
				<a href="default.htm">Slate Admin 2.0</a>
			</h1>

			<div id="info">

				<a href="javascript:;" id="info-trigger"> <i class="icon-cog"></i>
				</a>

				<div id="info-menu">

					<div class="info-details">

						<h4>Welcome back ${userLogin.nama}</h4>

						<p>
							Logged in as 
						</p>

					</div>
					<!-- /.info-details -->

					<div class="info-avatar">

						<img src="img/avatar.jpg" alt="avatar">

					</div>
					<!-- /.info-avatar -->

				</div>
				<!-- /#info-menu -->

			</div>
			<!-- /#info -->

		</div>
		<!-- /.container -->

	</div>
	<!-- /#header -->

	<div id="nav">

		<div class="container">

			<a href="javascript:;" class="btn-navbar" data-toggle="collapse"
				data-target=".nav-collapse"> <i class="icon-reorder"></i> </a>

			<div class="nav-collapse">

				<ul class="nav">

					<li class="nav-icon active"><a href="index.html"> <i
							class="icon-home"></i> <span>Home</span> </a></li>

					<li class="dropdown"><a href="/sscnServer/user.do"
						class="dropdown-toggle"> <i class="icon-th"></i> User
							Management <b class="caret"></b> </a></li>
							<li class="dropdown"><a href="/sscnServer/lokasi.do"
						class="dropdown-toggle"> <i class="icon-th"></i> Lokasi
							Management <b class="caret"></b> </a></li>

					<li class="dropdown"><a href="/sscnServer/formasi.do"
						class="dropdown-toggle"> <i class="icon-copy"></i> Formasi <b
							class="caret"></b> </a></li>

					<li class="dropdown"><a href="javascript:;"
						class="dropdown-toggle" data-toggle="dropdown"> <i
							class="icon-external-link"></i> Verifikasi <b class="caret"></b>
					</a>

						<ul class="dropdown-menu">
							<li><a href="login.html">Login</a></li>
							<li><a href="signup.html">Signup</a></li>
							<li><a href="error.html">Error</a></li>
							<li class="dropdown"><a href="javascript:;"> Dropdown
									Menu <i class="icon-chevron-right sub-menu-caret"></i> </a>

								<ul class="dropdown-menu sub-menu">
									<li><a href="javascript:;">Dropdown #1</a></li>
									<li><a href="javascript:;">Dropdown #2</a></li>
									<li><a href="javascript:;">Dropdown #3</a></li>
									<li><a href="javascript:;">Dropdown #4</a></li>
								</ul></li>
						</ul></li>

					<li class="dropdown"><a href="javascript:;"
						class="dropdown-toggle" data-toggle="dropdown"> <i
							class="icon-external-link"></i> Pengumuman <b class="caret"></b>
					</a></li>
				</ul>

				<ul class="nav pull-right">

					<li class="">
						<form class="navbar-search pull-left">
							<input type="text" class="search-query" placeholder="Search">
							<button class="search-btn">
								<i class="icon-search"></i>
							</button>
						</form></li>

				</ul>

			</div>
			<!-- /.nav-collapse -->

		</div>

	</div>
	<!-- /#nav -->

	<div id="content">

		<div class="container">

			<div id="page-title" class="clearfix">

				<ul class="breadcrumb">
					<li><a href="../../default.htm">Home</a><span class="divider">/</span>
					</li>
					<li><a href="#">Persyaratan</a><span class="divider">/</span>
					</li>
					<li class="active">List</li>
				</ul>

			</div>
			<!-- /.page-title -->

			<div class="row">
				<div class="span12">

					<div class="widget widget-table">

						<div class="widget-header">
							<h3>
								<i class="icon-th-list"></i> Persyaratan
							</h3>
						</div>
						<!-- /widget-header -->

						<div class="widget-content">
							<div id="example_wrapper" class="dataTables_wrapper form-inline"
								role="grid">
								<div class="row">
									<div class="span6">
										<div class="dataTables_length">
											<label> <select name="example_length" size="1"
												aria-controls="example">
													<option value="10" selected="selected">10</option>
													<option value="25">25</option>
													<option value="50">50</option>
													<option value="100">100</option>
											</select> records per page</label>
										</div>
									</div>
									<div class="span6">
										<div class="dataTables_filter">
											<label>Search: <input type="text"
												aria-controls="example"> </label>
										</div>
									</div>
								</div>

								<div class="row">
									<div class="span6 pull-right">
										<a href="#" id="addModal"
											class="btn btn-small btn-primary"><i class="icon-plus"></i>Tambah
											Persyaratan</a>
										
									</div>
								</div>
								<table
									class="table table-striped table-bordered table-highlight" id="myTable">
									<thead>
										<tr>
											<th>No</th>
											<th>Text</th>											
											<th>Action(s)</th>
										</tr>
									</thead>
									<tbody>					
										<c:set var="i" value="0"/>					
										<c:forEach items="${reqs}" var="req">
											<tr class="odd gradeX">
												<td>${i++}</td>
												<td>${req.syarat}</td>												
												<td><a href="#" onclick="prepareUbahForm(this,'${req.id }')"
											class="btn btn-small btn-primary"><i class="icon-edit"></i>Edit</a> | <a href="#" onclick="confirmDelete(this,'${req.id}')"
											class="btn btn-small btn-primary"><i class="icon-remove"></i>Delete</a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<div class="row">
									<div class="span6">
										<div class="dataTables_info" id="example_info">Showing 1
											to 10 of 57 entries</div>
									</div>
									<div class="span6">
										<div class="dataTables_paginate paging_bootstrap pagination">
											<ul>
												<li class="prev disabled"><a href="#">â? Previous</a>
												</li>
												<li class="active"><a href="#">1</a></li>
												<li><a href="#">2</a></li>
												<li><a href="#">3</a></li>
												<li><a href="#">4</a></li>
												<li><a href="#">5</a></li>
												<li class="next"><a href="#">Next â?? </a></li>
											</ul>
										</div>
									</div>
								</div>

							</div>
						</div>
						<!-- /widget-content -->

					</div>
					<!-- /widget -->

				</div>
				<!-- /span12 -->
			</div>
			<!-- /row -->
		</div>
		<!-- /.container -->
	</div>
	<!-- /#content -->

	<div id="footer">

		<div class="container">&copy; 2012 Propel UI, all rights
			reserved.</div>
		<!-- /.container -->

	</div>
	<!-- /#footer -->

	<div id="myModal" title="Tambah Persyaratan">
		<form class="form-horizontal" action="/sscnServer/reqSave.do" method="post" id="formAdd">
				<fieldset>
					<div id="loadingImage" style="display: none">
						<img src="/resources/img/ajax-loader.gif" />
					</div>
					<div id="alert" class="alert alert-error" style="display: none">											
					</div>
					<div class="control-group">
						<label class="control-label" for="input01">Nama</label>
						<div class="controls">
							<textarea rows="3" id="syarat" name="syarat" class="input-large"></textarea>
						</div>
					</div>
										
					<div class="form-actions">
						<button type="submit" class="btn btn-primary btn-large">
							Save changes
						</button>
						<button class="btn btn-large" id="btnCancelFAddSyarat">
							Cancel
						</button>
					</div>
				</fieldset>
			</form>
	</div>
	
	<div id="myModal2" title="Ubah Persyaratan">
		<form class="form-horizontal" action="/sscnServer/syaratUpdate.do" method="post" id="formUbah">
				<fieldset>
					<div id="loadingImage2" style="display: none">
						<img src="/resources/img/ajax-loader.gif" />
					</div>
					<div id="alert2" class="alert alert-error" style="display: none">											
					</div>
										
										<div class="control-group">
						<label class="control-label" for="input01">Nama</label>
						<div class="controls">
							<textarea rows="3" id="edsyarat" name="syarat" class="input-large"></textarea>
						</div>
					</div>
										
					<div class="form-actions">
						<button type="submit" class="btn btn-primary btn-large">
							Save changes
						</button>
						<button class="btn btn-large" id="btnCancelFEditSyarat">
							Cancel
						</button>
					</div>
					
				</fieldset>
			</form>
	</div>
	
	<div id="myModal3" title="Hapus Persyaratan">
		<form class="form-horizontal" action="/sscnServer/syaratDelete.do" method="post" id="formDelete">
				<fieldset>
					<div id="loadingImage3" style="display: none">
						<img src="/resources/img/ajax-loader.gif" />
					</div>
					<div id="alert3" class="alert alert-warning" >
						Apakah anda yakin ingin menghapus data ini ?											
					</div>
					<input type="hidden" name="id" id="syaratId"/>
					
					<div class="form-actions">
						<button type="submit" class="btn btn-primary btn-large">
							Ya
						</button>
						<button class="btn btn-large" id="btnCancelFDelete">
							Tidak
						</button>
					</div>
				</fieldset>
			</form>
	</div>

	<script>
		jQuery(document).ready(function() {

			$("#myModal").dialog({
				autoOpen : false,
				height : 350,
				width : 500,
				modal : true
			});

			$("#myModal2").dialog({
				autoOpen : false,
				height : 350,
				width : 500,
				modal : true
			});

			$("#myModal3").dialog({
				autoOpen : false,
				height : 260,
				width : 400,
				modal : true
			});

			var selRowTable;
			prepareUbahForm = function(elem, id) {
				selRowTable = $(elem).closest('tr');
				
				$.ajax({
				  type: "GET",
				  url: "/sscnServer/getSyarat.do?id="+id,
				  cache: false,
				  success: function(data){
					 $('#edsyarat').val(data.data.syarat);
					 $("#myModal2").dialog("open");
				  },
				  dataType:"json"
				});
				
			};

			$("#formUbah").submit(function(event) {
				 
				  /* stop form from submitting normally */
				  event.preventDefault();
				  
				  $('#loadingImage2').show();
				  $('#alert2').hide();
				  
				  /* get some values from elements on the page: */
				  var $form = $( this ),
				      term = $(this).serialize(),
				      url = $form.attr( 'action' );
				 
				  /* Send the data using post */
				  var posting = $.post( url, term,"json"
				  					  );
				 
				  /* Put the results in a div */
				  posting.done(function( data ) {
				  		$('#loadingImage2').hide();
				  		if (data.result == 0){
				  			var html = '<strong>Error!</strong> ' + data.message;
							$('#alert2').html(html);	
							$('#alert2').show();						
				  		} 
				  		
				  		if (data.result == 1){
				  			$("#myModal2").dialog("close");
				  			$('#edsyarat').val('');
				  			
				  			refreshUpdateTable(data.data);
			           		return false;
				  		}

				  		if (data.result == -1){
					  		window.location = "/sscnServer/login.do";
					  	}
				  });
				  
				  posting.error(function(){
			           alert('failure');        
			      });   
				});

			function refreshUpdateTable(data){
				
				var tesHtml = '<tr class="odd gradeX"> '+
					'<td>'+data.urutan+'</td> '+
					'<td>'+data.syarat+'</td> '+
					'<td><a href="#" onclick="prepareUbahForm(this,\''+data.id+'\')" '+
				'class="btn btn-small btn-primary"><i class="icon-edit"></i>Edit</a> | <a href="#" id="deleteUserModal" '+
				'class="btn btn-small btn-primary" confirmDelete(this,\''+data.id+'\')><i class="icon-remove"></i>Delete</a></td> '+
				'</tr>';
				
				$(selRowTable).replaceWith(tesHtml);
			}

			
			confirmDelete = function(elem, id) {
				selRowTable = $(elem).closest('tr');
				 $('#syaratId').val(id);				 
			     $("#myModal3").dialog("open");				
			};

			$("#formDelete").submit(function(event) {
				 
				  /* stop form from submitting normally */
				  event.preventDefault();
				  
				  $('#loadingImage3').show();
				 
				  /* get some values from elements on the page: */
				  var $form = $( this ),
				      term = $(this).serialize(),
				      url = $form.attr( 'action' );
				 
				  /* Send the data using post */
				  var posting = $.post( url, term,"json"
				  					  );
				 
				  /* Put the results in a div */
				  posting.done(function( data ) {
				  		$('#loadingImage3').hide();
				  		if (data.result == 0){
				  			var html = '<strong>Error!</strong> ' + data.message;
							$('#alert3').html(html);	
							$('#alert3').show();						
				  		} 
				  		
				  		if (data.result == 1){
				  			$("#myModal3").dialog("close");
				  			$('#syaratId').val('');
				  			
				  			refreshDeleteTable();
			           		return false;
				  		}

				  		if (data.result == -1){
					  		window.location = "/sscnServer/login.do";
					  	}
				  });
				  
				  posting.error(function(){
			           alert('failure');        
			      });   
				});

			function refreshDeleteTable(){
				
				var tesHtml = '';
				
				$(selRowTable).replaceWith(tesHtml);
			}
			
			$('#addModal').click(function() {
				$("#myModal").dialog("open");
			});

			$('#btnCancelFAddSyarat').click(function() {
				$("#myModal").dialog("close");
			});
			
		});
	</script>
	
	<script>
			jQuery(document).ready(function() {				

				$("#formAddUser").submit(function(event) {
					 
					  /* stop form from submitting normally */
					  event.preventDefault();
					  
					  $('#loadingImage').show();
					  $('#alert').hide();
					  //$('#alert').show();
					 
					  /* get some values from elements on the page: */
					  var $form = $( this ),
					      term = $(this).serialize(),
					      url = $form.attr( 'action' );
					 
					  /* Send the data using post */
					  var posting = $.post( "http://localhost:8080/sscnServer/userSave.do", term,"json"
					  					  );
					 
					  /* Put the results in a div */
					  posting.done(function( data ) {
					  		$('#loadingImage').hide();
					  		if (data.result == 0){
					  			var html = '<strong>Error!</strong> ' + data.message;
								$('#alert').html(html);	
								$('#alert').show();						
					  		} 
					  		
					  		if (data.result == 1){
					  			$("#myModal").dialog("close");
					  			$('#username').val('');
					  			$('#nip').val('');
					  			$('#password').val('');
					  			$('#instansiValue').val('');
					  			$('#instansi').val('');
					  			alert(data.message);
					  			refreshTable(data.data);
				           		return false;
					  		}

					  		if (data.result == -1){
						  		window.location = "http://localhost/sscnServer/login.do";
						  	}
					  });
					  
					  posting.error(function(){
				           alert('failure');        
				      });   
					});

				function refreshTable(data){
					var table = document.getElementById('myTable');
					var lenRow = table.rows.length;
					var row = table.insertRow(lenRow);
					var colCount = table.rows[0].cells.length;
					var profile = '';
					if (data.kewenangan == '1'){
						profile = 'Administrator';
					} else if (data.kewenangan == '2'){
						profile = 'Admin Instansi';
					} else if (data.kewenangan == '3'){
						profile = 'Verifikator';
					} else {
						profile = 'unknown profile';
					}
					var newRowHtml = '<tr class="odd gradeX"><td>'+ data.username+'</td><td>'+ data.refInstansi.nama +'</td><td>'+profile+'</td><td><a href="#" onclick="prepareUbahForm(this,\''+data.username+'\')" '+
					'class="btn btn-small btn-primary"><i class="icon-edit"></i>Edit</a> | <a href="#" id="deleteUserModal" '+
					'class="btn btn-small btn-primary" confirmDelete(this,\''+data.username+'\')><i class="icon-remove"></i>Delete</a></td></tr>';
					row.innerHTML = newRowHtml;	
				}
				
				$("#instansi").autocomplete({
					source : function(request, response) {
						$.ajax({
							//url : "http://ws.geonames.org/searchJSON",
							url : "http://localhost:8080/sscnServer/findInstansiLikeByName.do",
							dataType : "jsonp",
							data : {
								featureClass : "P",
								style : "full",
								maxRows : 12,
								name_startsWith : request.term
							},
							success : function(data) {
								response($.map(data.instansis, function(item) {
									return {
										code: item.kode,
						                label: item.nama,
						                value: item.nama
						            }
								}));
							}
						});
					},
					minLength : 2,
					select : function(event, ui) {
						log(ui.item ? "Selected: " + ui.item.label : "Nothing selected, input was " + this.value);
						$('#instansiValue').val(ui.item.code);
					},
					open : function() {
						$(this).removeClass("ui-corner-all").addClass("ui-corner-top");
					},
					close : function() {
						$(this).removeClass("ui-corner-top").addClass("ui-corner-all");
					}
				}); 
			});
		</script>
</body>
</html>
