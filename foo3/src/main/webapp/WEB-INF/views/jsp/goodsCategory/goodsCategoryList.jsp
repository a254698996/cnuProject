<%@ page language="java" contentType="text/html; charset=utf-8" 	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/paginationTaglib.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><html>
  <head>
    <title>物品分类列表</title>
    <link href="${ctx}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="${ctx}/static/bootstrap/js/jquery.js"></script>
    <script src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
     <script type="text/javascript">
     	$(document).ready(function(){
     		$("#addBut").click(function(){
     			document.location="${ctx}/goodsCategory/toAdd";
     		});
	    });
 		function del(id) {
 			if (confirm("确认删除吗？")) {
 				document.location="${pageContext.request.contextPath}/user/delete/"+id;
 			}
 		}
 		function changeState(id) {
 		   document.location="${pageContext.request.contextPath}/goodsCategory/changeState/"+id;
 		}
 		function update(id) {
  		   document.location="${pageContext.request.contextPath}/goodsCategory/toUpdate/"+id;
  		}
     </script>
  </head>
<body> 
<form class="form-inline" action="${ctx}/goodsCategory/list">
	  <div class="form-group">
	    <label class="sr-only" for="exampleInputPassword3">分类名</label>
	    <input type="text" name="_SCH_name" class="form-control" value="${param._SCH_name}" placeholder="分类名">
	  </div>
	  <button type="submit" class="btn btn-default">搜索</button><br/>
	</form>
	 <button type="button" class="btn btn-primary popover-show"
	            title="错误提示" data-container="body"
	            data-toggle="popover" 
	            data-content="没有输入密码"
	            id="addBut"  >
	  		         新增
	    </button>
	<div class="table-responsive">
       <table class="table  table-hover table-bordered">
         <thead>
           <tr>
             <th>编码</th>
             <th>名称</th>
             <th>子码</th>
             <th>是否是子码</th>
             <th>操作</th>
           </tr>
         </thead>
         <tbody>  
           <c:forEach items="${goodsCategoryList}" var="goodsCategory" varStatus="itr">
				<tr>
					<td>${goodsCategory.code}</td>
                    <td>${goodsCategory.name}</td>
					<td>${goodsCategory.subCode}</td>
					<td>${goodsCategory.isSub}</td>
					<td>
						<button type="button" class="btn btn-info btn-xs" onclick="changeState('${goodsCategory.id}')">禁用</button>
						<button type="button" class="btn btn-info btn-xs" onclick="update('${goodsCategory.id}')">更新</button>
					</td>
				</tr>
			</c:forEach>
         </tbody>
       </table>
       <!-- maxResults=steps -->
		<tag:paginate steps="${steps}" pageIndex= "${pageIndex }" count="${count}"
			uri="${ctx}/user/userList?pageIndex={0}" parms="${parms}" />
     </div>
</body>
</html>