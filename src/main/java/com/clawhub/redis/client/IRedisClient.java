package com.clawhub.redis.client;

import java.util.Map;

/**
 * <Description> Redis客户端接口<br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018/9/1 <br>
 */
public interface IRedisClient {
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
    String set(String key, String value, String nxxx, String expx, long time);

    /**
     * set
     *
     * @param key   key
     * @param value value
     * @return Status code reply
     */
    String set(String key, String value);

    /**
     * get
     *
     * @param key key
     * @return value
     */
    String get(String key);

    /**
     * exists
     *
     * @param key key
     * @return Boolean reply, true if the key exists, otherwise false
     */
    Boolean exists(String key);

    /**
     * expire
     *
     * @param key     key
     * @param seconds seconds
     * @return Integer reply, specifically: 1: the timeout was set. 0: the timeout was not set since
     * the key already has an associated timeout (this may happen only in Redis versions &lt;
     * 2.1.3, Redis &gt;= 2.1.3 will happily update the timeout), or the key does not exist.
     */
    Long expire(String key, int seconds);

    /**
     * del
     *
     * @param key key
     * @return Integer reply, specifically: an integer greater than 0 if one or more keys were removed
     * 0 if none of the specified key existed
     */
    Long del(String key);

    /**
     * hget
     *
     * @param key   key
     * @param field field
     * @return Bulk reply
     */
    String hget(String key, String field);

    /**
     * hset
     *
     * @param key   key
     * @param field field
     * @param value value
     * @return If the field already exists, and the HSET just produced an update of the value, 0 is
     * returned, otherwise if a new field is created 1 is returned.
     */
    Long hset(String key, String field, String value);

    /**
     * hgetAll
     *
     * @param key key
     * @return All the fields and values contained into a hash.
     */
    Map<String, String> hgetAll(String key);

    /**
     * close
     */
    void close();
}
