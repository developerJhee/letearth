<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri ="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" href="../resources/assets/css/project.css">
<script src="../resources/assets/js/vendor/jquery-3.5.1.min.js"></script>
<style>
#rangeGraph{
    height:10px;
    background:#e9e9ee;
}

#rangeGraph span{
    height:100%;
    width:0%;
    background:#29f0b4;
}
.user {
	background: #674df0;
    line-height: 30px;
    padding: 0 15px;
    font-size: 11px;
    font-weight: 500;
    text-transform: uppercase;
    letter-spacing: 3px;
    color: #fff;
    margin-right: 10px;
}
</style>

<!-- 탭1 : 글 페이지 -->
	<div class="project-details-content-top">
		<!-- 프로젝트 시작 -->
		<div class="row justify-content-center">
			<c:forEach var="pro" items="${proList }" >
				<div class="col-lg-4 col-md-6 col-sm-9">
					<div class="explore-projects-item mt-30">
						<div class="explore-projects-thumb">
							<img src="${pro.pro_thum }" alt="LetEarth" width="100px;" height="300px;"> 
							<a href="/prodetail/info?pro_no=${pro.pro_no}"> <i class="fa fa-heart"></i>
							</a>
						</div>
						<div class="explore-projects-content">
							<div class="item d-flex align-items-center">
								<span>D - ${pro.left_date}</span>
							</div>  <br>
							<a href="/prodetail/info?pro_no=${pro.pro_no}">
								<h3>${pro.pro_title }</h3>
							</a>

							<div class="projects-range">
								<div class="projects-range-content">
									<ul>
										<li>달성률:</li>
										<li id="rate">${pro.achievement_rate}%</li>
									</ul>
									<div id="rangeGraph">
										<span></span>
									</div>
								</div>
							</div>
							<div class="projects-goal">
								달성 금액: <span><fmt:formatNumber value="${pro.pro_tp }" pattern="#,###" /></span> 원 <br>
								목표 금액: <span><fmt:formatNumber value="${pro.pro_gp }" pattern="#,###" /></span> 원
							</div> <br>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<!-- 프로젝트 끝 -->
	</div>
<!-- 탭1 -->