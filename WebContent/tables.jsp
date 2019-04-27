<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>SB Admin - Tables</title>

<!-- Custom fonts for this template-->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">

<!-- Page level plugin CSS-->
<link href="vendor/datatables/dataTables.bootstrap4.css"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="css/sb-admin.css" rel="stylesheet">

</head>

<body id="page-top">

	<nav class="navbar navbar-expand navbar-dark bg-dark static-top">

	<a class="navbar-brand mr-1" href="index.html">智能停车管理系统</a>

	<button class="btn btn-link btn-sm text-white order-1 order-sm-0"
		id="sidebarToggle" href="#">
		<i class="fas fa-bars"></i>
	</button>

	<!-- Navbar Search -->
	<form
		class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0">
		<div class="input-group">
			<input type="text" class="form-control" placeholder="Search for..."
				aria-label="Search" aria-describedby="basic-addon2">
			<div class="input-group-append">
				<button class="btn btn-primary" type="button">
					<i class="fas fa-search"></i>
				</button>
			</div>
		</div>
	</form>

	<!-- Navbar -->
	<ul class="navbar-nav ml-auto ml-md-0">


		<li class="nav-item dropdown no-arrow"><a
			class="nav-link dropdown-toggle" href="#" id="userDropdown"
			role="button" data-toggle="dropdown" aria-haspopup="true"
			aria-expanded="false"> <i class="fas fa-user-circle fa-fw"></i>
		</a>
			<div class="dropdown-menu dropdown-menu-right"
				aria-labelledby="userDropdown">
				<a class="dropdown-item" href="#">个人信息</a>
				<div class="dropdown-divider"></div>
				<a class="dropdown-item" href="#" data-toggle="modal"
					data-target="#logoutModal">退出</a>
			</div></li>
	</ul>

	</nav>


	<div id="wrapper">
		<!-- Sidebar -->
		<ul class="sidebar navbar-nav">
			<li class="nav-item"><a class="nav-link" href="charts.jsp">
					<i class="fas fa-fw fa-chart-area"></i> <span>停车场管理</span>
			</a></li>
			<li class="nav-item active"><a class="nav-link"
				href="${pageContext.request.contextPath }/getusers"> <i class="fas fa-fw fa-table"></i> <span>用户管理</span></a>
			</li>
		</ul>



		<div id="content-wrapper">
			<div class="container-fluid">
				<!-- DataTables Example -->
				<div class="card mb-3">
					<div class="card-header">
						<i class="fas fa-table"></i> Data Table Example
					</div>
					<div class="card-body">
						<div class="table-responsive">
							<table class="table table-bordered" id="dataTable" width="100%"
								cellspacing="0">
								<thead>
									<tr>
										<th>id</th>
										<th>用户名</th>
										<th>密码</th>
										<th>真实姓名</th>
										<th>手机号码</th>
										<th>邮箱</th>
										<th>余额</th>
										<th>状态</th>
										<th>操作</th>
										private String userId;
