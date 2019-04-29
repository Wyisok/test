<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<%@include file="header.jsp"%>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=9sKBMQ6PvNbqkFhsz2UYfSMw5WVtkxVj"></script>

<style type="text/css">
.anchorBL {
	display: none;
}
</style>

</head>

<body class="bg-dark">

	<div class="container">
		<div class="card card-register mx-auto mt-5">
			<div class="card-header">加盟停车场</div>
			<div class="card-body">
				<form method="post" action="${pageContext.request.contextPath}/registerPark">
					<div id="first">
						<div class="form-group">
							<div class="form-row">
								<div class="col-md-6">
									<div class="form-label-group">
										<input type="text" id="username" class="form-control"
											placeholder="用户名" required="required" name="username"
											autofocus="autofocus"> <label for="username">用户名</label>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-label-group">
										<input type="text" id="lastName" class="form-control"
											name="name" placeholder="真实姓名" required="required" /> <label
											for="lastName">真实姓名</label>
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="form-row">
								<div class="col-md-6">
									<div class="form-label-group">
										<input type="password" id="inputPassword" class="form-control"
											name="password" placeholder="Password" required="required" />
										<label for="inputPassword">密码</label>
									</div>
								</div>
								<div class="col-md-6">
									<!-- id与onblur函数命名不能相同 -->
									<div class="form-label-group">
										<input type="password" id="confirmPassword"
											class="form-control" placeholder="Confirm password"
											required="required" /> <label for="confirmPassword">确认密码</label>
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="form-row">
								<div class="col-md-6">
									<div class="form-label-group">
										<input type="email" id="inputEmail" class="form-control"
											name="email" placeholder="Email address" required="required" />
										<label for="inputEmail">邮箱地址</label>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-label-group">
										<input type="tel " id="inputTel" class="form-control"
											name="telephone" placeholder="Tel" required="required" /> <label
											for="inputTel">联系电话</label>
									</div>
								</div>
							</div>
						</div>

						<a class="btn btn-primary btn-block"
							href="javascript:$('#first').hide();$('#second').show();">下一步</a>
					</div>
					<div id="second" style="display: none">
						<div class="form-group">
							<div class="form-inline">
								<div data-toggle="distpicker" id="ssq">
									<select class="form-control "></select> <select
										class="form-control "></select> <select class="form-control "></select>
								</div>
								<div>
									<label for="container" class="text-info"> &nbsp;
										&nbsp;请标注地址信息</label>
								</div>
							</div>
						</div>
						<div class="form-group" id="container"
							style="width: 600px; height: 300px;"></div>
						<input type="hidden" name="address" id="address" /> <input
							type="hidden" name="lngLat" value="" id="lng_lat" />
						<div class="form-group">
							<div class="form-row">
								<div class="col-md-6">
									<a class="btn btn-primary btn-block"
										href="javascript:$('#first').show();$('#second').hide();">上一步</a>
								</div>
								<div class="col-md-6">
									<a class="btn btn-primary btn-block"
										href="javascript:$('#second').hide();$('#third').show();">下一步</a>
								</div>
							</div>
						</div>
					</div>
					<div id="third" style="display: none">
						<!-- text、password、datetime  datetime-local、date、month、time、week、number、email、url、search、tel 和 color。-->
						、
						<div class="form-group">
							<div class="form-row">
								<div class="col-md-6">
									<div class="form-label-group">
										<input type="text" id="parkName" class="form-control"
											placeholder="停车场名称" required="required" name="parkName"
											autofocus="autofocus"> <label for="parkName">停车场名称</label>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-label-group">
										<input type="number" id="inputNum" class="form-control" name="parkSpotNum"
											placeholder="车位总数" required="required" min="0" max="9999">
										<label for="inputNum">车位总数</label>
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="form-inline">
								<select class="form-control col-md-5" name="chargeType"
									id="chargeType">
									<option value="">--收费类型--</option>
									<c:forEach items="${parkChargeTypes}" var="item">
										<option value="${item.dictId}"
											<c:if test="${item.dictId == park.chargeType}"> selected</c:if>>${item.dictItemName}</option>
									</c:forEach>
								</select>
								<div class="input-group col-md-7">
									<div class="input-group-prepend">
										<span class="input-group-text">￥</span> <input type="text"
											class="form-control" placeholder="金额" id="charge"
											name="charge" disabled="disabled"
											aria-label="Amount (to the nearest dollar)"> <span
											class="input-group-text">.00</span>
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="form-row">
								<div class="col-md-6">
									<a class="btn btn-primary btn-block"
										href="javascript:$('#second').show();$('#third').hide();">上一步</a>
								</div>
								<div class="col-md-6">
									<a class="btn btn-primary btn-block"
										href="javascript:$('form').submit();">注册</a>
								</div>
							</div>
						</div>
					</div>
				</form>
				<div class="text-center">
					<a class="d-block small mt-3" href="/login">前往登录</a> <a
						class="d-block small" href="/forgot-password">忘记密码?</a>
				</div>
			</div>
		</div>
	</div>

	<%@include file="footer.jsp"%>
	<script type="text/javascript">
		//地图设置
		var map = new BMap.Map("container", {
			enableMapClick : false
		}); //在container容器中创建一个地图,参数container为div的id属性;
		var point = new BMap.Point(116.40, 39.92); //创建点坐标
		map.centerAndZoom(point, 13); //初始化地图，设置中心点坐标和地图级别
		map.enableScrollWheelZoom(); //激活滚轮调整大小功能
		map.addControl(new BMap.NavigationControl()); //添加控件：缩放地图的控件，默认在左上角；
		map.addControl(new BMap.MapTypeControl()); //添加控件：地图类型控件，默认在右上方；
		map.addControl(new BMap.ScaleControl()); //添加控件：地图显示比例的控件，默认在左下方；
		map.addControl(new BMap.OverviewMapControl()); //添加控件：地图的缩略图的控件，默认在右下方； TrafficControl  

		map.addEventListener("click", getlng_lat); //地图添加点击事件

		//	var marker = new BMap.Marker(point);  // 创建标注
		//	map.addOverlay(marker);               // 将标注添加到地图中
		//	marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
		//var label  = new BMap.Label("1234");
		//map.addOverlay(label);
		var search = new BMap.LocalSearch("中国", {
			onSearchComplete : function(result) {
				if (search.getStatus() == BMAP_STATUS_SUCCESS) {
					var res = result.getPoi(0);
					var point = res.point;
					map.centerAndZoom(point, 13);
				}
			},
			renderOptions : { //结果呈现设置，
				map : map,
				autoViewport : true,
				selectFirstResult : true
			}
		//		onInfoHtmlSet: function(poi, html) {
		//			//标注气泡内容创建后的回调函数，有了这个，可以简单的改一下返回的html内容了。
		////			alert(html.innerHTML)
		//		} //这一段可以不要，只不过是为学习更深层次应用而加入的。
		});
	


		function register() {
			$("form").submit();
		}

		function setCity(addr) {
			search.search(addr);
		}

		function getlng_lat(e) {
			map.clearOverlays();//删除之前的标注
			var marker = new BMap.Marker(e.point); // 创建新标注
			map.addOverlay(marker); // 将标注添加到地图中
			marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
			$("#lng_lat").attr("value", e.point.lng + "*" + e.point.lat);
			var province = $("#ssq select").eq(0).val();
			var city = $("#ssq select").eq(1).val();
			var district = $("#ssq select").eq(2).val();
			var address = province + "*" + city + "*" + district;
			$("#address").attr("value", address);
			//	map.removeOverlay(marker);
		}
	</script>
	<script type="text/javascript">
	$(function() {
		//初始化省市区级联插件
		$('#ssq').distpicker();
		//设置监听，如果地区变化，则地图变化
		$("#ssq").on('change', function(e) {
			var address = "";
			//					var code = $('select option:selected').val();
			//					console.log(code);
			//获取所有的option的值
			$('select option:selected').each(function() {
				//					alert($(this).val());
				address = address + $(this).val();
			});
			//				alert(address);
			//地图变化
			setCity(address);
		});
		$("#chargeType").on("change",function(e){
			if($('#chargeType option:selected').val()=="" || $('#chargeType option:selected').text()=="免费"){
				$("#charge").attr("placeholder","金额");
				$("#charge").prop("disabled",true);
			}else if($('#chargeType option:selected').text()=="标准收费"){
				$("#charge").attr("placeholder","金额/小时");
				$("#charge").prop("disabled",false);
			}else if($('#chargeType option:selected').text()=="按次收费"){
				$("#charge").attr("placeholder","金额/次");
				$("#charge").prop("disabled",false);
			}else{
				$("#charge").attr("placeholder","金额/一次");
				$("#charge").prop("disabled",false);
			}
		});
		//验证密码是否相同
		$("#confirmPassword").blur(function(){
			if($("#inputPassword").val()!="" && $("#inputPassword").val()!=$("#confirmPassword").val()){
				$("#confirmPassword").addClass(" is-invalid");
			}
		});
		$("#confirmPassword").focusin(function(){
			$("#confirmPassword").removeClass("is-invalid");
		});
		
		<!-- asdf-->
	});
	</script>
</body>
</html>
