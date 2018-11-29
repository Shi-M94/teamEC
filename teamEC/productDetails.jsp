<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="./css/mimosa.css">
		<title>商品詳細画面</title>
	</head>
	<body>
		<s:include value="header.jsp" />
		<div id="contents">
			<h1 class="title">商品詳細画面</h1>
			<s:form action="AddCartAction">
				<div class="details-contents">
					<div class="left">
						<h2>商品画像</h2>
						<div>
							<img src='<s:property value="%{#session.imageFilePath}"/>/
							<s:property value="%{session.imageFileName}"/>' class="item-image-box-300"/><br>
						</div>
					</div>
					<div class="right">
						<h2>商品詳細</h2>
						<table class="details-table">
							<tr>
								<th scope="row"><s:label value="商品名"/></th>
								<td><s:property value="%{#session.productName}"/></td>
							</tr>
							<tr>
								<th scope="row"><s:label value="商品ふりがな"/></th>
								<td><s:property value="%{#session.productNameKana}"/></td>
							</tr>
							<tr>
								<th scope="row"><s:label value="値段"/></th>
								<td><s:property value="%{#session.price}"/>円</td>
							</tr>
							<tr>
								<th scope="row"><s:label value="購入個数"/></th>
								<td><s:select name="productCount" list="%{#session.productCountList}"/>個</td>
							</tr>
							<tr>
								<th scope="row"><s:label value="発売会社名"/></th>
								<td><s:property value="%{#session.releaseCompany}"/></td>
							</tr>
							<tr>
								<th scope="row"><s:label value="発売年月日"/></th>
								<td><s:property value="%{#session.releaseDate}"/></td>
							</tr>
							<tr>
								<th scope="row"><s:label value="商品詳細情報"/></th>
								<td><s:property value="%{#session.productDescription}"/></td>
							</tr>
						</table>
					</div>
					<s:hidden name="productId" value="%{#session.productId}"/>
					<s:hidden name="productName" value="%{#session.productName}"/>
					<s:hidden name="productNameKana" value="%{#session.productNameKana}"/>
					<s:hidden name="imageFilePath" value="%{#session.imageFilePath}"/>
					<s:hidden name="imageFileName" value="%{#session.imageFileName}"/>
					<s:hidden name="price" value="%{#session.price}"/>
					<s:hidden name="releaseCompany" value="%{#session.releaseCompany}"/>
					<s:hidden name="releaseDate" value="%{#session.releaseDate}"/>
					<s:hidden name="productDescription" value="%{#session.productDescription}"/>
					<br>
					<div class="submit_btn_box">
						<div id=".contents-btn-set">
							<s:token/>
							<div class="addCart_btn">
								<s:submit value="カートに追加" class="submit_kanryou"/>
							</div>
						</div>
					</div>
				</div>
			</s:form>
			<br><br>
			<div class="product-details-recommend-box">
				<p>関連商品</p>
				<div class="recommends">
					<s:iterator value="#session.productInfoDtoList">
						<ul>
							<li>
								<a href='<s:url action="ProductDetailsAction">
									<s:param name="productId" value="%{productId}"/>
									</s:url>'><img src='<s:property value="imageFilePath"/>/
								<s:property value="imageFileName"/>' class="recommend-image-box"/></a>
								<s:property value="productName"/><br>
							</li>
						</ul>
					</s:iterator>
				</div>
			</div>
		</div>
		<s:include value="footer.jsp"/>
	</body>
</html>