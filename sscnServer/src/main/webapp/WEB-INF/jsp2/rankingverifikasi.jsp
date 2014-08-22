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
								<i class="icon-th-list"></i> Verfikasi Pendaftaran
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
									<div class="span6">&nbsp;
									  <!-- <div class="">
											<form id="searchForm" method="post" action="verifikasi.do">
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
										</div>-->
									</div> 
								</div>

								<div class="row">
									<div class="span6">
										<div >
											<form class="form-horizontal" id="searchForm" action="verifikasiFilter.do" method="post">
												<input type="hidden" id="searchPar" name="searchPar" value="${searchPar}" />
												<input type="hidden" name="activePage" id="activePageBar" value="${activePage}"/>
												<fieldset>
													<div class="control-group">
											            <label for="tkPendidikan" class="control-label">Tk Pendidikan</label>
											            <div class="controls">
											              <select id="tkPendidikan" name="tkPendidikan">
											              <c:choose>
														      <c:when test="${tkPend=='10'}">
																	<option value="10" selected="selected">SD-SLTA</option>
																	<option value="20">D1-D3</option>
																	<option value="30">D4/S1-S3</option>											                		      
														      </c:when>
														      <c:when test="${tkPend=='20'}">
																	<option value="10">SD-SLTA</option>
																	<option value="20" selected="selected">D1-D3</option>
																	<option value="30">D4/S1-S3</option>																	      
														      </c:when>
														
															  <c:when test="${tkPend=='30'}">
																	<option value="10" >SD-SLTA</option>
											                		<option value="20" >D1-D3</option>
											                		<option value="30" selected="selected">D4/S1 - S3</option>      
														      </c:when>	
														      <c:otherwise>
														      		<option value="10" selected="selected">SD-SLTA</option>
											                		<option value="20">D1-D3</option>
											                		<option value="30">D4/S1-S3</option> 
														      </c:otherwise>
														</c:choose>
											                											                
											              </select>
											            </div>
											        </div>
											        <div class="control-group">
											            <label for="fromUsia" class="control-label">Range Usia</label>
											            <div class="controls">
											              <input type="number" value="<c:out value="${fromUsia}"/>" class="input-small" name="fromUsia" id="fromUsia"> s.d 
											              <input type="number" value="<c:out value="${toUsia}"/>" class="input-small" name="toUsia" id="toUsia">
											            </div>
											        </div>
											        <div class="control-group">
											            <label for="fromIPK" class="control-label">Range Indeks Prestasi</label>
											            <div class="controls">
											              <input type="number" value="<c:out value="${fromIPK}"/>" class="input-small" name="fromIPK" id="fromIPK"> s.d 
											              <input type="number" value="<c:out value="${toIPK}"/>" class="input-small" name="toIPK" id="toIPK">
														<!-- <a class="btn" onClick="submitFilter(event)"><i
																class="icon-search m-icon-white"></i>
														</a> -->	
											            </div>											            
											        </div> 
											 	    <div class="control-group">
											            <label for="jumlahData" class="control-label">Jumlah Data</label>
											            <div class="controls">
											              <input type="number" class="input-small" name="jumlahData" id="jumlahData" value="<c:out value="${jumlahData}"/>" > &nbsp; 
											              &nbsp;
											              <a class="btn" onClick="submitFilter(event)"><i
																class="icon-search m-icon-white"></i>
															</a>
											            </div>											            
											        </div>  
											        
									          	</fieldset>
											</form>
										</div>
									</div>
									<div class="span6"></div>
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
												<c:choose>
													<c:when
														test="${pendaftar.status == null || pendaftar.status == ''}">
														<td><a href="#"
															onclick="verifikasi(this,'${pendaftar.id }')"
															class="btn btn-small btn-primary"><i
																class="icon-edit"></i>Verifikasi</a>&nbsp;<a href="#"
															onclick="info(this,'${pendaftar.id }')"
															class="btn btn-small btn-primary"><i
																class="icon-edit"></i>Info</a>&nbsp;</td>
													</c:when>
													<c:otherwise>
														<td>Sudah diverifikasi &nbsp;<a href="#"
															onclick="info(this,'${pendaftar.id }')"
															class="btn btn-small btn-primary"><i
																class="icon-edit"></i>Info</a>&nbsp; <c:if
																test="${pendaftar.noPeserta != null && pendaftar.noPeserta != ''}">
																<a href="#"
																	onclick="cetak(this,'${pendaftar.noRegister }')"
																	class="btn btn-small btn-primary"><i
																	class="icon-edit"></i>Cetak </a>
															</c:if></td>
													</c:otherwise>
												</c:choose>

											</tr>
										</c:forEach>
									</tbody>
								</table>
								<div class="row">
									<jsp:include page="paging.jsp" />
									&nbsp;<a href="#" onclick="verifikasiBatch(this)" class="btn btn-small btn-primary">
												<i class="icon-edit"></i>Verifikasi Batch</a>										  	
									<form id="pagingForm" method="post" action="verifikasiFilter.do">
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

	<div id="myModal" title="Check">
		<form class="form-horizontal" action="verifikasiSave.do" method="post"
			id="formVerifikasi">
			<fieldset>
				<div id="loadingImage" style="display: none">
					<img src="/sscnServer/resources/img/ajax-loader.gif" />
				</div>
				<div id="alert" class="alert alert-error" style="display: none">
				</div>
				<input type="hidden" name="pendaftarId" id="pendaftarId" />
				<div id="infoPendaftar" style="display: none">
					<div class="control-group">
						<div id="namaPendaftar"></div>
					</div>
	
					<div class="control-group">
						<div id="ttlPendaftar"></div>
					</div>
	
					<div class="control-group">
						<div id="noRegPendaftar"></div>
					</div>
	
					<div class="control-group">
						<div id="pddknPendaftar"></div>
					</div>
				</div>
				<!-- <hr> -->
				<div class="form-actions">
					<button type="submit" class="btn btn-primary btn-large">
						Simpan</button>
					<button class="btn btn-large" id="btnCancel">Batal</button>
				</div>
				<div class="control-group">
					<label class="control-label" for="input01"
						style="width: 400px; padding-right: 20px">Select/Unselect
						All</label>
					<div class="controls">
						<input type="checkbox" id="selectedAll">
					</div>
				</div>
				<c:forEach items="${persyaratans}" var="persyaratan">
					<div class="control-group">
						<label class="control-label" for="input01"
							style="width: 400px; padding-right: 20px">${persyaratan.syarat}</label>
						<div class="controls">
							<input type="checkbox" name="persyaratanIds[]"
								value="${persyaratan.id}">
						</div>
					</div>
				</c:forEach>

				<div class="form-actions">
					<button type="submit" class="btn btn-primary btn-large">
						Simpan</button>
					<button class="btn btn-large" id="btnCancel2">Batal</button>
				</div>
			</fieldset>
		</form>
	</div>

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

	<div id="myModal3" title="Batch Verifikasi">
		<form class="form-horizontal" action="verifikasiBatchSave.do" method="post"
			id="formVerifikasiBatch">
			<fieldset>
				<div id="loadingImage2" style="display: none">
					<img src="/sscnServer/resources/img/ajax-loader.gif" />
				</div>
				<div id="alert2" class="alert alert-error" style="display: none">
				</div>
				<input type="hidden" name="tkPendidikan" value="<c:out value="${tkPendidikan}"/>">
				<input type="hidden" name="fromUsia" value="<c:out value="${fromUsia}"/>">
				<input type="hidden" name="toUsia" value="<c:out value="${toUsia}"/>">
				<input type="hidden" name="fromIPK" value="<c:out value="${fromIPK}"/>">
				<input type="hidden" name="toIPK" value="<c:out value="${toIPK}"/>">
				<input type="hidden" name="jmlh" value="<c:out value="${jmlh}"/>">
				<div class="form-actions">
					<button type="submit" class="btn btn-primary btn-large">
						Simpan</button>
					<button class="btn btn-large" id="btnBatchCancel">Batal</button>
				</div>
				<div class="control-group">
					<label class="control-label" for="input01"
						style="width: 400px; padding-right: 20px">Select/Unselect
						All</label>
					<div class="controls">
						<input type="checkbox" id="selectedAllBatch">
					</div>
				</div>
				<c:forEach items="${persyaratans}" var="persyaratan">
					<div class="control-group">
						<label class="control-label" for="input01"
							style="width: 400px; padding-right: 20px">${persyaratan.syarat}</label>
						<div class="controls">
							<input type="checkbox" name="persyaratanIds[]"
								value="${persyaratan.id}">
						</div>
					</div>
				</c:forEach>

				<div class="form-actions">
					<button type="submit" class="btn btn-primary btn-large">
						Simpan</button>
					<button class="btn btn-large" id="btnBatchCancel2">Batal</button>
				</div>
			</fieldset>
		</form>
	</div>

	<form action="/sscnServer/ReportServlet" method="post" id="cetak"
		target="_blank">
		<input type="hidden" name="typeReport" value="rptPesertaUjian" /> <input
			type="hidden" name="no_pendaftaran" id="rptNoPendaftaran" />
	</form>
	<div id="javaValues" style="display: none;">
		<div id="numRow">${numRow}</div>
	</div>
	<script>
	
	jQuery.fn.ForceNumericOnly =
		function()
		{
		    return this.each(function()
		    {
		        $(this).keydown(function(e)
		        {
		            var key = e.charCode || e.keyCode || 0;
		            // allow backspace, tab, delete, enter, arrows, numbers and keypad numbers ONLY
		            // home, end, period, and numpad decimal
		            return (
		                key == 8 || 
		                key == 9 ||
		                key == 13 ||
		                key == 46 ||
		                key == 110 ||
		                key == 190 ||
		                (key >= 35 && key <= 40) ||
		                (key >= 48 && key <= 57) ||
		                (key >= 96 && key <= 105));
		        });
		    });
		};
		jQuery(document)
				.ready(
						function() {

							$("#fromUsia").ForceNumericOnly();
							$("#toUsia").ForceNumericOnly();
							$("#fromIPK").ForceNumericOnly();
							$("#toIPK").ForceNumericOnly();		
									
							submitFilter = function(event) {
								event.preventDefault();
								$('#searchPar').val("1");
								$('#searchForm').submit();								
							}
							
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
												window.location = "verifikasi.do?numPage="
														+ $(this).val();
											});

							/*check all persyaratan*/
							$("#selectedAll")
									.click(
											function() {
												var checkedValue = $(this)
														.attr("checked");
												if (checkedValue == 'checked') {
													$(
															'input[name=persyaratanIds\\[\\]]')
															.attr("checked",
																	checkedValue);
												} else {
													$(
															'input[name=persyaratanIds\\[\\]]')
															.attr("checked",
																	false);
												}
											});
							
							$("#selectedAllBatch")
							.click(
									function() {
										var checkedValue = $(this)
												.attr("checked");
										if (checkedValue == 'checked') {
											$(
													'input[name=persyaratanIds\\[\\]]')
													.attr("checked",
															checkedValue);
										} else {
											$(
													'input[name=persyaratanIds\\[\\]]')
													.attr("checked",
															false);
										}
									});
							
							$("#myModal").dialog({
								autoOpen : false,
								height : 700,
								width : 900,
								position : {
									my : "center",
									at : "top",
									of : window
								},
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
								height : 700,
								width : 900,
								position : {
									my : "center",
									at : "top",
									of : window
								},
								modal : true
							});

							var selRowTable;

							ubah = function(elem, id) {

								$('#pendaftarId2').val(id);
								$
										.ajax({
											type : "GET",
											url : "getPendaftaran.do?id=" + id,
											cache : false,
											success : function(data) {
												if (data.data.status == "1") {
													// verifikasi == ok

												} else if (data.data.status == "0") {
													// verifikasi == nok
													var nokList = data.mapData.verNoks;
													for (i = 0; i < nokList.length; i++) {
														$(
																'#check_'
																		+ nokList[i])
																.attr(
																		'checked',
																		false);
													}
												}
											},
											dataType : "json"
										});
								$("#myModal3").dialog("open");
							}

							verifikasi = function(elem, id) {
								selRowTable = $(elem).closest('tr');
								$('#pendaftarId').val(id);
								$.ajax({
									type : "GET",
									url : "getPendaftaran.do?id=" + id,
									cache : false,
									success : function(data) {
										$('#namaPendaftar').html(
												"<label>Nama : &nbsp;"
														+ data.data.nama
														+ "</label>");
										$('#ttlPendaftar').html(
												"<label>Tempat, Tanggal Lahir : &nbsp;"
														+ data.data.tmpLahir
														+ ", "
														+ data.data.tglLahir
														+ "</label>");
										$('#noRegPendaftar').html(
												"<label>No Registrasi : &nbsp;"
														+ data.data.noRegister
														+ "</label>");
										$('#pddknPendaftar').html(
												"<label>Pendidikan : &nbsp;"
														+ data.data.pendidikan
														+ "</label>");
									},
									dataType : "json"
								});
								$('#infoPendaftar').show();
								$("#myModal").dialog("open");
							}

							verifikasiBatch = function(elem) {
								var count = <c:out value="${count}"/>;
								if (count > 0){
									$("#myModal3").dialog("open");
								} else {
									alert('Tidak ada data yang dapat diverifikasi');
								}
								
							}

							cetak = function(elem, id) {
								$('#rptNoPendaftaran').val(id);
								$('#cetak').submit();
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

							$('#btnCancel').click(function(event) {
								event.preventDefault();
								$('#myModal').dialog("close");
							});

							$('#btnCancel2').click(function(event) {
								event.preventDefault();
								$('#myModal').dialog("close");
							});

							$('#btnBatchCancel').click(function(event) {
								event.preventDefault();
								$('#myModal3').dialog("close");
							});

							$('#btnBatchCancel2').click(function(event) {
								event.preventDefault();
								$('#myModal3').dialog("close");
							});

							$('#btnCancelInfo').click(function(event) {
								event.preventDefault();
								$('#myModal2').dialog("close");
								$('#valueStatus').hide();
							});

							$('#btnCancelEdit').click(function(event) {
								event.preventDefault();
								$('#myModal3').dialog("close");
							});
							
							$("#formVerifikasiBatch").submit(
									function(event) {

										/* stop form from submitting normally */
										event.preventDefault();

										$('#loadingImage').show();
										$('#alert').hide();

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
														alert(data.message);
														refresh();
														return false;
													}

													if (data.result == -1) {
														window.location = "login.do";
													}
												});

										posting.error(function() {
											alert('failure');
										});

										function refresh() {
											window.location = "verifikasiFilter.do";
										}

									});
							
							$("#formVerifikasi")
									.submit(
											function(event) {

												/* stop form from submitting normally */
												event.preventDefault();

												$('#loadingImage').show();
												$('#alert').hide();

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
																alert(data.message);
																refresh();
																return false;
															}

															if (data.result == -1) {
																window.location = "login.do";
															}
														});

												posting.error(function() {
													alert('failure');
												});

												function refresh() {
													//window.location = "verifikasiFilter.do";
													$('searchPar').val("1");
													$('#searchForm').submit();
												}

											});

						});
	</script>
</body>
</html>
