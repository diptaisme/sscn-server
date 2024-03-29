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

<script src="/sscnServer/resources/js/demos/charts/bar.js"></script>

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
															"http://localhost:8080/sscnServer/userChangePassword.do",
															term, "json");

											/* Put the results in a div */
											posting
													.done(function(data) {
														$('#loadingImage')
																.hide();
														if (data.result == 0) {
															var html = '<strong>Error!</strong> '
																	+ data.message;
															$('#old_password').val(
															'');
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
															$('#old_password').val(
															'');
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
							<a href="#" onclick="openFormGantiPasword()"
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
						<li class="dropdown"><a href="/sscnServer/user.do"> <i
								class="icon-th"></i> User Management <b class="caret"></b>
						</a></li>


						<li class="dropdown"><a href="/sscnServer/lokasi.do"
							class="dropdown-toggle"> <i class="icon-th"></i> Lokasi
								Management <b class="caret"></b>
						</a></li>

						<li class="dropdown"><a href="/sscnServer/syarat.do"
							class="dropdown-toggle"> <i class="icon-copy"></i> Syarat
								Pendaftaran <b class="caret"></b>
						</a></li>

						<li class="dropdown"><a href="/sscnServer/formasi.do"
							class="dropdown-toggle"> <i class="icon-copy"></i> Formasi <b
								class="caret"></b>
						</a></li>

						<li class="dropdown"><a href="/sscnServer/pengumuman.do"
							class="dropdown-toggle"> <i class="icon-copy"></i> Pengumuman
								<b class="caret"></b>
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
									<ul class="dropdown-menu">
										<li><a href="#">Main Action</a></li>
										<li><a href="#">Secondary Action</a></li>
										<li><a href="#">Another action</a></li>
									</ul>
								</div>
							</div>
							<!-- /.widget-actions -->

						</div>
						<!-- /.widget-header -->

						<div class="widget-content">

							<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,
								sed do eiusmod tempor incididunt ut labore et dolore magna.</p>

							<div class="stat">

								<div class="stat-header">

									<div class="stat-label">Comments</div>
									<!-- /.stat-label -->

									<div class="stat-value">23</div>
									<!-- /.stat-value -->

								</div>
								<!-- /stat-header -->

								<div class="progress progress-primary progress-striped">
									<div class="bar" style="width: 87%;"></div>
									<!-- /.bar -->
								</div>
								<!-- /.progress -->

							</div>
							<!-- /.stat -->

							<div class="stat">

								<div class="stat-header">

									<div class="stat-label">Notifications</div>
									<!-- /.stat-label -->

									<div class="stat-value">9</div>
									<!-- /.stat-value -->

								</div>
								<!-- /stat-header -->

								<div class="progress progress-secondary progress-striped">
									<div class="bar" style="width: 37%;"></div>
									<!-- /.bar -->
								</div>
								<!-- /.progress -->

							</div>
							<!-- /.stat -->

							<div class="stat">

								<div class="stat-header">

									<div class="stat-label">Pages</div>
									<!-- /.stat-label -->

									<div class="stat-value">14</div>
									<!-- /.stat-value -->

								</div>
								<!-- /stat-header -->

								<div class="progress progress-tertiary progress-striped">
									<div class="bar" style="width: 65%;"></div>
									<!-- /.bar -->
								</div>
								<!-- /.progress -->

							</div>
							<!-- /.stat -->

							<br />

						</div>
						<!-- /.widget-content -->

					</div>
					<!-- /.widget -->

					<div class="widget">

						<div class="widget-header">
							<h3>
								<i class="icon-bookmark"></i> Shortcuts
							</h3>

							<div class="widget-actions">
								<span class="badge">8</span>
							</div>
							<!-- /.widget-actions -->

						</div>
						<!-- /.widget-header -->

						<div class="widget-content">

							<div class="shortcuts">
								<a href="javascript:;" class="shortcut"> <i
									class="shortcut-icon icon-list-alt"></i> <span
									class="shortcut-label">Apps</span>
								</a> <a href="javascript:;" class="shortcut"> <i
									class="shortcut-icon icon-bookmark"></i> <span
									class="shortcut-label">Bookmarks</span>
								</a> <a href="javascript:;" class="shortcut"> <i
									class="shortcut-icon icon-signal"></i> <span
									class="shortcut-label">Reports</span>
								</a> <a href="javascript:;" class="shortcut"> <i
									class="shortcut-icon icon-comment"></i> <span
									class="shortcut-label">Comments</span>
								</a> <a href="javascript:;" class="shortcut"> <i
									class="shortcut-icon icon-user"></i> <span
									class="shortcut-label">Users</span>
								</a> <a href="javascript:;" class="shortcut"> <i
									class="shortcut-icon icon-file"></i> <span
									class="shortcut-label">Notes</span>
								</a>

							</div>
							<!-- /.shortcuts -->

						</div>
						<!-- /.widget-content -->

					</div>
					<!-- /.widget -->

					<div class="widget widget-accordion">

						<div class="widget-header">

							<h3>
								<i class="icon-sort"></i> Widget Accordion
							</h3>
						</div>
						<!-- /.widget-header -->

						<div class="widget-content">

							<div class="accordion" id="sample-accordion">
								<div class="accordion-group">
									<div class="accordion-heading">
										<a class="accordion-toggle" data-toggle="collapse"
											data-parent="#sample-accordion" href="#collapseOne">
											Collapsible Group Item #1 </a> <i class="icon-plus toggle-icon"></i>
									</div>
									<div id="collapseOne" class="accordion-body in collapse">
										<div class="accordion-inner">
											<p>Anim pariatur cliche reprehenderit, enim eiusmod high
												life accusamus terry richardson ad squid. 3 wolf moon
												officia aute, non cupidatat skateboard dolor brunch. Food
												truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon
												tempor, sunt.</p>

											<p>Vivamus ac velit eget turpis pharetra placerat
												bibendum at risus. Vestibulum a sodales mauris. Curabitur et
												nibh nunc.</p>
										</div>
									</div>
								</div>
								<div class="accordion-group">
									<div class="accordion-heading">
										<a class="accordion-toggle" data-toggle="collapse"
											data-parent="#sample-accordion" href="#collapseTwo">
											Collapsible Group Item #2 </a> <i class="icon-plus toggle-icon"></i>
									</div>
									<div id="collapseTwo" class="accordion-body collapse">
										<div class="accordion-inner">
											<p>Anim pariatur cliche reprehenderit, enim eiusmod high
												life accusamus terry richardson ad squid. 3 wolf moon
												officia aute, non cupidatat skateboard dolor brunch. Food
												truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon
												tempor, sunt aliqu.</p>

											<p>Vivamus ac velit eget turpis pharetra placerat
												bibendum at risus. Vestibulum a sodales mauris. Curabitur et
												nibh nunc.</p>
										</div>
									</div>
								</div>
								<div class="accordion-group">
									<div class="accordion-heading">
										<a class="accordion-toggle" data-toggle="collapse"
											data-parent="#sample-accordion" href="#collapseThree">
											Collapsible Group Item #3 </a> <i class="icon-plus toggle-icon"></i>
									</div>
									<div id="collapseThree" class="accordion-body collapse">
										<div class="accordion-inner">
											<p>Anim pariatur cliche reprehenderit, enim eiusmod high
												life accusamus terry richardson ad squid. 3 wolf moon
												officia aute, non cupidatat skateboard dolor brunch. Food
												truck quinoa nesciunt laborum eiusmod. Brunch 3.</p>

											<p>Vivamus ac velit eget turpis pharetra placerat
												bibendum at risus. Vestibulum a sodales mauris. Curabitur et
												nibh nunc.</p>

										</div>
									</div>
								</div>
							</div>

						</div>
						<!-- /.widget-content -->

					</div>
					<!-- /.widget -->

				</div>
				<!-- /.span4 -->

				<div class="span8">


					<div class="widget widget-table">

						<div class="widget-header">

							<h3>
								<i class="icon-th-list"></i> User Management
							</h3>

							<div class="widget-actions">
								<input type="text" name="table-search" id="table-search"
									class="input-medium" placeholder="Search...">
							</div>
							<!-- /.widget-actions -->
						</div>
						<!-- /.widget-header -->

						<div class="widget-content">

							<table class="table table-bordered table-striped">
								<thead>
									<tr>
										<th>Username</th>
										<th>Role-Profile</th>
										<th>Action(s)</th>
									</tr>
								</thead>
								<tbody>
									<tr class="even gradeC">
										<td>admin</td>
										<td>Administrator</td>
										<td><a data-placement="bottom"
											class="btn btn-small btn-Tertiary ui-tooltip"
											href="javascript:;" data-original-title="Edit">Edit</a> <a
											data-placement="bottom"
											class="btn btn-small btn-danger ui-tooltip"
											href="javascript:;" data-original-title="Delete">Delete</a></td>
									</tr>
									<tr class="odd gradeA">
										<td>Doyok</td>
										<td>Pelamar</td>
										<td><a data-placement="bottom"
											class="btn btn-small btn-Tertiary ui-tooltip"
											href="javascript:;" data-original-title="Edit">Edit</a> <a
											data-placement="bottom"
											class="btn btn-small btn-danger ui-tooltip"
											href="javascript:;" data-original-title="Delete">Delete</a></td>
									</tr>
								</tbody>
							</table>

						</div>
						<!-- /.widget-content -->

					</div>
					<!-- /.widget -->

					<div class="widget">

						<div class="widget-header">

							<h3>
								<i class="icon-bar-chart"></i> Bar Chart
							</h3>
						</div>
						<!-- /.widget-header -->

						<div class="widget-toolbar clearfix">

							<div class="pull-left">

								Category:&nbsp;&nbsp;
								<div class="btn-group">
									<a class="btn btn-small dropdown-toggle" data-toggle="dropdown"
										href="#"> Admin Themes <span class="caret"></span>
									</a>
									<ul class="dropdown-menu">
										<li><a href="javascript:;">Admin Themes</a></li>
										<li><a href="javascript:;">Wordpress Themes</a></li>
										<li><a href="javascript:;">Site Themes</a></li>
										<li><a href="javascript:;">Email Themes</a></li>
									</ul>
								</div>

							</div>
							<!-- /.pull-left -->

							<div class="pull-right">

								<div class="btn-group" data-toggle="buttons-radio">
									<button class="btn btn-small active">Week</button>
									<button class="btn btn-small">Month</button>
									<button class="btn btn-small">Year</button>
								</div>

							</div>
							<!-- /.pull-left -->

						</div>
						<!-- /.chart-filters -->

						<div class="widget-content">

							<div id="bar-chart" class="chart-holder"></div>
							<!-- /bar-chart -->

						</div>
						<!-- /.widget-content -->

					</div>
					<!-- /.widget -->

					<div class="widget">

						<div class="widget-header">

							<h3>
								<i class="icon-sitemap"></i> Tabbed Panel
							</h3>

							<div class="widget-actions">
								<a href="javascript:;" class="btn btn-small btn-primary"> <i
									class="icon-plus"></i> Start Application
								</a>
							</div>
							<!-- /.widget-actions -->
						</div>
						<!-- /.widget-header -->

						<div class="widget-tabs">
							<ul class="nav nav-tabs">
								<li class="active"><a href="#applicants"><span
										class="badge badge-success">21</span>&nbsp;&nbsp;&nbsp;Applicants</a>
								</li>
								<li><a href="#rsvps"><span class="badge badge-warning">46</span>&nbsp;&nbsp;&nbsp;RSVPs</a>
								</li>
								<li><a href="#registrations"><span class="badge">215</span>&nbsp;&nbsp;&nbsp;Registrations</a>
								</li>
							</ul>

						</div>
						<!-- /.widget-tabs -->

						<div class="widget-content">

							<div class="tab-content">
								<div class="tab-pane active" id="applicants">
									<h3>Applicants</h3>

									<p>Lorem ipsum dolor sit amet, consectetur adipisicing
										elit, sed do eiusmod tempor incididunt ut labore et dolore
										magna aliqua. Ut enim ad minim veniam, quis nostrud
										exercitation ullamco laboris nisi.</p>

									<p>Ut aliquip ex ea commodo consequat. Duis aute irure
										dolor in reprehenderit in voluptate velit esse cillum dolore
										eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat
										non proident, sunt in culpa qui officia deserunt mollit anim
										id est laborum.</p>

									<p>Pellentesque nec semper lectus. Integer id enim vitae
										magna ultrices interdum vel sit amet enim. Suspendisse eu
										sapien eget ipsum ornare vestibulum. In hac habitasse platea
										dictumst. Morbi suscipit eleifend cursus. Vivamus ac velit
										eget turpis pharetra placerat bibendum at risus. Vestibulum a
										sodales mauris. Curabitur et nibh nunc.</p>

								</div>
								<div class="tab-pane" id="registrations">
									<h3>Registrations</h3>

									<p>Lorem ipsum dolor sit amet, consectetur adipisicing
										elit, sed do eiusmod tempor incididunt ut labore et dolore
										magna aliqua. Ut enim ad minim veniam, quis nostrud
										exercitation ullamco laboris nisi ut aliquip ex ea commodo
										consequat. Duis aute irure dolor in reprehenderit in voluptate
										velit esse cillum dolore eu fugiat nulla pariatur. Excepteur
										sint occaecat cupidatat non proident, sunt in culpa qui
										officia deserunt mollit anim id est laborum.</p>
								</div>
								<div class="tab-pane" id="rsvps">
									<h3>RSVPs</h3>

									<p>Lorem ipsum dolor sit amet, consectetur adipisicing
										elit, sed do eiusmod tempor incididunt ut labore et dolore
										magna aliqua. Ut enim ad minim veniam.</p>
								</div>
							</div>

						</div>
						<!-- /.widget-content -->

					</div>
					<!-- /.widget -->



					<div class="widget toolbar-bottom">

						<div class="widget-header">

							<h3>
								<i class="icon-hdd"></i> Toolbar Bottom
							</h3>

							<div class="widget-actions">
								<input type="text" name="searchit" class="input-medium"
									placeholder="Search...">
							</div>
							<!-- /.widget-actions -->
						</div>
						<!-- /.widget-header -->

						<div class="widget-content">

							<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,
								sed do eiusmod tempor incididunt ut labore et dolore magna
								aliqua. Ut enim ad minim veniam, quis nostrud exercitation
								ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis
								aute irure dolor in reprehenderit in voluptate velit esse cillum
								dolore eu fugiat nulla pariatur. Excepteur sint occaecat
								cupidatat non proident, sunt in culpa qui officia deserunt
								mollit anim id est laborum.</p>

							<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,
								sed do eiusmod tempor incididunt ut labore et dolore magna
								aliqua. Ut enim ad minim veniam, quis nostrud exercitation
								ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis
								aute irure dolor in reprehenderit in voluptate velit esse cillum
								dolore eu fugiat nulla pariatur. Excepteur sint occaecat
								cupidatat non proident, sunt in culpa qui officia deserunt
								mollit anim id est laborum.</p>

						</div>
						<!-- /.widget-content -->

						<div class="widget-toolbar">

							<button class="btn btn-small btn-primary">Primary Action
							</button>

							<button class="btn btn-small">Action Button</button>

						</div>
						<!-- /.widget-toolbar -->

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

		<div class="container">&copy; 2012 Propel UI, all rights
			reserved.</div>
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
						id="btnSaveChangePassword">Save changes</button>
					<!--  <button class="btn btn-large" id="btnCancelChangePassword" onclick="closeFormGantiPasword()">
						Cancel</button>-->
				</div>
			</fieldset>
		</form>
	</div>

</body>
</html>
