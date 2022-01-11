package com.github.springredis.businessImp;


import com.github.springredis.business.RedisListMetier;
import com.github.springredis.model.Obj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
/**
 * @author Mohamed Anouar BENCHEIKH
 * @project springredis
 */
@Service
public class RedisListMetierImp implements RedisListMetier {

   /* @Autowired
    //@Qualifier("redisTemplate")
    private RedisTemplate<String, String> listRedisTemplate;*/

    @Autowired
    private RedisTemplate<String, Object> listIntegerRedisTemplate;

    Obj obj;

    @PostConstruct
    public void start(){
        System.out.println("/************************* List Integer *****************************/");
        leftSaveIntegerInListBdd("listInteger:1", 10);
        rightSaveIntegerInListBdd("listInteger:1", 11);
        leftDeleteIntegerInListBdd("listInteger:1");
        rightDeleteIntegerInListBdd("listInteger:1");
        //deleteIntegerInListBdd("listInteger:1", 10);
        System.out.println("size list integer : " + listIntegerRedisTemplate.opsForList().size("listInteger:1"));
        System.out.println("integer value in index : " + listIntegerRedisTemplate.opsForList().index("listInteger:1", 0));


    }

    @Transactional
    public void leftSaveIntegerInListBdd(String key, Integer value){
        listIntegerRedisTemplate.opsForList().leftPush(key, value);
    }

    @Transactional
    public void rightSaveIntegerInListBdd(String key, Integer value){
        listIntegerRedisTemplate.opsForList().rightPush(key, value);
    }

    @Transactional
    public void leftDeleteIntegerInListBdd(String key){
        listIntegerRedisTemplate.opsForList().leftPop(key);
    }

    @Transactional
    public void rightDeleteIntegerInListBdd(String key){
        listIntegerRedisTemplate.opsForList().rightPop(key);
    }

    @Transactional
    public void deleteIntegerInListBdd(String key, Integer value){
        listIntegerRedisTemplate.opsForList().remove(key, 1, value);
    }
}
