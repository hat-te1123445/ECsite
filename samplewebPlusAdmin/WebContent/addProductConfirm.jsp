<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<link rel="stylesheet" href="./css/tabicale.css">
<link rel="stylesheet" href="./css/addProductConfirmAction.css">

<title>商品追加確認画面</title>

<!-- URLの直打対策 -->
<script type="text/JavaScript" src="./js/transitionControl.js"></script>

</head>
<body>

	<jsp:include page="masterHeader.jsp"/>
	<div class="contents">
			<h1>商品追加確認画面</h1>
		<s:form action="AddProductCompleteAction">
			<table class="vertical-list-table-mini-add">
				<tr>
					<th scope="row"><s:label value="商品名"/></th>
					<td><s:property value="productName"/></td>
				</tr>
				<tr>
					<th scope="row"><s:label value="商品名カナ"/></th>
					<td><s:property value="productNameKana"/></td>
				</tr>
				<tr>
					<th scope="row"><s:label value="商品詳細"/></th>
					<td><s:property value="productDescription"/></td>
				</tr>
				<tr>
					<th scope="row"><s:label value="カテゴリ名"/></th>
					<td><s:property value="#session.categoryName"/></td>
				</tr>
				<tr>
					<th scope="row"><s:label value="価格"/></th>
					<td><s:property value="price"/></td>
				</tr>
				<tr>
					<th scope="row"><s:label value="発売年月日"/></th>
					<td><s:property value="releaseDate"/></td>
				</tr>
				<tr>
					<th scope="row"><s:label value="発売会社"/></th>
					<td><s:property value="releaseCompany"/></td>
				</tr>
				<tr class="no-image">
					<th colspan="1" class="left-image"><s:label value="商品画像"/></th>
					<td class="border-none"><img src='<s:property value="%{#session.imageFilePath}"/>/<s:property value="%{#session.imageFileName}"/>' class="left-image image"></td>

				</tr>
			</table>
		<s:hidden name="productId" value="%{#session.productId}"/>
		<s:hidden name="productName" value="%{#session.productName}"/>
		<s:hidden name="productNameKana" value="%{#session.productNameKana}"/>
		<s:hidden name="productDescription" value="%{#session.productDescription}"/>
		<s:hidden name="categoryId" value="%{#session.categoryId}"/>
		<s:hidden name="price" value="%{#session.price}"/>
		<s:hidden name="imageFilePath" value="%{#session.imageFilePath}"/>
		<s:hidden name="imageFileName" value="%{#session.imageFileName}"/>
		<s:hidden name="releaseCompany" value="%{#session.releaseCompany}"/>
		<s:hidden name="releaseDate" value="%{#session.releaseDate}"/>
		<s:token/>

		<div class="submit_btn_box">
			<div id="contents-btn-set">
				<s:submit value="追加完了" class="submit_btn"/>
			</div>
		</div>

		</s:form>
	</div>

		<s:include value="footer.jsp"/>


</body>
</html>