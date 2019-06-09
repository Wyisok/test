<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>智能停车管理系统</title>
<style type="text/css">
.table{ /*数据表格标题文字居中*/
text-align: center;
vertical-align: middle!important;
}
</style>
<%@include file="header.jsp"%>
</head>
<body id="page-top">

	<nav class="navbar navbar-expand navbar-dark bg-dark static-top">

		<a class="navbar-brand mr-1" href="${pageContext.request.contextPath}">智能停车管理系统</a>

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
			<li class="nav-item dropdown no-arrow mx-1"><a
				class="nav-link dropdown-toggle" href="#" id="messagesDropdown"
				role="button" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"> <i class="fas fa-envelope fa-fw"></i> <span
					class="badge badge-danger"></span>
			</a>
				<div class="dropdown-menu dropdown-menu-right"
					aria-labelledby="alertsDropdown">
					<a class="dropdown-item" href="#">Action</a> <a
						class="dropdown-item" href="#">Another action</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="#">Something else here</a>
				</div></li>
			<li class="nav-item dropdown no-arrow mx-1"></li>


			<li class="nav-item dropdown no-arrow"><a
				class="nav-link dropdown-toggle" id="userDropdown" role="button"
				data-toggle="dropdown" aria-haspopup="true"> <i
					class="fas fa-user-circle fa-fw"> </i>
			</a>
				<div class="dropdown-menu dropdown-menu-right"
					aria-labelledby="userDropdown">
					<a class="dropdown-item" href="#">用户名：${username}</a> 
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="" data-toggle="modal"
						data-target="#logoutModal">退出登录</a>
				</div></li>
		</ul>
	</nav>

	<div id="wrapper">
		<!-- Sidebar -->
			<!-- Sidebar -->
		<ul class="sidebar navbar-nav">
			<c:forEach items="${menu}" var="m">
			<%-- 	<c:if test="${'/index'.equals(m.menuUrl)}">
					<li class="nav-item active">
					<a class="nav-link" href="${pageContext.request.contextPath}"> 
						<i class="fas fa-fw fa-tachometer-alt"></i> 
						<span>${m.menuName }</span>
					</a>
					</li>
				</c:if> --%>
				<c:if test="${'/park'.equals(m.menuUrl)}">
					<li class="nav-item active">
					<a class="nav-link" href="${pageContext.request.contextPath}/park"> 
						<i class="fas fa-fw fa-tachometer-alt"></i> 
						<span>${m.menuName }</span>
					</a>
					</li>
				</c:if>
				<c:if test="${!'/park'.equals(m.menuUrl)}">
					<li class="nav-item">
					<a class="nav-link" onclick=openAjaxURL('${m.menuUrl }') >
					 <i class="fas fa-fw fa-chart-area">
					</i> 
					<span>${m.menuName }</span>
					</a>
					</li>
				</c:if>
			</c:forEach>
		</ul>
		<script type="text/javascript">
	function openAjaxURL(url){
		var url = "${pageContext.request.contextPath}"+url;
		$('#content-wrapper').load(url);
	}
