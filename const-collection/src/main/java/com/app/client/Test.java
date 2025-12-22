package com.app.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.app.model.Mycollection;

public class Test {
	public static void main(String[] args) {
		ApplicationContext ap = new ClassPathXmlApplicationContext("beans.xml");
		Mycollection my = ap.getBean(Mycollection.class);
		System.out.println(my);
	}

}
