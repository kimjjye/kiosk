<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<%@include file="../include/header.jsp"%>
<!-- Main content -->
<section class="wrapper">
	<script>
		var result = '${msg}';

		if (result == 'SUCCESS') {
			alert("처리가 완료되었습니다.");
		}
	</script>

	<div class="container">


		<!-- Page-Title -->
		<div class="row">
			<div class="col-sm-12">
				<div class="page-title-box">
					<div class="btn-group pull-right">
						<ol class="breadcrumb hide-phone p-0 m-0">
							<li><a href="#">디지털 조직도</a></li>
							<li><a href="#">기초설정</a></li>
							<li class="active">계정생성</li>
						</ol>
					</div>
					<h4 class="page-title">
						계정수정 <small>계정을 <code>수정</code> 할 수 있습니다.
						</small>
					</h4>
				</div>
			</div>
		</div>
		<!-- end page title end breadcrumb -->

		<div class="row">
			<div class="col-lg-12">
				<div class="card-box">

					<form class="form-horizontal"  id="modUserForm"
						method="post">
						<div class="row">

							<div class="col-md-7">
								<div class="form-horizontal" role="form">
									<!-- section Select -->
									<div class="form-group">
										<label class="col-md-2 control-label">부서</label>
										<div class="col-md-5">
											<select class="form-control" name="section_fullcode" id="section_fullcode">
												<c:forEach items="${sectionList}" var="com_sectionVO">
													<option value="${com_sectionVO.section_fullcode}">${com_sectionVO.section_fullpath}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<!-- section_name -->
									<div class="form-group">
										<label class="col-md-2 control-label">NAME</label>
										<div class="col-md-5">
											<input type="text" class="form-control" placeholder="Name"
												id="section_name" name="name" value="${userVO.name}">
										</div>
									</div>

									<!-- section_id -->
									<div class="form-group">
										<label class="col-md-2 control-label">계정ID</label>
										<div class="col-md-5">
											<input type="text" class="form-control" placeholder="ID"
												id="section_id" name="id" value="${userVO.id}" readonly="readonly">
										</div>
								
									</div>

									<!-- section_pass_ok -->
									<div class="form-group">
										<label class="col-md-2 control-label">비밀번호 변경</label>
										<div class="col-md-5">
											<input type="password" class="form-control"
												placeholder="변경할 비밀번호" id="section_pass_ok" name="password">
										</div>
										<div class="col-md-4">
											<p>숫자만 가능합니다.</p>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">권한</label>
										<div class="col-md-5">
											<div class="radio radio-warning">
												<input type="radio" name="auth" id="auth_1" value="0">
												<label for="auth_1">Super </label>
											</div>

											<div class="radio radio-success">
												<input type="radio" name="auth" id="auth_2" value="1">
												<label for="auth_2"> 일반 </label>
											</div>
										</div>
									</div>

								</div>
							</div>

						</div>
						<div class="form-group" style="text-align: center">
							<button type="button"
								class="btn btn-primary waves-effect w-md waves-light m-b-5" id = "modUser">수정</button>
						</div>
					</form>
				</div>
			</div>
			<!-- end col -->


		</div>
		<script>
		$(document).ready(function() {
			dulCheck="FASLE";
			
			var auth = ${userVO.auth};
			$('input:radio[name="auth"]:input[value='+auth+']').attr("checked",true);
	
			var section = "${userVO.section_fullcode}";
			
			$("#section_fullcode").val(section);
			
				
			$("#modUser").click(function(){
				
				$("#modUserForm").submit();
				
			});
			
		});
		</script>
		<!-- end row -->
		<%@include file="../include/footer.jsp"%>