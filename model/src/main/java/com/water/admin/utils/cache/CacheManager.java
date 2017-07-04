package com.water.admin.utils.cache;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.List;

/**
 * Created by zhangmiaojie on 2017/2/4.
 */
public interface CacheManager {
    public ShardedJedis getShardedJedis();

    public ShardedJedisPool getShardedJedisPool();

    public void set(String key, String value);

    public void set(String key, byte[] value);

    public byte[] get(String key);

    public Long sadd(String key, String... value);

    public Long llen(String key);

    public void del(String key);

    public void lpush(String key, String... value);

    public void lpush(String key, byte[]... value);

    public String lpop(String key);

    public byte[] lpop(byte[] key);

    public List<byte[]> lrange(byte[] key, long start, long end);

}
