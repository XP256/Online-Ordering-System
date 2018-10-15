package com.imooc.sell.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

//在秒杀服务中使用，此项目内未去实现

@Component
@Slf4j
public class RedisLock {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     *
     * @param key
     * @param value
     * @return
     */
    public boolean lock(String key,String value){
        //if can be locked
        if(redisTemplate.opsForValue().setIfAbsent(key,value)){
            return true;
        }

        //cannot be locked, get exceeding time
        String currentValue = redisTemplate.opsForValue().get(key);

        //if lock time exceeds
        if(!StringUtils.isEmpty(currentValue)
                && Long.parseLong(currentValue)<System.currentTimeMillis()){
            //get last lock's time and set new lock
            String oldValue = redisTemplate.opsForValue().getAndSet(key,value);
            //这个判断可以只让一个线程拿到锁
            if( !StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)){
                return true;
            }
        }
        return false;
    }


    /**
     *
     * @param key
     * @param value
     */
    public void unlock(String key, String value){
        try {
            String currentValue = redisTemplate.opsForValue().get(key);
            if(!StringUtils.isEmpty(currentValue) && currentValue.equals(value)){
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (Exception e) {
            log.error("[redis distributed lock] unlock exception,{}",e);
        }

    }

}
