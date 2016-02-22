package com.ljy.redis;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class RedisManager {
	protected Logger logger = Logger.getLogger(RedisManager.class);
	@Autowired
	private JedisPool jedisPool;

	public  Jedis getJedis() {
		Jedis jedis = jedisPool.getResource();
		//System.out.println(jedis);
		return jedis;
	}
	public Jedis getSingletonJedis(){
		SingletonJedis singletonJedis=SingletonJedis.getSingletonJedis(jedisPool);
		return singletonJedis.getJedisField();
	}

	

}
