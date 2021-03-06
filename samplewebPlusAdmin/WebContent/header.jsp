<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <link rel="stylesheet" href="./css/style.css"> -->
<title>ヘッダー</title>
<script>
	function goLoginAction(){
		document.getElementById("form").action="GoLoginAction";
	}
	function goMyPageAction(){
		document.getElementById("form").action="MyPageAction";
	}
	function goCartAction(){
		document.getElementById("form").action="CartAction";
	}
	function goProductListAction(){
		document.getElementById("categoryId").value=1;
		document.getElementById("form").action="ProductListAction";
	}
	function goLogoutAction(){
		document.getElementById("form").action="LogoutAction";
	}
	function goSearchItemAction(){
		document.getElementById("form").action="SearchItemAction";
	}
</script>
</head>
<body>
<header>
	<div id="header">
		<div id="header-title">
		Sample Web
		</div>
		<div id="header-menu">
		<ul>
		<s:form id="form" name="form">
			<!-- sessionにmCategpryDtoListが存在しているなら実行 -->
			<s:if test='#session.containsKey("mCategoryDtoList")'>
				<li>
				<!-- ①カテゴリーリスト -->
				<s:select name="categoryId" list="#session.mCategoryDtoList" listValue="categoryName" listKey="categoryId" class="cs-div" id="categoryId"/>
				</li>
			</s:if>
				<!-- ②検索ワード入力 -->
				<li><s:textfield name="keywords" class="txt-keywords" placeholder="検索ワード" /></li>
				<!-- ③検索ボタン -->
				<li><s:submit value="商品検索" class="submit_btn" onclick="goSearchItemAction();" /></li>

			<!-- ④-1 ログアウト -->
			<s:if test="#session.logined==1">
				<li><s:submit value="ログアウト" class="submit_btn" onclick="goLogoutAction();"/></li>
			</s:if>

			<!-- ④-2ログイン -->
			<s:else>
				<li><s:submit value="ログイン" class="submit_btn" onclick="goLoginAction();"/></li>
			</s:else>

				<!-- ⑤カート -->
				<li><s:submit value="カート" class="submit_btn" onclick="goCartAction();"/></li>
				<!-- ⑥商品一覧 -->
				<li><s:submit value="商品一覧" class="submit_btn" onclick="goProductListAction();" /></li>

			<!-- ⑦loginedが1であればマイページを表示する -->
			<s:if test="#session.logined==1">
				<li>
				<s:submit value="マイページ" class="submit_btn" onclick="goMyPageAction();" />
				</li>
			</s:if>
		</s:form>
		</ul>
		</div>
	</div>
</header>
</body>
</html>