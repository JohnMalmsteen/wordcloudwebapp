<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Word Cloud</title>
</head>
<body>	
	<form action="image">
		<input type="radio" name="pattern" value="gauss" checked> Gaussian<br>
		<input type="radio" name="pattern" value="log"> Logarithmic<br>
		<input type="text" name="url" style="width:200px"> URL<br>
		<input type="submit" value="Submit">
	</form> 
</body>
</html>