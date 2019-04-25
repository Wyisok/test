<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<!-- 包含公共的JSP代码片段 -->
	
<title>停车场后台管理</title>



<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath }/sys/style/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/sys/style/js/page_common.js"></script>
<link href="${pageContext.request.contextPath }/sys/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/sys/style/css/index_1.css" />
</head>
<body>
<!-- 页面标题 -->
<div id="TitleArea">
	<div id="TitleArea_Head"></div>
	<div id="TitleArea_Title">
		<div id="TitleArea_Title_Content">
			<img border="0" width="13" height="13" src="style/images/title_arrow.gif"/>用户列表
		</div>
    </div>
	<div id="TitleArea_End"></div>
</div>


	<!-- 过滤条件 -->
	<div id="QueryArea">
		<form action="/wirelessplatform/food.html" method="get">
			<input type="hidden" name="method" value="search">
			<input type="text" name="keyword" title="请输入菜品名称">
			<input type="submit" value="搜索">
		</form>
	</div>
<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <table class="MainArea_Content" align="center" cellspacing="0" cellpadding="0">
        <!-- 表头-->
        <thead>
            <tr align="center" valign="middle" id="TableTitle">
				<td>id</td>
				<td>用户名</td>
				<td>密码</td>
				<td>真实姓名</td>
                <td>性别</td>
				<td>手机号码</td>
				<td>邮箱</td>
				<td>账户余额</td>
				<td>状态</td>
				<td>创建时间</td>
				<td>停车场号码</td>
				<td>操作</td>
			</tr>
		</thead>	
		<!--显示数据列表 -->
        <tbody id="TableData">
		
			<c:choose>
			<c:when test="${not empty page}">
			<c:forEach var="p" items="${page.userList}">
			<tr align="center">  
									<td>${p.user_id }</td>
									<td>${p.username }</td>
									<td>${p.password }</td>
									<td>${p.name }</td>
									<td>${p.sex }</td>
									<td>${p.telephone }</td>
									<td>${p.email }</td>
									<td>${p.balance }</td>
									<td>${p.state }</td>
									<td>${p.create_time }</td>
									<td>${p.park_id }</td>
									
									
									<td>
										<a href="${pageContext.request.contextPath }/food?id=${food.id}&method=ViewUpdate" class="FunctionButton">更新</a>
										
										<a href="${pageContext.request.contextPath }/userdelete?id=${p.user_id}" class="FunctionButton">删除</a>
									</td>
								</tr>
			</c:forEach>
			</c:when>
			<c:otherwise>
							<tr>
								<td colspan="3">没有你要找找的数据，请先保存记录再查看！</td>
							</tr>
						</c:otherwise>
			</c:choose>
        
        </tbody>
    </table>
	
   <!-- 其他功能超链接 -->
	<div id="TableTail" align="center">
		<div class="FunctionButton"><a href="${pageContext.request.contextPath}/sys/userType/addUser.jsp">添加</a></div>
   
     <table align="center">
    <tr>
  			<td colspan="3" align="center">
  				当前${page.currentPageNo }/${page.pageNo }页     &nbsp;&nbsp;
  				
  				<a href="${pageContext.request.contextPath }/users?id=1">首页</a>&nbsp;&nbsp;
  				<a href="${pageContext.request.contextPath }/users?id=${page.currentPageNo-1}">上一页 </a>
  				&nbsp;<a href="${pageContext.request.contextPath }/users?id=${page.currentPageNo+1}">下一页 </a>
  			&nbsp;	&nbsp;<a href="${pageContext.request.contextPath }/users?id=${page.pageNo}">末页</a>
  			</td>
  		</tr>
  		</table>
  		 </div> 
</div>
</body>
</html>
