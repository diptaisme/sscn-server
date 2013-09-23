<%@page language="java" session="true"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="userLogin" value="${sessionScope.userLogin}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Administrasi Sistem Seleksi CPNS Nasional 2013</title>

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
<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-44208865-1']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>
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

<script type="text/javascript"
	src="/sscnServer/resources/js/jquery.validate.js"></script>
<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
		<script src="../../../html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->
<style>
/*css validation*/
label.valid {
	width: 24px;
	height: 24px;
	background: url(assets/img/valid.png) center center no-repeat;
	display: inline-block;
	text-indent: -9999px;
}

label.error {
	font-weight: bold;
	color: red;
	padding: 2px 8px;
	margin-top: 2px;
}
</style>
</head>

<body>

	<div id="header">

		<div class="container">

			<h1>
				<a href="/sscnServer/dashboard.do">Administrasi SSCN 2013</a>
			</h1>

			<div id="info">

				<a href="javascript:;" id="info-trigger"> <i class="icon-cog"></i>
				</a>

				<div id="info-menu">

					<div class="info-details">
						<h4>Selamat Datang ${userLogin.nama}</h4>
						<p>
							Login sebagai
							<c:choose>
								<c:when test="${userLogin.kewenangan == 1}">
									Administrator
								</c:when>
								<c:when test="${userLogin.kewenangan == 2}">
									Admin Instansi
								</c:when>
								<c:otherwise>
									Verificator
								</c:otherwise>
							</c:choose>
							${userLogin.refInstansi.nama}
						</p>
						<p>
						<form action="/sscnServer/logout.do" method="POST" name="formLogout">
							<input class="btn btn-small btn-primary" type="submit"
								value="logout" name="btnLogout" />
						</form>
						</p>
					</div>
					<!-- /.info-details -->

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

					<li class="nav-icon active"><a href="/sscnServer/dashboard.do">
							<i class="icon-home"></i> <span>Home</span>
					</a></li>
					<c:if test="${userLogin.kewenangan != 3}">
						<li class="dropdown"><a href="javascript:;"
							class="dropdown-toggle" data-toggle="dropdown"> <i
								class="icon-external-link"></i> Manajemen <b class="caret"></b>
						</a>
							<ul class="dropdown-menu">
								<li><a href="/sscnServer/user.do">User</a></li>
								<li><a href="/sscnServer/lokasi.do">Lokasi</a></li>
								<li><a href="/sscnServer/syarat.do">Syarat Pendaftaran</a></li>
							</ul></li>
						<li class="dropdown"><a href="/sscnServer/pengumuman.do"
							class="dropdown-toggle"> <i class="icon-copy"></i> Pengumuman
								<b class="caret"></b>
						</a></li>
						<li class="dropdown"><a href="/sscnServer/formasi.do"
							class="dropdown-toggle"> <i class="icon-copy"></i> Formasi <b
								class="caret"></b>
						</a></li>
						<li class="dropdown"><a href="/sscnServer/downloadData.do"
							class="dropdown-toggle"> <i class="icon-copy"></i> Download
								Data <b class="caret"></b>
						</a></li>
						<li class="dropdown"><a href="/sscnServer/statistik.do"
							class="dropdown-toggle"> <i class="icon-copy"></i> Statistik <b class="caret"></b>
						</a></li>
					</c:if>
					<li class="dropdown"><a href="/sscnServer/verifikasi.do"
						class="dropdown-toggle"> <i class="icon-copy"></i> Verfikasi <b
							class="caret"></b>
					</a></li>

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
					<li><a href="/sscnServer/dashboard.do">Home</a><span class="divider">/</span>
					</li>
					<li><a href="#">Lokasi Management</a><span class="divider">/</span>
					</li>
					<li class="active">List</li>
				</ul>

			</div>
			<div>
				<marquee>Untuk tampilan terbaik diharapkan menggunakan browser <b>Mozilla Firefox 3 atau Safari</b> atau diatasnya.</marquee>
			</div>
			<!-- /.page-title -->

			<div class="row">
				<div class="span12">

					<div class="widget widget-table">

						<div class="widget-header">
							<h3>
								<i class="icon-th-list"></i> Lokasi Management
							</h3>
						</div>
						<!-- /widget-header -->

						<div class="widget-content">
							<div id="example_wrapper" class="dataTables_wrapper form-inline"
								role="grid">
								<div class="row">
									<div class="span6">
										<div class="dataTables_length">
											<label><!-- <select name="example_length" size="1"
												aria-controls="example">
													<option value="10" selected="selected">10</option>
													<option value="25">25</option>
													<option value="50">50</option>
													<option value="100">100</option>
											</select> --> 10 records per page
											</label>
										</div>
									</div>
									<div class="span6">
										<div class="dataTables_filter">
											<!-- <label>Search: <input type="text"
												aria-controls="example">
											</label>  -->
										</div>
									</div>
								</div>

								<div class="row">
									<div class="span6 pull-right">
										<a href="#" id="addLokasiModal"
											class="btn btn-small btn-primary"><i class="icon-plus"></i>Tambah Lokasi Baru</a>

									</div>
								</div>
								<table
									class="table table-striped table-bordered table-highlight"
									id="myTable">
									<thead>
										<tr>
											<th>Kode</th>
											<th>Instansi</th>
											<th>Nama</th>
											<th>Action(s)</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${lokasis}" var="lokasi">
											<tr class="odd gradeX">
												<td>${lokasi.kode}</td>
												<td>${lokasi.refInstansi.nama}</td>
												<td>${lokasi.nama}</td>
												<!-- <td>Edit | Delete</td>-->
												<td><a href="#"
													onclick="prepareUbahForm(this,'${lokasi.kode }')"
													class="btn btn-small btn-primary"><i class="icon-edit"></i>Edit</a>
													| <a href="#"
													onclick="confirmDelete(this,'${lokasi.kode}')"
													class="btn btn-small btn-primary"><i
														class="icon-remove"></i>Delete</a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<div class="row">
									<jsp:include page="paging.jsp" />
									<form id="pagingForm" method="post" action="/sscnServer/lokasi.do">
										<input type="hidden" name="activePage" id="activePage"/>
										<input type="hidden" name="numPage" id="numPage"/>
										<input type="hidden" name="searchPage" id="searchPage"/>
									</form>							
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

		<div class="container">Hak Cipta &copy; 2013 Badan Kepegawaian
			Negara. Semua Hak Dilindungi.</div>
		<!-- /.container -->

	</div>
	<!-- /#footer -->

	<div id="myModal" title="Tambah Lokasi Baru">
		<p class="validateTips">All form fields are required.</p>
		<form class="form-horizontal" action="/sscnServer/lokasiSave.do"
			method="post" id="formAddLokasi">
			<fieldset>
				<div id="loadingImage" style="display: none">
					<img src="img/ajax-loader.gif" />
				</div>
				<div id="alert" class="alert alert-error" style="display: none">
				</div>
				<div class="control-group">
					Instansi
						${userLogin.refInstansi.nama}
				</div>
				<div class="control-group">
					<label class="control-label" for="input01">Kode</label>
					<div class="controls">
						<input type="number" class="input-large" id="kode" name="kode"
							size="4" maxlength="4">
						<p class="help-block">
							<!--In addition to freeform text, any HTML5 text-based input appears like so.-->
						</p>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="input01">Nama</label>
					<div class="controls">
						<input type="text" class="input-large" id="nama" name="nama">
						<p class="help-block">
							<!--In addition to freeform text, any HTML5 text-based input appears like so.-->
						</p>
					</div>
				</div>

				<div class="control-group">
					<!--  <label class="control-label" for="input01">Instansi</label> -->
					<div class="controls">
						<div class="ui-widget">
							<input type="hidden" name="instansi" id="instansiValue"
								value="${userLogin.refInstansi.kode}" />
							<!--  <input type="text" class="input-large" id="instansi" name="instansiLabel">-->
						</div>
					</div>
				</div>

				<div class="form-actions">
					<button type="submit" class="btn btn-primary btn-large">
						Simpan</button>

				</div>
			</fieldset>
		</form>
	</div>
	<div id="myModal2" title="Ubah Lokasi">
		<p class="validateTips">All form fields are required.</p>
		<form class="form-horizontal" action="/sscnServer/lokasiUpdate.do"
			method="post" id="formUbahLokasi">
			<fieldset>
				<div id="loadingImage2" style="display: none">
					<img src="/sscnServer/resources/img/ajax-loader.gif" />
				</div>
				<div id="alert2" class="alert alert-error" style="display: none">
				</div>
				<div class="control-group">
					<!-- <input type="hidden" class="input-large" id="edkode" name="kode">-->
					<label class="control-label">Kode</label>
					<div class="controls">
						<input type="text" class="input-large" id="edkode" name="kode"
							readonly="readonly">
						<p class="help-block">
							<!--In addition to freeform text, any HTML5 text-based input appears like so.-->
						</p>
					</div>
					<label class="control-label" for="input01">Nama</label>
					<div class="controls">
						<input type="text" class="input-large" id="ednama" name="nama">
						<p class="help-block">
							<!--In addition to freeform text, any HTML5 text-based input appears like so.-->
						</p>
					</div>
				</div>

				<div class="form-actions">
					<button type="submit" class="btn btn-primary btn-large">
						Update</button>

				</div>
			</fieldset>
		</form>
	</div>

	<div id="myModal3" title="Delete Lokasi">
		<form class="form-horizontal" action="/sscnServer/lokasiDelete.do"
			method="post" id="formDeleteLokasi">
			<fieldset>
				<div id="loadingImage3" style="display: none">
					<img src="/sscnServer/resources/img/ajax-loader.gif" />
				</div>
				<div id="alert3" class="alert alert-warning">Apakah anda yakin
					ingin menghapus data ini ?</div>
				<input type="hidden" name="kode" id="delkode" />

				<div class="form-actions">
					<button type="submit" class="btn btn-primary btn-large">
						Ya</button>

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
								height : 350,
								width : 500,
								modal : true
							});
							$("#myModal2").dialog({
								autoOpen : false,
								height : 250,
								width : 500,
								modal : true
							});

							$("#myModal3").dialog({
								autoOpen : false,
								height : 220,
								width : 400,
								modal : true
							});

							var selRowTable;
							prepareUbahForm = function(elem, id) {
								selRowTable = $(elem).closest('tr');

								$
										.ajax({
											type : "GET",
											url : "/sscnServer/getLokasi.do?kode="
													+ id,
											cache : false,
											success : function(data) {

												$('#edkode')
														.val(data.data.kode);
												$('#ednama')
														.val(data.data.nama);
												$("#myModal2").dialog("open");
											},
											dataType : "json"
										});

							};

							confirmDelete = function(elem, id) {
								selRowTable = $(elem).closest('tr');
								$('#delkode').val(id);
								$("#myModal3").dialog("open");
							};

							$("#formDeleteLokasi")
									.submit(
											function(event) {

												/* stop form from submitting normally */
												event.preventDefault();

												$('#loadingImage3').show();
												//$('#alert3').hide();
												//$('#alert').show();

												/* get some values from elements on the page: */
												var $form = $(this), term = $(
														this).serialize(), url = $form
														.attr('action');

												/* Send the data using post */
												var posting = $.post(url, term,
														"json");

												/* Put the results in a div */
												posting
														.done(function(data) {
															$('#loadingImage3')
																	.hide();
															if (data.result == 0) {
																var html = '<strong>Error!</strong> '
																		+ data.message;
																$('#alert3')
																		.html(
																				html);
																$('#alert3')
																		.show();
															}

															if (data.result == 1) {
																$("#myModal3")
																		.dialog(
																				"close");
																$('#delkode')
																		.val('');
																alert(data.message);
																refreshDeleteTable();
																return false;
															}

															if (data.result == -1) {
																window.location = "/sscnServer/login.do";
															}
														});

												posting.error(function(e) {
													alert('failure' + e);
												});
											});

							function refreshDeleteTable() {

								var tesHtml = '';

								$(selRowTable).replaceWith(tesHtml);
							}							

							$('#addLokasiModal').click(function() {
								$("#myModal").dialog("open");
							});							

							$("#formUbahLokasi")
									.submit(
											function(event) {

												/* stop form from submitting normally */
												event.preventDefault();

												$('#loadingImage2').show();
												$('#alert2').hide();
												//$('#alert').show();

												/* get some values from elements on the page: */
												var $form = $(this), term = $(
														this).serialize(), url = $form
														.attr('action');

												/* Send the data using post */
												var posting = $.post(url, term,
														"json");

												/* Put the results in a div */
												posting
														.done(function(data) {
															$('#loadingImage2')
																	.hide();
															if (data.result == 0) {
																var html = '<strong>Error!</strong> '
																		+ data.message;
																$('#alert2')
																		.html(
																				html);
																$('#alert2')
																		.show();
															}

															if (data.result == 1) {
																$("#myModal2")
																		.dialog(
																				"close");
																$('#edkode')
																		.val('');
																$('#ednama')
																		.val('');
																alert(data.message);
																refreshUpdateTable(data.data);
																return false;
															}

															if (data.result == -1) {
																window.location = "/sscnServer/login.do";
															}
														});

												posting.error(function() {
													alert('failure');
												});
											});

							function refreshUpdateTable(data) {
								var tesHtml = '<tr class="odd gradeX"> '
										+ '<td>'
										+ data.kode
										+ '</td> '
										+ '<td>'
										+ data.refInstansi.nama
										+ '</td> '
										+ '<td>'
										+ data.nama
										+ '</td> '
										+ '<td><a href="#" onclick="prepareUbahForm(this,\''
										+ data.kode
										+ '\')" '
										+ 'class="btn btn-small btn-primary"><i class="icon-edit"></i>Edit</a> | <a href="#" '
										+ 'class="btn btn-small btn-primary" onclick="confirmDelete(this,\''
										+ data.kode
										+ '\')"><i class="icon-remove"></i>Delete</a></td> '
										+ '</tr>';

								$(selRowTable).replaceWith(tesHtml);
							}
						});
	</script>

	<script>
		jQuery(document)
				.ready(
						function() {

							function log(message) {
								$("<div>").text(message).prependTo("#log");
								$("#log").scrollTop(0);
							}

							$("#formAddLokasi")
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
																"/sscnServer/lokasiSave.do",
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
																$('#kode').val(
																		'');
																$('#nama').val(
																		'');
																alert(data.message);
																refreshTable(data.data);
																return false;
															}

															if (data.result == -1) {
																window.location = "/sscnServer/login.do";
															}
														});

												posting.error(function() {
													alert('failure');
												});
											});

							function refreshTable(data) {
								var table = document.getElementById('myTable');
								var lenRow = table.rows.length;
								var row = table.insertRow(lenRow);
								var colCount = table.rows[0].cells.length;
								var profile = '';
								var tesHtml = '<tr class="odd gradeX"> '
										+ '<td>'
										+ data.kode
										+ '</td> '
										+ '<td>'
										+ data.refInstansi.nama
										+ '</td> '
										+ '<td>'
										+ data.nama
										+ '</td> '
										+ '<td><a href="#" onclick="prepareUbahForm(this,\''
										+ data.kode
										+ '\')" '
										+ 'class="btn btn-small btn-primary"><i class="icon-edit"></i>Edit</a> | <a href="#" '
										+ 'class="btn btn-small btn-primary" onclick="confirmDelete(this,\''
										+ data.kode
										+ '\')"><i class="icon-remove"></i>Delete</a></td> '
										+ '</tr>';

								row.innerHTML = tesHtml;
							}

						});
	</script>
	<!-- validation -->
	<script>
			$(document).ready(
				function() {
				$('#formAddLokasi').validate(
						{
							rules : {
								kode : {
									required : true,							
								},
								nama : {
									required : true
								},
								
							},
							highlight : function(element) {
								$(element).closest('.control-group')
										.removeClass('success').addClass(
												'error');
							},
							success : function(element) {
								element.text('OK!').addClass('valid').closest(
										'.control-group').removeClass('error')
										.addClass('success');
							}
						});	
			$('#formUbahLokasi').validate(
					{
						rules : {
							ednama : {
								required : true
							},
							
						},
						highlight : function(element) {
							$(element).closest('.control-group')
									.removeClass('success').addClass(
											'error');
						},
						success : function(element) {
							element.text('OK!').addClass('valid').closest(
									'.control-group').removeClass('error')
									.addClass('success');
						}
					});	
		});
	</script>
</body>
</html>