package com.lhs.spring.ex1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CalcTest {

	public static void main(String[] args) {
		// 3. 현재 src 내에 xml파일이 있기 때문에 ClassPathXmlApplicationContext 사용
		ApplicationContext context = new ClassPathXmlApplicationContext("aopTest.xml");
		Calculator cal = (Calculator) context.getBean("proxyCal");
		cal.add(100, 20);
		System.out.println();
		cal.subtract(100, 20);
		System.out.println();
		cal.multiply(100, 20);
		System.out.println();
		cal.divide(100, 20);
		System.out.println();

	}

}
