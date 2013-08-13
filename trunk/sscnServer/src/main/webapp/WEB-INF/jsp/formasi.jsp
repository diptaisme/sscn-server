<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>SSCN | Formasi Management</title>

<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">

<!-- Styles -->
<link href="/sscnServer/resources/css/bootstrap.css" rel="stylesheet">
<link href="/sscnServer/resources/css/bootstrap-responsive.css" rel="stylesheet">
<link href="/sscnServer/resources/css/bootstrap-overrides.css" rel="stylesheet">

<link href="/sscnServer/resources/css/ui-lightness/jquery.ui.all.css" rel="stylesheet">
<!--<link href="css/ui-lightness/jquery.ui.dialog.css" rel="stylesheet">
		<link href="css/ui-lightness/jquery.ui.resizable.css" rel="stylesheet">-->
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

						<h4>Welcome back, John D.</h4>

						<p>
							Logged in as Admin. <br> You have <a href="javascript:;">5
								messages.</a>
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
				data-target=".nav-collapse"> <i class="icon-reorder"></i>
			</a>

			<div class="nav-collapse">

				<ul class="nav">

					<li class="nav-icon active"><a href="index.html"> <i
							class="icon-home"></i> <span>Home</span>
					</a></li>

					<li class="dropdown"><a href="usermanagement.html"
						class="dropdown-toggle"> <i class="icon-th"></i> User
							Management <b class="caret"></b>
					</a> <!--
							<ul class="dropdown-menu">
							<li>
							<a href="forms.html">Forms</a>
							</li>
							<li>
							<a href="ui-elements.html">UI Elements</a>
							</li>
							<li>
							<a href="grid.html">Grid Layout</a>
							</li>
							<li>
							<a href="tables.html">Tables</a>
							</li>
							<li>
							<a href="widgets.html">Widget Boxes</a>
							</li>
							<li>
							<a href="charts.html">Charts</a>
							</li>
							<li>
							<a href="tabs.html">Tabs & Accordion</a>
							</li>
							<li>
							<a href="buttons.html">Buttons</a>
							</li>
							</ul>--></li>

					<li class="dropdown"><a href="/sscnServer/formasi.do"
						class="dropdown-toggle"> <i class="icon-copy"></i> Formasi <b
							class="caret"></b>
					</a></li>

					<li class="dropdown"><a href="javascript:;"
						class="dropdown-toggle" data-toggle="dropdown"> <i
							class="icon-external-link"></i> Verifikasi <b class="caret"></b>
					</a>

						<ul class="dropdown-menu">
							<li><a href="login.html">Login</a></li>
							<li><a href="signup.html">Signup</a></li>
							<li><a href="error.html">Error</a></li>
							<li class="dropdown"><a href="javascript:;"> Dropdown
									Menu <i class="icon-chevron-right sub-menu-caret"></i>
							</a>

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
						</form>
					</li>

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
					<li><a href="#">User Management</a><span class="divider">/</span>
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
								<i class="icon-th-list"></i> Formasi <b>${instansiNama}</b>
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
											</select> records per page
											</label>
										</div>
									</div>
									<div class="span6">
										<div class="dataTables_filter">
											<label>Search: <input type="text"
												aria-controls="example">
											</label>
										</div>
									</div>
								</div>
							<!-- 
								<div class="row">
									<div class="span6 pull-right">
										<a href="#" id="addFormasiModal"
											class="btn btn-small btn-primary"><i class="icon-plus"></i>Add
											Formasi</a>
									</div>
								</div>
							 -->	
								<table
									class="table table-striped table-bordered table-highlight">
									<thead>
										<tr>
											<th>Nama Jabatan</th>
											<th>Pendidikan</th>
											<th>Jumlah</th>
											<th>Lokasi</th>
											<th>Action(s)</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${formasis}" var="formasi">
											
											<tr class="odd gradeX">
												<td>${formasi.refJabatan.nama}</td>
												<td>
												<c:forEach items="${formasi.dtFormasis}" var="dtFormasi">
													<c:out value="${dtFormasi.pendidikan.nama}"/> (<c:out value="${dtFormasi.jumlah}"/>)  
												</c:forEach>
												</td>
												<td>${formasi.jumlah}</td>
												<td>${formasi.refLokasi.nama}</td>
												<td>Edit | Delete</td>
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
												<li class="prev disabled"><a href="#">← Previous</a>
												</li>
												<li class="active"><a href="#">1</a></li>
												<li><a href="#">2</a></li>
												<li><a href="#">3</a></li>
												<li><a href="#">4</a></li>
												<li><a href="#">5</a></li>
												<li class="next"><a href="#">Next → </a></li>
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
			
			<div class="row">
				<div class="span12">
					<div id="validation" class="widget highlight widget-form">
	      				
	      			<div class="widget-header">	      				
	      				<h3>
	      					<i class="icon-pencil"></i>
	      					Tambah Formasi				
      					</h3>	
      				</div> <!-- /widget-header -->
					
					<div class="widget-content">
						
						<div class="alert alert-block" style="display:none">
						  <a class="close" data-dismiss="alert" href="#">&times;</a>
						  <h4 class="alert-heading">Validation Example!</h4>
						  Submit the below form to view the live form validation with integrated Bootstrap error messages.
						</div>
						
						<form action="/sscnServer/formasiSave.do" method="POST" id="formasiForm" class="form-horizontal" novalidate="novalidate">
							<fieldset>
							    <div class="control-group">
							      <label class="control-label" for="lokasi">Lokasi</label>
							      <div class="controls">
							      	<input type="hidden" class="input-large" name="lokasi" id="lokasiValue">
							        <input type="text" class="input-large" name="lokasiLabel" id="lokasiLabel">
							      </div>
							    </div>
							    <div class="control-group">
							      <label class="control-label" for="jabatan">Jabatan</label>
							      <div class="controls">
							      	<input type="hidden" class="input-large" name="jabatan" id="jabatanValue">
							        <input type="text" class="input-large" name="jabatanLabel" id="jabatanLabel">
							      </div>
							    </div>
							    <div class="control-group" id="pendidikanRow">
							      <label class="control-label" for="pendidikan">Pendidikan</label>
							      <div class="controls">
							        <input type="hidden" class="input-large" name="pendidikan[]" id="pendidikanValue">
							        <input type="text" class="input-large" name="pendidikanLabel[]" id="pendidikanLabel">
							        <input type="text" class="input-small" name="pendidikanJmlh[]" id="pendidikanJmlh">
							        <button id="btnAddPddkn" class="btn btn-primary btn-small">Add</button>
							      </div>
							    </div>
							    <div class="control-group">
							      <label class="control-label" for="jumlah">Jumlah</label>
							      <div class="controls">
							        <input type="text" class="input-small" name="jumlah" id="jumlah">
							      </div>
							    </div>
							    
							    <div class="form-actions">
							      <button type="submit" class="btn btn-danger btn-large">Validate</button>
							      <button type="reset" class="btn btn-large">Cancel</button>
							    </div>
							  </fieldset>
							</form>
						
					</div> <!-- /widget-content -->
						
				</div>
					
				</div>
				<!-- /span12 -->
			</div>
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
	
	<script>
	jQuery(document).ready(function() {											
							var i = 0;
							$('#btnAddPddkn')
									.click(
											function(e) {
												e.preventDefault();

												var html = '<div class="control-group" id="pendidikanRow'
														+ (i + 1)
														+ '"> '
														+ '<label class="control-label" for="input01"></label> '
														+ '<div class="controls"> '
														+ '<div class="ui-widget">'
														+ '<input type="hidden" name="pendidikanValue[]" id="pendidikanValue"/> '
														+ '<input type="text" class="input-large" id="pendidikanLabel" name="pendidikanLabel[]"> '
														+ '<input type="text" class="input-small" id="pendidikanJmlh" name="pendidikanJmlh[]"> '
														+ '</div>'
														+ '</div>'
														+ '</div>';
												if (i == 0) {
													$(html).insertAfter(
															'#pendidikanRow');
												} else {
													$(html).insertAfter(
															'#pendidikanRow'
																	+ i);
												}

												i++;
											});

							$("#lokasiLabel").autocomplete({
								source : function(request, response) {
									$.ajax({
										
										url : "http://localhost:8080/sscnServer/findLokasiLikeByName.do",
										dataType : "jsonp",
										data : {
											featureClass : "P",
											style : "full",
											maxRows : 12,
											name_startsWith : request.term
										},
										success : function(data) {
											response($.map(data.lokasis, function(item) {
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
									//log(ui.item ? "Selected: " + ui.item.label : "Nothing selected, input was " + this.value);
									$('#lokasiValue').val(ui.item.code);
								},
								open : function() {
									$(this).removeClass("ui-corner-all").addClass("ui-corner-top");
								},
								close : function() {
									$(this).removeClass("ui-corner-top").addClass("ui-corner-all");
								}
							}); 

							$("#jabatanLabel").autocomplete({
								source : function(request, response) {
									$.ajax({
										
										url : "http://localhost:8080/sscnServer/findJabatanLikeByName.do",
										dataType : "jsonp",
										data : {
											featureClass : "P",
											style : "full",
											maxRows : 12,
											name_startsWith : request.term
										},
										success : function(data) {
											response($.map(data.jabatans, function(item) {
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
									//log(ui.item ? "Selected: " + ui.item.label : "Nothing selected, input was " + this.value);
									$('#jabatanValue').val(ui.item.code);
								},
								open : function() {
									$(this).removeClass("ui-corner-all").addClass("ui-corner-top");
								},
								close : function() {
									$(this).removeClass("ui-corner-top").addClass("ui-corner-all");
								}
							}); 

							$("#pendidikanLabel").autocomplete({
								source : function(request, response) {
									$.ajax({
										
										url : "http://localhost:8080/sscnServer/findPendidikanLikeByName.do",
										dataType : "jsonp",
										data : {
											featureClass : "P",
											style : "full",
											maxRows : 12,
											name_startsWith : request.term
										},
										success : function(data) {
											response($.map(data.pendidikans, function(item) {
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
									//log(ui.item ? "Selected: " + ui.item.label : "Nothing selected, input was " + this.value);
									$('#pendidikanValue').val(ui.item.code);
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
