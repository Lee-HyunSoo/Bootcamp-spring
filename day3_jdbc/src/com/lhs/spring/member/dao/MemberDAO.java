package com.lhs.spring.member.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.lhs.spring.member.vo.MemberVO;

public interface MemberDAO {
	public List<MemberVO> selectAllMembers() throws DataAccessException ;
	public void addMember(MemberVO memberVO) throws DataAccessException ;
}
