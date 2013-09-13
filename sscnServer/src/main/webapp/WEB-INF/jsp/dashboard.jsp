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

<link
	href="/sscnServer/resources/css/ui-lightness/jquery-ui-1.8.21.custom.css"
	rel="stylesheet">

<link href="/sscnServer/resources/css/slate.css" rel="stylesheet">
<link href="/sscnServer/resources/css/slate-responsive.css"
	rel="stylesheet">

<link href="/sscnServer/resources/css/pages/dashboard.css"
	rel="stylesheet">

<!-- Javascript -->
<script src="/sscnServer/resources/js/jquery-1.7.2.min.js"></script>
<script src="/sscnServer/resources/js/jquery-ui-1.8.21.custom.min.js"></script>
<script src="/sscnServer/resources/js/jquery.ui.touch-punch.min.js"></script>
<script src="/sscnServer/resources/js/jquery.ui.dialog.min.js"></script>
<script src="/sscnServer/resources/js/bootstrap.js"></script>

<script src="/sscnServer/resources/js/Slate.js"></script>

<script src="/sscnServer/resources/js/plugins/excanvas/excanvas.min.js"></script>
<script src="/sscnServer/resources/js/plugins/flot/jquery.flot.js"></script>
<script
	src="/sscnServer/resources/js/plugins/flot/jquery.flot.orderBars.js"></script>
<script src="/sscnServer/resources/js/plugins/flot/jquery.flot.pie.js"></script>
<script
	src="/sscnServer/resources/js/plugins/flot/jquery.flot.resize.js"></script>



