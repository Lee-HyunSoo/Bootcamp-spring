package com.lhs.spring.member.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.lhs.spring.member.vo.MemberVO;

public class MemberDAOImpl implements MemberDAO {

	private JdbcTemplate jdbcTemplate;

	public MemberDAOImpl() {
		System.out.println("public MemberDAOImpl()");
	}

	@Override
	public List<MemberVO> selectAllMembers() throws DataAccessException {
		String query = "select id,pwd,name,email,joinDate from t_member "
						+ "order by joinDate desc";

		List<MemberVO> membersList = new ArrayList<MemberVO>();
		membersList = this.jdbcTemplate.query(query, new RowMapper<MemberVO>() {

			public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {

				MemberVO memberVO = new MemberVO();
				memberVO.setId(rs.getString("id"));
				memberVO.setPwd(rs.getString("pwd"));
				memberVO.setName(rs.getString("name"));
				memberVO.setEmail(rs.getString("email"));
				memberVO.setJoinDate(rs.getDate("joinDate"));
				return memberVO;
			}
		});
		return membersList;
	}
	
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		System.out.println("this.jdbcTemplate = new JdbcTemplate(dataSource);");
	}
	
	@Override
	public void addMember(MemberVO memberVO) throws DataAccessException {
		String id = memberVO.getId();
		String pwd = memberVO.getPwd();
		String name = memberVO.getName();
		String email = memberVO.getEmail();
		String query = "insert into t_member(id,pwd, name,email) values  ("
		                 + "'" + id + "' ,"
	 	                 + "'" + pwd + "' ,"
		                 + "'" + name + "' ,"
		                 + "'" + email + "') ";
		System.out.println(query);
		int result = jdbcTemplate.update(query);
		System.out.println(result);
//		return result;
	}
	
}
