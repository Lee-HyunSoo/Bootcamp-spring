package com.lhs.spring.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataAccessException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.lhs.spring.member.service.MemberService;
import com.lhs.spring.member.service.MemberServiceImpl;
import com.lhs.spring.member.vo.MemberVO;

public class MemberControllerImpl extends MultiActionController implements MemberController {
	private MemberService memberService;

	public MemberControllerImpl() {
		System.out.println("public MemberControllerImpl()");
	}

	/*
	 * 1. action-servlet.xml로 부터 service를 주입받는다.
	 * 2. service의 구현체는 action-service.xml에서 주입받는다.
	 */
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	/*
	 * 1. /member/listMembers.do 요청
	 * 2. Service -> DAO를 통해 멤버들 조회
	 * 3. ModelAndView에 조회한 멤버들, viewName(listMembers) 저장
	 */
	@Override
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<MemberVO> membersList = memberService.listMembers();

		ModelAndView mv = new ModelAndView(getViewName(request));
		mv.addObject("membersList", membersList);
		return mv;
	}
	
	/*
	 * 1. /member/memberForm.do 요청
	 * 2. ModelAndView에 viewName(memberForm) 저장
	 * 3. memberForm.jsp로 이동
	 */
	@Override
	public ModelAndView memberForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView(getViewName(request));
		return mv;
	}
	
	/*
	 * 1. /member/addMember.do 요청
	 * 2. INSERT Query를 위한 MemberVO
	 * 3. Service -> DAO를 통해 INSERT 실행
	 * 4. listMembers.do로 redirect
	 */
	@Override
	public void addMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MemberVO memberVO = new MemberVO();
		memberVO.setId(request.getParameter("id"));
		memberVO.setPwd(request.getParameter("pwd"));
		memberVO.setName(request.getParameter("name"));
		memberVO.setEmail(request.getParameter("email"));
		
		memberService.addMember(memberVO);
		response.sendRedirect(request.getContextPath() + "/member/listMembers.do");
	}

	private String getViewName(HttpServletRequest request) throws Exception {
		String contextPath = request.getContextPath();
		String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
		if (uri == null || uri.trim().equals("")) {
			uri = request.getRequestURI();
		}

		int begin = 0;
		if (!((contextPath == null) || ("".equals(contextPath)))) {
			begin = contextPath.length();
		}

		int end;
		if (uri.indexOf(";") != -1) {
			end = uri.indexOf(";");
		} else if (uri.indexOf("?") != -1) {
			end = uri.indexOf("?");
		} else {
			end = uri.length();
		}

		String fileName = uri.substring(begin, end);
		if (fileName.indexOf(".") != -1) {
			fileName = fileName.substring(0, fileName.lastIndexOf("."));
		}
		if (fileName.lastIndexOf("/") != -1) {
			fileName = fileName.substring(fileName.lastIndexOf("/"), fileName.length());
		}
		return fileName;
	}
}
