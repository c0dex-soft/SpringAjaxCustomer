<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SpringMVC Ajax Project</title>
<link href="${pageContext.request.contextPath}/resources/css/style.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/js/jquery-ui-1.12.1.custom/jquery-ui.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/resources/js/jquery-ui-1.12.1.custom/external/jquery/jquery.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery-ui-1.12.1.custom/jquery-ui.js"></script>
<script src="<c:url value="/resources/js/javascript.js"/>"></script>

</head>
<body>
	<div id="container">

		<h2>Create New Costumer</h2>
		<form id="saveContact">
			<div>
				<label for="firstNameInput">First Name</label> <input type="text"
					name="firstName" id="firstNameInput" />
			</div>
			<div>
				<label for="lastNameInput">Last Name</label> <input type="text"
					name="lastName" id="lastNameInput">
			</div>
			<div>
				<label for="emailInput">Email</label> <input type="text"
					name="email" id="emailInput">
			</div>
			<div>
				<label for="phoneInput">Phone Number</label> <input type="text"
					name="phone" id="phoneInput">
			</div>
			<div>
				<label for="dobInput">Date of Birth</label> <input type="text"
					name="dob" id="dobInput">
			</div>
			<div>
				<input id="submit" type="submit" value="Save Contact">
			</div>
		</form>

		<hr>
		<h2>List of Customers</h2>
		<table id="contactTableResponse" class="table">
			<thead>
				<tr>
					<th>ID</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Phone Number</th>
					<th>Date of Birth</th>
					<th>Edit</th>
					<th>Delete</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="customer" items="${customers}">
					<tr id="tr_${customer.id}">
						<td>${customer.id}</td>
						<td>${customer.firstName}</td>
						<td>${customer.lastName}</td>
						<td>${customer.email}</td>
						<td>${customer.phoneNumber}</td>
						<td id ="dob_${customer.id}" class="date" >${customer.dob}</td>
						<td><input class="edit" type="button" value="Edit" /></td>
						<td><input class="delete" type="button" value="Delete" /></td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<th scope="row" colspan="5">SpringMVC-Ajax-Hibernate</th>
				<td colspan="3">Marko Z. Petkovic</td>
			</tfoot>
		</table>
	</div>
</body>
</html>