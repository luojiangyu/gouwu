package com.ljy.redis;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ljy.vo.GoodsOrder;

import redis.clients.jedis.Jedis;

@Component
public class RedisOperation {
	@Autowired
	private RedisManager redisManager;
	public static final String ORDERGOODS = "order:goods:";
	public static final long SIZE = -1L;
	protected Logger logger = Logger.getLogger(RedisOperation.class);


	public String addToRedisHash(final String key, final Map<String, String> map) {
		if (map.entrySet().isEmpty()) {
			return "failed";
		}
		System.out.println("addToRedisHash");
		Jedis jedis = null;
		try {
			jedis = redisManager.getJedis();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("jedis+" + jedis);

		return jedis.hmset(key, map);
	}

	/**
	 * 在redis生成订单set
	 * 
	 * @param key
	 * @param goodsOrder
	 * @param stockField
	 * @return
	 */
	public String addCart(String key, GoodsOrder goodsOrder, String stockField) {
		Jedis jedis = redisManager.getJedis();
		String result = "failed";
		String orderKey = ORDERGOODS + goodsOrder.getGoodsId();
		boolean isJoin = jedis.sismember(orderKey, String.valueOf(goodsOrder.getUserId()));
		if (isJoin) {
			logger.info(
					"the user:" + goodsOrder.getGoodsId() + " already buy the goods(" + goodsOrder.getGoodsId() + ").");
			return "bought";
		}

		int value = Integer.parseInt(jedis.hget(key, stockField));
		if (value >= SIZE) {
			jedis.sadd(orderKey, new String[] { String.valueOf(goodsOrder.getUserId()) });
			result = String.valueOf(jedis.hincrBy(key, stockField, SIZE));
		}
		return result;
	}

	public Map<String, String> exchangeObjToMap(Object obj) {
		Field[] fields = obj.getClass().getDeclaredFields();
		Map<String, String> map = new HashMap<>();
		for (Field field : fields) {
			field.setAccessible(true);
			Object fieldValue = null;
			String fieldName = null;
			try {
				fieldName = field.getName();
				fieldValue = field.get(obj);

			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				field.setAccessible(false);
			}
			if (fieldValue != null) {
				map.put(fieldName, String.valueOf(fieldValue));
			}

		}
		return map;
	}

	/**
	 * 根据正则表达式获取符合条件的key
	 * 
	 * @param pattern
	 * @return
	 */
	public Set<String> getKeysForRegex(String pattern, boolean daemon) {
		Set<String> set = new HashSet<String>();
		Jedis jedis = null;
		if (daemon) {
			jedis = redisManager.getSingletonJedis();
		} else {
			jedis = redisManager.getJedis();
		}
		set = jedis.keys(pattern);
		return set;
	}

	/**
	 * 通过key获取集合所有的值
	 * 
	 * @param key
	 * @return
	 */
	public Set<String> getSetValue(String key, boolean daemon) {

		Set<String> set = new HashSet<String>();
		Jedis jedis = null;
		if (daemon) {
			jedis = redisManager.getSingletonJedis();
		} else {
			jedis = redisManager.getJedis();
		}
		set = jedis.smembers(key);
		return set;
	}

	/**
	 * 通过key获取散列所有的值
	 * 
	 * @param key
	 * @return
	 */
	public Map<String, String> getHashValue(String key, boolean daemon) {
		Map<String, String> map = new HashMap<String, String>();
		Jedis jedis = null;
		if (daemon) {
			jedis = redisManager.getSingletonJedis();
		} else {
			jedis = redisManager.getJedis();
		}
		map = jedis.hgetAll(key);
		return map;
	}

	/**
	 * 删除redis的key
	 * 
	 * @param key
	 * @return
	 */
	public long deleteRedisKey(String key) {
		Jedis jedis = redisManager.getJedis();
		return jedis.del(key);
	}

}
