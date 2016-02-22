package com.test;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.ljy.util.RequestProducer;

public class RequestUnit {
   public static void main(String[] args) {
	   ApplicationContext aCtx = new FileSystemXmlApplicationContext("classpath:conf/applicationContext.xml");
	   RequestProducer request=aCtx.getBean(RequestProducer.class);
	   request.publish("buyActivity", "", "hello world");
}
}