<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
		<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->

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
						$("#formChangePassword")
								.submit(
										function(event) {

											/* stop form from submitting normally */
											event.preventDefault();

											$('#loadingImage').show();
											$('#alert').hide();
											//$('#alert').show();

											/* get some values from elements on the page: */
											var $form = $(this), term = $(this)
													.serialize(), url = $form
													.attr('action');

											/* Send the data using post */
											var posting = $
													.post(
															"/sscnServer/userChangePassword.do",
															term, "json");

											/* Put the results in a div */
											posting
													.done(function(data) {
														$('#loadingImage')
																.hide();
														if (data.result == 0) {
															var html = '<strong>Error!</strong> '
																	+ data.message;
															$('#old_password')
																	.val('');
															$('#password').val(
																	'');
															$('#alert').html(
																	html);
															$('#alert').show();
														}

														if (data.result == 1) {
															$("#myModal")
																	.dialog(
																			"close");
															$('#username').val(
																	'');
															$('#old_password')
																	.val('');
															$('#password').val(
																	'');
															alert(data.message);
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

					});
	function openFormGantiPasword() {
		$("#myModal").dialog("open");
	}

	function closeFormGantiPasword() {
		$("#myModal").dialog("close");
	}
</script>
<style type="text/css">
<!--
.style2 {
	color: #0033FF
}
-->
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
						<form action="logout.do" method="POST" name="formLogout">
							<!-- <button id="btnPasswordModal">
									Ganti Password</button>-->
							<a href="#" onClick="openFormGantiPasword()"
								class="btn btn-small btn-primary">Ganti Password</a> <input
								class="btn btn-small btn-primary" type="submit" value="logout"
								name="btnLogout" />

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
			<div>
				<marquee>
					Untuk tampilan terbaik diharapkan menggunakan browser <b>Mozilla
						Firefox 3 atau Safari</b> atau diatasnya.
				</marquee>
			</div>
		</div>
		<!-- /.container -->

	</div>
	<!-- /#nav -->

	<div id="content">

		<div class="container">

			<div class="row">

				<div class="span4">

					<div class="widget">

						<div class="widget-header">

							<h3>
								<i class="icon-tasks"></i> Quick Stats
							</h3>

							<div class="widget-actions">
								<div class="btn-group">
									<a class="btn dropdown-toggle btn-small" data-toggle="dropdown"
										href="javascript:;"> <i class="icon-cog"></i> <span
										class="caret"></span>
									</a>
								</div>
							</div>
							<!-- /.widget-actions -->

						</div>
						<!-- /.widget-header -->

						<div class="widget-content">
							<p align="center" class="style1">
								<strong>Selamat Datang di Menu Administrasi</strong>
							</p>
							Perhatian : <br> Sebelum pendaftaran dibuka kepada para
							pelamar, Administrator <font color="red">WAJIB</font> melengkapi
							:
							</p>
							<ol>
								<li>Lokasi formasi (wajib diisi karena menjadi default
									dalam pengisian formasi jabatan) Catt : Untuk instansi daerah
									agar mengisi 1 (satu) lokasi formasi dengan ibukota instansi
									daerah tersebut.</li>
								<li>Syarat Pendaftaran (wajib diisi karena untuk
									kepentingan verifikasi, serta pencetakan form registrasi
									pelamar, jika tidak diisi maka cetakan bukti registrasi
									pendaftar akan blank)</li>
								<li>Formasi (wajib diisi karena menjadi pilihan bagi
									pelamar untuk mendaftar pada suatu jabatan formasi tertentu)</li>
								<li>Pengumuman Instansi (pdf) yang di upload di sscn admin
								</li>
							</ol>





							<p>
								<strong><em>Unduh Buku Petunjuk Teknis
										Administrator Instansi (<span class="style2"><a
											href="resources/img/manual_sscn.pdf" target="_BLANK">disini</a></span>)
								</em></strong>
							</p>
							<div class="stat"></div>
							<!-- /.stat -->

							<div class="stat"></div>
							<!-- /.stat -->

							<br />

						</div>
						<!-- /.widget-content -->

					</div>

				</div>
				<!-- /.span4 -->

				<div class="span8">


					<!-- /.widget -->


					<div class="widget toolbar-bottom">

						<div class="widget-header">

							<h3>
								<i class="icon-hdd"></i> Alur Administrator
							</h3>

						</div>
						<!-- /.widget-header -->

						<div class="widget-content">
							<p align="center">
								<img src="/sscnServer/resources/img/alur2.png" width="587"
									height="776">
							</p>
						</div>

					</div>
					<!-- /.widget -->

				</div>
				<!-- /.span8 -->

			</div>
			<!-- /.row -->

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
	<div id="myModal" title="Ganti Password">
		<p class="validateTips">All form fields are required.</p>
		<form class="form-horizontal"
			action="/sscnServer/userChangePassword.do" method="post"
			id="formChangePassword">
			<fieldset>
				<div id="loadingImage" style="display: none">
					<img src="img/ajax-loader.gif" />
				</div>
				<div id="alert" class="alert alert-error" style="display: none">
				</div>

				<div class="control-group">
					<label class="control-label">Username</label>
					<div class="controls">
						<input type="text" class="input-large" id="username"
							name="username" value="${userLogin.username}" readonly="readonly">
						<p class="help-block">
							<!--In addition to freeform text, any HTML5 text-based input appears like so.-->
						</p>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">Password Lama</label>
					<div class="controls">
						<input type="password" class="input-large" id="old_password"
							name="old_password">
						<p class="help-block">
							<!--In addition to freeform text, any HTML5 text-based input appears like so.-->
						</p>
					</div>
				</div>

				<div class="control-group">
					<label class="control-label">Password Baru</label>
					<div class="controls">
						<input type="password" class="input-large" id="password"
							name="password">
						<p class="help-block">
							<!--In addition to freeform text, any HTML5 text-based input appears like so.-->
						</p>
					</div>
				</div>

				<div class="form-actions">
					<button type="submit" class="btn btn-primary btn-large"
						id="btnSaveChangePassword">Simpan</button>
					<!--  <button class="btn btn-large" id="btnCancelChangePassword" onclick="closeFormGantiPasword()">
						Cancel</button>-->
				</div>
			</fieldset>
		</form>
	</div>

</body>
</html>
