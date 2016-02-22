package com.ljy.dao;

import org.springframework.stereotype.Repository;

import com.ljy.vo.Goods;

@Repository
public interface GoodsMapper {
	public int updateGoodsStatus(Goods goods);

	public Goods selectGoodsById(int goodsId);
}
