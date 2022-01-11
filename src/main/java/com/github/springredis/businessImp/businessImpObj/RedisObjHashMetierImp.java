package com.github.springredis.businessImp.businessImpObj;


import com.github.springredis.business.RedisHashMetier;
import com.github.springredis.model.Obj;
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
public class RedisObjHashMetierImp implements RedisHashMetier {

    @Autowired
    private RedisTemplate<String, Object> hashObjRedisTemplate;

    Obj obj;

    @PostConstruct
    public void start(){
        System.out.println("/************************* List Integer *****************************/");
       /* obj = new Obj();
        obj.setAttr1(900);
        obj.setAttr2("hash obj1");
        obj.setAttr3(1000L);
        saveObjInHashBdd("hashObj:1", 800, obj);

        obj = new Obj();
        obj.setAttr1(901);
        obj.setAttr2("hash obj2");
        obj.setAttr3(1001L);
        saveObjInHashBdd("hashObj:1", 801, obj);*/
       // saveIntegerInHashBdd("hash:1",1,700);
       // saveIntegerInHashBdd("hash:1",2,701);
       // saveIntegerInHashBdd("hash:1",3,702);
        System.out.println("obj value in hash : " + hashObjRedisTemplate.opsForHash().get("hashObj:1",800));
    }

    @Transactional
    public void saveObjInHashBdd(String key, Integer id, Obj value){
        hashObjRedisTemplate.opsForHash().put(key, id, value);
    }

    @Transactional
    public void deleteInObjBdd(String key, Integer id){
        hashObjRedisTemplate.opsForHash().delete(key,id);
    }

    @Transactional
    public void deleteMultiInObjBdd(String key, List<Integer> id){
        hashObjRedisTemplate.opsForHash().delete(key,id);
    }
}
