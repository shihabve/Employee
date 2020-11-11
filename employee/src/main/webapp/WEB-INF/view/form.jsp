<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<body>
<script>
$(document).ready(function(){
	getList();
});


function save(){
	var formData = $("#formID").serialize();
	$.ajax({
        type: 'POST',
        url: "saveEmployee",
        data: formData,
        success: function (data, status) {
            if(data.flag == 1){
            	alert(data.msg)
            }else{
            	alert(data.msg)
            }
        }
    });
}</script>
<form id="formID">
<table>
	<tr>
		<td>Name</td>
		<td><input name="name" type="text"></td>
		
	</tr>
	<tr>
		<td>Email</td>
		<td><input name="email" type="text"></td>
	</tr>
	<tr>
		<td>Address1</td>
		<td><input name="address" type="text">
		<input type="hidden" name="addressID" value="0">
		<input type="hidden" name="type" value="1"></td>
	</tr>
	<tr>
		<td>Address2</td>
		<td><input name="address" type="text">
		<input type="hidden" name="addressID" value="0">
		<input type="hidden" name="type" value="2"></td>
	</tr>	
	<tr>
		<td><input type="button" id='saveBtnID' onclick='save()' value='save'></td>
	</tr>			
</table>
<table id="listTable">
	<tbody></tbody>			
</table>
</form>
</body>
</html>