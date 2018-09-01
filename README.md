# springboot-redis
简单封装了Jedis客户端，支持连接单机与集群redis服务器，jedis版本2.9.0。

主要原因是线上用spring-boot-starter-data-redis时，出现了集群TCP连接数过多的问题，所以自己封装了一套使用。
