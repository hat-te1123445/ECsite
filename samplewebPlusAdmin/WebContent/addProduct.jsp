<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<link rel="stylesheet" href="./css/tabicale.css">
<link rel="stylesheet" href="./css/addProductAction.css">
<title>商品追加</title>

<!-- URLの直打対策 -->
<script type="text/JavaScript" src="./js/transitionControl.js"></script>

<style type="text/css">
</style>
<script>
function lengthcheck(){
	  var value = document.getElementById("result");
	  var files1 = document.getElementById('userImage').files;
	  var sumfile = 0;
	  var output = [];
	  var result = true;

	  if(files1[0].size > 2097152){
		  sumfile = (files1[0].size)/1048576;
		  value.innerHTML = "<div class='error'><div class='error-message'>画像は2MB以下でお願いします。現在のファイルサイズ["+sumfile+"MB]</div></div>";
		  result = false
	  }
	  return result;
};
</script>
</head>
<body>
<jsp:include page="masterHeader.jsp"/>

	<div id="contents">

			<h1>商品追加画面</h1>
				<s:form method="post" action="AddProductConfirmAction" enctype="multipart/form-data" onsubmit="return lengthcheck()">
					<table class="vertical-list-table-mini-add">
						<tr>
							<th scope="row" id="input_form"><s:label value="商品名" /></th>
							<td><s:textfield name="productName" class="txt" value="%{#session.productName}" placeholder="商品名"/></td>
						</tr>
						<tr>
							<s:if test="!session.productNameErrorMessageList.isEmpty()">
								<th colspan="2">
									<div class="error">
									<div class="error-message">
										<s:iterator value="#session.productNameErrorMessageList"><s:property /><br></s:iterator>
									</div>
									</div>
								</th>
							</s:if>
						</tr>
						<tr>
							<s:if test="!session.identical_productNameErrorMessageList.isEmpty()">
								<th colspan="2">
									<div class="error">
									<div class="error-message">
										<s:iterator value="#session.identical_productNameErrorMessageList"><s:property /><br></s:iterator>
									</div>
									</div>
								</th>
							</s:if>
						</tr>

						<tr>
							<th scope="row" id="input_form"><s:label value="商品名かな" /></th>
							<td><s:textfield name="productNameKana" class="txt" value="%{#session.productNameKana}" placeholder="商品名かな"/></td>
						</tr>

						<tr>
							<s:if test="!session.productNameKanaErrorMessageList.isEmpty()">
								<th colspan="2">
									<div class="error">
									<div class="error-message">
										<s:iterator value="#session.productNameKanaErrorMessageList"><s:property /><br></s:iterator>
									</div>
									</div>
								</th>
							</s:if>
						</tr>
						<tr>
							<s:if test="!session.identical_productNameKanaErrorMessageList.isEmpty()">
								<th colspan="2">
									<div class="error">
									<div class="error-message">
										<s:iterator value="#session.identical_productNameKanaErrorMessageList"><s:property /><br></s:iterator>
									</div>
									</div>
								</th>
							</s:if>
						</tr>

						<tr>
							<th scope="row" id="input_form"><s:label value="商品詳細" /></th>
							<td><s:textfield name="productDescription" class="txt" value="%{#session.productDescription}" placeholder="商品詳細"/></td>
						</tr>

						<tr>
							<s:if test="!session.productDescriptionErrorMessageList.isEmpty()">
								<th colspan="2">
									<div class="error">
									<div class="error-message">
										<s:iterator value="#session.productDescriptionErrorMessageList"><s:property /><br></s:iterator>
									</div>
									</div>
								</th>
							</s:if>
						</tr>

						<tr>
							<th scope="row" id="input_form"><s:label value="カテゴリ名" /></th>
							<td class="categoryId"><s:select name="categoryId" list="#session.mCategoryDtoList" listValue="categoryName"
							listKey="categoryId" class="cs-div" id="categoryId"/></td>
						</tr>

						<tr>
							<s:if test="!session.categoryIdErrorMessageList.isEmpty()">
								<th colspan="2">
									<div class="error">
									<div class="error-message">
										<s:iterator value="#session.categoryIdErrorMessageList"><s:property /><br></s:iterator>
									</div>
									</div>
								</th>
							</s:if>
						</tr>

						<tr>
							<th scope="row" id="input_form"><s:label value="価格" /></th>
							<td><s:textfield name="price" class="txt" value='%{#session.price}'/></td>
						</tr>

						<tr>
							<s:if test="!session.priceErrorMessageList.isEmpty()">
								<th colspan="2">
									<div class="error">
									<div class="error-message">
										<s:iterator value="#session.priceErrorMessageList"><s:property /><br></s:iterator>
									</div>
									</div>
								</th>
							</s:if>
						</tr>

						<tr>
							<th scope="row" id="input_form"><s:label value="発売年月日" /></th>
							<td><s:textfield name="releaseDate" class="txt" value="%{#session.releaseDate}" placeholder="yyyy-MM-dd"/></td>
						</tr>

						<tr>
							<s:if test="!session.releaseDateErrorMessageList.isEmpty()">
								<th colspan="2">
									<div class="error">
									<div class="error-message">
										<s:iterator value="#session.releaseDateErrorMessageList"><s:property /><br></s:iterator>
									</div>
									</div>
								</th>
							</s:if>
						</tr>

						<tr>
							<th scope="row" id="input_form"><s:label value="発売会社" /></th>
							<td><s:textfield name="releaseCompany" class="txt" value="%{#session.releaseCompany}" placeholder="発売会社"/></td>
						</tr>

						<tr>
							<s:if test="!session.releaseCompanyErrorMessageList.isEmpty()">
								<th colspan="2">
									<div class="error">
									<div class="error-message">
										<s:iterator value="#session.releaseCompanyErrorMessageList"><s:property /><br></s:iterator>
									</div>
									</div>
								</th>
							</s:if>
						</tr>

						<tr>
							<th scope="row" id="input_form"><s:label value="商品画像" /></th>
							<td class="image"><s:file name="userImage" id="userImage" accept='image/jpg, image/jpeg' /></td>
						</tr>
						<tr>

						<tr>
							<s:if test="!session.imageFilePathErrorMessageList.isEmpty()">
								<th colspan="2">
									<div class="error">
									<div class="error-message">
										<s:iterator value="#session.imageFilePathErrorMessageList"><s:property /><br></s:iterator>
									</div>
									</div>
								</th>
							</s:if>
							<s:if test="!session.imageFileNameErrorMessageList.isEmpty()">
								<th colspan="2">
									<div class="error">
									<div class="error-message">
										<s:iterator value="#session.imageFileNameErrorMessageList"><s:property /><br></s:iterator>
									</div>
									</div>
								</th>
							</s:if>
						</tr>


					</table>
					<div class="submi_btn_box">
						<s:submit value="商品追加" class="submit_btn"/>
					</div>
				</s:form>
			</div>
		<s:include value="footer.jsp"/>
</body>
</html>