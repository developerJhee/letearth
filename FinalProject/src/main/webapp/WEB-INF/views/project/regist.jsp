<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 헤더 -->
<%@ include file="../include/header.jsp" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.js"></script>
<script src="../resources/assets/js/vendor/jquery-3.5.1.min.js"></script>
<link rel="stylesheet" href="../resources/assets/css/project.css">

<script type="text/javascript">
function createReward(seq){
	Swal.fire({
	  title: '저장하시겠습니까?',
	  icon: 'info',
	  showCancelButton: true,
	  confirmButtonColor: '#3085d6',
	  cancelButtonColor: 'grey',
	  confirmButtonText: '등록',
	  cancelButtonText: '취소'
	}).then((result) => {
	  if (result.value) {
          //"등록" 버튼을 눌렀을 때 작업할 내용을 이곳에 넣어주면 된다. 
	 		if($('input:radio[name=par_cat]').is(':checked') == false){
	 		    Swal.fire({
	 		    	icon: 'error',
					text: '구분을 선택해주세요'
				})
	 			document.fr.par_cat.focus();
	 			return;
	 		}
// 	 		if($('#scan').val() == ""){
// 	 		    Swal.fire({
// 	 		    	icon: 'error',
// 					text: '사진을 입력해주세요'
// 				})
// 	 			document.fr.scan.focus();
// 	 			return;
// 	 		}
	 		if(document.fr.par_intro.value == ""){
	 			Swal.fire({
	 		    	icon: 'error',
					text: '판매자 소개를 입력해주세요'
				})
	 			document.fr.par_intro.focus();
	 			return;
	 		}
	 		if(document.fr.par_acc_name.value == ""){
	 			Swal.fire({
	 		    	icon: 'error',
					text: '예금주명을 입력해주세요'
				})
	 			document.fr.par_acc_name.focus();
	 			return;
	 		}
	 		if(document.fr.par_acc.value == ""){
	 			Swal.fire({
	 		    	icon: 'error',
					text: '계좌번호를 입력해주세요'
				})
	 			document.fr.par_acc.focus();
	 			return;
	 		}
	        fr.submit();
	 	 }
	})
}
</script>

