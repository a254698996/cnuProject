<%@ page language="java" contentType="text/html; charset=utf-8" 	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/paginationTaglib.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><html>
  <head>
    <title>物品列表</title>
    <link href="${ctx}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="${ctx}/static/bootstrap/js/jquery.js"></script>
    <script src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
     <script type="text/javascript">
     	$(document).ready(function(){
     		$("#addBut").click(function(){
     			document.location="${ctx}/goods/toAddGoods";
     		});
     		$("#groundingBut").click(function(){
     			var goodsIds=new Array();
     			$("input[name='goodsIds']:checkbox:checked").each(function(index,param){
     				   goodsIds[index]=$(this).val();
     			 });
     			document.location="${ctx}/goods/adminGrounding/"+goodsIds.toString();
     		});
	    });
     	function update(id) {
   		   document.location="${pageContext.request.contextPath}/goods/toUpdate/"+id;
   		}
 		function grounding(id) {
  		   document.location="${pageContext.request.contextPath}/goods/adminGrounding/"+id;
  		}
     </script>
  </head>
<body> 
<form class="form-inline" action="${ctx}/goods/list">
	  <div class="form-group">
	    <label class="sr-only" for="exampleInputPassword3">物品名</label>
	    <input type="text" name="_SCH_name" class="form-control" value="${param._SCH_name}" placeholder="分类名">
	  </div>
	  <button type="submit" class="btn btn-default">搜索</button><br/>
	</form> 
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
           <c:forEach items="${goodsList}" var="goods" varStatus="itr">
				<tr>
                    <td><a href="${ctx}/goods/get/${goods.id}">${goods.name}</a></td>
                    <td>${goods.goodsCategoryName}</td>
                    <td>${goods.goodsCategorySubName}</td>
                    <td>
 						<c:choose>
						     <c:when test="${goods.state eq 1 }">
						      	已上架
						     </c:when>
						     <c:when test="${goods.state eq 2 }">
						      	已成交
						     </c:when>
							 <c:otherwise >
								  未上架
							 </c:otherwise>
						 </c:choose>
					</td>
					<td>
						 <c:choose>
						     <c:when test="${goods.state eq 1 }">
						      		<button type="button" class="btn btn-info btn-xs" onclick="grounding('${goods.id}')">强制下架</button>
						     </c:when>
							 <c:otherwise >
								  <button type="button" class="btn btn-info btn-xs" onclick="grounding('${goods.id}')">上架</button>
							 </c:otherwise>
						 </c:choose>
					</td>
				</tr>
			</c:forEach>
         </tbody>
       </table>
       <!-- maxResults=steps -->
		<tag:paginate steps="${steps}" pageIndex= "${pageIndex }" count="${count}"
			uri="${ctx}/goods/list?pageIndex={0}" parms="${parms}" />
     </div>
</body>
</html>