<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <link rel="stylesheet" href="./css/style.css"> -->
<title>商品追加画面</title>
</head>
<body>
<jsp:include page="masterHeader.jsp"/>
<div id="contents">
	<h1>商品の追加と削除</h1>
	<s:if test="#session.productInfoDtoList==null">
	<div class="info">
	商品は0件です。
	</div>
	</s:if>
	<s:else>
	<s:form id="form" action="DeleteProductAction">
		<table class="horizontal-list-table">
		<thead>
		<tr>
			<th><s:label value="削除"/></th>
			<th><s:label value="ID"/></th>
			<th><s:label value="商品ID"/></th>
			<th><s:label value="名前"/></th>
			<th><s:label value="ふりがな"/></th>
			<th><s:label value="詳細"/></th>
			<th><s:label value="カテゴリーID"/></th>
			<th><s:label value="料金"/></th>
			<th><s:label value="ファイルパス"/></th>
			<th><s:label value="ファイル名"/></th>
			<th><s:label value="発売日"/></th>
			<th><s:label value="発売会社"/></th>
			<th><s:label value="ステータス"/></th>
			<th><s:label value="登録日"/></th>
			<th><s:label value="更新日"/></th>

		</tr>
		</thead>
		<tbody>
			<s:iterator value="#session.productInfoDtoList">
				<tr>
					<td><s:checkbox name="checkList" fieldValue="%{id}"/></td>
					<td><s:property value="id"/><br></td>
					<td><s:property value="productId"/><br></td>
					<td><s:property value="productName"/><br></td>
					<td><s:property value="productNameKana"/><br></td>
					<td><s:property value="productDescription"/><br></td>
					<td><s:property value="categoryId"/><br></td>
					<td><s:property value="price"/><br></td>
					<td><s:property value="imageFilePath"/><br></td>
					<td><s:property value="imageFileName"/><br></td>
					<td><s:property value="releaseDate"/><br></td>
					<td><s:property value="releaseCompany"/><br></td>
					<td><s:property value="status"/><br></td>
					<td><s:property value="registDate"/><br></td>
					<td><s:property value="updateDate"/><br></td>
				</tr>
			</s:iterator>
		</tbody>
		</table>
		<div class="submit_btn_box">
			<div id="contents-btn-set">
				<s:submit value="削除する" class="submit_btn"/>
			</div>
		</div>
	</s:form>

	<s:form action="AddProductAction">
		<div class="submit_btn_box">
			<div id="contents-btn-set">
				<s:submit value="商品を追加する" class="submit_btn"/>
			</div>
		</div>
	</s:form>
	</s:else>
</div>
<s:include value="footer.jsp"/>
</body>
</html>