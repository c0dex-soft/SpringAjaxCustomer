<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach var="customer" items="${customers}">
    <tr id="tr_${customer.id}">
		<td>${customer.id}</td>
		<td>${customer.firstName}</td>
		<td><c:out value="${not empty customer.lastName ? customer.lastName : 'null'}"></c:out></td>
		<td><c:out value="${not empty customer.email ? customer.email : 'null'}"></c:out></td>
		<td><c:out value="${not empty customer.phoneNumber ? customer.phoneNumber : 'null'}"></c:out></td>
		<td id ="dob_${customer.id}" class="date" ><c:out value="${not empty customer.dob ? customer.dob : 'null'}"></c:out></td>
		<td><input class="edit" type="button" value="Edit" /></td>
		<td><input class="delete" type="button" value="Delete" /></td>
	</tr>
</c:forEach>