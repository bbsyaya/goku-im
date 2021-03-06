package com.goku.im.logic.redis;

import com.goku.im.logic.global.RedisKeyConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

/**
 * Created by moueimei on 15/12/10.
 * 离线通知队列, 负责存储掉线用户的未读通知
 */
@Repository
public class OffLineNotifyRedis {
    @Autowired
    Jedis jedis;

    public void add(int userId, long createTime, String notifyId) throws Exception {
        String key = RedisKeyConst.makeKey(RedisKeyConst.OFFLINE_NOTIFY_LIST_KEY_PREFIX, userId);
        jedis.zadd(key, createTime, notifyId);
    }

    public void delete(int userId, String notifyId) throws Exception {
        String key = RedisKeyConst.makeKey(RedisKeyConst.OFFLINE_NOTIFY_LIST_KEY_PREFIX, userId);
        jedis.zrem(key, notifyId);
    }
}