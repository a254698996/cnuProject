<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/paginationTaglib.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>更新物品</title>
<link href="${ctx}/static/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<script src="${ctx}/static/bootstrap/js/jquery.js"></script>
<script src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="${ctx}/static/plugins/jstree/themes/classic/style.css">
<script type="text/javascript"
	src="${ctx}/static/plugins/jstree/_lib/jquery.js"></script>
<script type="text/javascript"
	src="${ctx}/static/plugins/jstree/_lib/jquery.cookie.js"></script>
<script type="text/javascript"
	src="${ctx}/static/plugins/jstree/_lib/jquery.hotkeys.js"></script>
<script type="text/javascript"
	src="${ctx}/static/plugins/jstree/jquery.jstree.js"></script>
<script type="text/javascript"
	src="${ctx}/static/bootstrap-fileinput/js/fileinput.min.js"></script>
<link rel="stylesheet"
	href="${ctx}/static/bootstrap-fileinput/css/fileinput.min.css">
	<script type="text/javascript">
	   function trans(goodsId){
			document.location="${pageContext.request.contextPath}/goods/trans/"+goodsId;
	   }
	</script>
</head>
<body>
	浏览物品  <br>
	  <label class="control-lable">名称:</label> 
	       ${goods.name}<br>
		<label class="control-lable">大类:</label>
		  ${goods.goodsCategoryName}<br>
	   <label class="control-lable">细类:</label> 
		   ${goods.goodsCategorySubName}<br>
	   <label class="control-lable">状态:</label> 
		  <c:choose>
		     <c:when test="${goods.state eq 1 }">
		      	已上架
		     </c:when>
		     <c:when test="${goods.state eq 0 }">
		      	 未上架
		     </c:when>
			 <c:otherwise >
				 成交
			 </c:otherwise>
		 </c:choose>  <br> 
	   <label class="control-lable">成交状态:</label> 
	   	<c:choose>
		     <c:when test="${goods.deal eq 1 }">
		      	已成交
		     </c:when> 
			 <c:otherwise >
				未成交
			 </c:otherwise>
		 </c:choose>
		   <br> 
		<c:forEach items="${picList }" var="goodsPic">
		  <img  src="${ctx}/upload/${goodsPic.url}">
		</c:forEach>
		<div class="table-responsive">
       <table class="table  table-hover table-bordered">
         <thead>
           <tr>
             <th>名称</th>
             <th>大类</th>
             <th>细类</th>
             <th>状态</th>
             <th>操作</th>
           </tr>
         </thead>
         <tbody>  
         <c:set var="goodsDeal" scope="request" value="${goods.deal}"/>
           <c:forEach items="${goodsList}" var="goods" varStatus="itr">
				<tr>
                    <td><a href="${ctx}/goods/get/${goods.id}">${goods.name}</a></td>
                    <td>${goods.goodsCategoryName}</td>
                    <td>${goods.goodsCategorySubName}</td>
                    <td> 
						 <c:choose>
						     <c:when test="${goods.eo.exchangeState eq 0 }">
						      	 未匹配成功
						     </c:when>
							 <c:otherwise >
								 匹配成功
							 </c:otherwise>
						 </c:choose>
					</td>
					<td> 
						 <c:if test="${goodsDeal eq 0}">
							     <button type="button" class="btn btn-info btn-xs" onclick="trans('${goods.eo.id}')">与他交易</button>
						 </c:if>
					</td>
				</tr>
			</c:forEach>
         </tbody>
       </table>
       <!-- maxResults=steps -->
<%-- 		<tag:paginate steps="${steps}" pageIndex= "${pageIndex }" count="${count}" --%>
<%-- 			uri="${ctx}/goods/list?pageIndex={0}" parms="${parms}" /> --%>
     </div>
</body>
</html>