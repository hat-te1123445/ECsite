<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <link rel="stylesheet" href="./css/style.css"> -->
<title>管理者ヘッダー</title>
<script>
	function goManagementProductAction(){
		document.getElementById("form").action="GoManagementProductAction";
	}
	function goProductStockAction(){
		document.getElementById("form").action="GoProductStockAction";
	}
	function goLogoutAction(){
		document.getElementById("form").action="MasterLogoutAction";
	}
</script>
</head>
<body>
<header>
	<div id="header">
		<div id="header-title">
		Sample Web(Master)
		</div>
		<div id="header-menu">
			<ul>
			<s:form id="form" name="form">
				<li><s:submit value="商品の追加と削除" class="submit_btn" onclick="goManagementProductAction()"/></li>
				<li><s:submit value="ログアウト" class="submit_btn" onclick="goLogoutAction()"/></li>
			</s:form>
			</ul>
		</div>
	</div>
</header>
</body>
</html>