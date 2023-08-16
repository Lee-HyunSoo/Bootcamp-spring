package com.lhs.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.lhs.service.BoardService;
import com.lhs.vo.BoardVO;

public class BoardControllerImpl extends MultiActionController implements BoardController {
	
	private BoardService boardService;

	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}

	/*
	 * 1. 게시글들을 가져오기 위해 boardService의 listBoards() 실행
	 * 2. ModelAndView의 model에 boards 저장
	 * 3. ModelAndView의 view에 viewName 저장
	 */
	@Override
	public ModelAndView listBoards(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		List<BoardVO> boards = boardService.listBoards();
		mv.addObject("boards", boards);
		mv.setViewName(getViewName(request));
		return mv;
	}

	/*
	 * 1. MultiActionController의 bind 메서드를 통해 request로 넘어온 파라미터와 BoardVO의 필드 변수명이 일치하면, 데이터 저장
	 * 2. INSERT Query 실행
	 * 3. POST - Redirect - GET 패턴에 따라, POST 요청을 통해 들어온 데이터를 INSERT 후 Redirect  
	 */
	@Override
	public ModelAndView insertBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardVO boardVO = new BoardVO();

		bind(request, boardVO);
		boardService.insertBoard(boardVO);
		return new ModelAndView("redirect:/board/listBoards.do");
	}

	/*
	 * 1. DELETE Query를 실행하는데 필요한 파라미터를 받아와 저장
	 * 2. DELETE Query 실행
	 * 3. POST - Redirect - GET 패턴에 따라, POST 요청을 통해 들어온 데이터를 DELETE 후 Redirect  
	 */
	@Override
	public ModelAndView deleteBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String articleNo = request.getParameter("articleNo");
		boardService.deleteBoard(articleNo);
		return new ModelAndView("redirect:/board/listBoards.do");
	}
	
	/*
	 * 1. 게시글 작성 버튼을 누를 시, 페이지 이동을 위해 GET 방식 요청이 들어옴
	 * 2. 페이지 이동 전, 기존 게시글 번호와 중복되면 안되기 때문에 SELECT Query를 통해 가장 최근 게시글의 번호를 가져와 + 1 
	 * 3. ModelAndView의 model에 가장 최근 게시글의 번호 + 1 을 저장
	 * 4. ModelAndView의 view에 viewName 저장
	 */
	@Override
	public ModelAndView boardForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.addObject("articleNo", boardService.findMaxArticleNo() + 1);
		mv.setViewName(getViewName(request));
		return mv;
	}

	/* 들어온 요청에 따라 jsp의 파일명을 추출 */
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
