package com.ljy.service;

import com.ljy.vo.Goods;

public interface IGoodsService {
	public String addGoodsToActivity(Goods goods);
	public String buyActivityGoods(int goodsId,int userId);
}
