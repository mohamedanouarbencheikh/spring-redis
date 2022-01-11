package com.github.springredis.stream;


import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.codec.StringCodec;
import io.lettuce.core.output.StatusOutput;
import io.lettuce.core.protocol.CommandArgs;
import io.lettuce.core.protocol.CommandKeyword;
import io.lettuce.core.protocol.CommandType;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.data.redis.stream.Subscription;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.time.Duration;
/**
 * @author Mohamed Anouar BENCHEIKH
 * @project springredis
 */

@Component
public class StreamConsumer implements StreamListener<String, MapRecord<String, Object, Object>>, InitializingBean,
        DisposableBean {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private StreamMessageListenerContainer<String, MapRecord<String, Object, Object>> listenerContainer;
    private Subscription subscription;
    private String consumerName;
    private String consumerGroupName;
    private String streamName;


    @Override
    public void onMessage(MapRecord<String, Object, Object> message) {
        try {
            String inputNumber = (String) message.getValue().get("number");
            String nameNumber = (String) message.getValue().get("name");
            System.out.println("--------- name " + nameNumber);
            System.out.println("--------- value " + inputNumber);
            redisTemplate.opsForStream().acknowledge("grConsumer", message);
            System.out.println("Message has been processed");
        } catch (Exception ex) {
            //log the exception and increment the number of errors count
            System.out.println("Failed to process the message: {} "+ message.getValue().get(1)+ ex);
            redisTemplate.opsForHash().increment("recordKey", "errors", 1);
        }

    }

    @Override
    public void destroy() throws Exception {
        if (subscription != null) {
            subscription.cancel();
        }

        if (listenerContainer != null) {
            listenerContainer.stop();
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        consumerName = "consumer2";
        consumerGroupName = "grConsumer1";
        streamName = "stream1";

        try {
            if (!redisTemplate.hasKey(streamName)) {
                RedisAsyncCommands commands = (RedisAsyncCommands) redisTemplate.getConnectionFactory()
                        .getConnection().getNativeConnection();
                CommandArgs<String, String> args = new CommandArgs<>(StringCodec.UTF8)
                        .add(CommandKeyword.CREATE)
                        .add(streamName)
                        .add(consumerGroupName)
                        .add("0")
                        .add("MKSTREAM");
                commands.dispatch(CommandType.XGROUP, new StatusOutput<>(StringCodec.UTF8), args);
            } else {
                redisTemplate.opsForStream().createGroup(streamName, ReadOffset.from("0"), consumerGroupName);
            }
        } catch (Exception ex) {
        }


        this.listenerContainer = StreamMessageListenerContainer.create(redisTemplate.getConnectionFactory(),
                StreamMessageListenerContainer
                        .StreamMessageListenerContainerOptions.builder()
                        .hashKeySerializer(new JdkSerializationRedisSerializer())
                        //.hashValueSerializer(new JdkSerializationRedisSerializer())
                        .pollTimeout(Duration.ofMillis(500))
                        .build());

        this.subscription = listenerContainer.receive(
                Consumer.from(consumerGroupName, consumerName),
                StreamOffset.create(streamName, ReadOffset.lastConsumed()),
                this);

        subscription.await(Duration.ofSeconds(2));
        listenerContainer.start();

    }
}
