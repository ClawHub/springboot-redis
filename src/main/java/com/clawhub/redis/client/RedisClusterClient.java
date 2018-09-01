package com.clawhub.redis.client;

import com.clawhub.redis.config.RedisProperties;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <Description> 集群版redis客户端<br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018/9/1 <br>
 */
public class RedisClusterClient implements IRedisClient {
    /**
     * 逗号
     */
    private static final String COMMA;
    /**
     * 冒号
     */
    private static final String COLON;

    static {
        COMMA = ",";
        COLON = ":";
    }

    /**
     * The Logger.
     */
    private Logger logger = LoggerFactory.getLogger(RedisClusterClient.class);
    /**
     * jedisCluster
     */
    private JedisCluster jedisCluster;

    /**
     * 构造
     *
     * @param redisProperties redisProperties
     */
    public RedisClusterClient(RedisProperties redisProperties) {
        // 第一步：使用JedisCluster对象。需要一个Set<HostAndPort>参数。Redis节点的列表。
        Set<HostAndPort> nodes = new HashSet<>();
        String connectString = redisProperties.getConnectString();
        if (StringUtils.isEmpty(connectString)) {
            logger.error("redis cluster connectString is empty.");
            return;
        }
        String[] hostAndPorts = connectString.split(COMMA);
        if (hostAndPorts.length < 1) {
            logger.error("redis cluster host and port is illegal.");
            return;
        }
        for (String hostAndPort : hostAndPorts) {
            if (StringUtils.isEmpty(hostAndPort)) {
                logger.error("redis cluster host and port is is illegal.");
                return;
            }
            String[] node = hostAndPort.split(COLON);
            if (node.length != 2) {
                logger.error("redis cluster host and port is illegal.");
                return;
            }
            int port;
            try {
                port = Integer.parseInt(node[1]);
            } catch (Exception e) {
                logger.error("redis cluster  port is illegal.", e);
                return;
            }
            nodes.add(new HostAndPort(node[0], port));
        }
        int soTimeout = redisProperties.getSoTimeout();
        int maxAttempts = redisProperties.getMaxAttempts();
        GenericObjectPoolConfig poolConfig = redisProperties.getPoolConfig();
        int connectionTimeout = redisProperties.getConnectionTimeout();
        String password = redisProperties.getPassword();
        jedisCluster = new JedisCluster(nodes, connectionTimeout, soTimeout, maxAttempts, password, poolConfig);
    }

    @Override
    public String set(String key, String value, String nxxx, String expx, long time) {
        return jedisCluster.set(key, value, nxxx, expx, time);
    }

    @Override
    public String set(String key, String value) {
        return jedisCluster.set(key, value);
    }

    @Override
    public String get(String key) {
        return jedisCluster.get(key);
    }

    @Override
    public Boolean exists(String key) {
        return jedisCluster.exists(key);
    }

    @Override
    public Long expire(String key, int seconds) {
        return jedisCluster.expire(key, seconds);
    }

    @Override
    public Long del(String key) {
        return jedisCluster.del(key);
    }

    @Override
    public String hget(String key, String field) {
        return jedisCluster.hget(key, field);
    }

    @Override
    public Long hset(String key, String field, String value) {
        return jedisCluster.hset(key, field, value);
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        return jedisCluster.hgetAll(key);
    }

    @Override
    public void close() {
        try {
            jedisCluster.close();
        } catch (IOException e) {
            logger.error("close redis Cluster failed.", e);
        }
    }
}
