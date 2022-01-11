package com.github.springredis.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.connection.stream.StringRecord;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author Mohamed Anouar BENCHEIKH
 * @project springredis
 */
@Component
public class StreamProducer {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @PostConstruct
    public void start(){
        produceNumbers();
    }

    public void produceNumbers() {
        Random random = new Random();
        while (true) {
            int number = random.nextInt(2000);
            Map<String, String> fields = new HashMap<>();
            fields.put("number", String.valueOf(number));
            fields.put("name", "name"+number);
            StringRecord record = StreamRecords.string(fields).withStreamKey("stream1");
            redisTemplate.opsForStream().add(record);
            System.out.println("Message has been published : {} "+ number);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Thread error: "+ e);
            }
        }
    }
}
