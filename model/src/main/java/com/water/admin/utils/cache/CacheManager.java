package com.water.admin.utils.cache;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.List;

/**
 * Created by zhangmiaojie on 2017/2/4.
 */
public interface CacheManager {
    ShardedJedis getShardedJedis();

    ShardedJedisPool getShardedJedisPool();

    void set(String key, String value);

    void set(String key, byte[] value);

    byte[] get(String key);

    Long sadd(String key, String... value);

    Long llen(String key);

    void del(String key);

    void lpush(String key, String... value);

    void lpush(String key, byte[]... value);

    String lpop(String key);

    byte[] lpop(byte[] key);

    long lrem(byte[] key, byte[] value);

    String ltrim(String key, long start, long end);

    List<byte[]> lrange(byte[] key, long start, long end);

    List getList(String key, Class classType);

    void setList(String key, List list, int seconds, Class classType);

    void set(String key, byte[] data, int seconds);

    void rpush(String key, byte[] value);
}
