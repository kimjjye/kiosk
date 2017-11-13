<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>
<%@include file="../include/header.jsp"%>
<!-- Main content -->
<section class="wrapper">
	<div class="container">
		<!-- Page-Title -->
		<div class="row">
			<div class="col-sm-12">
				<div class="page-title-box">
					<div class="btn-group pull-right">
						<ol class="breadcrumb hide-phone p-0 m-0">
							<li><a href="#">디지털조직도</a></li>
							<li><a href="#">갤러리</a></li>
							<li class="active">갤러리조회</li>
						</ol>
					</div>
					<h4 class="page-title">갤러리조회</h4>
				</div>
			</div>
		</div>
		<!-- end page title end breadcrumb -->

		<c:forEach items="${list}" var="com_imageVO">
			<div class="col-md-4 col-sm-6">
				<div class="property-card">
					<div class="property-image"
						style="background: url('/resources/assets/images/properties/6.jpg') center center/cover no-repeat;">
					</div>

					<div class="property-content">
						<div class="listingInfo">
							<p></p>
							<div class="">

								<h3 class="text-overflow">
									<a href="/galleryboard/readPage?img_no=${com_imageVO.img_no}"
										class="text-dark">${com_imageVO.img_title}</a>
								</h3>

								<div class="m-t-20" style="text-align: center">
									<button type="button"
										class="btn btn-custom btn-inline waves-effect waves-light"
										onclick="location.href='/galleryboard/modifyPage?img_no=${com_imageVO.img_no}'">정보수정</button>
									<form action="/galleryboard/removePage?img_no=${com_imageVO.img_no }" method="post">
										<button type="submit"
											class="btn btn-danger btn-inline waves-effect waves-light">
											<i class="glyphicon glyphicon-trash"></i>삭제
										</button>
									</form>
								</div>

							</div>
						</div>
						<!-- end. Card actions -->
					</div>
					<!-- /inner row -->
				</div>
				<!-- End property item -->
			</div>
		</c:forEach>

		<!-- end col -->
		<div class="text-right">
			<ul class="pagination pagination-split m-t-0">
				<li class="disabled"><a href="#"><i
						class="fa fa-angle-left"></i></a></li>
				<c:if test="${pageMaker.prev}">
					<li><a
						href="list${pageMaker.makeSearch(pageMaker.startPage - 1) }">&laquo;</a></li>
				</c:if>

				<c:forEach begin="${pageMaker.startPage }"
					end="${pageMaker.endPage }" var="idx">
					<li
						<c:out value="${pageMaker.cri.page == idx?'class =active':''}"/>>
						<a href="list${pageMaker.makeSearch(idx)}">${idx}</a>
					</li>
				</c:forEach>

				<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
					<li><a
						href="list${pageMaker.makeSearch(pageMaker.endPage +1) }">&raquo;</a></li>
				</c:if>
				<li><a href="#"><i class="fa fa-angle-right"></i></a></li>

			</ul>
		</div>
		<!-- end row -->
	</div>



</section>
<%@include file="../include/footer.jsp"%>