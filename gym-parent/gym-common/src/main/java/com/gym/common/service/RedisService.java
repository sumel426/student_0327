package com.gym.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

@Service
public class RedisService {
	//required=false 使用时才注入
	@Autowired(required=false)
	private JedisSentinelPool pool;
	
	public void set(String key,String value) {
		Jedis jedis = pool.getResource();
		jedis.set(key, value);
		jedis.expire(key, 7*24*3600);
		jedis.close();
	}
	
	public String get(String key) {
		Jedis jedis = pool.getResource();
		String result = jedis.get(key);
		jedis.close();
		return result;
	}
}
