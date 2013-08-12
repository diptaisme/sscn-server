<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<style>
.error {
	color: #ff0000;
}
.errorblock{
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding:8px;
	margin:16px;
}

</style>
</head>

<body>
<h2>Hasil Form Registrasi</h2>
Selamat registrasi anda berhasil
<br>
No Pendaftaran : ${pendaftaran.noRegister}
<br>
No NIK : ${pendaftaran.noNik}
<br>
Nama : ${pendaftaran.nama}
<br>
Silahkan melakukan verifikasi untuk proses test selanjutnya.
<br>
<a href="http://localhost:8080/SSCN/">Klik untuk kembali ke web SSCN</a>  
</html>