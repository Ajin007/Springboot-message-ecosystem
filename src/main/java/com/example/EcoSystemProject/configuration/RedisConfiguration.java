package com.example.EcoSystemProject.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableRedisRepositories
public class RedisConfiguration {

	
	@Bean
	/*
	 * JedisConnectionFactory- It defines the connection to the Redis Database
	 * RedisStandaloneConfiguration contains host and port for making the connection using the 
	 * JedisConnectionFactory
	 */
	public JedisConnectionFactory ConnectionFactory() {
		
		RedisStandaloneConfiguration configuration=new RedisStandaloneConfiguration();
		configuration.setHostName("localhost");
		configuration.setPort(6311);
		
		return new JedisConnectionFactory(configuration);
	}
	
	@Bean
	public RedisTemplate<String, Object> template(){
		
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		//JedisConnectionFactory is used to send the port and the hostname
		/*
		 * setserializer,jdkserializerr these all are used for creating the 
		 * standard java serialization mechanism
		 */
		template.setConnectionFactory(ConnectionFactory());
		template.setKeySerializer(new StringRedisSerializer());
		template.setHashKeySerializer(new StringRedisSerializer());
		template.setHashKeySerializer(new JdkSerializationRedisSerializer());
		template.setValueSerializer(new JdkSerializationRedisSerializer());
		
		//It supports transaction support for redis
		template.setEnableTransactionSupport(true);
		//set all  the properties over here
		template.afterPropertiesSet();
		
		return template;
		
	}
}
