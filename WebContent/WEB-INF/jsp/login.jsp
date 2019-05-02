<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">

<head>

 <%@include file="header.jsp" %>
</head>

<body class="bg-dark">

  <div class="container">
    <div class="card card-login mx-auto mt-5">
      <div class="card-header">Login</div>
      <div class="card-body">
        <form id="addUserForm" action="${pageContext.request.contextPath}/add" method="post">
          <div class="form-group">
              <label for="inputEmail">User name</label>
            <div class="form-label-group">
              <input type="text" id="inputUser" name="username" class="form-control" placeholder="Email address" required="required" autofocus="autofocus">
            </div>
          </div>
          <div class="form-group">
              <label for="inputPassword">Password</label>
            <div class="form-label-group">
              <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required="required">
            </div>
          </div>
          <div class="form-group">
            <div class="checkbox">
              <label>
                <input type="checkbox" value="remember-me">
                Remember Password
              </label>
              <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${msg }</span>
            </div>
          </div>
          <a class="btn btn-primary btn-block"  onclick="document:addUserForm.submit()"  >Login</a>
        </form>
        <div class="text-center">
          <a class="d-block small mt-3" href="register">Register an Account</a>
          <a class="d-block small" href="forgot-password">Forgot Password?</a>
        </div>
      </div>
    </div>
  </div>
  

<%@include file="footer.jsp" %>
	


 <script type="text/javascript">
		<!-- 
		 保存按钮单机事件
		-->
		
		
		function login() {
		alert("sdfsfds")
			//1.模态框填写的表单数据提交给服务器保存
			//2.发送ajax请求保存员工
			$.ajax({
				url : "${APP_PATH}/add",
				type : "POST",
				data : $("#addUserForm").serialize(),
				success : function(result) {
				
				}
			});
		}
		
</script>
</body>
 
  
</html>
