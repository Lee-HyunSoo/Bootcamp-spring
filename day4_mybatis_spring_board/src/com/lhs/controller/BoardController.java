package com.lhs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface BoardController {
	public ModelAndView listBoards(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView insertBoard(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView deleteBoard(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView boardForm(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
