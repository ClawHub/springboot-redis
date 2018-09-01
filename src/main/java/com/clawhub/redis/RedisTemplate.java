package com.clawhub.redis;

import com.clawhub.redis.client.IRedisClient;
import com.clawhub.redis.client.RedisClientFactory;
import com.clawhub.redis.config.RedisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Map;

/**
 * <Description> 封装的redis管理客户端<br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018/9/1 <br>
 */
@Component
public class RedisTemplate {

    /**
     * The Properties.
     */
    @Autowired
    private RedisProperties properties;

    /**
     * The Redis client.
     */
    private IRedisClient redisClient;

    /**
     * Init.
     */
    @PostConstruct
    public void init() {
        redisClient = RedisClientFactory.getClient(properties);
    }

    /**
     * Close.
     */
    @PreDestroy
    public void close() {
        redisClient.close();
    }

    /**
     * Set the string value as value of the key. The string can't be longer than 1073741824 bytes (1
     * GB).
     *
     * @param key   key
     * @param value value
     * @param nxxx  NX|XX, NX -- Only set the key if it does not already exist. XX -- Only set the key
     *              if it already exist.
     * @param expx  EX|PX, expire time units: EX = seconds; PX = milliseconds
     * @param time  expire time in the units of <code>expx</code>
     * @return Status code reply
     */
    public String set(String key, String value, String nxxx, String expx, long time) {
        return redisClient.set(key, value, nxxx, expx, time);
    }

    /**
     * set
     *
     * @param key   key
     * @param value value
     * @return Status code reply
     */
    public String set(String key, String value) {
        return redisClient.set(key, value);
    }

    /**
     * get
     *
     * @param key key
     * @return value
     */
    public String get(String key) {
        return redisClient.get(key);
    }

    /**
     * exists
     *
     * @param key key
     * @return Boolean reply, true if the key exists, otherwise false
     */
    public Boolean exists(String key) {
        return redisClient.exists(key);
    }

    /**
     * expire
     *
     * @param key     key
     * @param seconds seconds
     * @return Integer reply, specifically: 1: the timeout was set. 0: the timeout was not set since
     * the key already has an associated timeout (this may happen only in Redis versions &lt;
     * 2.1.3, Redis &gt;= 2.1.3 will happily update the timeout), or the key does not exist.
     */
    public Long expire(String key, int seconds) {
        return redisClient.expire(key, seconds);
    }

    /**
     * del
     *
     * @param key key
     * @return Integer reply, specifically: an integer greater than 0 if one or more keys were removed
     * 0 if none of the specified key existed
     */
    public Long del(String key) {
        return redisClient.del(key);
    }

    /**
     * hget
     *
     * @param key   key
     * @param field field
     * @return Bulk reply
     */
    public String hget(String key, String field) {
        return redisClient.hget(key, field);
    }

    /**
     * hset
     *
     * @param key   key
     * @param field field
     * @param value value
     * @return If the field already exists, and the HSET just produced an update of the value, 0 is
     * returned, otherwise if a new field is created 1 is returned.
     */
    public Long hset(String key, String field, String value) {
        return redisClient.hset(key, field, value);
    }

    /**
     * hgetAll
     *
     * @param key key
     * @return All the fields and values contained into a hash.
     */
    public Map<String, String> hgetAll(String key) {
        return redisClient.hgetAll(key);
    }

}
