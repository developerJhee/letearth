package com.letearth.project.service;

import java.util.List;

import com.letearth.project.domain.ProjectVO;
import com.letearth.project.domain.RewardVO;

public interface ProjectService {
	
	/**
	 * 프로젝트 번호 생성
	 */
	public void insertProNo(ProjectVO proVo) throws Exception;
	
	/**
	 * 프로젝트 기본정보 등록
	 */
	public void updatePro_basicInfo(ProjectVO proVo) throws Exception;
	
	/**
	 * 프로젝트 일정 등록
	 */
	public void updatePlan(ProjectVO proVo) throws Exception;
	/**
	 * 리워드 생성
	 */
	public void createReward(RewardVO rewVO) throws Exception;
	
	/**
	 * 리워드 불러오기
	 */
	public List<RewardVO> getReward(RewardVO rewVO) throws Exception;
	
	/**
	 * 리워드 삭제
	 */
	public void deleteReward(RewardVO rewVO) throws Exception;
	
	/**
	 * 스토리 업데이트
	 */
	public void updateStory(ProjectVO proVO);
	
	/**
	 * 정책 업데이트
	 */
	public void updatePolicy(ProjectVO proVO);
	
	/**
	 * 대표자 및 정산 정보 등록
	 */
	public void updatePartner(ProjectVO proVO);

}