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

</head>
<body id="page-top">
	<div id="content-wrapper">
		<div class="container-fluid">
			<!-- DataTables Example -->
			<div class="card mb-3">
				<div class="card-header">
					<i class="fas fa-table"></i>
					<href onclick=reloadTable()> 用户列表</href>
				</div>
				<div class="card-body">
					<div class="table-responsive">
						<table class="table table-striped table-hover table-bordered"
							id="dataTable" width="100%" cellspacing="0">
							<thead>
								<tr>
									<th></th>
									<th>用户名</th>
									<th>密码</th>
									<th>姓名</th>
									<th>电话</th>
									<th>邮箱</th>
									<th>余额</th>
									<th>账户状态</th>
									<th>创建时间</th>
									<th></th>
								</tr>
							</thead>
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
	<!-- 员工编辑的模态框 -->
	<div class="modal fade" id="AddModel"  role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">用户编辑</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="userSaveForm" role="form">
						<div class="form-group">
							<label class="col-sm-10 control-label">ID：
							</label>
							<input type="text" class="form-control" id="userId"name="userId"
									readonly="readonly">
							
						</div>
						<div class="form-group">
							<label for="inputPassword" class="col-sm-2 control-label">Username</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="inputUsername" name="username"
									>
							</div>
						</div>
							<div class="form-group">
							<label for="inputPassword" class="col-sm-2 control-label">Password</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="inputPassword"name="password"
									placeholder="Password">
							</div>
						</div>
							<div class="form-group">
							<label for="inputPassword" class="col-sm-2 control-label">Real name</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="inputName"name="name"
									placeholder="Password">
							</div>
						</div>
							<div class="form-group">
							<label for="inputPassword" class="col-sm-2 control-label">电话</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="inputTelephone"name="telephone"
									placeholder="Password">
							</div>
						</div>
							<div class="form-group">
							<label for="inputPassword" class="col-sm-2 control-label">邮箱</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="inputEmail"name="email"
									placeholder="Email">
							</div>
						</div>
							<div class="form-group">
							<label for="inputPassword" class="col-sm-2 control-label">余额</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="inputBalance"name="balance"
									placeholder="balance">
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword" class="col-sm-2 control-label">状态</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="inputState"name="state"
									placeholder="Password">
							</div>
						</div>
						
					</form>



				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" id="saveUser" onclick="save()" class="btn btn-primary">保存</button>
				</div>
			</div>
		</div>
	</div>
<%@include file="footer.jsp" %>




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
		        "autoWidth": true,//自动列宽
				 "columns": [
					{"data": "userId","width":10},
					{ "data": "username"},
					{ "data": "password"},
					{ "data": "name"},
					//{ "data": "sex","targets": null},
					{ "data": "telephone"},
					{ "data": "email"},
					{ "data": "state" },
					{ "data": "balance"},
					{ "data": "createTime"}
					],
					
		          "columnDefs": [
			                {
			                    "targets": [9],
			                    "data": "userId",
			                    "render": function(data, type, full) {
				
			                        return "<button type='button' onclick=getUser('"+data+"') id='edit' class='btn btn-primary' data-toggle='modal' data-target='#AddModel' edit-id='userId'> 编辑</button><button type='button' id='delete1'  onclick=del('"+data+"') class='btn btn-danger' value='userId'>删除</button>";
			                    }
			                }
			            ], 
					 
					 // numbers:数字
		            // simple:前一页，后一页
		            // simple_numbers:前一页，后一页，数字
		            // full:第一页，前一页，后一页，最后页
		            //full_numbers:第一页，前一页，后一页，最后页，数字
		            //first_last_numbers:第一页，最后页，数字
				"sPaginationType": "full_numbers", //把所有页码显示出来
				"bProcessing" : true, // 是否显示取数据时的那个等待提示    
				"bServerSide" : true, //这个用来指明是通过服务端来取数据    
				"sAjaxSource" : "${pageContext.request.contextPath}/getUserTable", //这个是请求的地址    
				"fnServerData" : retrieveData,// 获取数据的处理函数    
		 		"fnDrawCallback": function(){
					　　var api = this.api();
					　　//var startIndex= api.context[0]._iDisplayStart;//获取到本页开始的条数
					　　api.column(0).nodes().each(function(cell, i) {
					　　　　//此处 startIndex + i + 1;会出现翻页序号不连续，主要是因为startIndex 的原因,去掉即可。
					　　　　//cell.innerHTML = startIndex + i + 1;
					　　　　cell.innerHTML =  i + 1;

					　　}); 
					} 
			}); 
			
		
		});
		 
		 function del(userId){
			 $.ajax({
					url : "${pageContext.request.contextPath}/userdelete?userId="+userId,
					type : "POST",
					success : function(result) {
						reloadTable();
					}
				});
		 }
		 <!-- 
		 保存按钮事件
		 -->
		 function save(){
			
			 
				$.ajax({
					url : "${pageContext.request.contextPath}/update",
					type : "POST",
					data : $("#userSaveForm").serialize(),
					success : function(data) {
						//$("#AddModel").modal('hide');
						reloadTable();
					}
				});
		 }
		 <!-- 根据id查询员工信息 -->
		 function getUser(userId){
				$.ajax({
					url : "${pageContext.request.contextPath}/getuser?userId="+userId,
					type : "POST",
					success : function(data) {
						$("#userId").val(data.userId);
						$("#inputUsername").val(data.username);
						$("#inputPassword").val(data.password);
						$("#inputName").val(data.name);
						$("#inputTelephone").val(data.telephone);
						$("#inputEmail").val(data.email);
						$("#inputBalance").val(data.balance);
						$("#inputState").val(data.state);
						
						
					}
				});
		 }
		
		 
		 
		 
		 
		 
		 
			
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
		function  reloadTable(){
			var table = table = $('#dataTable').DataTable();
			table.ajax.reload( function( json) {
			} , false);
		}
	</script>
	
	

</body>

</html>
