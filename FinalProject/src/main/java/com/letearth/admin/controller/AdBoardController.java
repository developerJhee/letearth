package com.letearth.admin.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.letearth.admin.domain.AdminVO;
import com.letearth.prodetail.domain.Criteria;
import com.letearth.prodetail.domain.PageVO;
import com.letearth.admin.service.AdBoardService;


@Controller
@RequestMapping("/board/*")
public class AdBoardController {

	private static final Logger mylog 
			= LoggerFactory.getLogger(AdBoardController.class);
	
	@Inject
	private AdBoardService adBoardService;
	
	
	// http://localhost:8080/board/adMain
	
	/**
	 * 관리자메인 GET
	 */
	@RequestMapping(value = "/adMain", method = RequestMethod.GET)
	public void adMainGET() throws Exception{
		mylog.debug("관리자메인페이지이동");
	}
	
	
	/**
	 * FAQ 글쓰기 GET/POST
	 */
	// GET
	@RequestMapping(value = "/adFaqRegist", method = RequestMethod.GET)
	public void adFaqRegistGET() throws Exception{
		mylog.debug("/board/adFaqRegist(GET) 호출 -> 페이지 이동 완 ");
	}
	
	// GET2
	@RequestMapping(value = "/adFaqRegist2", method = RequestMethod.GET)
	public void adFaqRegist2GET() throws Exception{
		mylog.debug("/board/adFaqRegist(GET) 호출 -> 페이지 이동 완 ");
	}
	
	// POST
	@RequestMapping(value = "/adFaqRegist", method = RequestMethod.POST)
	public String adFaqRegistPOST(AdminVO vo, RedirectAttributes rttr) throws Exception{
		mylog.debug("/board/adFaqRegist(POST) 호출 ");
		
		// 전달정보 저장 확인
		mylog.debug(vo+"");
		
		// 서비스
		adBoardService.insertFaq(vo);
		mylog.debug("게시판 글쓰기 완료!");
		
		// 페이지 이동
		rttr.addFlashAttribute("result", "등록완");
		
		return "redirect:/board/adFaqList";
	}
	
	
	
	
	/**
	 * FAQ 리스트 GET/POST - 페이징처리
	 */
	// http://localhost:8080/board/adFaqList?page=1&perPageNum=10
	// GET
	@RequestMapping(value = "/adFaqList",method = RequestMethod.GET)
	public String adFaqListGET(Criteria cri,HttpSession session,Model model) throws Exception{
		
		session.setAttribute("updateCheck", true); // => 세션 존재하면 true
		
		List<AdminVO> adFaqList = adBoardService.getListFaq(cri);
		
		// 페이징처리 하단부 정보 준비
		PageVO pvo = new PageVO();
		pvo.setCri(cri);
		mylog.debug("totalFaqCnt : " + adBoardService.totalFaqCnt());
		pvo.setTotalCount(adBoardService.totalFaqCnt()); // 작성되어있는 글 전체 개수
		
		model.addAttribute("pvo", pvo);
		
		// 연결되어 있는 뷰페이지로 정보 전달 (Model 객체)
		model.addAttribute("adFaqList", adFaqList);
		
		// 페이지 이동(/board/adFaqList)	
		return "/board/adFaqList";
		
	}
		
	
	/**
	 * FAQ 본문보기 GET
	 */
	// GET
	@RequestMapping(value = "/adFaqRead", method = RequestMethod.GET)
	public void adFaqReadGET(Model model, @RequestParam("not_no") int not_no, HttpSession session) throws Exception{
		
		// 세션객체 (for 조회수)
		boolean isUpdateCheck = (boolean) session.getAttribute("updateCheck");
		
		if(isUpdateCheck) { //= true
			// 조회수 1증가(list -> read 증가o, F5증가X)
			adBoardService.updateViewcnt(not_no);
			mylog.debug("조회수 1증가" );
			
			// 상태변경 (true -> false);
			session.setAttribute("updateCheck", !isUpdateCheck);				
		}
		
		// 서비스 -> DAO (특정 글번호에 해당하는 정보 가져오기)
		AdminVO vo = adBoardService.getFaq(not_no);
				
		// 연결된 뷰페이지로 정보 전달		
		model.addAttribute("vo", vo);
		
	}
	
	
	
	
	/**
	 * FAQ 글수정 GET/POST
	 */
	// GET
	@RequestMapping(value = "/adFaqModify", method = RequestMethod.GET)
	public void adFaqModifyGET(Model model, int not_no) throws Exception{
		
		// model객체를 사용해서 뷰페이지로 정보 전달
		model.addAttribute("vo", adBoardService.getFaq(not_no));
	}
	
