<%@page language="java" session="true" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
				<a href="/sscnServer/dashboard.do">Administrasi SSCN 2013</a>
			</h1>

			<div id="info">

				<a href="javascript:;" id="info-trigger"> <i class="icon-cog"></i>
				</a>

				<div id="info-menu">

					<div class="info-details">
						<h4>Selamat Datang ${userLogin.nama}</h4>
						<p>							
							Login sebagai <c:choose>
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
							<form action="logout.do" method="POST" name="formLogout">
								<input class="btn btn-small btn-primary" type="submit" value="logout" name="btnLogout"/>
							</form>
						</p>
					</div>

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
								</ul>
							</li>
							<li class="dropdown"><a href="/sscnServer/pengumuman.do"
								class="dropdown-toggle"> <i class="icon-copy"></i> Pengumuman
									<b class="caret"></b>
							</a></li>								
							<li class="dropdown"><a href="/sscnServer/formasi.do"
								class="dropdown-toggle"> <i class="icon-copy"></i> Formasi <b
									class="caret"></b>
								</a>
							</li>
							<li class="dropdown"><a href="/sscnServer/downloadData.do"
						class="dropdown-toggle"> <i class="icon-copy"></i> Download Data <b
							class="caret"></b>
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
					<li><a href="../../default.htm">Home</a>
					</li>
					<li><span class="divider">/</span>
					</li>
					<li class="active">Pengumuman</li>
				</ul>

			</div>
			<div>
				<marquee>Untuk tampilan terbaik diharapkan menggunakan browser <b>Mozilla Firefox 3 atau Safari</b> atau diatasnya.</marquee>
			</div>
			<!-- /.page-title -->

						<div class="widget-header">
							<h3>
								<!-- <i class="icon-th-list"></i> Formasi <b>${instansiNama}</b> -->
								<i class="icon-th-list"></i> Upload Laporan Pengumuman
							</h3>
						</div>
						<!-- /widget-header -->
						
					<div class="widget-content">
						
						<form:form action="" commandName="fileUploadCommand" method="POST" enctype="multipart/form-data">
						
							<fieldset>
							    <div class="control-group">
							      <label class="control-label">Maksimum file Laporan (pdf) sebesar 2MB</label>
									<br>
							      <label class="control-label" for="Laporan">Pilih File</label>
							      <div class="controls">
							      		<form:input type="file" path="file" accept="application/pdf" class="required"/>
							      		<input type="hidden" value="${userLogin.refInstansi.kode}" id="kodeInstansi" name="kodeInstansi"/>
							        	<input type="submit" value="Upload File"><form:errors path="*" cssStyle="color : red;"/>&nbsp;&nbsp;${errors}							      		
							      	<!--
							      	<input type="hidden" class="input-large" name="Laporan" id="LaporanValue">
							        <input type="file" class="input-large" name="LaporanFileLabel" id="LaporanFileLabel">
							        <input type="submit" value="Upload">
							        -->
							        <br>
							        ${statusUpload}
							      </div>
							    </div>								
							</fieldset>	
						</form:form>
						
						<!--
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
							-->
						
					</div> <!-- /widget-content -->
						

	<div id="footer">
		<div class="container">
			Hak Cipta  &copy;  2013 Badan Kepegawaian Negara. Semua Hak Dilindungi.
		</div>
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
										
										url : "/sscnServer/findLokasiLikeByName.do",
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
										
										url : "/sscnServer/findJabatanLikeByName.do",
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
										
										url : "/sscnServer/findPendidikanLikeByName.do",
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
