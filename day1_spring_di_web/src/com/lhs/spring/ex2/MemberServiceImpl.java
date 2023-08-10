package com.lhs.spring.ex2;

public class MemberServiceImpl implements MemberService {

	private MemberDAO memberDAO;
	
	public MemberServiceImpl() {
	}
	
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	public void listMembers() {
		memberDAO.listMembers();
	}

}
