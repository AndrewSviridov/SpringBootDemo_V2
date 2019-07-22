package com.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Title:
 * Description:
 * Copyright: 2019 北京拓尔思信息技术股份有限公司 版权所有.保留所有权
 * Company:北京拓尔思信息技术股份有限公司(TRS)
 * Project: SpringBootDemo
 * Author: 王杰
 * Create Time:2019/5/30 16:43
 */
@Service
public class RedisGeoService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 增加地址位置的坐标
     */
    public Long geoAdd(String key, Point point, String memberName) {
        return redisTemplate.opsForGeo().geoAdd(key, point, memberName);
    }

    /**
     * 批量添加地理位置
     */
    public Long geoBatchAdd(String key, Map<String, Point> memberPointMap) {
        return redisTemplate.opsForGeo().geoAdd(key, memberPointMap);
    }

    /**
     * 获取指定member的经纬度信息，可以指定多个member，批量返回
     */
    public List<Point> geoPos(String key, String... members) {
        return redisTemplate.opsForGeo().geoPos(key, members);
    }

    /**
     * 返回两个地方的距离，可以指定单位，比如米m，千米KM，英里mi，英尺ft
     */
    public Distance geoDist(String key, String member1, String member2) {
        return redisTemplate.opsForGeo().geoDist(key, member1, member2, Metrics.KILOMETERS);
    }

    /**
     * 返回指定半径内的按照升序排序的最多returnNum条数据
     */
    public GeoResults<RedisGeoCommands.GeoLocation<String>> geoRadius(String key, double limitedDistance, Point point, long returnNum) {
        Distance distance = new Distance(limitedDistance, Metrics.KILOMETERS);
        Circle circle = new Circle(point, distance);
        RedisGeoCommands.GeoRadiusCommandArgs geoRadiusCommandArgs = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs();
        // 返回的是
        geoRadiusCommandArgs.includeCoordinates();
        // 返回数据量
        geoRadiusCommandArgs.limit(returnNum);
        // 排序方式
        geoRadiusCommandArgs.sortAscending();
        return redisTemplate.opsForGeo().geoRadius(key, circle, geoRadiusCommandArgs);
    }

    /**
     * 按照member 返回指定半径内的按照升序排序的最多returnNum条数据
     */
    public void geoRadiusByMember(String key, String memberName, double limitedDistance, long returnNum) {
        Distance distance = new Distance(limitedDistance, Metrics.KILOMETERS);
        RedisGeoCommands.GeoRadiusCommandArgs geoRadiusCommandArgs = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs();
        // 返回的是
        geoRadiusCommandArgs.includeCoordinates();
        // 返回数据量
        geoRadiusCommandArgs.limit(returnNum);
        // 排序方式
        geoRadiusCommandArgs.sortAscending();
        redisTemplate.opsForGeo().geoRadiusByMember(key, memberName, distance, geoRadiusCommandArgs);
    }

    /**
     * 返回某几个member的geohash集合
     */
    public List<String> geoHash(String key, String... members) {
        return redisTemplate.opsForGeo().geoHash(key, members);
    }

}