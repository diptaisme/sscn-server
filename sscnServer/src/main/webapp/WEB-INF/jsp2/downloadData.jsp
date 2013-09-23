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
<script>
	function cekTime() {
		/*var d = new Date();		
		if(parseInt(d.getHours()) > 8 && parseInt(d.getHours()) < 17){
			alert("Maaf, anda tidak bisa melakukan download data diantara pukul 8 dan 17.");
			return false;	
		}*/
		return true;
	}
</script>
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
#instansi {
	color: blue;
}

#edinstansiLabel {
	color: blue;
}
</style>
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
				<a href="dashboard.do">Administrasi SSCN 2013</a>
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
					<li><a href="#">Download Data</a><span class="divider">/</span>
					</li>
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
								<i class="icon-th-list"></i> Download Data
							</h3>
						</div>
						<!-- /widget-header -->

						<div class="widget-content">
							<div id="example_wrapper" class="dataTables_wrapper form-inline"
								>
								<div>									
									<div>
										<!--<BR/>
										&nbsp;&nbsp;Under Construction-->
											<BR/>											
											<form method="POST" name="formDownloadDataPendaftaran" action="/sscnServer/ReportServlet" target="_BLANK">
												<input type="hidden" value="rptDataPendaftaran" name="typeReport" id="typeReport"
													 />
												<input type="hidden" value="${userLogin.refInstansi.kode}" name="kodeInstansi" id="kodeInstansi"
													 />
												<input type="hidden" value="${userLogin.refInstansi.nama}" name="namaInstansi" id="namaInstansi"
													 />
												&nbsp;&nbsp;<input type="submit" value="Download Data Pendaftaran" onclick="return cekTime()" title="Klik untuk download data pendaftaran"
													class="btn btn-small btn-primary"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Download Data Pendaftaran SSCN untuk <B>Instansi ${userLogin.refInstansi.nama}</B>																								
											</form>
											<BR/>
											<form method="POST" name="formDownloadDataPesertaTest" action="/sscnServer/ReportServlet" target="_BLANK">
												<input type="hidden" value="rptDataPesertaTest" name="typeReport" id="typeReport2"
													 />
												&nbsp;&nbsp;<input type="submit" value="Download Data Peserta Test" onclick="return cekTime()" title="Klik untuk download data peserta test"
													class="btn btn-small btn-primary" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Download Data Peserta Test SSCN untuk <B>Instansi ${userLogin.refInstansi.nama}</B>
												<input type="hidden" value="${userLogin.refInstansi.kode}" name="kodeInstansi2" id="kodeInstansi2"
													 />
												<input type="hidden" value="${userLogin.refInstansi.nama}" name="namaInstansi2" id="namaInstansi2"
													 />																																				
											</form>
										</div>
									</div>																		
								</div>								
							</div>
						</div>

					</div>

				</div>
			</div>
		</div>
	</div>
	<!-- /#content -->

	<div id="footer">

		<div class="container">Hak Cipta &copy; 2013 Badan Kepegawaian
			Negara. Semua Hak Dilindungi.</div>
		<!-- /.container -->
	</div>
	<!-- /#footer -->
</body>
</html>
