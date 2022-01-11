package com.github.springredis.businessImp;

import com.github.springredis.business.RedisSetMetier;
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
public class RedisSetMetierImp implements RedisSetMetier {

    @Autowired
    private RedisTemplate<String, Object> setIntegerRedisTemplate;

    @PostConstruct
    public void start(){
        System.out.println("/************************* set Integer *****************************/");
        saveIntegerInSetBdd("setInteger:1",500);
        saveIntegerInSetBdd("setInteger:1",600);
        deleteIntegerInSetBdd("setInteger:1");
        System.out.println("integer value in set : " + setIntegerRedisTemplate.opsForSet().members("setInteger:1"));
        System.out.println("size integer set : " + setIntegerRedisTemplate.opsForSet().size("setInteger:1"));
    }

    @Transactional
    public void saveIntegerInSetBdd(String key, Integer value){
        setIntegerRedisTemplate.opsForSet().add(key, value);
    }

    @Transactional
    public void deleteIntegerInSetBdd(String key){
        setIntegerRedisTemplate.opsForSet().pop(key);
    }


}
