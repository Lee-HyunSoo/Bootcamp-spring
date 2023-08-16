package com.lhs.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.lhs.vo.BoardVO;

public interface BoardService {
	 public List<BoardVO> listBoards() throws DataAccessException;
	 public int insertBoard(BoardVO boardVO) throws DataAccessException;
	 public int deleteBoard(String articleNo) throws DataAccessException;
	 public int findMaxArticleNo();

}
