package com.xlljoy.o2o.cache;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolWrapper {
	private JedisPool jedisPool;
	
	public JedisPoolWrapper(final JedisPoolConfig poolConfig, final String host,
			final int port) {
		try {
			jedisPool = new JedisPool(poolConfig, host, port);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// get redis pool object
	public JedisPool getJedisPool() {
		return jedisPool;
	}
	
	//inject Redis object
	
	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}
}
