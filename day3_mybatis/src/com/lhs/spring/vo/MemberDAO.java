package com.lhs.spring.vo;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MemberDAO {

	public static SqlSessionFactory sqlMapper = null;
	
	public MemberDAO() {
	}
	
	private static SqlSessionFactory getInstance() {
		if (sqlMapper == null) {
			try {
				String resource = "mybatis/SqlMapConfig.xml";
				Reader reader = Resources.getResourceAsReader(resource);
				sqlMapper = new SqlSessionFactoryBuilder().build(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sqlMapper;
	}

	public List<MemberVO> selectAllMembers() {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		
		return session.selectList("mapper.member.selectAllMemberList");
	}
	
	public void addMembers(MemberVO memberVO) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		session.insert("mapper.member.addMember", memberVO);
		session.commit();
	}
	
	

}
