package com.lhs.spring.ex1;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class UserController extends MultiActionController {

	public UserController() {
	}

	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ModelAndView mv = new ModelAndView();
		mv.addObject("userID", request.getParameter("userID"));
		mv.addObject("passwd", request.getParameter("passwd"));
		mv.setViewName("result");
		return mv;	
	}
	
	public ModelAndView memberInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ModelAndView mv = new ModelAndView();
		mv.addObject("id", request.getParameter("id"));
		mv.addObject("pwd", request.getParameter("pwd"));
		mv.addObject("name", request.getParameter("name"));
		mv.addObject("email", request.getParameter("email"));
		mv.setViewName(getViewName(request));
		return mv;
	}

	private String getViewName(HttpServletRequest request) throws Exception {
		/* 패키지 이름을 가져오는 부분 */
		String contextPath = request.getContextPath();
		/* contextPath : /day2_mvc_multi */
		System.out.println("contextPath : " + contextPath);
        String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
        if (uri == null || uri.trim().equals("")) {
        	/* 요청 URI를 가져온다. : /day2_mvc_multi/test/memberInfo.do */
            uri = request.getRequestURI();
            /* uri = request.getRequestURI() : /day2_mvc_multi/test/memberInfo.do */
            System.out.println("uri = request.getRequestURI() : " + uri);
        }
 
        int begin = 0;
        /* 가져온 contextPath가 null이나 공백이 아니라면 */
        if (!((contextPath == null) || ("".equals(contextPath)))) {
        	/* begin에 contextPath의 길이를 저장 */ 
            begin = contextPath.length();
            /* begin = contextPath.length() : 15 */
            /* day2_mvc_multi/test/memberInfo.do에서 memberInfo를 추출하기 위한 subString의 startIndex  */
            System.out.println("begin = contextPath.length() : " + begin);
        }
 
        int end;
        /* uri에 ; 나 ? 가 포함되어있을 경우를 위해. 즉, jsessionid; 나 쿼리 파라미터를 사용하는 경우를 대비 */
        if (uri.indexOf(";") != -1) {
            end = uri.indexOf(";");
        } 
        else if (uri.indexOf("?") != -1) {
            end = uri.indexOf("?");
        } 
        else {
            end = uri.length();
        }
        
        /* end = uri.length() : 34 */
        /* /day2_mvc_multi/test/memberInfo.do에서 memberInfo를 추출하기 위한 subString의 lastIndex */
        System.out.println("end = uri.length() : " + end);
 
        /* /day2_mvc_multi/test/memberInfo.do를 /test/memberInfo.do로 subString */
        String viewName = uri.substring(begin, end);
        System.out.println("viewName : " + viewName);
        
        /* .do로 끝나는 경우 .do를 잘라내기 위해 */
        if (viewName.indexOf(".") != -1) {
            viewName = viewName.substring(0, viewName.lastIndexOf("."));   
            /* viewName1 : /test/memberInfo */
            System.out.println("viewName1 : " + viewName);
        }
        /* /test/memberInfo에서 memberInfo를 추출하기 위해 */
        if (viewName.lastIndexOf("/") != -1) {
            viewName = viewName.substring(viewName.lastIndexOf("/") + 1, viewName.length());
            /* viewName2 : memberInfo */
            System.out.println("viewName2 : " + viewName);
        }
        
		return viewName;
	}
	
}
