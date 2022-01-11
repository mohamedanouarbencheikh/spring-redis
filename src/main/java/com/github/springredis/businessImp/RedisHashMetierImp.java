package com.github.springredis.businessImp;

import com.github.springredis.business.RedisHashMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
/**
 * @author Mohamed Anouar BENCHEIKH
 * @project springredis
 */
@Service
public class RedisHashMetierImp implements RedisHashMetier {

    @Autowired
    private RedisTemplate<String, Object> hashIntegerRedisTemplate;

    @PostConstruct
    public void start(){
        System.out.println("/************************* List Integer *****************************/");
        saveIntegerInHashBdd("hash:1",1,700);
        saveIntegerInHashBdd("hash:1",2,701);
        saveIntegerInHashBdd("hash:1",3,702);
        System.out.println("integer value in hash : " + hashIntegerRedisTemplate.opsForHash().get("hash:1",1));
    }

    @Transactional
    public void saveIntegerInHashBdd(String key, Integer id, Integer value){
        hashIntegerRedisTemplate.opsForHash().put(key, id, value);
    }

    @Transactional
    public void deleteInHashBdd(String key, Integer id){
        hashIntegerRedisTemplate.opsForHash().delete(key,id);
    }

    @Transactional
    public void deleteMultiInHashBdd(String key, List<Integer> id){
        hashIntegerRedisTemplate.opsForHash().delete(key,id);
    }
}
