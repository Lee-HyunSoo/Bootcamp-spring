package com.lhs.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lhs.service.BoardService;
import com.lhs.vo.BoardVO;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;

	@RequestMapping(value = "/listBoards.do", method = RequestMethod.GET)
	public String listBoards(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		List<BoardVO> boards = boardService.listBoards();
		model.addAttribute("boards", boards);
		return "listBoards";
	}

	@RequestMapping(value = "/boardForm.do", method = RequestMethod.GET)
	public String boardForm(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		model.addAttribute("articleNo", boardService.findMaxArticleNo() + 1);
		return "boardForm";
	}

	@RequestMapping(value = "/insertBoard.do", method = RequestMethod.POST)
	public String insertBoard(@ModelAttribute BoardVO boardVO, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		boardService.insertBoard(boardVO);
		return "redirect:/board/listBoards.do";
	}

	@RequestMapping(value = "/deleteBoard.do", method = RequestMethod.GET)
	public String deleteBoard(@RequestParam String articleNo, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		boardService.deleteBoard(articleNo);
		return "redirect:/board/listBoards.do";
	}

}
