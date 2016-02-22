package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.ljy.util.GoodsConsumer;

public class ConsumerTest {
	public static void main(String[] args) {
		   ApplicationContext aCtx = new FileSystemXmlApplicationContext("classpath:conf/applicationContext.xml");
		   GoodsConsumer request=aCtx.getBean(GoodsConsumer.class);
	}
}
