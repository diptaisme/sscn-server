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
<link href="/sscnServer/resources/js/plugins/timepicker/jquery.ui.timepicker.css" rel="stylesheet">
<link href="/sscnServer/resources/js/plugins/lightbox/themes/evolution-dark/jquery.lightbox.css" rel="stylesheet">  
<link href="/sscnServer/resources/css/slate.css" rel="stylesheet">
<link href="/sscnServer/resources/css/slate-responsive.css"
	rel="stylesheet">
<link href="/sscnServer/resources/css/pages/ui-elements.css" rel="stylesheet">

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
<script src="/sscnServer/resources/js/plugins/timepicker/jquery.ui.timepicker.min.js"></script>


<script
	src="/sscnServer/resources/js/plugins/datatables/jquery.dataTables.js"></script>
<script
	src="/sscnServer/resources/js/plugins/datatables/DT_bootstrap.js"></script>
<script
	src="/sscnServer/resources/js/plugins/responsive-tables/responsive-tables.js"></script>
<script src="/sscnServer/resources/js/plugins/lightbox/jquery.lightbox.min.js"></script>
<script src="/sscnServer/resources/js/Slate.js"></script>

<script src="/sscnServer/resources/js/demos/demo.tables.js"></script>
<script src="/sscnServer/resources/js/demos/demo.ui-elements.js"></script>
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
							Login sebagai
							<c:choose>
								<c:when test="${userLogin.kewenangan == 1}">
									Administrator
								</c:when>
								<c:when test="${userLogin.kewenangan == 2}">
									Admin Instansi
								</c:when>
								<c:otherwise>
									Verifikator
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
					<li><a href="/sscnServer/dashboard.do">Home</a><span
						class="divider">/</span></li>
					<li><a href="/sscnServer/formasi.do">Formasi Management</a><span
						class="divider">/</span></li>
					<li class="active">List</li>
				</ul>

			</div>
			<div>
				<marquee>Untuk tampilan terbaik diharapkan menggunakan browser <b>Mozilla Firefox 3 atau Safari</b> atau diatasnya.</marquee>
			</div>
			<!-- /.page-title -->

			<div class="row">
				<div class="span12">
					<div id="validation" class="widget highlight widget-form">							
						<div class="widget-header">
							<h3><a name="tambah"></a>
								Pengaturan Max Jumlah Pilihan dan Penentu Lokasi Ujian
							</h3>
						</div>
						<!-- /widget-header -->

						<div class="widget-content">
							<form action="saveInstansiconf.do" method="post" class="form-horizontal">
							 <fieldset>
								<div class="control-group">
								<label class="control-label" for="input01">Pilihan Jabatan yang dapat dipilih</label>	
									<div class="controls">
										<select name="maxPilihan" size="1" 
																				aria-controls="example">
																			 
																			  	<c:choose>
																			  		<c:when test="${instansi.pilihanJabatan == '1'}">
																			  			<option value="1" selected="selected">1 Pilihan</option>
																			  			<option value="2" >2 Pilihan</option>
																			  			<option value="3" >3 Pilihan</option>
																			  		</c:when>
																			  		<c:when test="${instansi.pilihanJabatan == '2'}">
																			  			<option value="1">1 Pilihan</option>
																			  			<option value="2" selected="selected">2 Pilihan</option>
																			  			<option value="3" >3 Pilihan</option>
																			  		</c:when>
																			  		<c:when test="${instansi.pilihanJabatan == '3'}">
																			  			<option value="1" >1 Pilihan</option>
																			  			<option value="2" >2 Pilihan</option>
																			  			<option value="3" selected="selected">3 Pilihan</option>
																			  		</c:when>
																			  		<c:otherwise>
																			  			<option value="1" >1 Pilihan</option>
																			  			<option value="2" >2 Pilihan</option>
																			  			<option value="3" >3 Pilihan</option>
																			  		</c:otherwise>
																			  	</c:choose>																					  			
																			
																		  </select>	
							           					         												
									</div>																		
								</div>
								<div class="control-group">
									<label class="control-label" for="input01">Lokasi Test Ditentukan Oleh</label>
									<div class="controls">
										<select name="lokasitest" size="1" 
																				aria-controls="example">
																			  	<c:choose>
																			  		<c:when test="${instansi.pilihanLokasiTest == '1'}">
																			  			<option value="1" selected="selected">Peserta</option>
																			  			<option value="2" >Verifikator</option>																			  			
																			  		</c:when>
																			  		<c:otherwise>
																			  			<option value="1" >Peserta</option>
																			  			<option value="2" selected='selected'>Verifikator</option>																			  			
																			  		</c:otherwise>
																			  	</c:choose>																					  			
																			 
																		  </select>	
							           
							          														
									</div>
								</div>
								<div class="form-actions">
									<button <c:out value="${isDisabled}"/> class="btn btn-primary btn-large" type="submit">Simpan</button>
									<!-- <button type="reset" class="btn btn-large">Reset</button>  -->
								</div>
								</fieldset>
							</form>
						</div>
						<!-- /widget-content -->

					</div>

				</div>
				<!-- /span12 -->
			</div>
		</div>
		<!-- /.container -->
	</div>
	<!-- /#content -->

	<div id="footer">

		<div class="container">
			Hak Cipta  &copy;  2013 Badan Kepegawaian Negara. Semua Hak Dilindungi.
		</div>
		<!-- /.container -->

	</div>
	<!-- /#footer -->
</body>
</html>

<script>
$(document).ready(function() {
	//$.fn.datepicker.defaults.format = "mm/dd/yyyy";		
});
</script>