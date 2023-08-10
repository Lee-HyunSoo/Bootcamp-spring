package com.lhs.spring.ex2;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

public class MemberTest {

	public static void main(String[] args) {
		BeanFactory beanFactory = new XmlBeanFactory(new FileSystemResource("member.xml"));
		
		MemberService memberService = (MemberService) beanFactory.getBean("memberService");
		memberService.listMembers();	
	}

}
