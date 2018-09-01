package com.clawhub.redis.client;

import com.clawhub.redis.config.RedisProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * <Description> redis客户端工厂<br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018/9/1 <br>
 */
public class RedisClientFactory {
    /**
     * The Logger.
     */
    private static Logger logger = LoggerFactory.getLogger(RedisClientFactory.class);

    /**
     * Get client.
     *
     * @param properties the properties
     * @return the client
     */
    public static IRedisClient getClient(RedisProperties properties) {
        if (!StringUtils.isEmpty(properties.getConnectString())) {
            logger.info("init redis cluster client");
            return new RedisClusterClient(properties);
        } else {
            logger.info("init redis client");
            return new RedisClient(properties);
        }
    }
}
