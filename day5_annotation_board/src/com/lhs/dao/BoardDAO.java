package com.lhs.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lhs.vo.BoardVO;

@Repository
public class BoardDAO {

	@Autowired
	private SqlSession sqlSession;
	
	public List<BoardVO> selectAllBoards() {
		return sqlSession.selectList("mapper.board.selectAllBoards");
	}

	public int findMaxArticleNo() {
		return (int) sqlSession.selectOne("mapper.board.findMaxArticleNo");
	}

	public int insertBoard(BoardVO boardVO) {
		return sqlSession.insert("mapper.board.insertBoard", boardVO);
	}

	public int deleteBoard(String articleNo) {
		return sqlSession.delete("mapper.board.deleteBoard", articleNo);
	}

}
