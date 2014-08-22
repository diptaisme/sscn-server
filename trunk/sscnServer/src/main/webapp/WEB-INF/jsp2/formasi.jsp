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
						<form action="/sscnServer/logout.do" method="POST"
							name="formLogout">
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
					<li><a href="/sscnServer/dashboard.do">Home</a><span
						class="divider">/</span>
					</li>
					<li><a href="/sscnServer/formasi.do">Formasi Management</a><span
						class="divider">/</span>
					</li>
					<li class="active">List</li>
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
								<i class="icon-th-list"></i> Formasi <b>${instansiNama}</b>
							</h3>
						</div>
						<!-- /widget-header -->

						<div class="widget-content">
							<c:if test="${pesan != null}">
								<div class="alert alert-error" id="mainAlert">
									<a href="#" data-dismiss="alert" class="close">×</a>
									<h4 class="alert-heading">Info!</h4>
									${pesan }
								</div>
							</c:if>

							<div id="example_wrapper" class="dataTables_wrapper form-inline"
								role="grid">
								<div class="row">
									<div class="span6">
										<div class="dataTables_length">
											<label> 10 <!-- <select name="example_length" size="1" id="paging_numpage"
												aria-controls="example">
													<option value="10" selected="selected">10</option>
													<option value="25">25</option>
													<option value="50">50</option>
													<option value="100">100</option>
											</select>  -->records per page </label>
										</div>
									</div>
									<div class="span6">
										<div class="">
											<form id="searchForm" method="post" action="formasi.do">
												<div class="control-group" id="searchBar">
													<input type="hidden" name="searchPar" value="0" /> <input
														type="hidden" name="activePage" id="activePageBar" />
													<c:choose>
														<c:when test="${lokasi != null}">
															<label>Lokasi &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
																&nbsp; &nbsp;<select name="slokasi" size="1" 
																				aria-controls="example">
																			  <c:forEach items="${lokasis}" var="clokasi">
																			  	<c:choose>
																			  		<c:when test="${lokasi == clokasi.kode}">
																			  			<option value="${clokasi.kode}" selected="selected">${clokasi.nama }</option>
																			  		</c:when>
																			  		<c:otherwise>
																			  			<option value="${clokasi.kode}">${clokasi.nama }</option>
																			  		</c:otherwise>
																			  	</c:choose>																					  			
																			  </c:forEach>	
																		  </select>	
															</label>															
														</c:when>
														<c:otherwise>
															<label>Lokasi &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
																&nbsp; &nbsp; <select name="slokasi" size="1" 
																				aria-controls="example">
																			  <option value="">--- PILIH LOKASI ---</option>
																			  <c:forEach items="${lokasis}" var="lokasi">
																			  			<option value="${lokasi.kode}">${lokasi.nama }</option>
																			  </c:forEach>	
																		  </select>
															</label>			  															
														</c:otherwise>
													</c:choose>
												</div>
												
												<div class="control-group" >
													<c:choose>
														<c:when test="${namaJabatan != null}">
															<label>Nama Jabatan  <input type="text"
																aria-controls="example" name="namaJabatan"
																value="${namaJabatan}"> </label>															
														</c:when>
														<c:otherwise>
															<label>Nama Jabatan <input type="text" aria-controls="example"
																name="namaJabatan" > </label>															
														</c:otherwise>
													</c:choose>

													<a class="btn" onClick="submitSearch(event)"><i
														class="icon-search m-icon-white"></i> </a>
													<a class="btn" href="/sscnServer/formasi.do"><i
														 class="m-icon-white">Refresh</i> </a>	
												</div>
											</form>
										</div>
									</div>
								</div>

								<div class="row">
									<div class="span6 pull-right">
										<c:choose>
											<c:when test="${formasi!=null}">
												<a href="/sscnServer/formasi.do#tambah" id="addFormasiModal"
													class="btn btn-small btn-primary"><i class="icon-plus"></i>Tambah
													Formasi</a>
											</c:when>
											<c:otherwise>
												<a href="#tambah" id="addFormasiModal"
													class="btn btn-small btn-primary"><i class="icon-plus"></i>Tambah
													Formasi</a>
											</c:otherwise>
										</c:choose>

									</div>
								</div>

								<table
									class="table table-striped table-bordered table-highlight">
									<thead>
										<tr>
											<th class="sorting_asc" role="columnheader" onclick="sortBy('namaJabatan')">Nama Jabatan</th>
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
												<c:set var="i" value="${0}" />
												<td><c:forEach items="${formasi.dtFormasis}"
														var="dtFormasi">
														<c:out value="${dtFormasi.pendidikan.nama}" /> (<c:out
															value="${dtFormasi.jumlah}" />) <br />
													</c:forEach>
												</td>
												<td>${formasi.jumlah}</td>
												<td>${formasi.refLokasi.nama}</td>
												<td>
													<!--  <form action="getFormasi.do" method="post">
														<input type="hidden" name="id" value="${formasi.id}"> -->
													<button class="btn btn-primary btn-mini"
														onclick="editFormasi(event, '${formasi.id}')">Edit</button>
													<!--  </form>
													<form action="formasiDelete.do" method="post">
														<input type="hidden" name="id" value="${formasi.id}">-->
													<button class="btn btn-primary btn-mini"
														onclick="deleteFormasi(event, '${formasi.id}')">Delete</button>
													<!--  </form> --></td>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<div class="row">
									<jsp:include page="paging.jsp" />
									<form id="pagingForm" method="post"
										action="/sscnServer/formasi.do">
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

			<div class="row">
				<div class="span12">
					<div id="validation" class="widget highlight widget-form">
						<div class="widget-header">
							<h3>
								<a name="tambah"></a>
								<c:choose>
									<c:when test="${formasi != null}">
										<i class="icon-pencil"></i> Ubah Formasi</c:when>
									<c:otherwise>
										<i class="icon-pencil"></i> Tambah Formasi</c:otherwise>
								</c:choose>


							</h3>
						</div>
						<!-- /widget-header -->

						<div class="widget-content">

							<div class="alert alert-block" style="display: none">
								<a class="close" data-dismiss="alert" href="#">&times;</a>
								<h4 class="alert-heading">Validation Example!</h4>
								Submit the below form to view the live form validation with
								integrated Bootstrap error messages.
							</div>
							<c:if test="${formasi != null}">
								<form action="/sscnServer/formasiUpdate.do" method="POST"
									id="formasiForm" class="form-horizontal"
									novalidate="novalidate">
									<input type="hidden" name="id" value="${formasi.id}" />
									<input type="hidden" name="jenisFormasi" value="1" />
									<fieldset>
										<c:if test="${isDaerah == false}">
											<div class="control-group">
												<label class="control-label" for="lokasi">Lokasi</label>
												<div class="controls">
													<input
														type="hidden" class="input-large" name="lokasi"
														id="lokasiValue" value="${formasi.refLokasi.kode}">
													<input type="text" class="input-large" name="lokasiLabel"
														id="lokasiLabel" value="${formasi.refLokasi.nama}">
													<span class="help-inline"
														style="display: none; color: #b94a48;" id="lokasiAlert">Something
														may have gone wrong</span>
												</div>
											</div>
										</c:if>
										<c:if test="${isDaerah == true}">
											<input type="hidden" class="input-large" name="lokasi"
												value="${kodeLokasi}" id="lokasiValue">
										</c:if>
										<div class="control-group">
											<label class="control-label" for="jabatan">Jabatan</label>
											<div class="controls">
												<input type="hidden" class="input-large" name="jabatan"
													id="jabatanValue" value="${formasi.refJabatan.kode}">
												<input type="text" class="input-large" name="jabatanLabel"
													id="jabatanLabel" value="${formasi.refJabatan.nama}">
												<span class="help-inline"
													style="display: none; color: #b94a48;" id="jabatanAlert">Something
													may have gone wrong</span>
											</div>
										</div>
										<c:set var="i" value="0" />
										<c:forEach items="${formasi.dtFormasis}" var="dtFormasi">
											<c:if test="${i==0}">
												<div class="control-group" id="pendidikanRow">
													<label class="control-label">Jenjang Pendidikan</label>
													<div class="controls">
														<select name="tkPendidikan[]" id="cTkPendidikan">
															<option value="0">--PILIH--</option>
															<c:choose>
																<c:when test="${dtFormasi.pendidikan.tingkat == '10'}">
																	<option value="10" selected="selected">SD-SLTP</option>
																	<option value="20" >SLTA-D3</option>
																	<option value="30" >D4/S1-S3</option>
																</c:when>
																<c:when test="${dtFormasi.pendidikan.tingkat == '20'}">
																	<option value="10" >SD-SLTP</option>
																	<option value="20" selected="selected">SLTA-D3</option>
																	<option value="30" >D4/S1-S3</option>
																</c:when>
																<c:when test="${dtFormasi.pendidikan.tingkat == '30'}">
																	<option value="10" >SD-SLTP</option>
																	<option value="20" >SLTA-D3</option>
																	<option value="30" selected="selected">D4/S1-S3</option>
																</c:when>
																<c:otherwise>
																	<option value="10" >SD-SLTP</option>
																	<option value="20" >SLTA-D3</option>
																	<option value="30" >D4/S1-S3</option>
																</c:otherwise>
															</c:choose>	
														</select>
													</div> &nbsp;
													<label class="control-label" for="input01">Pendidikan</label>
													<div class="controls">
														<input type="hidden" class="input-large"
															name="pendidikan[]" value="${dtFormasi.pendidikan.kode}"
															id="pendidikanValue"> <input type="text"
															class="input-large" name="pendidikanLabel[]"
															value="${dtFormasi.pendidikan.nama}" id="pendidikanLabel">
														<input type="text" class="input-mini"
															name="pendidikanJmlh[]" value="${dtFormasi.jumlah }"
															id="pendidikanJmlh" onChange="hitungUlang()">
														<button id="btnAddPddkn" class="btn btn-primary btn-small">Add</button>
														<span class="help-inline"
															style="display: none; color: #b94a48;"
															id="pendidikanAlert">Something may have gone wrong</span>
													</div>
												</div>
											</c:if>
											<c:if test="${i>0}">
												<div class="control-group" id="pendidikanRow${i}">
													<label class="control-label" for="pendidikan">Jenjang
														Pendidikan</label>
													<div class="controls">
														<select name="tkPendidikan[]" >
															<option value="0">--PILIH--</option>
															<c:choose>
																<c:when test="${dtFormasi.pendidikan.tingkat == '10'}">
																	<option value="10" selected="selected">SD-SLTP</option>
																	<option value="20" >SLTA-D3</option>
																	<option value="30" >D4/S1-S3</option>
																</c:when>
																<c:when test="${dtFormasi.pendidikan.tingkat == '20'}">
																	<option value="10" >SD-SLTP</option>
																	<option value="20" selected="selected">SLTA-D3</option>
																	<option value="30" >D4/S1-S3</option>
																</c:when>
																<c:when test="${dtFormasi.pendidikan.tingkat == '30'}">
																	<option value="10" >SD-SLTP</option>
																	<option value="20" >SLTA-D3</option>
																	<option value="30" selected="selected">D4/S1-S3</option>
																</c:when>
																<c:otherwise>
																	<option value="10" >SD-SLTP</option>
																	<option value="20" >SLTA-D3</option>
																	<option value="30" >D4/S1-S3</option>
																</c:otherwise>
															</c:choose>
														</select>
													</div>&nbsp;
													<label class="control-label" for="pendidikan">Pendidikan</label>
													<div class="controls">
														<input type="hidden" class="input-large"
															name="pendidikan[]" value="${dtFormasi.pendidikan.kode}"
															id="pendidikanValue${i}"> <input type="text"
															class="input-large" name="pendidikanLabel[]"
															value="${dtFormasi.pendidikan.nama}"
															id="pendidikanLabel${i}"> <input type="text"
															class="input-mini" name="pendidikanJmlh[]"
															value="${dtFormasi.jumlah }" id="pendidikanJmlh${i}"
															onChange="hitungUlang()">
														<button id="btnDelPddkn" onClick="delPddkn(event, this)"
															class="btn btn-primary btn-small">X</button>
														<span class="help-inline"
															style="display: none; color: #b94a48;">Something
															may have gone wrong</span>
													</div>
												</div>
											</c:if>
											<c:set var="i" value="${i+1}" />

										</c:forEach>
										<div class="control-group">
											<label class="control-label" for="jumlah">Jumlah</label>
											<div class="controls">
												<input type="text" class="input-mini" name="jumlah"
													id="jumlah" value="${formasi.jumlah }">
											</div>
										</div>

										<div class="form-actions">
											<button class="btn btn-primary btn-large" type="submit">Simpan</button>
											<!-- <button type="reset" class="btn btn-large">Reset</button>  -->
										</div>
									</fieldset>
								</form>
							</c:if>
							<c:if test="${formasi == null}">
								<form action="/sscnServer/formasiSave.do" method="POST"
									id="formasiForm" class="form-horizontal"
									novalidate="novalidate">
									<input type="hidden" name="jenisFormasi" value="1" />
									<fieldset>
										<c:if test="${isDaerah == false}">
											<div class="control-group">
												<label class="control-label" for="lokasi">Lokasi</label>
												<div class="controls">
													<input type="hidden" class="input-large" name="lokasi"
														value="${lastInputLokasi.kode}" id="lokasiValue">
													<input type="text" class="input-large" name="lokasiLabel"
														id="lokasiLabel" value="${lastInputLokasi.nama}">
													<span class="help-inline"
														style="display: none; color: #b94a48;" id="lokasiAlert">Something
														may have gone wrong</span>
												</div>
											</div>
										</c:if>
										<c:if test="${isDaerah == true}">
											<input type="hidden" class="input-large" name="lokasi"
												value="${kodeLokasi}" id="lokasiValue">
										</c:if>
										<div class="control-group">
											<label class="control-label" for="jabatan">Jabatan</label>
											<div class="controls">
												<input type="hidden" class="input-large" name="jabatan"
													id="jabatanValue"> <input type="text"
													class="input-large" name="jabatanLabel" id="jabatanLabel">
												<span class="help-inline"
													style="display: none; color: #b94a48;" id="jabatanAlert">Something
													may have gone wrong</span>
											</div>
										</div>
										<div class="control-group" id="pendidikanRow">
											<label class="control-label">Jenjang Pendidikan</label>
											<div class="controls">
												<select name="tkPendidikan[]" class="cTkPendidikan"
													id="cTkPendidikan">
													<option value="0">--PILIH--</option>
													<option value="10">SD-SLTP</option>
													<option value="20">SLTA-D3</option>
													<option value="30">D4/S1-S3</option>
												</select>
											</div>
											&nbsp; <label class="control-label" for="pendidikan">Pendidikan</label>
											<div class="controls">
												<input type="hidden" class="input-large" name="pendidikan[]"
													id="pendidikanValue"> <input type="text"
													class="input-large" name="pendidikanLabel[]"
													id="pendidikanLabel" >
												<input type="text" class="input-mini"
													name="pendidikanJmlh[]" id="pendidikanJmlh"
													onChange="hitungUlang()">
												<button id="btnAddPddkn" class="btn btn-primary btn-small">Add</button>
												<span class="help-inline"
													style="display: none; color: #b94a48;" id="pendidikanAlert">Something
													may have gone wrong</span>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="jumlah">Jumlah</label>
											<div class="controls">
												<input type="text" class="input-mini" name="jumlah"
													id="jumlah"> <span class="help-inline"
													style="display: none; color: #b94a48;">Something may
													have gone wrong</span>
											</div>
										</div>

										<div class="form-actions">
											<button class="btn btn-primary btn-large" type="submit">Tambah</button>
											<!-- <button type="reset" class="btn btn-large">Reset</button>  -->
										</div>
									</fieldset>
								</form>
							</c:if>
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

		<div class="container">Hak Cipta &copy; 2013 Badan Kepegawaian
			Negara. Semua Hak Dilindungi.</div>
		<!-- /.container -->

	</div>
	<!-- /#footer -->

	<script>
		jQuery(document)
				.ready(
						function() {
							sortBy = function (by){
								if (by == 'instansi'){
									window.location = "formasi.do";
								} else {
									window.location = "formasi.do?sorting=" + by;
								} 					
							};
							
							$("#mainAlert").delay(3200).fadeOut(800);
							
							var autocomp_opt = {
				
								source : function(request, response) {
									var tkPend = this.element.closest('div').prev().prev().find('select');
						            //alert(tkPend.val());
									$.ajax({
												url : "/sscnServer/findPendidikanLikeByNameAndTkPddkn.do",
												dataType : "jsonp",
												data : {
													featureClass : "P",
													style : "full",
													maxRows : 12,
													name_startsWith : request.term,															
													tingkat : tkPend.val()
												},
												success : function(data) {
													availableTagsArr = data.pendidikans;
													response($
															.map(
																	data.pendidikans,
																	function(
																			item) {
																		return {
																			code : item.kode,
																			label : item.nama,
																			value : item.nama
																		}
																	}));
												}
											});
								},
								minLength : 2,
								select : function(event, ui) {
									var el = $(this).prev();
									el.val(ui.item.code);
									
								},
								open : function() {
									$(this).removeClass("ui-corner-all")
											.addClass("ui-corner-top");
								},
								close : function() {
									$(this).removeClass("ui-corner-top")
											.addClass("ui-corner-all");
								},
								change: function(event, ui){
									if (ui.item == null){
										validasiPendidikanArr(this);
									}
								}
							};

							
							
							var i = 0;
							<c:if test="${formasi != null}">
								i = <c:out value="${fn:length(formasi.dtFormasis) - 1}"/>;
								for (q=0;q<i;q++){
									$('#pendidikanLabel'+(q+1)).autocomplete(autocomp_opt);	
								}
															
							</c:if>	
							
							$('#btnAddPddkn')
									.click(
											function(e) {
												e.preventDefault();
												var newDiv = '<div class="control-group pddkn" id="pendidikanRow'
														+ (i + 1)
														+ '"> '
														+ '<label class="control-label">Jenjang Pendidikan</label>'
														+ '<div class="controls">'
														+ '<select name="tkPendidikan[]" class="cTkPendidikan" id="cTkPendidikan'+(i+1)+'">'
														+ '	<option value="0">--PILIH--</option>'
														+ '	<option value="10">SD-SLTA</option>'
														+ ' <option value="20">D1-D3</option>'
														+ ' <option value="30">D4/S1-S3</option>'
														+ '</select>'
														+ '	</div> &nbsp;'
														+ '<label class="control-label" for="input01"></label> '
														+ '<div class="controls"> '													
														+ '<input type="hidden" name="pendidikan[]" id="pendidikanValue'+(i+1)+'"/> '
														+ '<input type="text" class="input-large" id="pendidikanLabel'+(i+1)+'" name="pendidikanLabel[]"> '
														+ '<input type="text" class="input-mini" id="pendidikanJmlh'+(i+1)+'" name="pendidikanJmlh[]" onchange="hitungUlang()"> <button id="btnDelPddkn" onClick="delPddkn(event, this)" class="btn btn-primary btn-small">X</button><span class="help-inline" style="display:none;color: #b94a48;">Something may have gone wrong</span>'
														+ '</div>'													
														+ '</div>';

																										
												if (i == 0) {
													$(newDiv).insertAfter(
															'#pendidikanRow');
												} else {
													$(newDiv).insertAfter(
															'#pendidikanRow'
																	+ i);
												}
												var obj = autocomp_opt;
												$('#pendidikanLabel'+(i+1)).autocomplete(autocomp_opt);
												i++;
											});

							validasiPendidikanArr = function(elem){
								var x=0;
								var found=false;
								var selValue = 0;
								while (x<=(availableTagsArr.length-1) && !found){
									if (availableTagsArr[x].nama.toLowerCase() === $(elem).val().toLowerCase() ){
										selValue = availableTagsArr[x].kode;
										found = true;	
									}	
									x++;
								}
								if (found){
									$(elem).prev().val(selValue);
								} else {
									$(elem).prev().val("");
									$(elem).val("");
									var alert = $(elem).next().next().next(); 
									$(alert).html("input tidak sesuai dengan data yang disediakan");
									$(alert).show();	
									$(alert).delay(3200).fadeOut(300);
								}	
							}
							
							

							delPddkn = function(ev, elem){
								ev.preventDefault();
								var pdiv = $(elem).closest('div').parent('div'); 
								$(pdiv).remove();
								i--;
								hitungUlang();
							};

														
							var availableTags = new Array();
							var availableTagsArr = new Array();
							var isValidating = true;
							
							$("#lokasiLabel")
							.autocomplete(
									{
										source : function(request,
												response) {
											$
													.ajax({
														
														url : "/sscnServer/findLokasiLikeByName.do",
														dataType : "jsonp",
														data : {
															featureClass : "P",
															style : "full",
															maxRows : 12,
															name_startsWith : request.term
														},
														success : function(
																data) {
															availableTags = data.lokasis;
															response($
																	.map(
																			data.lokasis,
																			function(
																					item) {
																				return {
																					code : item.kode,
																					label : item.nama,
																					value : item.nama
																				}
																			}));
														}
													});
										},
										minLength : 2,
										select : function(event, ui, data) {
											$('#lokasiValue').val(
													ui.item.code);										
										},
										open : function() {
											$(this)
													.removeClass(
															"ui-corner-all")
													.addClass(
															"ui-corner-top");
										},
										close : function() {
											$(this)
													.removeClass(
															"ui-corner-top")
													.addClass(
															"ui-corner-all");
										},
										change : function(event, ui){
											if (ui.item == null){
												validasiLokasi();
											}											
										}
							});


							$("#jabatanLabel")
							.autocomplete(
									{
										source : function(request,
												response) {
											$
													.ajax({
														url : "/sscnServer/findJabatanLikeByName.do",
														dataType : "jsonp",
														data : {
															featureClass : "P",
															style : "full",
															maxRows : 12,
															name_startsWith : request.term
														},
														success : function(
																data) {
															availableTags = data.jabatans;
															response($
																	.map(
																			data.jabatans,
																			function(
																					item) {
																				return {
																					code : item.kode,
																					label : item.nama,
																					value : item.nama
																				}
																			}));
														}
													});
										},
										minLength : 3,
										select : function(event, ui) {
											$('#jabatanValue').val(ui.item.code);
										},
										open : function() {
											$(this)
													.removeClass(
															"ui-corner-all")
													.addClass(
															"ui-corner-top");
										},
										close : function() {
											$(this)
													.removeClass(
															"ui-corner-top")
													.addClass(
															"ui-corner-all");
										},
										change : function(event, ui){
											if (ui.item == null){
												validasiJabatan();
											}
										}
									});	
							
							validasiLokasi = function(){
								var x=0;
								var found=false;
								var selValue=0;
							
								while (x<availableTags.length && !found){
									if (availableTags[x].nama.toLowerCase() === $('#lokasiLabel').val().toLowerCase() ){
										selValue = availableTags[x].kode;
										found = true;	
									}	
									x++;
								}
								if (found){
									$('#lokasiValue').val(
											selValue);
		
								} else {
									$('#lokasiValue').val("");
									$('#lokasiLabel').val("");
									$('#lokasiAlert').html("input tidak sesuai dengan data yang disediakan");
									$('#lokasiAlert').show();	
									$("#lokasiAlert").delay(3200).fadeOut(300);
								}	
							}

							validasiJabatan = function(){
								var x=0;
								var found=false;
								var selValue=0;
							
								while (x<availableTags.length && !found){
									if (availableTags[x].nama.toLowerCase() === $('#jabatanLabel').val().toLowerCase() ){
										selValue = availableTags[x].kode;
										found = true;	
									}	
									x++;
								}
								if (found){									
									$('#jabatanValue').val(selValue);
								} else {
									$('#jabatanValue').val("");
									$('#jabatanLabel').val("");
									$('#jabatanAlert').html("input tidak sesuai dengan data yang disediakan");
									$('#jabatanAlert').show();	
									$("#jabatanAlert").delay(3200).fadeOut(300);
								}	
							}
							
							$("#pendidikanLabel")
							.autocomplete(
									{	
										source : function(request,
												response) {
											var tkPend = this.element.closest('div').prev().prev().find('select');
											$
													.ajax({
														url : "/sscnServer/findPendidikanLikeByNameAndTkPddkn.do",
														dataType : "jsonp",
														data : {
															featureClass : "P",
															style : "full",
															maxRows : 12,
															name_startsWith : request.term,
															tingkat : tkPend.val()
														},
														success : function(
																data) {
															availableTags = data.pendidikans;
															response($
																	.map(
																			data.pendidikans,
																			function(
																					item) {
																				return {
																					code : item.kode,
																					label : item.nama,
																					value : item.nama
																				}
																			}));
														}
													});
										},
										minLength : 2,
										select : function(event, ui) {
											$('#pendidikanValue').val(ui.item.code);		
										},
										open : function() {
											$(this)
													.removeClass(
															"ui-corner-all")
													.addClass(
															"ui-corner-top");
										},
										close : function() {
											$(this)
													.removeClass(
															"ui-corner-top")
													.addClass(
															"ui-corner-all");
										},
										change : function(event, ui){
											if (ui.item == null){
												//validasiPendidikan();
											}
										}
									});
							
							validasiPendidikan = function(){
								var x=0;
								var found=false;
								var selValue = 0;
								
								while (x<availableTags.length && !found){
									if (availableTags[x].nama.toLowerCase() === $('#pendidikanLabel').val().toLowerCase() ){
										selValue = availableTags[x].kode;
										found = true;	
									}	
									x++;
								}
								if (found){
									$('#pendidikanValue').val(selValue);		
								} else {
									$('#pendidikanValue').val("");
									$('#pendidikanLabel').val("");
									$('#pendidikanAlert').html("input tidak sesuai dengan data yang disediakan");
									$('#pendidikanAlert').show();	
									$("#pendidikanAlert").delay(3200).fadeOut(300);
								}	
							}
							
							hitungUlang = function(){
								var jmlP = $('#pendidikanJmlh').val();
								if (typeof(jmlP) === 'undefined'){
									jmlP = 0;
								} 
								var jmlTot = parseInt(jmlP);
								var jml = new Array()
								if (i>0){
									for (x=1;x<=i;x++){
										jml[x] = $('#pendidikanJmlh' + x).val();
										if (typeof(jml[x]) !== "undefined"){
											jmlTot += parseInt(jml[x]);
										}	
									}
								}

								$('#jumlah').val(jmlTot);								
							}

							editFormasi = function(event, id){
								event.preventDefault();
								window.location = '/sscnServer/getFormasi.do?id='+id;
							}

							deleteFormasi = function(event, id){
								event.preventDefault();
								window.location = '/sscnServer/formasiDelete.do?id='+id;
							}

							$("#formasiForm").submit(function(event) {
								 
								  /* stop form from submitting normally */
								  //event.preventDefault();
								  var valid = true;
								  $("#formasiForm :input").each(function(){
									  var input = $(this); // This is the jquery object of the input, do what you will
									  //alert($(input).attr('name') + " -> " +  $(input).val());
									  if (typeof($(input).attr('name')) != "undefined"){
										   if ($(input).val() == ""){											  
											   var alert2 = $(input).siblings('.help-inline');
											   $(alert2).html("input tidak boleh kosong");
											   $(alert2).show();											   				
											   valid = false;							   
										   }
									  }
								  });
								  if (!valid){
									  return false;
								  }
							});	  
						});
		
	</script>
</body>
</html>
