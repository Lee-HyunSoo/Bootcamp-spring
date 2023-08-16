package com.lhs.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.lhs.dao.BoardDAO;
import com.lhs.vo.BoardVO;

public class BoardServiceImpl implements BoardService {
	
	private BoardDAO boardDAO;

	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}

	@Override
	public List<BoardVO> listBoards() throws DataAccessException {
		return boardDAO.selectAllBoards();
	}

	@Override
	public int insertBoard(BoardVO boardVO) throws DataAccessException {
		return boardDAO.insertBoard(boardVO);
	}

	@Override
	public int deleteBoard(String articleNo) throws DataAccessException {
		return boardDAO.deleteBoard(articleNo);
	}

	@Override
	public int findMaxArticleNo() {
		return boardDAO.findMaxArticleNo();
	}

}
