package com.lhs.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.dao.DataAccessException;

import com.lhs.vo.BoardVO;

public class BoardDAOImpl implements BoardDAO {
	
	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	/* DB에서 게시글들을 SELECT */
	@Override
	public List<BoardVO> selectAllBoards() throws DataAccessException {
		return sqlSession.selectList("mapper.board.selectAllBoards");
	}

	/* INSERT Query 실행 */
	@Override
	public int insertBoard(BoardVO boardVO) throws DataAccessException {
		return sqlSession.insert("mapper.board.insertBoard", boardVO);
	}

	/* DELETE Query 실행 */
	@Override
	public int deleteBoard(String articleNo) throws DataAccessException {
		return sqlSession.delete("mapper.board.deleteBoard", articleNo);
	}

	/* INSERT 시 Primary Key인 articleNo를 집어넣기 위해, 가장 최근 게시글 번호를 가져옴 */
	@Override
	public int findMaxArticleNo() {
		return (int) sqlSession.selectOne("mapper.board.findMaxArticleNo");
	}
	
}
