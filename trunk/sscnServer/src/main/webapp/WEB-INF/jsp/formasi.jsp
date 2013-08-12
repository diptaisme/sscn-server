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
<link href="/sscnServer/resources/css/bootstrap-responsive.css"
	rel="stylesheet">
<link href="/sscnServer/resources/css/bootstrap-overrides.css"
	rel="stylesheet">

<link href="/sscnServer/resources/css/ui-lightness/jquery.ui.all.css"
	rel="stylesheet">
<!--<link href="css/ui-lightness/jquery.ui.dialog.css" rel="stylesheet">
		<link href="css/ui-lightness/jquery.ui.resizable.css" rel="stylesheet">-->
<link
	href="/sscnServer/resources/js/plugins/datatables/DT_bootstrap.css"
	rel="stylesheet">
<link
	href="/sscnServer/resources/js/plugins/responsive-tables/responsive-tables.css"
	rel="stylesheet">

<link href="/sscnServer/resources/css/slate.css" rel="stylesheet">
<link href="/sscnServer/resources/css/slate-responsive.css"
	rel="stylesheet">

<!-- Javascript -->
<script src="/sscnServer/resources/js/jquery-1.7.2.min.js"></script>
<script src="/sscnServer/resources/js/jquery-ui-1.8.21.custom.min.js"></script>
<script src="/sscnServer/resources/js/jquery.ui.dialog.min.js"></script>
<script src="/sscnServer/resources/js/jquery.ui.autocomplete.js"></script>
<!--<script src="js/jquery-1.9.1.js"></script>
		<script src="js/jquery-ui-1.10.3.custom.min.js"></script>-->
<script src="/sscnServer/resources/js/jquery.ui.touch-punch.min.js"></script>
<script src="/sscnServer/resources/js/bootstrap.js"></script>

<script
	src="/sscnServer/resources/js/plugins/datatables/jquery.dataTables.js"></script>
<script
	src="/sscnServer/resources/js/plugins/datatables/DT_bootstrap.js"></script>
<script
	src="/sscnServer/resources/js/plugins/responsive-tables/responsive-tables.js"></script>

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
												<td></td>
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
												<td>${formasi.refInstansi.nama}</td>
												<td></td>
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
	<div id="myModal" title="Create new formasi">
		<p class="validateTips">All form fields are required.</p>
		<form class="form-horizontal" action="/sscnServer/formasiSave.do"
			method="post" id="formAddFormasi">
			<fieldset>
				<div id="loadingImage" style="display: none">
					<img src="img/ajax-loader.gif" />
				</div>
				<div id="alert" class="alert alert-error" style="display: none">
				</div>
				<div class="control-group">
					<label class="control-label" for="input01">Lokasi</label>
					<div class="controls">
						<input type="text" class="input-large" id="lokasi" name="lokasi">
						<p class="help-block">
							<!--In addition to freeform text, any HTML5 text-based input appears like so.-->
						</p>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="input01">Jabatan</label>
					<div class="controls">
						<input type="hidden" class="input-large" id="jabatanValue"
							name="jabatanValue"> <input type="text"
							class="input-large" id="jabatanLabel" name="jabatanLabel">
						<p class="help-block"></p>
					</div>
				</div>
				<div class="control-group" id="pendidikanRow">
					<label class="control-label" for="input01">Pendidikan</label>
					<div class="controls">
						<div class="ui-widget">
							<input type="hidden" name="pendidikanValue" id="pendidikanValue" />
							<input type="text" class="input-large" id="pendidikanLabel"
								name="pendidikanLabel"> <input type="text"
								class="input-small" id="pendidikanJmlh" name="pendidikanJmlh">
							<button id="btnAddPddkn" class="btn btn-primary btn-small">Add</button>
						</div>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="input01">Jumlah</label>
					<div class="controls">
						<div class="ui-widget">
							<input type="text" class="input-small" id="jumlah" name="jumlah">
						</div>
					</div>
				</div>

				<div class="form-actions">
					<button type="submit" class="btn btn-primary btn-large">
						Save changes</button>
					<button class="btn btn-large" id="btnCancelFAddUser">
						Cancel</button>
				</div>
			</fieldset>
		</form>
	</div>
	<script>
		jQuery(document)
				.ready(
						function() {
							$("#myModal").dialog({
								autoOpen : false,
								height : 400,
								width : 600,
								modal : true
							});

							$('#addFormasiModal').click(function() {
								$("#myModal").dialog("open");
							});
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

							$("#formAddFormasi")
									.submit(
											function(event) {

												/* stop form from submitting normally */
												event.preventDefault();

												$('#loadingImage').show();
												$('#alert').hide();
												//$('#alert').show();

												/* get some values from elements on the page: */
												var $form = $(this), term = $(
														this).serialize(), url = $form
														.attr('action');

												/* Send the data using post */
												var posting = $
														.post(
																"http://localhost:8080/sscnServer/formasiSave.do",
																term, "json");

												/* Put the results in a div */
												posting
														.done(function(data) {
															$('#loadingImage')
																	.hide();
															if (data.result == 0) {
																var html = '<strong>Error!</strong> '
																		+ data.message;
																$('#alert')
																		.html(
																				html);
																$('#alert')
																		.show();
															}

															if (data.result == 1) {
																$("#myModal")
																		.dialog(
																				"close");
																$('#username')
																		.val('');
																$('#nip').val(
																		'');
																$('#password')
																		.val('');
																$(
																		'#instansiValue')
																		.val('');
																$('#instansi')
																		.val('');
																alert(data.message);
																refreshTable(data.data);
																return false;
															}

															if (data.result == -1) {
																window.location = "http://localhost/sscnServer/login.do";
															}
														});

												posting.error(function() {
													alert('failure');
												});
											});

						});
	</script>
</body>
</html>
