<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../header.jsp" %>
<script type="text/javascript">
    $(function () {
        $("#toptop").hide();
        $(window).scroll(function () {
            if (parseFloat($(window).scrollTop()) > 200) {
                $("#toptop").show();
            } else {
                $("#toptop").hide();
            }
        });
    	$("#addBut").click(function(){
 			document.location="${ctx}/goods/toAddGoods";
 		});
 		$("#groundingBut").click(function(){
 			document.location="${ctx}/user/toUpdate";
 		});
    	//后台返回提示
			var code=$('#msg').val();
			if(code!=''){
				$('#myModal').modal('show');
			}
    });
    function update(id) {
		   document.location="${pageContext.request.contextPath}/goods/toUpdate/"+id;
		}
	function grounding(id) {
	   document.location="${pageContext.request.contextPath}/goods/grounding/"+id;
	}
</script>
    <div class="Ioutside">
       
        <div class="ReContent">
           <form class="form-inline" action="${ctx}/user/owner">
	  <div class="form-group">
	    <label class="sr-only" for="exampleInputPassword3">物品名</label>
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
	    <button type="button" class="btn btn-primary popover-show"
	            title="错误提示" data-container="body"
	            data-toggle="popover" 
	            data-content="没有输入密码"
	            id="groundingBut"  >
	  		    个人信修改
	    </button>
	<div class="table-responsive">
       <table class="table  table-hover table-bordered">
         <thead>
           <tr>
<!--            	 <th>选择</th> -->
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
<%-- 					<td><input type="checkbox" name="goodsIds"  value="${goods.id}"></td> --%>
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
						<c:if test="${goods.deal eq 0 }">
							<button type="button" class="btn btn-info btn-xs" onclick="update('${goods.id}')">更新</button>
							 <c:choose>
							     <c:when test="${goods.state eq 1 }">
							      		<button type="button" class="btn btn-info btn-xs" onclick="grounding('${goods.id}')">下架</button>
							     </c:when>
								 <c:otherwise >
									  <button type="button" class="btn btn-info btn-xs" onclick="grounding('${goods.id}')">上架</button>
								 </c:otherwise>
							 </c:choose>
						 </c:if>
					</td>
				</tr>
			</c:forEach>
         </tbody>
       </table>
       <!-- maxResults=steps -->
		<tag:paginate steps="${steps}" pageIndex= "${pageIndex }" count="${count}"
			uri="${ctx}/user/owner?pageIndex={0}" parms="${parms}" />
     </div>
        </div>
        
    </div>
<!-- 模态框（Modal） -->
<input type="hidden" value="${msg}" id="msg">
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">提示</h4>
            </div>
            <div class="modal-body" id="pageMsg" >${msg}</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

    <div class="footer">

        <div class="copyright">
            <div class="copyright_inner">
					<img class="foot_logo" src="${ctx}/static/ggt/App_Themes/UI/images/foot_logo.jpg" width="112" height="26" alt=" " />
           </div>
             
        </div>
    </div>
<!-- footer end -->
</body>
 
</html>
 