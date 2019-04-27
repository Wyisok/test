<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<%@include file="header.jsp"%>
</head>
<body id="page-top">
	<div id="content-wrapper">
		<div class="container-fluid">
			<!-- DataTables Example -->
			<div class="card mb-3">
				<div class="card-header">
					<i class="fas fa-table"></i> Data Table Example
				</div>
				<div class="card-body">
					<div class="table-responsive">
						<table class="table table-striped table-hover table-bordered" id="dataTable" width="100%"
							cellspacing="0">
						 <thead>
								<tr>
									<th>用户ID</th>
									<th>用户名</th>
									<th>密码</th>
									<th>姓名</th>
									<th>性别</th>
									<th>电话</th>
									<th>邮箱</th>
									<th>余额</th>
									<th>账户状态</th>
									<th>创建时间</th>
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th>用户ID</th>
									<th>用户名</th>
									<th>密码</th>
									<th>姓名</th>
									<th>性别</th>
									<th>电话</th>
									<th>邮箱</th>
									<th>余额</th>
									<th>账户状态</th>
									<th>创建时间</th>
								</tr>
							</tfoot> 
							<tbody>
							</tbody>
						</table>
					</div>
				</div>
				<div class="card-footer small text-muted">Updated yesterday at
					11:59 PM</div>
			</div>

			<p class="small text-center text-muted my-5">
				<em>More table examples coming soon...</em>
			</p>

		</div>
		<!-- /.container-fluid -->

		<!-- Sticky Footer -->
		<footer class="sticky-footer">
			<div class="container my-auto">
				<div class="copyright text-center my-auto">
					<span>Copyright © Your Website 2019</span>
				</div>
			</div>
		</footer>

	</div>
	<!-- /.content-wrapper -->

	</div>
	<!-- /#wrapper -->

	<!-- Scroll to Top Button-->
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
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">Select "Logout" below if you are ready
					to end your current session.</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">Cancel</button>
					<a class="btn btn-primary" href="login.html">Logout</a>
				</div>
			</div>
		</div>
	</div>

	<%@include file="footer.jsp"%>
	<!-- 表格的初始化 与后台交互  -->
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
					{ "data": "sex" },
					{ "data": "telephone" },
					{ "data": "email" },
					{ "data": "state" },
					{ "data": "balance" },
					{ "data": "createTime" }
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
				"sAjaxSource" : "${pageContext.request.contextPath}/getUserTable", //这个是请求的地址    
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
</body>

</html>
