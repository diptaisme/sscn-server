
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Seleksi CPNS 2013</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script>
function kembali(){
	window.location.href = 'http://sscn.bkn.go.id';
}
</script>
<style type="text/css">
<!--
body {
	background-image: url(/resources/img/bg.png);
	background-repeat: repeat;
}
-->
</style></head>
<body id="page1">
<div> 
  <div align="center"><a href="#"><img src="/resources/img/header-bg2.png" alt="" width="972" height="205" border="0" /></a></div>
</div>
<p align="center">
		Anda sudah mendaftarkan untuk mengikuti Seleksi CPNS di posisi yang
		anda lamar. <br> Silahkan melakukan Cetak Nomor Pendaftaran pada
		tombol dibawah ini.	</p>
	<p align="center">
	<form action="ReportServlet" method="post" target="_blank"
		name="formCetakRegistrasi" id="formCetakRegistrasi">
		<div align="center">
		  <input type="hidden" name="idRegistrasi" value="${idRegistrasi}" /> 
		  <input
			type="hidden" name="formID" value="32063786011852" /> 
		  <input
			type="hidden" name="typeReport" value="rptRegistrasi" /> 
		  <input
			type="submit" value="Cetak Nomor Pendaftaran"
			class="form-submit-button style2" /> 
		  <input type="button" onClick="kembali()"
			name="btnKembali" value="Kembali" class="form-submit-button style2" />
</form>  
<div>		
		<p align="center" class="lf">&nbsp;</p>
		<p align="center" class="lf">Hak Cipta &copy; 2013 Badan Kepegawaian Negara.
		  Semua Hak Dilindungi.</p>
		<p align="center" class="rf">
			Website : <a href="http://www.bkn.go.id/">www.bkn.go.id</a>		</p>
</div>
</body>
</html>
