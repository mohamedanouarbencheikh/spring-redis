package com.github.springredis.businessImp.businessImpObj;


import com.github.springredis.business.businessObj.RedisObjSetMetier;
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
public class RedisObjSetMetierImp implements RedisObjSetMetier {

    @Autowired
    private RedisTemplate<String, Object> setObjRedisTemplate;
    Obj obj;
    @PostConstruct
    public void start(){
        /*obj = new Obj();
        obj.setAttr1(2000);
        obj.setAttr2("setObj1");
        obj.setAttr3(2000L);
        saveObjInSetBdd("setObj:1",obj);
        obj = new Obj();
        obj.setAttr1(2001);
        obj.setAttr2("setObj1");
        obj.setAttr3(2001L);
        saveObjInSetBdd("setObj:1",obj);*/
        //deleteObjInSetBdd("setObj:1");
        System.out.println("obj value in set : " + setObjRedisTemplate.opsForSet().members("setObj:1"));
        System.out.println("size obj set : " + setObjRedisTemplate.opsForSet().size("setObj:1"));
    }

    @Transactional
    public void saveObjInSetBdd(String key, Obj value){
        setObjRedisTemplate.opsForSet().add(key, value);
    }

    @Transactional
    public void deleteObjInSetBdd(String key){
        setObjRedisTemplate.opsForSet().pop(key);
    }
}
