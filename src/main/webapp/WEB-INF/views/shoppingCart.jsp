<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Shopping Cart</title>
<link rel="stylesheet" type="text/css" href="${contextPath}/css/styles.css">
</head>
<body>
	<jsp:include page="_header.jsp" />

	<jsp:include page="_menu.jsp" />

	<fmt:setLocale value="en_US" scope="session" />

	<div class="page-title">My Cart</div>

	<c:if test="${empty cartForm or empty cartForm.cartLineInfos}">
		<h2>There is no items in Cart</h2>
		<a href="${contextPath}/productList">Show Product List</a>
	</c:if>

	<c:if test="${not empty cartForm and not empty cartForm.cartLineInfos}">
		<form:form method="POST" modelAttribute="cartForm" action="${contextPath}/shoppingCart">
			<c:forEach items="${cartForm.cartLineInfos}" var="cartLineInfo"	varStatus="varStatus">
				<div class="product-preview-container">
					<ul>
						<li><img class="product-image" src="${contextPath}/productImage?code=${cartLineInfo.productInfo.code}" />
						</li>
						<li>Code: ${cartLineInfo.productInfo.code} 
							<form:hidden path="cartLineInfos[${varStatus.index}].productInfo.code" />
						</li>
						<li>Name: ${cartLineInfo.productInfo.name}</li>
						<li>Price: 
							<span class="price">
								<fmt:formatNumber value="${cartLineInfo.productInfo.price}" type="currency" />
							</span>
						</li>
						<li>Quantity:
							<form:input	path="cartLineInfos[${varStatus.index}].quantity" />
						</li>
						<li>Subtotal:
							<span class="subtotal">
							<fmt:formatNumber value="${cartLineInfo.amount}" type="currency" />
							</span>
						</li>
						<li>
							<a href="${contextPath}/shoppingCartRemoveProduct?code=${cartLineInfo.productInfo.code}">Delete </a>
						</li>
					</ul>
				</div>
			</c:forEach>
			
			<div style="clear: both"></div>
				<input class="button-update-sc" type="submit" value="Update Quantity" />
			<a class="navi-item" href="${contextPath}/shoppingCartCustomer">Enter Customer Info</a>
			<a class="navi-item" href="${contextPath}/productList">Continue	Buy</a>
		</form:form>

	</c:if>

	<jsp:include page="_footer.jsp" />

</body>
</html>