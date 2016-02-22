package com.ljy.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class SingletonJedis {

	private static SingletonJedis singletonJedis;
	private Jedis jedis;

	private SingletonJedis(JedisPool jedisPool) {
		jedis = jedisPool.getResource();
	}

	public static synchronized SingletonJedis getSingletonJedis(JedisPool jedisPool) {
		if (singletonJedis == null) {
			singletonJedis = new SingletonJedis(jedisPool);
		}
		return singletonJedis;
	}

	public Jedis getJedisField() {
		return jedis;
	}
}
