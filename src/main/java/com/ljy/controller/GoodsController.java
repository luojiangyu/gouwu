package com.ljy.controller;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ljy.service.IGoodsService;
import com.ljy.vo.Goods;
import com.ljy.vo.User;

@Controller
public class GoodsController {
	@Autowired
	private IGoodsService promotionService;

	@RequestMapping("/addGoodsToActivity")
	@ResponseBody
	public String addGoodsToActivity(int goodsId, String startTime, String endTime) {
		Goods goods = new Goods();
		goods.setId(goodsId);
		System.out.println(startTime);
		try{
		goods.setStartTime(Timestamp.valueOf(startTime));
		goods.setEndTime(Timestamp.valueOf(endTime));
		goods.setIsActivity('1');}
		catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("before");
		String result = promotionService.addGoodsToActivity(goods);
		System.out.println(result);
		return result;
	}
	@RequestMapping("/buyActivityGoods")
	public String buyActivityGoods(int goodsId,HttpServletRequest request){
		User user=(User) request.getSession().getAttribute("user");
		return promotionService.buyActivityGoods(goodsId, user.getId());
		
	}
}
