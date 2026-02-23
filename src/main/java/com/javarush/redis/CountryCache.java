package com.javarush.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.domain.Country;
import redis.clients.jedis.Jedis;

public class CountryCache {

    private static final ObjectMapper mapper = new ObjectMapper();

    public Country get(String code) {
        try (Jedis jedis = RedisConfig.getClient()) {
            String json = jedis.get(code);
            if (json == null) {
                return null;
            }
            return mapper.readValue(json, Country.class);
        } catch (Exception e) {
            return null;
        }
    }

    public void put(String code, Country country) {
        try (Jedis jedis = RedisConfig.getClient()) {
            String json = mapper.writeValueAsString(country);
            jedis.set(code, json);
        } catch (Exception ignored) {

        }
    }
}
