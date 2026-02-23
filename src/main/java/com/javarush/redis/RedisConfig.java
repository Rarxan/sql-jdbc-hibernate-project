package com.javarush.redis;

import redis.clients.jedis.Jedis;

public class RedisConfig {

    private static final String REDIS_HOST = "localhost";
    private static final int REDIS_PORT = 6379;

    public static Jedis getClient(){
        return new Jedis(REDIS_HOST, REDIS_PORT);
    }
}
