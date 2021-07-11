package com.balldontlie.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.balldontlie.model.Player;


@Configuration
@EnableCaching
public class RedisConfig {

	@Bean
	public JedisConnectionFactory jedisConnectioFactory() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		redisStandaloneConfiguration.setHostName("127.0.0.1");
		redisStandaloneConfiguration.setPort(6379);
		JedisConnectionFactory  jedisConnectioFactory = new JedisConnectionFactory(redisStandaloneConfiguration);
		return jedisConnectioFactory;
	}
	
	@Bean
	public RedisTemplate<String, Player> redisTemplate() {
		RedisTemplate<String, Player> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectioFactory());
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new JdkSerializationRedisSerializer());
		redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
		redisTemplate.setEnableTransactionSupport(true);
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}
}
