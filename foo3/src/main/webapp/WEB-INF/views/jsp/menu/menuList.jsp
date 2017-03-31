<%@ page language="java" contentType="text/html; charset=utf-8" 	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/paginationTaglib.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><html>
  <head>
    <title>菜单列表</title>
    <link href="${ctx}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="${ctx}/static/bootstrap/js/jquery.js"></script>
    <script src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
     <script type="text/javascript">
     	$(document).ready(function(){
     		$("#addBut").click(function(){
     			document.location="${ctx}/menu/toAdd";
     		});
	    });
 		function remove(id) {
 			if (confirm("确认删除吗？")) {
 				document.location="${pageContext.request.contextPath}/menu/delete/"+id;
 			}
 		}
 		function changeState(id) {
 		   document.location="${pageContext.request.contextPath}/menu/changeState/"+id;
 		}
 		function update(id) {
  		   document.location="${pageContext.request.contextPath}/menu/toUpdate/"+id;
  		}
     </script>
  </head>
<body> 
<form class="form-inline" action="${ctx}/menu/list">
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
             <th>名称</th>
             <th>地址</th>
             <th>状态</th>
             <th>操作</th>
           </tr>
         </thead>
         <tbody>  
           <c:forEach items="${menuList}" var="menu" varStatus="itr">
				<tr>
					<td>${menu.name}</td>
                    <td>${menu.url}</td>
					<td>${menu.state}</td>
					<td>
						<button type="button" class="btn btn-info btn-xs" onclick="changeState('${menu.id}')">禁用</button>
						<button type="button" class="btn btn-info btn-xs" onclick="update('${menu.id}')">更新</button>
						<button type="button" class="btn btn-info btn-xs" onclick="remove('${menu.id}')">删除</button>
					</td>
				</tr>
			</c:forEach>
         </tbody>
       </table>
       <!-- maxResults=steps -->
		<tag:paginate steps="${steps}" pageIndex= "${pageIndex }" count="${count}"
			uri="${ctx}/menu/list?pageIndex={0}" parms="${parms}" />
     </div>
</body>
</html>