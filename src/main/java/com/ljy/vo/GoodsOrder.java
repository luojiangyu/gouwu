package com.ljy.vo;

import java.io.Serializable;

public class GoodsOrder implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int goodsId;
	private int userId;
   public GoodsOrder(){
	   
   }
   public GoodsOrder(int goodsId,int userId){
	   super();
	   this.goodsId=goodsId;
	   this.userId=userId;
   }
	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
