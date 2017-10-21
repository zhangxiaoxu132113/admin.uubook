package com.water.admin.utils.cache;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.List;

/**
 * Created by zhangmiaojie on 2017/2/4.
 */
public class RedisCacheManagerImpl implements CacheManager {

    private ShardedJedisPool pool;

    public RedisCacheManagerImpl() {

    }

    public RedisCacheManagerImpl(ShardedJedisPool shardedJedisPool) {
        this.pool = shardedJedisPool;
    }

    @Override
    public ShardedJedis getShardedJedis() {
        if (pool == null) return null;
        return pool.getResource();
    }

    @Override
    public ShardedJedisPool getShardedJedisPool() {
        return pool;
    }

    @Override
    public void set(String key, String value) {
        ShardedJedis jedis = this.pool.getResource();
        try {
            jedis.set(key, value);
            this.pool.returnResource(jedis);
        } catch (Exception e) {
            this.pool.returnBrokenResource(jedis);
        }
    }

    @Override
    public void set(String key, byte[] value) {
        ShardedJedis jedis = this.pool.getResource();
        try {
            jedis.set(key.getBytes(), value);
            this.pool.returnResource(jedis);
        } catch (Exception e) {
            this.pool.returnBrokenResource(jedis);
        }
    }

    @Override
    public byte[] get(String key) {
        ShardedJedis jedis = this.pool.getResource();
        byte[] value = null;
        try {
            value = jedis.get(key.getBytes());
            this.pool.returnResource(jedis);
        } catch (Exception e) {
            this.pool.returnBrokenResource(jedis);
        }
        return value;
    }

    @Override
    public Long sadd(String key, String... value) {
        ShardedJedis jedis = this.pool.getResource();
        Long result = Long.valueOf(0L);
        try {
            result = jedis.sadd(key, value);
            this.pool.returnResource(jedis);
        } catch (Exception e) {
            this.pool.returnBrokenResource(jedis);
        }
        return result;
    }

    @Override
    public Long llen(String key) {
        ShardedJedis jedis = this.pool.getResource();
        Long result = Long.valueOf(-1L);
        try {
            result = jedis.llen(key);
            this.pool.returnResource(jedis);
        } catch (Exception e) {
            this.pool.returnBrokenResource(jedis);
        }
        return result;
    }

    @Override
    public void del(String key) {
        ShardedJedis jedis = this.pool.getResource();
        try {
            jedis.del(key);
            this.pool.returnResource(jedis);
        } catch (Exception e) {
            this.pool.returnBrokenResource(jedis);
        }
    }

    @Override
    public void lpush(String key, String... value) {
        ShardedJedis jedis = this.pool.getResource();
        try {
            jedis.lpush(key, value);
            this.pool.returnResource(jedis);
        } catch (Exception e) {
            this.pool.returnBrokenResource(jedis);
        }
    }

    @Override
    public void lpush(String key, byte[]... value) {
        ShardedJedis jedis = this.pool.getResource();
        try {
            jedis.lpush(key.getBytes(), value);
            this.pool.returnResource(jedis);
        } catch (Exception e) {
            this.pool.returnBrokenResource(jedis);
        }
    }

    @Override
    public String lpop(String key) {
        ShardedJedis jedis = this.pool.getResource();
        String result = "";
        try {
            result = jedis.lpop(key);
            this.pool.returnResource(jedis);
        } catch (Exception e) {
            this.pool.returnBrokenResource(jedis);
        }
        return result;
    }

    @Override
    public byte[] lpop(byte[] key) {
        ShardedJedis jedis = this.pool.getResource();
        byte[] result = null;
        try {
            result = jedis.lpop(key);
            this.pool.returnResource(jedis);
        } catch (Exception e) {
            this.pool.returnBrokenResource(jedis);
        }
        return result;
    }

    @Override
    public long lrem(byte[] key, byte[] value) {
        ShardedJedis jedis = this.pool.getResource();
        Long result = null;
        try {
            result = jedis.lrem(key, 0, value);
            this.pool.returnResource(jedis);
        } catch (Exception e) {
            this.pool.returnBrokenResource(jedis);
        }
        return result;
    }

    @Override
    public String ltrim(String key, long start, long end) {
        ShardedJedis jedis = this.pool.getResource();
        String result = null;
        try {
            result = jedis.ltrim(key, start, end);
            this.pool.returnResource(jedis);
        } catch (Exception e) {
            this.pool.returnBrokenResource(jedis);
        }
        return result;
    }

    @Override
    public List<byte[]> lrange(byte[] key, long start, long end) {
        ShardedJedis jedis = this.pool.getResource();
        List<byte[]> result = null;
        try {
            result = jedis.lrange(key, start, end);
            this.pool.returnResource(jedis);
        } catch (Exception e) {
            this.pool.returnBrokenResource(jedis);
        }
        return result;
    }

    @Override
    public List getList(String key, Class classType) {
        if (key == null || "".equals(key)) {
            return null;
        }
        ListTranscoder<Class> listTranscoder = new ListTranscoder<Class>();
        byte[] bytes = get(key);
        if (bytes == null) {
            return null;
        }
        return (List) listTranscoder.deserialize(bytes);
    }

    @Override
    public void setList(String key, List list, int seconds, Class classType) {
        ListTranscoder<Class> listTranscoder = new ListTranscoder<Class>();
        set(key, listTranscoder.serialize(list), seconds);
    }

    @Override
    public void set(String key, byte[] data, int seconds) {
        ShardedJedis jedis = pool.getResource();
        try {
            byte[] key_data = key.getBytes("utf8");
            jedis.set(key_data, data);
            jedis.expire(key_data, seconds);
        } catch (Exception e) {
            //  e.printStackTrace();
        } finally {
            pool.returnResource(jedis);
        }
    }

    @Override
    public void rpush(String key, byte[] value) {
        ShardedJedis jedis = this.pool.getResource();
        try {
            jedis.rpush(key.getBytes(), value);
            this.pool.returnResource(jedis);
        } catch (Exception e) {
            this.pool.returnBrokenResource(jedis);
        }
    }

}
