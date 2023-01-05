package com.letearth.admin.persistence;

import java.util.List;

import com.letearth.admin.domain.AdminVO;
import com.letearth.prodetail.domain.Criteria;
import com.letearth.member.domain.MemberVO;
import com.letearth.project.domain.ProjectVO;

public interface AdReportDAO {
	
	/**
	 * 블랙리스트 관련 메서드
	 */
	
	// 블랙리스트 처리(회원)
	public Integer updateBlackMem(AdminVO vo) throws Exception;
	
	// 블랙리스트 처리(프로젝트)
	public Integer updateBlackPro(AdminVO vo) throws Exception;
	
	// 블랙리스트 목록
	public List<AdminVO> getListBlack(Criteria cri) throws Exception;
	
	// 블랙리스트 전체개수
	public int totalBlackCnt() throws Exception;
	
	
	/**
	 * 신고관련 메서드 
	 */
	// 신고접수 목록
	public List<AdminVO> getListReport(Criteria cri) throws Exception;
	
	// 신고접수 전체개수
	public int totalRepCnt() throws Exception;
	
	// 신고상세
	public AdminVO getReport(Integer rep_no) throws Exception;
	
}