private String username;
private String password;
private String name;
private String sex;
private String telephone;
private String email;
private String balance;
private int state;
private Date createTime;
private String parkId;
									</tr>
								</thead>

								<tbody>
									<c:choose>
										<c:when test="${not empty list}">
											<c:forEach var="p" items="${list}">
												<tr>
													<td>${p.userId }</td>
													<td>${p.username }</td>
													<td>${p.password }</td>
													<td>${p.name }</td>
													<td>${p.telephone }</td>
													<td>${p.email }</td>
													<td>${p.balance }</td>
													<td>${p.state }</td>


													<td><a href="${pageContext.request.contextPath }/getuser?userid=${p.userId}" class="btn btn-primary btn-xs"
														data-toggle="modal" data-target="#update"
														>更新</a> <a href=""
														class="btn btn-danger btn-xs"
														onclick="deleteCustomer(${row.cust_id})">删除</a></td>
												</tr>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<p class="small text-center text-muted my-5">
														<em>没有你要找的数据，请先保存记录再查看！</em>
													</p>
										</c:otherwise>
									</c:choose>

								</tbody>
							</table>
					
				
					</div>
					</div>
					<div class="card-footer small text-muted">Updated yesterday
						at 11:59 PM</div>
				</div>
		
        <p class="small text-center text-muted my-5">
          <em>More table examples coming soon...</em>
        </p>

			</div>
		
		<!-- 	<footer class="sticky-footer">
			<div class="container my-auto">
				<div class="copyright text-center my-auto">
					<span>Copyright © Your Website 2019</span>
				</div>
				
		
			</div>
			</footer> -->

		</div>
	</div>
	<!--更新模态框 -->
	<div class="modal fade" id="update" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">更新用户</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
				</div>
				<div class="modal-body">
					<!-- 表单 -->
					<form class="form-horizontal" role="form">
						<div class="form-group">
							<label class="col-sm-2 control-label">ID</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="user_id"
									value="1" disabled>
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword" class="col-sm-2 control-label">账号</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="username"
								placeholder="账号 "	value="${user.username }">
							</div>
						</div>
						<!-- 密码 -->
						<div class="form-group">
							<label for="inputPassword" class="col-sm-2 control-label">密码</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="password"
									placeholder="密码  " value="${user.password }">
							</div>
						</div>


						<!--真实姓名 -->
						<div class="form-group">
							<label for="disabledTextInput">&nbsp;&nbsp;&nbsp;&nbsp;真实姓名</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="name"
									placeholder="真实姓名" value="${user.name }">
							</div>
						</div>
						<!-- 手机号 -->
						<div class="form-group">
							<label for="inputPassword">&nbsp;&nbsp;&nbsp;&nbsp;手机号</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="telephone"
									placeholder="手机号" value="${user.telephone}">
							</div>
						</div>
						<!-- 邮箱 -->
						<div class="form-group">
							<label for="inputPassword" class="col-sm-2 control-label">邮箱</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="email"
									placeholder="邮箱" value="${user.email }">
							</div>
						</div>
						<!-- 余额 -->
						<div class="form-group">
							<label for="inputPassword" class="col-sm-2 control-label">余额</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="balance"
									placeholder="余额" value="${user.balance }"> 
							</div>
						</div>
						<!-- 状态 -->
						<div class="form-group">
							<label for="inputPassword" class="col-sm-2 control-label">状态</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="state"
									placeholder="状态" value="${user.state }">
							</div>
						</div>







					</form>





				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary">Save changes</button>
				</div>
			</div>
			<!-- /.modal-content -->s
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->




	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Logout Modal-->
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">Ã</span>
					</button>
				</div>
				<div class="modal-body">Select "Logout" below if you are ready
					to end your current session.</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">取消</button>
					<a class="btn btn-primary" href="login.html">退出</a>
				</div>
			</div>
		</div>
	</div>
	<!-- 分页信息 -->
	<script type="text/javascript">
		/* $(function(){
		 alert(123);
		 }); */
		$(document).ready(function() {
			$("#dataTable").dataTable({
				searching: false, //是否开启搜索功能
		        ordering: false,//是否排序
		        bPaginate:true,//是否允许分页
		        bInfo:true,//是否显示表格相关信息
				 "columns": [
					{ "data": "userId" },
					{ "data": "username" },
					{ "data": "password" },
					{ "data": "name" },
					{ "data": "telephone" },
					{ "data": "email" },
					{ "data": "balance" },
					{ "data": "state" }
					], 
					 // numbers:数字
		            // simple:前一页，后一页
		            // simple_numbers:前一页，后一页，数字
		            // full:第一页，前一页，后一页，最后页
		            //full_numbers:第一页，前一页，后一页，最后页，数字
		            //first_last_numbers:第一页，最后页，数字
				"sPaginationType": "full_numbers", //把所有页码显示出来
				"bProcessing" : false, // 是否显示取数据时的那个等待提示    
				"bServerSide" : true, //这个用来指明是通过服务端来取数据    
				"sAjaxSource" : "${pageContext.request.contextPath}/getusers", //这个是请求的地址    
				"fnServerData" : retrieveData,// 获取数据的处理函数    
			}); 
		});
		// 3个参数的名字可以随便命名,但必须是3个参数,少一个都不行    
		function retrieveData(sSource, allData, fnCallback) {
			$.ajax({
				url : sSource, //这个就是请求地址对应sAjaxSource    
			 	data : {
					"allData" : JSON.stringify(allData)
				}, //这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数 ,分页,排序,查询等的值    
				type : 'post',
				dataType : 'json',
				async : false,
			 	success : function(result) {
					fnCallback(result); //把返回的数据传给这个方法就可以了,datatable会自动绑定数据的    
				}, 
				error : function(msg) {
				}
			});
		}
	</script>

	<!-- Bootstrap core JavaScript-->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Page level plugin JavaScript-->
	<script src="vendor/chart.js/Chart.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="js/sb-admin.min.js"></script>

	<!-- Demo scripts for this page-->
	<script src="js/demo/chart-area-demo.js"></script>
	<script src="js/demo/chart-bar-demo.js"></script>
	<script src="js/demo/chart-pie-demo.js"></script>
</body>

</html>