	// POST
	@RequestMapping(value = "/adFaqModify", method = RequestMethod.POST)
	public String adFaqModifyPOST(AdminVO vo, RedirectAttributes rttr) throws Exception{
		
		Integer result = adBoardService.updateFaq(vo);
		
		if(result > 0) {			
			rttr.addFlashAttribute("result", "수정완");
		}
		
		return "redirect:/board/adFaqList";
	}
	
	
	
	
	/**
	 * FAQ 글삭제 GET/POST
	 */
	// POST
	@RequestMapping(value = "/adFaqRemove", method = RequestMethod.POST)
	public String adFaqRemovePOST(int not_no, RedirectAttributes rttr) throws Exception{
		
		// 서비스
		adBoardService.deleteFaq(not_no);
		
		// "삭제완료" 정보를 list 페이지로 전달
		rttr.addFlashAttribute("result", "삭제완");
		
		// 게시판 리스트로 이동(/board/adFaqList)		
		return "redirect:/board/adFaqList";
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	/**
	 * 1:1문의 리스트 GET/POST
	 */
	// http://localhost:8080/board/adRewriteList?page=1&perPageNum=10
	// GET
	@RequestMapping(value = "/adRewriteList",method = RequestMethod.GET)
	public String adRewriteListGET(Criteria cri,HttpSession session,Model model) throws Exception{
		
		session.setAttribute("updateCheck", true); // => 세션 존재하면 true
		
		List<AdminVO> adRewriteList = adBoardService.adRewriteList(cri);
		
		// 페이징처리 하단부 정보 준비
		PageVO pvo = new PageVO();
		pvo.setCri(cri);
		mylog.debug("totalRewriteCnt : " + adBoardService.totalRewriteCnt());
		pvo.setTotalCount(adBoardService.totalRewriteCnt()); // 작성되어있는 글 전체 개수
		
		model.addAttribute("pvo", pvo);
		
		// 연결되어 있는 뷰페이지로 정보 전달 (Model 객체)
		model.addAttribute("adRewriteList", adRewriteList);
		
		// 페이지 이동(/board/adRewriteList)	
		return "/board/adRewriteList";
		
	}
	
	
	/**
	 * 1:1문의 조회 GET
	 */
	// GET
	@RequestMapping(value = "/adRewriteRead", method = RequestMethod.GET)
	public void adRereplyReadGET(Model model, @RequestParam("not_no") int not_no, HttpSession session) throws Exception{
		
		// 세션객체 (for 조회수)
		boolean isUpdateCheck = (boolean) session.getAttribute("updateCheck");
		
		if(isUpdateCheck) { //= true
			// 조회수 1증가(list -> read 증가o, F5증가X)
			adBoardService.updateViewcnt(not_no);
			mylog.debug("조회수 1증가" );
			
			// 상태변경 (true -> false);
			session.setAttribute("updateCheck", !isUpdateCheck);				
		}
		
		// 서비스 -> DAO (특정 글번호에 해당하는 정보 가져오기)
		AdminVO vo = adBoardService.getRewrite(not_no);
				
		// 연결된 뷰페이지로 정보 전달		
		model.addAttribute("vo", vo);
		
	}
	
	
	/**
	 * 1:1문의 답글작성 GET/POST
	 */
	// GET
		@RequestMapping(value = "/adRewriteRegist", method = RequestMethod.GET)
		public void adRewriteRegistGET() throws Exception{
			
			mylog.debug("/board/adRewriteRegistGET 호출 -> 페이지 이동 완 ");
		}
		
		// POST
		@RequestMapping(value = "/adRewriteRegist", method = RequestMethod.POST)
		public String adRewriteRegistPOST(AdminVO vo, RedirectAttributes rttr) throws Exception{
			mylog.debug("/board/adRewriteRegistPOST 호출 ");
			
			// 전달정보 저장 확인
			mylog.debug(vo+"");

			// 서비스
			Integer result = adBoardService.insertRewrite(vo);
			mylog.debug("답글 완료!");
			
			if(result > 0) {			
				rttr.addFlashAttribute("result", "답글완");
			}
		
			return "redirect:/board/adRewriteList";
		}
	
		
		
	/**
	 * 1:1문의 답글삭제 GET/POST
	 */
	// POST
	@RequestMapping(value = "/adRewriteRemove", method = RequestMethod.GET)
	public String adRewriteRemovePOST(int not_no, RedirectAttributes rttr) throws Exception{
		
		// 서비스
		adBoardService.deleteRewrite(not_no);
		
		// "삭제완료" 정보를 list 페이지로 전달
		rttr.addFlashAttribute("result", "삭제완");
		
		// 게시판 리스트로 이동(/board/adFaqList)		
		return "redirect:/board/adRewriteList";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
