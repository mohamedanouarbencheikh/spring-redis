package com.github.springredis.businessImp.businessImpObj;


import com.github.springredis.business.businessObj.RedisObjListMetier;
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
public class RedisObjListMetierImp implements RedisObjListMetier {

    @Autowired
    private RedisTemplate<String, Object> listObjRedisTemplate;
    private Obj obj;
    @PostConstruct
    public void start(){
       // System.out.println("/************************* List obj *****************************/");
      /* obj = new Obj();
        obj.setAttr1(100);
        obj.setAttr2("obj1");
        obj.setAttr3(200L);
        leftSaveObjInListBdd("listObj:1", obj);
        obj = new Obj();
        obj.setAttr1(101);
        obj.setAttr2("obj2");
        obj.setAttr3(201L);
        rightSaveObjInListBdd("listObj:2", obj);
        leftDeleteObjInListBdd("listObj:1");
        rightDeleteObjInListBdd("listObj:1");*/
        //deleteObjInListBdd("listObj:1", obj);

     //   System.out.println("size list obj : " + listObjRedisTemplate.opsForList().size("listObj:1"));
       // System.out.println("obj value in index : " + listObjRedisTemplate.opsForList().index("listObj:1", 0));
    }

    @Transactional
    public void leftSaveObjInListBdd(String key, Obj value){
        listObjRedisTemplate.opsForList().leftPush(key, value);
    }

    @Transactional
    public void rightSaveObjInListBdd(String key, Obj value){
        listObjRedisTemplate.opsForList().rightPush(key, value);
    }

    @Transactional
    public void leftDeleteObjInListBdd(String key){
        listObjRedisTemplate.opsForList().leftPop(key);
    }

    @Transactional
    public void rightDeleteObjInListBdd(String key){
        listObjRedisTemplate.opsForList().rightPop(key);
    }

    @Transactional
    public void deleteObjInListBdd(String key, Obj value){
        listObjRedisTemplate.opsForList().remove(key, 1, value);
    }
}
