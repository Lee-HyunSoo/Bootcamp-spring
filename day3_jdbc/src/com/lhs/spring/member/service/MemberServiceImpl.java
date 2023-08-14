package com.lhs.spring.member.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.lhs.spring.member.dao.MemberDAO;
import com.lhs.spring.member.vo.MemberVO;

public class MemberServiceImpl implements MemberService {
	
	private MemberDAO memberDAO;

	public MemberServiceImpl() {
		System.out.println("public MemberServiceImpl()");
	}
	
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
		System.out.println("this.memberDAO = memberDAO;");
	}

	@Override
	public List<MemberVO> listMembers() throws DataAccessException {
		System.out.println("public List listMembers() throws DataAccessException");
		
		List<MemberVO> membersList = null;
		membersList = memberDAO.selectAllMembers();
		
		return membersList;
	}
	
	@Override
	public void addMember(MemberVO memberVO) {
		memberDAO.addMember(memberVO);
	}
	
	

}
