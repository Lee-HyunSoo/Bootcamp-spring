package com.freeflux.spring.ex01;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("mainController") // xml에서 등록하던 id명
/* DefaultAnnotationHandlerMapping을 통해 class level에서 annotation을 사용 */
@RequestMapping("/test") // 요청 url
public class MainController {

	/* AnnotationMethodHandlerAdapter를 통해 method level에서 annotation을 사용 */
	/* GET 방식 요청일 때 해당 method 호출 */
	@RequestMapping(value = "/main1.do", method = RequestMethod.GET)
	public ModelAndView main1(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", "main1");
		mav.setViewName("main");
		return mav;
	}

	@RequestMapping(value = "/main2.do", method = RequestMethod.GET)
	public ModelAndView main2(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", "main2");
		mav.setViewName("main");
		return mav;
	}
}
