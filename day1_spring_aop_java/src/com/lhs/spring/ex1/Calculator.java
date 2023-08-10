package com.lhs.spring.ex1;

public class Calculator {

	public Calculator() {
	}
	
	public void add(int x, int y) {
		System.out.println("결과 : " + (x + y));
	}
	
	public void subtract(int x, int y) {
		System.out.println("결과 : " + (x - y));
	}
	
	public void multiply(int x, int y) {
		System.out.println("결과 : " + (x * y));
	}
	
	public void divide(int x, int y) {
		System.out.println("결과 : " + (x / y));
	}

}
