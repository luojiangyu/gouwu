package com.ljy.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ljy.dao.GoodsMapper;
import com.ljy.redis.RedisOperation;
import com.ljy.service.IGoodsService;
import com.ljy.util.MessageConvert;
import com.ljy.util.RequestProducer;
import com.ljy.vo.Goods;
import com.ljy.vo.GoodsOrder;

@Service
public class GoodsService implements IGoodsService {
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private RedisOperation redisOperation;
	@Autowired
	private RequestProducer requestProducer;
	public static final String EXCHANGE = "buyActivity";
	public static final String ROUTINGKEY = "goods";

	@Override
	public String addGoodsToActivity(Goods goods) {
		System.out.println("test---");
		int updateRow = goodsMapper.updateGoodsStatus(goods);
		System.out.println(updateRow);
		if (updateRow <= 0) {
			return "update error";
		}
		Goods result = goodsMapper.selectGoodsById(goods.getId());
		System.out.println(result);
		if (result != null) {
			String redisResult = redisOperation.addToRedisHash("goods:" + result.getId(),
					redisOperation.exchangeObjToMap(result));
			System.out.println(redisResult);
			if ("OK".equals(redisResult)) {
				return redisResult;
			}

		}

		return "failed";
	}

	@Override
	public String buyActivityGoods(int goodsId, int userId) {
		// TODO Auto-generated method stub
		GoodsOrder goodsOrder = new GoodsOrder();
		goodsOrder.setGoodsId(goodsId);
		goodsOrder.setUserId(userId);
		String msg = MessageConvert.serizableToStr(goodsOrder);
		requestProducer.publish(EXCHANGE, ROUTINGKEY, msg);
		return "join the queue";
	}

}
