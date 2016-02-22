package com.ljy.util;

import com.alibaba.fastjson.JSON;

public class MessageConvert {
   public static String serizableToStr(Object obj){
	   return JSON.toJSONString(obj);
   }
   public static Object unserizableToObj(String str){
	   return JSON.parse(str);
   }
}
