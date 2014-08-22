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
	_gaq.push([ '_setAccount', 'UA-44208865-1' ]);
	_gaq.push([ '_trackPageview' ]);

	(function() {
		var ga = document.createElement('script');
		ga.type = 'text/javascript';
		ga.async = true;
		ga.src = ('https:' == document.location.protocol ? 'https://ssl'
				: 'http://www')
				+ '.google-analytics.com/ga.js';
		var s = document.getElementsByTagName('script')[0];
		s.parentNode.insertBefore(ga, s);
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

<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
		<script src="../../../html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->

</head>

<body>
	<div id="header">
		<div class="container">

			<h1>
				<a href="dashboard.do">Administrasi SSCN 2014</a>
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
				data-target=".nav-collapse"> <i class="icon-reorder"></i> </a>

			<div class="nav-collapse">
				<jsp:include page="menu.jsp" />
			</div>
			<!-- /.nav-collapse -->
		</div>
	</div>
	<!-- /#nav -->

	<div id="content">
		<div class="container">
			<div id="page-title" class="clearfix">
				<ul class="breadcrumb">
					<li><a href="dashboard.do">Home</a><span class="divider">/</span>
					</li>
					<li><a href="#">Verfikasi Pendaftaran</a>
					</li>
				</ul>
			</div>
			<div>
				<marquee>
					Untuk tampilan terbaik diharapkan menggunakan browser <b>Mozilla
						Firefox 3 atau Safari</b> atau diatasnya.
				</marquee>
			</div>
			<!-- /.page-title -->

			<div class="row">
				<div class="span12">

					<div class="widget widget-table">

						<div class="widget-header">
							<h3>
								<i class="icon-th-list"></i> Reset Status Pendaftar
							</h3>
						</div>
						<!-- /widget-header -->

						<div class="widget-content">
							<div id="example_wrapper" class="dataTables_wrapper form-inline"
								role="grid">
								<div class="row">
									<div class="span6">
										<div class="dataTables_length">
											<label><select id='paging_numpage'
												name="example_length" size="1" aria-controls="example">
													<option value="10" selected="selected">10</option>
													<option value="25">25</option>
													<option value="50">50</option>
													<option value="100">100</option>
											</select> records per page </label>
										</div>
									</div>
									<div class="span6">
										<div class="">
											<form id="searchForm" method="post" action="resetverifikasi.do">
												<div class="control-group" id="searchBar">
													<input type="hidden" name="searchPar" value="0" /> <input
														type="hidden" name="activePage" id="activePageBar" />
													<c:choose>
														<c:when test="${no_reg != null}">
															<label>Cari No Registrasi : <input type="text"
																aria-controls="example" name="no_reg"
																id="defaultSearchField" value="${no_reg}"> </label>
														</c:when>
														<c:otherwise>
															<label>Cari No Registrasi : <input type="text"
																aria-controls="example" name="no_reg"
																id="defaultSearchField"> </label>
														</c:otherwise>
													</c:choose>

													<a class="btn" onClick="submitSearch(event)"><i
														class="icon-search m-icon-white"></i>
													</a>
												</div>
											</form>
										</div>
									</div>
								</div>

								<table
									class="table table-striped table-bordered table-highlight"
									id="myTable">
									<thead>
										<tr>
											<th>Nama Pendaftar</th>
											<th>No Register</th>
											<th>Tgl Lahir</th>
											<th>IPK</th>
											<th>Status</th>
											<th>Action(s)</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${pendaftars}" var="pendaftar">
											<tr class="odd gradeX">
												<td>${pendaftar.nama}</td>
												<td>${pendaftar.noRegister}</td>
												<td>${pendaftar.tglLahir}</td>
												<td>${pendaftar.nilaiIpk}</td>
												<c:choose>
													<c:when
														test="${pendaftar.status == null || pendaftar.status == ''}">
														<td>Belum diverifikasi</td>
													</c:when>
													<c:otherwise>
														<c:choose>
															<c:when test="${pendaftar.status == '0'}">
																<td>Tidak Lulus</td>
															</c:when>
															<c:when test="${pendaftar.status == '1'}">
																<td>Lulus</td>
															</c:when>
														</c:choose>
													</c:otherwise>
												</c:choose>
												<td> <a href="#"
															onclick="reset(this,'${pendaftar.id }')"
															class="btn btn-small btn-primary"><i
																class="icon-edit"></i>Reset</a>&nbsp;<a href="#"
															onclick="info(this,'${pendaftar.id }')"
															class="btn btn-small btn-primary"><i
																class="icon-edit"></i>Info</a>&nbsp; 
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<div class="row">
									<jsp:include page="paging.jsp" />
									<form id="pagingForm" method="post" action="resetverifikasi.do">
										<input type="hidden" name="activePage" id="activePage" /> <input
											type="hidden" name="numPage" id="numPage" /> <input
											type="hidden" name="searchPage" id="searchPage" />
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

		<div class="container">Hak Cipta &copy; 2014 Badan Kepegawaian
			Negara. Semua Hak Dilindungi.</div>
		<!-- /.container -->

	</div>
	<!-- /#footer -->

	<div id="myModal2" title="Info Pendaftar">
		<fieldset>
			<div class="control-group">
				<label>Nama : </label>
				<div class="controls">
					<input type="text" value="" id="valuePendaftar" readonly="readonly" />
				</div>
			</div>

			<div class="control-group">
				<label>Tempat, Tanggal Lahir : </label>
				<div class="controls">
					<input type="text" value="" id="valueTTL" readonly="readonly" />
				</div>
			</div>
			<div id="valueStatus"></div>
			<div class="form-actions">
				<button class="btn btn-large" id="btnCancelInfo">Tutup</button>
			</div>
		</fieldset>
	</div>

	<div id="javaValues" style="display: none;">
		<div id="numRow">${numRow}</div>
	</div>
	<script>
		jQuery(document)
				.ready(
						function() {
							var numRow = $('#numRow').html().trim();
							$('#paging_numpage option').each(function() {
								if ($(this).val() == numRow) {
									$(this).prop("selected", true);
								}
							});
							/*change row perpage*/
							$("#paging_numpage")
									.change(
											function() {
												window.location = "resetverifikasi.do?numPage="
														+ $(this).val();
											});

							$("#myModal2").dialog({
								autoOpen : false,
								height : 350,
								width : 500,
								modal : true
							});

							var selRowTable;

							reset = function(elem, pendaftarId) {
								var formData = "id="+pendaftarId;
								$
										.ajax({
											type : "POST",
											url : "resetverifikasiById.do",
											data: formData,
											cache : false,
											success : function(data) {
												if (data.result == "1") {
													// verifikasi == ok
													location.reload();
												} else if (data.result == "0") {
													// verifikasi == nok
													alert(data.message)
												}
											},
											dataType : "json"
										});
																
							}


							info = function(elem, id) {
								$
										.ajax({
											type : "GET",
											url : "getPendaftaran.do?id=" + id,
											cache : false,
											success : function(data) {
												$('#valuePendaftar').val(
														data.data.nama);
												$('#valueTTL')
														.val(
																data.data.tmpLahir
																		+ ", "
																		+ data.data.tglLahir);
												if (data.mapData != null) {
													var html = "<span>Status Tidak Lulus dengan syarat yang tidak terpenuhi : </span></br>";
													html = html + "<lu>";
													for (i = 0; i < data.mapData.verNoks.length; i++) {
														html = html
																+ "<li>"
																+ data.mapData.verNoks[i].persyaratan.syarat
																+ "</li>";
													}
													html = html + "</lu>";
													$('#valueStatus')
															.html(html);
													$('#valueStatus').show();
												} else {
													$('#valueStatus').hide();
												}
											},
											dataType : "json"
										});
								$("#myModal2").dialog("open");
							}							

							$('#btnCancelInfo').click(function(event) {
								event.preventDefault();
								$('#myModal2').dialog("close");
								$('#valueStatus').hide();
							});
							
						});
	</script>
</body>
</html>
