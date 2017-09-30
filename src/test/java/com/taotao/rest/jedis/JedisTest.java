package com.taotao.rest.jedis;

import java.util.HashSet;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class JedisTest {

	@Test
	public void testJedisSingle(){
		// 创建一个jedis对象
		Jedis jedis = new Jedis("192.168.159.131",6379);
		// 调用jedis对象的方法，方法名和redis命令一致
		jedis.set("a", "aaaa");
		System.out.println(jedis.get("a"));
		// 关闭jedis
		jedis.close();
	}
	/**
	 * 使用连接池
	 */
	@Test
	public void testJedisPool(){
		// 创建jedis连接池
		JedisPool jedisPool = new JedisPool("192.168.159.131",6379);
		Jedis jedis = jedisPool.getResource();
		System.out.println(jedis.get("a"));
		jedis.close();
		jedisPool.close();
	}
	
	@Test
	public void testJedisCluster(){
		HashSet<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.159.131", 7001));
		nodes.add(new HostAndPort("192.168.159.131", 7002));
		nodes.add(new HostAndPort("192.168.159.131", 7003));
		nodes.add(new HostAndPort("192.168.159.131", 7004));
		nodes.add(new HostAndPort("192.168.159.131", 7005));
		nodes.add(new HostAndPort("192.168.159.131", 7006));
		JedisCluster cluster = new JedisCluster(nodes);
		
		cluster.set("c", "1000");
		String string = cluster.get("c");
		System.out.println(string);
		cluster.close();
	}
	
	/**
	* @Title: testSpringJedisSingle
	* @Description: 单机版测试
	* @return void   返回类型
	* @throws
	*/
	@Test
	public void testSpringJedisSingle(){
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		JedisPool jedisPool = (JedisPool) applicationContext.getBean("redisClient");
		Jedis jedis = jedisPool.getResource();
		System.out.println(jedis.get("a"));
		jedis.close();
		jedisPool.close();
	}
	@Test
	public void testSpringJedisCluster(){
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		JedisCluster jedisCluster = (JedisCluster) applicationContext.getBean("redisClient");
		System.out.println(jedisCluster.get("c"));
		jedisCluster.close();
	}
}
