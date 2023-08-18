package com.lhs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhs.dao.BoardDAO;
import com.lhs.vo.BoardVO;

@Service
public class BoardService {

	@Autowired
	private BoardDAO boardDAO;

	public List<BoardVO> listBoards() {
		return boardDAO.selectAllBoards();
	}

	public int findMaxArticleNo() {
		return boardDAO.findMaxArticleNo();
	}

	public int insertBoard(BoardVO boardVO) {
		return boardDAO.insertBoard(boardVO);
		
	}

	public int deleteBoard(String articleNo) {
		return boardDAO.deleteBoard(articleNo);	
	}

}
