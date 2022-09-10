<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Shopping Cart Customer</title>
<link rel="stylesheet" type="text/css" href="${contextPath}/css/styles.css">
</head>
<body>
	<jsp:include page="_header.jsp" />
	<jsp:include page="_menu.jsp" />

	<div class="page-title">Enter Customer Information</div>

	<form:form method="POST" modelAttribute="customerForm" action="${contextPath}/shoppingCartCustomer">

		<table>
			<tr>
				<td>Name *</td>
				<td><form:input path="name" /></td>
				<td><form:errors path="name" class="error-message" /></td>
			</tr>

			<tr>
				<td>Email *</td>
				<td><form:input path="email" /></td>
				<td><form:errors path="email" class="error-message" /></td>
			</tr>

			<tr>
				<td>Phone *</td>
				<td><form:input path="phone" /></td>
				<td><form:errors path="phone" class="error-message" /></td>
			</tr>

			<tr>
				<td>Address *</td>
				<td><form:input path="address" /></td>
				<td><form:errors path="address" class="error-message" /></td>
			</tr>

			<tr>
				<td>&nbsp;</td>
				<td>
					<input type="submit" value="Submit" />
					<input type="reset" value="Reset">
				</td>
			</tr>
		</table>

	</form:form>


	<jsp:include page="_footer.jsp" />

</body>
</html>