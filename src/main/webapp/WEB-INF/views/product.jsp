<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product</title>
<link rel="stylesheet" type="text/css" href="${contextPath}/css/styles.css">
</head>
<body>
	<jsp:include page="_header.jsp" />
	<jsp:include page="_menu.jsp" />

	<div class="page-title">Product</div>

	<c:if test="${not empty errorMessage }">
		<div class="error-message">${errorMessage}</div>
	</c:if>
	<form:form modelAttribute="productForm" method="POST" enctype="multipart/form-data" action="${contextPath}/product">
		<table style="text-align: left;">
			<tr>
				<td>Code *</td>
				<td style="color: red;">
						<form:input path="code" />
						<form:hidden path="newProduct" />
				</td>
				<td>
					<form:errors path="code" class="error-message" />
				</td>
			</tr>

			<tr>
				<td>Name *</td>
				<td><form:input path="name" /></td>
				<td><form:errors path="name" class="error-message" /></td>
			</tr>

			<tr>
				<td>Price *</td>
				<td><form:input path="price" /></td>
				<td><form:errors path="price" class="error-message" /></td>
			</tr>
			<tr>
				<td>Image</td>
				<td>
					<img src="${contextPath}/productImage?code=${productForm.code}" width="100"/>
				</td>
				<td></td>
			</tr>
			<tr>
				<td>Upload Image</td>
				<td>
					<form:input type="file" path="fileData"/>
				</td>
				<td></td>
			</tr>

			<tr>
				<td>&nbsp;</td>
				<td>
					<input type="submit" value="Submit" />
					<input type="reset"	value="Reset" />
				</td>
			</tr>
		</table>
	</form:form>

	<jsp:include page="_footer.jsp" />

</body>
</html>