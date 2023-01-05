package com.letearth.admin.service;

import java.util.List;

import com.letearth.admin.domain.AdminVO;
import com.letearth.prodetail.domain.Criteria;

public interface AdBoardService {
	
	/**
	 * FAQ관련 메서드
	 */
	
	// FAQ 작성
	public void insertFaq(AdminVO vo) throws Exception;
	
	// FAQ 목록(페이징처리-cri)
	public List<AdminVO> getListFaq(Criteria cri) throws Exception;		
	
	// FAQ전체개수
	public int totalFaqCnt() throws Exception;
	
	// FAQ 조회수 1증가
	public void updateViewcnt(Integer not_no) throws Exception;
	
	// FAQ notice_no를 사용한 정보조회
	public AdminVO getFaq(Integer not_no) throws Exception;
	
	// FAQ 수정
	public Integer updateFaq(AdminVO vo) throws Exception;
	
	// FAQ 삭제
	public void deleteFaq(Integer not_no) throws Exception; 
		
	
	
	
	/**
	 * 1:1문의 관련 메서드
	 */
	// 1:1문의 목록(페이징처리-cri)
	public List<AdminVO> adRewriteList(Criteria cri) throws Exception;
	
	// 전체 1:1문의 글 개수
	public int totalRewriteCnt() throws Exception;
	
	// 1:1문의 notice_no를 사용한 정보조회
	public AdminVO getRewrite(Integer not_no) throws Exception;
	
	// 1:1 문의 작성
	public Integer insertRewrite(AdminVO vo) throws Exception;
	
	// 1:1 문의 삭제
	public void deleteRewrite(Integer not_no) throws Exception;  
	
	
}