<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8"%><!DOCTYPE html>
<html>
	<head>
		<title>用户信息管理页面</title>
		<jsp:include page="/resources/js/common/common.jsp"></jsp:include>
	</head>
	<body class="gray-bg">
		<div class="wrapper wrapper-content animated fadeInRight">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>用户信息管理</h5>
				</div>
				<div class="ibox-content">
					<div class="row row-lg">
						<div class="col-sm-12">
							<!-- Example Events -->
							<div class="example-wrap">
								<div class="example">
									<div class="btn-group hidden-xs" id="exampleTableEventsToolbar" role="group">
										<button type="button" class="btn btn-outline btn-default">
                                        <i class="glyphicon glyphicon-plus" aria-hidden="true"></i> 新增
                                    </button>
										<button type="button" class="btn btn-outline btn-default">
                                        <i class="glyphicon glyphicon-pencil" aria-hidden="true"></i> 修改
                                    </button>
										<button type="button" class="btn btn-outline btn-default">
                                        <i class="glyphicon glyphicon-trash" aria-hidden="true"></i> 删除
                                    </button>
                                    	<button type="button" class="btn btn-outline btn-default">
                                        <i class="glyphicon glyphicon-trash" aria-hidden="true"></i> 批量删除
                                    </button>
									</div>
									<table id="exampleTableEvents" data-height="750" data-mobile-responsive="true">
										<thead>
											<tr>
												<%--<th data-field="state" data-checkbox="true"></th>--%>
												<th data-field="bookId">ID</th>
												<th data-field="name">名称</th>
												<th data-field="number">价格</th>
											</tr>
										</thead>
									</table>
								</div>
							</div>
							<!-- End Example Events -->
						</div>
					</div>
				</div>
			</div>
		</div>
		<script src="/resources/user-manager/user.js"></script>
	</body>

</html>