<script type="text/javascript">
$(document).ready(function(){
	
    $('#scan').hide();   // 초깃값 설정
    $('#scan2').hide();   // 초깃값 설정
    $('#com_num').hide();
    $('#birth').hide();
		
	$("input[name='par_cat']").change(function(){
		if($("input[name='par_cat']:checked").val() == '개인'){
			$('#scan2').hide();
			$('#com_num').hide();
			$('#scan').show();
			$('#birth').show();
		}	
		else if($("input[name='par_cat']:checked").val() == '사업자'){
			$('#scan').hide();
			$('#birth').hide();
			$('#scan2').show();
			$('#com_num').show();
		}

	});
		
});
</script>

    <!--====== PAGE TITLE PART START ======-->
    
    <section class="page-title-area">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div>
                        <h3 class="title" style="color: black; font-size: 3em">6. 대표자 및 정산 정보</h3>
                    </div>
                </div>
            </div>
        </div>
    </section>
    
    <!--====== PAGE TITLE PART ENDS ======-->
    
	<!--====== PROJECT CONTENT PART START ====== -->
    <section class="project-details-content-area pb-110">
        <div class="container">
            <div class="row justify-content-center">
            
        		<!-- 사이드 바 -->
				<%@ include file="../include/proSidebar.jsp" %>
				<!-- 사이드 바 -->
            	
                <div class="col-lg-8">
                <form action="/project/regist" method="post" name="fr">
                    <div class="tab-content" id="pills-tabContent">
                        <div class="tab-pane fade show active" id="pills-home" role="tabpanel" aria-labelledby="pills-home-tab">
                            <div class="project-details-content-top">
	                           	 <div class="col-lg-4 col-md-7 col-sm-9">
	                           	 </div>
                            </div>
                            <div class="project-details-item">
                            	
								<!-- 개인 선택하면 주민등록증 스캔해서 올리기, 사업자 클릭하면 사업자 등록증 클릭해서 올리기 -->
                            	<p style="font-size: 2.5em;">판매자 구분</p>
                            	<div class="select">
	                                <input type="radio" id="select" name="par_cat" value="개인"><label for="select">개인</label>
	                                <input type="radio" id="select2" name="par_cat" value="사업자"><label for="select2">사업자</label>
                                </div>
                                
                                <label class="btn btn-primary btn-lg" for="input-file" id="scan">
									주민등록증 추가
				  				</label>
	                           	<div style="display:none">
									<input type="file" id="input-file" name="par_scan"/> <br><br>
             					</div>
             					
             					<label class="btn btn-primary btn-lg" for="input-file" id="scan2">
									사업자 등록증 추가
				  				</label>
	                           	<div style="display:none">
									<input type="file" id="input-file" name="par_scan"/> <br><br>
             					</div>
             					
	                            <p style="font-size: 2.5em;">프로필 이미지</p>
                                <h5 class="title">창작자 개인이나 팀의 사진을 올려주세요</h5> <br>
	                           	<label class="btn btn-primary btn-lg" for="input-file">
									사진 업로드 @@@@@@ 멤버 테이블로 넣어야함
				  				</label>
	                           	<div style="display:none">
									<input type="file" id="input-file" name=""/> <br><br>
             					</div>
             	
								<p style="font-size: 2.5em;">판매자 소개</p>
                                <h5 class="title">2~3문장으로 판매자님의 이력과 간단한 소개를 써주세요.</h5> <br>
                                <div id="intro_div">
                                <textarea name="par_intro" style="resize: none;" id="intro" placeholder="내용을 입력해주세요." class="textArea"></textarea>
								</div>
								
								<p style="font-size: 2.5em;">입금 계좌</p>
                                <h5 class="title">후원금을 전달받을 계좌를 등록해주세요. <br> 법인사업자는 법인계좌로만 정산받을 수 있습니다.</h5> <br>
                                <fieldset>
                                <div id="birth">
	                                예금주 생년월일 <br>
									<div class="step-body">
										<input type="date" class="textBox" name="par_birth" value="">
									</div>
                                </div>
                                <div id="com_num">
	                                사업자 등록번호 <br>
	                                <input type="text" name="par_com_num" class="textBox" datetimeonly="true" > <br>
                                </div>
                                거래 은행 <br>
								<select name="par_bank" class="textBox">
								    <option value=''>-선택-</option>
								    <option value='SC제일은행'>SC제일은행</option>
								    <option value='경남은행'>경남은행</option>
								    <option value='광주은행'>광주은행</option>
								    <option value='국민은행'>국민은행</option>
								    <option value='굿모닝신한증권'>굿모닝신한증권</option>
								    <option value='기업은행'>기업은행</option>
								    <option value='농협중앙회'>농협중앙회</option>
								    <option value='농협회원조합'>농협회원조합</option>
								    <option value='대구은행'>대구은행</option>
								    <option value='대신증권'>대신증권</option>
								    <option value='대우증권'>대우증권</option>
								    <option value='동부증권'>동부증권</option>
								    <option value='동양종합금융증권'>동양종합금융증권</option>
								    <option value='메리츠증권'>메리츠증권</option>
								    <option value='미래에셋증권'>미래에셋증권</option>
								    <option value='뱅크오브아메리카(BOA)'>뱅크오브아메리카(BOA)</option>
								    <option value='부국증권'>부국증권</option>
								    <option value='부산은행'>부산은행</option>
								    <option value='산림조합중앙회'>산림조합중앙회</option>
								    <option value='산업은행'>산업은행</option>
								    <option value='삼성증권'>삼성증권</option>
								    <option value='상호신용금고'>상호신용금고</option>
								    <option value='새마을금고'>새마을금고</option>
								    <option value='수출입은행'>수출입은행</option>
								    <option value='수협중앙회'>수협중앙회</option>
								    <option value='신영증권'>신영증권</option>
								    <option value='신한은행'>신한은행</option>
								    <option value='신협중앙회'>신협중앙회</option>
								    <option value='에스케이증권'>에스케이증권</option>
								    <option value='에이치엠씨투자증권'>에이치엠씨투자증권</option>
								    <option value='엔에이치투자증권'>엔에이치투자증권</option>
								    <option value='엘아이지투자증권'>엘아이지투자증권</option>
								    <option value='외환은행'>외환은행</option>
								    <option value='우리은행'>우리은행</option>
								    <option value='우리투자증권'>우리투자증권</option>
								    <option value='우체국'>우체국</option>
								    <option value='유진투자증권'>유진투자증권</option>
								    <option value='전북은행'>전북은행</option>
								    <option value='제주은행'>제주은행</option>
								    <option value='키움증권'>키움증권</option>
								    <option value='하나대투증권'>하나대투증권</option>
									<option value='하나은행'>하나은행</option>
								    <option value='하이투자증권'>하이투자증권</option>
								    <option value='한국씨티은행'>한국씨티은행</option>
								    <option value='한국투자증권'>한국투자증권</option>
								    <option value='한화증권'>한화증권</option>
								    <option value='현대증권'>현대증권</option>
								    <option value='홍콩상하이은행'>홍콩상하이은행</option>
							    </select> <br>
			                    예금주명 <br>
                                <input type="text" name="par_acc_name" class="textBox"> <br>
                                계좌 번호 <br>
                                <input type="text" name="par_acc" class="textBox"> <br>
                                </fieldset>
                                
                            </div>
                    	</div>
                	</div>
					</form>
					<button type="button" class="main-btn" style="float: right;" onclick="return createReward();">다음</button>
                       
            	</div>
			</div>
    	</div>
    </section>
    
<script type="text/javascript">
// 글자수 제어 1
$(document).ready(function() {
	$('#intro').one('click',function(){
		$('#intro_div').append('<div id="intro_cnt">ㅤ(0 / 200)</div>');
	});
	
    $('#intro').on('keyup', function() {
        $('#intro_cnt').html("("+$(this).val().length+" / 200)");
 
        if($(this).val().length > 200) {
            $(this).val($(this).val().substring(0, 200));
            $('#intro_cnt').html("(200 / 200");
        }
    });
});
</script>




<!-- 푸터 -->
<%@ include file="../include/footer.jsp" %>