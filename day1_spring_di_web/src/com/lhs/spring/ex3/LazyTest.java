package com.lhs.spring.ex3;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;

public class LazyTest {

	public static void main(String[] args) {		
		/* 1. file system을 사용해 xml을 가져오는 방식 */
		FileSystemResource path = new FileSystemResource("person.xml");
		BeanFactory beanFactory = new XmlBeanFactory(path);
		
		/* 2. application context를 사용해 xml문서를 가져오는 방식 */
		ApplicationContext context = new FileSystemXmlApplicationContext("lazy.xml");
		System.out.println("Second 객체 얻기");
		context.getBean("second");

	}

}
