<%@page import="java.io.PrintWriter"%>
<%@page language="java" session="true"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<ul class="nav">
	<li class="nav-icon active"><a href="/sscnServer/dashboard.do">
			<i class="icon-home"></i> <span>Home</span> </a>
	</li>
	<c:if test="${userLogin.kewenangan != 3}">
		<li class="dropdown"><a href="javascript:;"
			class="dropdown-toggle" data-toggle="dropdown"> <i
				class="icon-external-link"></i> Manajemen <b class="caret"></b> </a>
			<ul class="dropdown-menu">
				<li><a href="/sscnServer/user.do">User</a>
				</li>
				<li><a href="/sscnServer/lokasi.do">Lokasi Formasi</a>
				</li>
				<li><a href="/sscnServer/lokasiTest.do">Lokasi Ujian</a>
				</li>
				<li><a href="/sscnServer/syarat.do">Syarat Pendaftaran</a>
				</li>
				<li><a href="/sscnServer/periodeDaftar.do">Periode
						Pendaftaran</a>
				</li>
				<li><a href="/sscnServer/instansiconf.do">Pengaturan Jumlah Max Pilihan dan Lokasi Test</a>
				</li>
			</ul>
		</li>
		<li class="dropdown"><a href="/sscnServer/pengumuman.do"
			class="dropdown-toggle"> <i class="icon-copy"></i> Pengumuman <b
				class="caret"></b> </a>
		</li>
		<li class="dropdown"><a href="javascript:;"
			class="dropdown-toggle" data-toggle="dropdown"> <i
				class="icon-copy"></i> Formasi <b class="caret"></b>
		</a>
			<ul class="dropdown-menu">
				<li><a href="formasi.do">Formasi Umum</a></li>
				<li><a href="formasiKhusus.do">Formasi Khusus</a></li>
			</ul></li>
		<li class="dropdown"><a href="javascript:;"
			class="dropdown-toggle" data-toggle="dropdown"> <i
				class="icon-copy"></i> Verfikasi <b class="caret"></b> </a>
			<ul class="dropdown-menu">
				<li><a href="verifikasi.do">Verifikasi Pendaftar</a></li>
				<li><a href="verifikasiFilter.do">Verifikasi Pengurutan</a></li>
				<li><a href="resetverifikasi.do">Reset Verifikasi</a></li>
			</ul></li>
		<li class="dropdown"><a href="/sscnServer/statistik.do"
			class="dropdown-toggle"> <i class="icon-copy"></i> Statistik <b
				class="caret"></b> </a>
		</li>
		<li class="dropdown"><a href="/sscnServer/downloadData.do"
			class="dropdown-toggle"> <i class="icon-copy"></i> Download Data
				<b class="caret"></b> </a>
		</li>
	</c:if>
	<c:if test="${userLogin.kewenangan == 3}">
		<li class="dropdown"><a href="javascript:;"
			class="dropdown-toggle" data-toggle="dropdown"> <i
				class="icon-copy"></i> Verfikasi <b class="caret"></b> </a>
			<ul class="dropdown-menu">
				<li><a href="verifikasi.do">Verifikasi Pendaftar</a></li>
				<li><a href="verifikasiFilter.do">Verifikasi Pengurutan</a></li>
			</ul></li>
	</c:if>
</ul>
