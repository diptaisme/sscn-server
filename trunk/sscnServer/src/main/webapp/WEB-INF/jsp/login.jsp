<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Administrasi Sistem Seleksi CPNS Nasional 2013</title>

	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">   
    
    <!-- Styles -->
    
    <link href="/sscnServer/resources/css/bootstrap.css" rel="stylesheet">
    <link href="/sscnServer/resources/css/bootstrap-responsive.css" rel="stylesheet">
    <link href="/sscnServer/resources/css/bootstrap-overrides.css" rel="stylesheet">
    
	<link href="/sscnServer/resources/css/ui-lightness/jquery-ui-1.8.21.custom.css" rel="stylesheet">
        
    <link href="/sscnServer/resources/css/slate.css" rel="stylesheet">
    
	<link href="/sscnServer/resources/css/components/signin.css" rel="stylesheet" type="text/css">   
    
    
    <!-- Javascript -->
    
    <script src="/sscnServer/resources/js/jquery-1.7.2.min.js"></script>
	<script src="/sscnServer/resources/js/jquery-ui-1.8.18.custom.min.js"></script>    
	<script src="/sscnServer/resources/js/jquery.ui.touch-punch.min.js"></script>
	<script src="/sscnServer/resources/js/bootstrap.js"></script>

	<script src="/sscnServer/resources/js/demos/signin.js"></script>
	<script>
		jQuery(document).ready(
							function() {
							$("#mainAlert").delay(3200).fadeOut(800);
						});
	</script>
</head>

<body>


<div class="account-container login">
	
	<div class="content clearfix">
		
		<form action="/sscnServer/processLogin.do" method="post">
		
			<h1>Login</h1>		
			
			<div class="login-fields">				
				
				<c:if test="${pesan != null}">					
					<div class="alert alert-error" id="mainAlert">
								  <a href="#" data-dismiss="alert" class="close">×</a>
								  <h4 class="alert-heading">Info!</h4>
								  		${pesan}
								</div>
				</c:if>
				<div class="field">
					<label for="username">Username:</label>
					<input type="text" id="username" name="username" value="" placeholder="Username" class="login username-field" title="username"/>
				</div> <!-- /field -->
				
				<div class="field">
					<label for="password">Password:</label>
					<input type="password" id="password" name="password" value="" placeholder="Password" class="login password-field" title="password"/>
				</div> <!-- /password -->
				
			</div> <!-- /login-fields -->
			
			<div class="login-actions">				
				<button class="button btn btn-secondary btn-large" title="Klik untuk login">Login</button>				
			</div> 			
			
		</form>		
	</div>	
</div>
</body>
</html>