</script>


















	<div id="content-wrapper">
		<div class="container-fluid">
			<!-- DataTables Example -->
			<div class="card mb-3">
				<div class="card-header">
					<i class="fas fa-table"></i>
					<href onclick=reloadTable()> 停车场列表</href>
				</div>
				<div class="card-body">
					<div class="table-responsive">
						<table class="table table-striped table-hover table-bordered"
							id="dataTable" width="100%" cellspacing="0">
							<thead>
								<tr>
									<th></th>
									<th>停车场名称</th>
									<th>车位数量</th>
									<th>地址</th>
									<th>收费金额</th>
									<th>收费类型</th>
									<th>状态</th>
									<th>
									<a class="badge badge-success" href="#" style="font-size:15px"><i class="fa fa-plus-square">&nbsp;添加</i></a>&nbsp;
									<a class="badge badge-danger" href="#" style="font-size:15px"><i class="fa fa-minus-square">&nbsp;删除</i></a>
									</th>
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
	<!-- 员工编辑的模态框 -->
	<div class="modal fade" id="AddModel"  role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">编辑</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
				</div>
				<div class="modal-body">
					<form  id="userSaveForm" role="form">
						<!-- 	<div class="form-group">
							<div class="form-row">
							<label class="col-md-2 control-label">ID：
							</label>
							<div class="col-md-10">
							<input type="text" class="form-control" id="parkId"name="parkId"
									readonly="readonly">
							</div>
							</div>
						</div> -->
						<div class="form-group">
							<div class="input-group-prepend">
								<span class="input-group-text">停车场编号</span> <input type="text"
									class="form-control" placeholder="" id="inputParkId" name="parkId"
									readonly="readonly">
								<!-- <span class="input-group-text">.00</span> -->
							</div>
						</div>
						<div class="form-group ">
							<div class="input-group-prepend">
								<span class="input-group-text">停车场名称</span> <input type="text"
									class="form-control" placeholder="" id="inputParkName" name="parkName"
									>
								<!-- <span class="input-group-text">.00</span> -->
							</div>
						</div>
						<div class="form-group ">
							<div class="input-group-prepend">
								<span class="input-group-text">车位数量</span> <input type="number" maxlength="9999" min="0"
									class="form-control" placeholder="" id="inputParkSpotNum" name="parkSpotNum"
									>
								<!-- <span class="input-group-text">.00</span> -->
							</div>
						</div>
						
							<div class="form-group">
							<div class="form-inline">
								<select class="form-control col-md-5" name="chargeType"
									id="inputChargeType">
									<%-- <option value="">--收费类型--</option>
									<c:forEach items="${parkChargeTypes}" var="item">
										<option value="${item.dictItemName}">${item.dictItemName}</option>
									</c:forEach> --%>
									
									<option value="">--收费类型--</option>
									<c:forEach items="${parkChargeTypes}" var="item">
										<option value="${item.dictItemName} ">${item.dictItemName}</option>
									</c:forEach>
									
								</select>
								<div class="input-group col-md-5">
									<div class="input-group-prepend">
										<span class="input-group-text">￥</span> 
										<input type="number"  id="inputCharge" name="charge" style="width:168px;"
											class="form-control"  maxlength="9999" min="0"> 
										<span class="input-group-text">.00</span>
									</div>
								</div>
							</div>
						</div>
						
						
						<div class="form-group ">
							<div class="input-group-prepend">
								<label class="radio-inline form-control"><input type="radio" name="state"   value="1"  id="inputState" >启用</label>
								<label class="radio-inline form-control"><input type="radio" name="state"   value="0" id="inputState" >禁用</label>
							</div>
						</div>
					</form>



				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" id="saveUser" onclick="save()" class="btn btn-primary" data-dismiss="modal">保存</button>
				</div>
			</div>
		</div>
	</div>
	
	
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
					<h5 class="modal-title" id="exampleModalLabel">确定要退出登录吗?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true"></span>
					</button>
				</div>
				<!-- <div class="modal-body">Select "Logout" below if you are ready
					to end your current session.</div> -->
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">取消</button>
					<a class="btn btn-primary"
						href="${pageContext.request.contextPath}/doLogout">退出</a>
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
					{"data": "parkId","width":10},
					{ "data": "parkName"},
					{ "data": "parkSpotNum"},
					{ "data": "address"},
					{ "data": "charge"},
					{ "data": "chargeType"},
					//{ "data": "state"},
					],
		          "columnDefs": [
			    				{
				                    "targets": [6],
				                    "data": "state",
				                    "render": function(data, type, full) {
										if(data==1){
				                        return '<span class="badge badge-pill badge-success">已启用</span>';
										}else{
											return '<span class="badge badge-pill badge-warning">已禁用</span>';
										}
				                    }
				                },
			                {
			                    "targets": [7],
			                    "data": "parkId",
			                    "render": function(data, type, full) {
			                        return '<a href="#" onclick=getPark("'+data+'") data-toggle="modal" data-target="#AddModel"  class="badge badge-info" style="font-size:15px"><i class="fa fa-edit"></i></a>&nbsp;&nbsp;<a href="#"  onclick=delPark("'+data+'") class="badge badge-danger" style="font-size:15px"><i class="fa fa-trash"></i></a>';
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
				"sAjaxSource" : "${pageContext.request.contextPath}/getParkTable", //这个是请求的地址    
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
		 
		 
		 
		 
		 function delPark(parkId){
			 $.ajax({
					url : "${pageContext.request.contextPath}/delPark?parkId="+parkId,
					type : "POST",
					async : true,
					success : function(result) {
						reloadTable();
					}
				});
		 }
		 function save(){
				$.ajax({
					url : "${pageContext.request.contextPath}/updatePark",
					type : "POST",
					async : true,
					data : $("#userSaveForm").serialize(),
					success : function(data) {
						$("#AddModel").modal('hide');
						reloadTable();
					}
				});
		 }
		 <!-- 根据id查询停车场信息 -->
		 function getPark(parkId){
				$.ajax({
					url : "${pageContext.request.contextPath}/getPark",
					
					data: "parkId="+parkId,
					type : "POST",
					async : false,
					success : function(data) {
						$("#inputParkId").val(data.parkId);
						$("#inputParkName").val(data.parkName);
						$("#inputParkSpotNum").val(data.parkSpotNum);
						$("#inputCharge").val(data.charge);
						$("[name='state'][value='"+data.state+"']").prop("checked",true);
						$("#inputChargeType").val(data.chargeType);
						//$("#inputChargeType").find("option[text='"+data.chargeType+"']").prop("selected",true);
					}
				});
				
		 }
		
		 
		 
		 
		 
		 
		 
			
		// 3个参数的名字可以随便命名,但必须是3个参数,少一个都不行    
		function retrieveData(sSource, allData, fnCallback) {
			$.ajax({
				url : sSource, //这个就是请求地址对应sAjaxSource    
			 	//data : {
					//"allData" : JSON.stringify(allData)
				//}, //这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数 ,分页,排序,查询等的值    
				//data:JSON.stringify(allData),
				data:allData,
				//contentType:'application/json;charset=utf-8',		
				type : 'post',
				dataType : 'json',
				async : false,
			 	success : function(result) {
					fnCallback(result); //把返回的数据传给这个方法就可以了,datatable会自动绑定数据的    
				}, 
				error : function(msg) {
					alert(msg);
				}
			});
		}
		function  reloadTable(){
			var table = table = $('#dataTable').DataTable();
			table.ajax.reload( function( json) {
			} , false);
		}
	</script>
</html>