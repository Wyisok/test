<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>车位</title>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
.table { /*数据表格标题文字居中*/
	text-align: center;
	vertical-align: middle !important;
}
</style>
</head>
<body id="page-top">
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
									<th>车位地点</th>
									<th>车位编号</th>
									<th>车位类型</th>
									<th>占用车辆</th>
									<th>是否占用</th>
									<th>是否停用</th>
									<th><a class="badge badge-success" href="#" onclick=addBefore();
										style="font-size: 15px" data-toggle="modal"
										><i
											class="fa fa-plus-square">&nbsp;添加</i></a>&nbsp; <a
										class="badge badge-danger" href="#" style="font-size: 15px"><i
											class="fa fa-minus-square">&nbsp;删除</i></a></th>
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

	<!-- 添加车位的模态框 -->
	<div class="modal fade" id="AddParkSpotModel" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">添加车位</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
				</div>
				<div class="modal-body">
					<form id="addParkSpotForm" role="form">
					<input type="hidden" name="parkId"  id="inputParkId" value=""/>
						<div class="form-group">
								<div class="input-group-prepend">
									<span class="input-group-text">车位地点</span> 
									<input type="text" id="inputSpotPlace" name="spotPlace" class="form-control">
								</div>
						</div>
						<div class="form-group">
								<div class="input-group-prepend">
									<span class="input-group-text">添加数量</span> 
									<input type="number" placeholder=""
										id="inputSpotNum" name="spotNum" class="form-control"
										max="" min="0">
							</div>
						</div>
						<div class="form-group">
						<select class="form-control" name="spotType"
									id="inputType" >
									<c:forEach items="${parkSpotTypes}" var="item">
										<option value="${item.dictItemName}">${item.dictItemName}</option>
									</c:forEach>
								</select>
							</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" onclick=addParkSpot() data-dismiss="modal"
						class="btn btn-primary">完成</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 员工编辑的模态框 -->
	<div class="modal fade" id="AddModel" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">编辑</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
				</div>
				<div class="modal-body">
					<form id="saveSpotForm" role="form">
				<input type="hidden" name="parkSpotId"  id="inputParkSpotId"  value=""/>
							<div class="form-group">
								<select class="form-control " name="spotType"
									id="inputParkSpotType">
									<option value="">--车位类型--</option>
									<c:forEach items="${parkSpotTypes}" var="item">
										<option value="${item.dictItemName}">${item.dictItemName}</option>
									</c:forEach>
								</select>
						</div>
						<div class="form-group ">
							<div class="input-group-prepend">
								<label class="radio-inline form-control"><input
									type="radio" name="state" value="1" id="inputState">启用</label>
								<label class="radio-inline form-control"><input
									type="radio" name="state" value="0" id="inputState">禁用</label>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button"  onclick="saveParkSpot()"data-dismiss="modal"
						class="btn btn-primary">保存</button>
				</div>
			</div>
		</div>
	</div>
	<%@include file="footer.jsp"%>




	<!-- 表格的初始化 与后台交互  -->
	<script type="text/javascript">
		$(document).ready(function() {
			$("#dataTable").dataTable({
				searching: false, //是否开启搜索功能
		        ordering: false,//是否排序
		        bPaginate:true,//是否允许分页
		        bInfo:true,//是否显示表格相关信息
		        "autoWidth": true,//自动列宽
				 "columns": [
					{"data": "parkSpotId","width":10},
					{ "data": "spotPlace"},
					{ "data": "spotNum"},
					{ "data": "spotType"},
					{ "data": "carId"},
					//{ "data": "spotState"},
					//{ "data": "state"},
					],
		          "columnDefs": [
						{
				                    "targets": [5],
				                    "data": "spotState",
				                    "render": function(data, type, full) {
										if(data==1){
				                        return '<span class="badge badge-pill badge-warning">已占用</span>';
										}else{
											return '<span class="badge badge-pill badge-success">未占用</span>';
										}
				                    }
				                },
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
			                    "data": "parkSpotId",
			                    "render": function(data, type, full) {
			                        return '<a href="#" onclick=getParkSpot("'+data+'") data-toggle="modal" data-target="#AddModel"  class="badge badge-info" style="font-size:15px"><i class="fa fa-edit"></i></a>&nbsp;&nbsp;<a href="#"  onclick=delParkSpot("'+data+'") class="badge badge-danger" style="font-size:15px"><i class="fa fa-trash"></i></a>';
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
				"sAjaxSource" : "${pageContext.request.contextPath}/getParkSpotTable?parkId=${park.parkId}", //这个是请求的地址    
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
		// 3个参数的名字可以随便命名,但必须是3个参数,少一个都不行    
		function retrieveData(sSource, allData, fnCallback) {
			$.ajax({
				url : sSource, //这个就是请求地址对应sAjaxSource    
			 	//data : {
					//"allData" : JSON.stringify(allData)
				//}, //这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数 ,分页,排序,查询等的值    
				//data:JSON.stringify(allData),
				data: allData,
				//contentType:'application/json;charset=utf-8',		
				type : 'post',
				dataType : 'json',
				async : false,
			 	success : function(result) {
					fnCallback(result); //把返回的数据传给这个方法就可以了,datatable会自动绑定数据的    
				}, 
				error : function(msg) {
					alert(JSON.stringify(msg));
				}
			});
		}
		function  reloadTable(){
			var table = table = $('#dataTable').DataTable();
			table.ajax.reload( function( json) {
			} , false);
		}
		<!-- 添加按钮实现 -->
		function addBefore(){
			$.post("${pageContext.request.contextPath}/getRemanentSpotNum",
			{"parkId":"${park.parkId}","parkSpotNum":"${park.parkSpotNum}"},
			function(data){
				$("#inputSpotNum").val(data);
				$("#inputSpotNum").attr("max", data);
				if(data>0){
					$("#AddParkSpotModel").modal("show");
				}
			}
			);
		}
		<!-- 添加模态框 完成按钮实现-->
		function addParkSpot(){
			<!-- 验证合法性 -->
			if($("#inputSpotPlace").val()=="" || $("#inputType").val()=="" || "${park.parkId}"==""){
				return;
			}
			if($("#inputSpotNum").attr("max")=="" || $("#inputSpotNum").attr("max")==undefined || $("#inputSpotNum").attr("max")==null){
				return;
			}
			<!-- 若车位数没有填写，默认按最大值提交 -->
			var spotAddNum;
			if($("#inputSpotNum").val()=="" || parseInt($("#inputSpotNum").val())>parseInt($("#inputSpotNum").attr("max"))){
				spotAddNum = $("#inputSpotNum").attr("max");
			}else{
				spotAddNum = $("#inputSpotNum").val();
			}
			var param={
				"parkId":"${park.parkId}",
				"spotPlace":$("#inputSpotPlace").val(),
				"spotAddNum":spotAddNum,
				"spotType":$("#inputType").val()
			};
			
			$.post("${pageContext.request.contextPath}/addParkSpot",
				param,				
				function(data){
				reloadTable();
			}
			);
		}
		
		
		
		 <!-- 删除车位信息 删除按钮-->
		 function delParkSpot(parkSpotId){
			 $.ajax({
					url : "${pageContext.request.contextPath}/delParkSpot?parkSpotId="+parkSpotId,
					type : "POST",
					success : function(result) {
						reloadTable();
					}
				});
		 }
		 <!-- 保存停车场车位信息   保存按钮 -->
		 function saveParkSpot(){
				$.ajax({
					url : "${pageContext.request.contextPath}/updateParkSpot",
					type : "POST",
					data : $("#saveSpotForm").serialize(),
					success : function(data) {
						reloadTable();
					}
				});
		 }
		 <!-- 根据id查询停车场车位信息  编辑按钮 -->
		 function getParkSpot(parkSpotId){
				$.ajax({
					url : "${pageContext.request.contextPath}/getParkSpot",
					data: "parkSpotId="+parkSpotId,
					type : "POST",
					async : false,
					success : function(data) {
					/* 	$("#inputParkId").val(data.parkId);
						$("#inputParkName").val(data.parkName);
						$("#inputParkSpotNum").val(data.parkSpotNum);
						$("#inputCharge").val(data.charge);
						$("[name='state'][value='"+data.state+"']").prop("checked",true);
						$("#inputChargeType").val(data.chargeType); */
						//$("#inputChargeType").find("option[text='"+data.chargeType+"']").prop("selected",true);
						$("#inputParkSpotId").val(data.parkSpotId);
						$("#inputParkSpotType").val(data.spotType);
						$("[name='state'][value='"+data.state+"']").prop("checked",true);
					}
				});
		 }	
		 
		 
		 
			
	</script>
</html>