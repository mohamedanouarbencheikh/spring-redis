package com.github.springredis.businessImp;

import com.github.springredis.business.RedisStringMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
/**
 * @author Mohamed Anouar BENCHEIKH
 * @project springredis
 */
@Service
public class RedisStringMetierImp implements RedisStringMetier {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostConstruct
    public void start(){
        System.out.println("/************************* String *****************************/");
        saveStringBdd("string:1", "s1");
        System.out.println("value string : " + stringRedisTemplate.opsForValue().get("string:1"));

        System.out.println("/************************* list String *****************************/");
        saveRightStringListBdd("listString:1","a");
        saveLeftStringListBdd("listString:1","b");

        //deleteRightStringListBdd("listString:1");
        //deleteLeftStringListBdd("listString:1");

        //deleteStringInListBdd("listString:1", "a");

        System.out.println("size listString : " + stringRedisTemplate.opsForList().size("listString:1"));

        System.out.println("/************************* set String *****************************/");
        saveStringInSetBdd("setString:1","set1");
        saveStringInSetBdd("setString:1","set2");
        //deleteStringInSetBdd("setString:1");
        System.out.println("string value in set : " + stringRedisTemplate.opsForSet().members("setString:1"));
        System.out.println("size string set : " + stringRedisTemplate.opsForSet().size("setString:1"));
    }

    @Transactional
    public void saveStringBdd(String key, String value){
        stringRedisTemplate.opsForValue().set(key, value);
    }

    @Transactional
    public void saveRightStringListBdd(String key, String value){
        stringRedisTemplate.opsForList().rightPush(key,value);
    }

    @Transactional
    public void saveLeftStringListBdd(String key, String value){
        stringRedisTemplate.opsForList().leftPush(key,value);
    }

    @Transactional
    public void deleteRightStringListBdd(String key){
        stringRedisTemplate.opsForList().rightPop(key);
    }

    @Transactional
    public void deleteLeftStringListBdd(String key){
        stringRedisTemplate.opsForList().leftPop(key);
    }

    @Transactional
    public void deleteStringInListBdd(String key, String value){
        stringRedisTemplate.opsForList().remove(key, 1, value);
    }

    @Transactional
    public void saveStringInSetBdd(String key, String value){
        stringRedisTemplate.opsForSet().add(key, value);
    }

    @Transactional
    public void deleteStringInSetBdd(String key){
        stringRedisTemplate.opsForSet().pop(key);
    }
}
