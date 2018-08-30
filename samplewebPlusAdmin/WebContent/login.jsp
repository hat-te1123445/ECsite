<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/style.css">
<title>ログイン</title>
<script>
	function goLoginAction(){
		document.getElementById("form").action="LoginAction";
	}
	function goCreateUserAction(){
		document.getElementById("form").action="CreateUserAction";
	}
	function goResetPasswordAction(){
		document.getElementById("form").action="ResetPasswordAction";
	}
</script>
</head>
<body>
<jsp:include page="header.jsp" />
<div id="contents">
<h1>ログイン画面</h1>
<s:form id="form" action="LoginAction">
	<!-- sessionのloginErrorMessageListが空欄なら -->
	<s:if test="!#session.loginIdErrorMessageList.isEmpty()">
		<div class="error">
			<div class="error-message">
				<!-- iterator⇒sessionに入っているloginIdErrorMessageListを繰り返す -->
				<!-- property⇒アクションのクラスが保持している属性への参照機能を提供します。
					value属性に参照したいアクションクラスの属性名を記述するのみです。
					この場合はiteratorで順繰りに指定されていくのでvalueは不要 -->
				<s:iterator value="#session.loginIdErrorMessageList"><s:property /><br></s:iterator>
			</div>
		</div>
	</s:if>
	<!-- sessionのpasswordErrorMessageListが空なら -->
	<s:if test="!#session.passwordErrorMessageList.isEmpty()">
		<div class="error">
			<div class="error-message">
				<s:iterator value="#session.passwordErrorMessageList"><s:property /><br></s:iterator>
			</div>
		</div>
	</s:if>
	<table class="vertical-list-table">
		<tr>
			<th scope="row"><s:label value="ログインID" /></th>
			<!-- savedLoginIdがtrueならログインID入力ボックスにloginIdを表示 -->
			<s:if test="#session.savedLoginId==true">
			<!-- autocomplete="off"⇒入力時の補完候補を表示しない -->
			<td><s:textfield name="loginId" class="txt" placeholder="ログインID" value='%{#session.loginId}' autocomplete="off"/></td>
			</s:if>
			<s:else>
			<td><s:textfield name="loginId" class="txt" placeholder="ログインID" autocomplete="off"/></td>
			</s:else>
		</tr>
		<tr>
			<th scope="row"><s:label value="パスワード" /></th>
			<td><s:password name="password" class="txt" placeholder="パスワード" autocomplete="off"/></td>
		</tr>
	</table>
	<div class="box">
		<!-- savedLoginIdがtrueならsavedLoginIdチェックボックスにチェックを入れる -->
		<s:if test="#session.savedLoginId==true">
			<s:checkbox name="savedLoginId" checked="checked" />
		</s:if>
		<s:else>
			<s:checkbox name="savedLoginId" />
		</s:else>
		<s:label value="ログインID保存" /><br>
	</div>
	<div class="submit_btn_box">
		<s:submit value="ログイン" class="submit_btn" onclick="goLoginAction();" />
	</div>
</s:form>
<br>
<div class="submit_btn_box">
	<div id=".contents-btn_set">
		<s:form action="CreateUserAction">
			<s:submit value="新規ユーザー登録" class="submit_btn" />
		</s:form>
	</div>
</div>
<div class="submit_btn_box">
<div id=".contents-btn-set">
<s:form action="ResetPasswordAction">
	<s:submit value="パスワード再設定" class="submit_btn" />
</s:form>
</div>
</div>
</div>

<s:include value="footer.jsp" />
</body>
</html>