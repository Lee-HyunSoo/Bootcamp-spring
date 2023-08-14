package com.lhs.spring.vo;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/*")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final MemberDAO memberDAO = new MemberDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* pathInfo 추출 */
		String path = request.getPathInfo();
		
		/* GET 방식으로 들어온 요청 */
		if (path.equals("/mem.do"))
			members(request, response);
		else if (path.equals("/memberForm.do"))
			memberForm(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		/* pathInfo 추출 */
		String path = request.getPathInfo();
		
		/* POST 방식으로 들어온 요청 */
		if (path.equals("/addMember.do"))
			addMember(request, response);
	}
	
	/* mem.do 요청이 들어올 시, DB에서 Member 정보 SELECT */
	private void members(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<MemberVO> members = memberDAO.selectAllMembers();
		
		request.setAttribute("members", members);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/test/listMembers.jsp");
		requestDispatcher.forward(request, response);
	}
	
	/* memberForm.do 요청이 들어올 시(회원가입 버튼을 누를 시), memberForm.jsp로 forward  */
	private void memberForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/test/memberForm.jsp");
		requestDispatcher.forward(request, response);
	}
	
	/* addMember.do 요청이 들어올 시(가입하기 버튼을 누를 시), DB INSERT 후 Redirect */
	private void addMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberVO memberVO = new MemberVO();
		memberVO.setId(request.getParameter("id"));
		memberVO.setPwd(request.getParameter("pwd"));
		memberVO.setName(request.getParameter("name"));
		memberVO.setEmail(request.getParameter("email"));
		memberDAO.addMembers(memberVO);
		
		response.sendRedirect(request.getContextPath() + "/member/mem.do");
	}

}
