package com.clawhub.redis.client;

import com.clawhub.redis.config.RedisProperties;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;

/**
 * <Description> 单机版redis客户端<br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018/9/1 <br>
 */
public class RedisClient implements IRedisClient {
    /**
     * jedisPool
     */
    private JedisPool jedisPool;

    /**
     * 构造函数
     *
     * @param redisProperties
     */
    public RedisClient(RedisProperties redisProperties) {
        GenericObjectPoolConfig poolConfig = redisProperties.getPoolConfig();
        String host = redisProperties.getHost();
        int port = redisProperties.getPort();
        int soTimeout = redisProperties.getSoTimeout();
        int connectionTimeout = redisProperties.getConnectionTimeout();
        String password = redisProperties.getPassword();
        int database = redisProperties.getDatabase();
        jedisPool = new JedisPool(poolConfig, host, port, connectionTimeout, soTimeout, password, database, null, false,
                null, null, null);
    }

    @Override
    public String set(final String key, final String value, final String nxxx, final String expx,
                      final long time) {
        Jedis jedis = jedisPool.getResource();
        String result = jedis.set(key, value, nxxx, expx, time);
        jedis.close();
        return result;
    }

    @Override
    public String set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        String result = jedis.set(key, value);
        jedis.close();
        return result;
    }

    @Override
    public String get(String key) {
        Jedis jedis = jedisPool.getResource();
        String result = jedis.get(key);
        jedis.close();
        return result;
    }

    @Override
    public Boolean exists(String key) {
        Jedis jedis = jedisPool.getResource();
        Boolean result = jedis.exists(key);
        jedis.close();
        return result;
    }

    @Override
    public Long expire(String key, int seconds) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.expire(key, seconds);
        jedis.close();
        return result;
    }

    @Override
    public Long del(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.del(key);
        jedis.close();
        return result;
    }

    @Override
    public String hget(String key, String field) {
        Jedis jedis = jedisPool.getResource();
        String result = jedis.hget(key, field);
        jedis.close();
        return result;
    }

    @Override
    public Long hset(String key, String field, String value) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.hset(key, field, value);
        jedis.close();
        return result;
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        Jedis jedis = jedisPool.getResource();
        Map<String, String> result = jedis.hgetAll(key);
        jedis.close();
        return result;
    }

    @Override
    public void close() {
        jedisPool.close();
    }
}
