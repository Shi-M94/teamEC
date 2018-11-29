<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="./css/mimosa.css">
		<title>商品一覧画面</title>
	</head>
	<body>
		<s:include value="header.jsp" />
		<div id="contents">
			<h1 class="title">商品一覧画面</h1>
			<s:if test="#session.keywordsErrorMessageList != null">
				<div class="error">
					<div class="error-message">
						<s:iterator value="#session.keywordsErrorMessageList"><s:property/><br></s:iterator>
					</div>
				</div>
			</s:if>
			<s:elseif test="#session.productInfoDtoList==null">
				<div class="info">
					検索結果がありません。
				</div>
			</s:elseif>
			<s:else>
				<div id="products">
					<s:iterator value="#session.productInfoDtoList">
						<div id="product-list">
							<ul>
								<li>
									<a href='<s:url action="ProductDetailsAction">
										<s:param name="productId" value="%{productId}"/>
										</s:url>'><img src='<s:property value="imageFilePath"/>/
									<s:property value="imageFileName"/>' class="item-image-box-200"/></a><br>
									<span><s:property value="productName"/></span><br>
									<span><s:property value="productNameKana"/></span><br>
									<span><s:property value="price"/>円(税込)</span>
								</li>
							</ul>
						</div>
					</s:iterator>
				</div>
			</s:else>
		</div>
		<s:include value="footer.jsp"/>
	</body>
</html>