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
<h2>Form Registrasi</h2>

<form method="POST" action="registrasi.do" name="formRegistrasi">
<table>
<tr>
<td>Kota : </td>
<td><input type="text" id="kota" name="kota" /></td>
</tr>
<tr>
<td>Nama : </td>
<td><input type="text" id="nama" name="nama" /></td>
</tr>

<tr>
<td colspan="3"><input type="submit" /></td>
</tr>
</table>
</form>

</body>
</html>