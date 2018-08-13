package com.cl.dao.cache;

import com.cl.entity.Seckill;
import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 *
 * 使用redis缓存秒杀地址
 */
public class RedisDao {


    private JedisPool jedisPool;
    //自定义序列化
    private RuntimeSchema<Seckill>seckillRuntimeSchema=RuntimeSchema.createFrom(Seckill.class);

    public  RedisDao (String ip,int port){

        jedisPool=new JedisPool(ip,port);
    }

    public Seckill getSeckill(Long seckillId){

        try{
            Jedis jedis=jedisPool.getResource();
            try{
                String key="seckill:"+seckillId;
                //并没有实现内部序列化操作
                //采用自定义序列化
                byte[] bytes= jedis.get(key.getBytes());
                if(bytes!=null){
                    //反序列seckill
                    Seckill seckill=seckillRuntimeSchema.newMessage();
                    ProtobufIOUtil.mergeFrom(bytes,seckill,seckillRuntimeSchema);
                    return  seckill;
                }

            }finally {
                jedis.close();
            }

        }catch (Exception e){
            e.printStackTrace();

        }

        return  null;

    }

    public  String putSeckill(Seckill seckill){

        //把seckill对象序列化成字节数组发送给redis

        Jedis jedis=jedisPool.getResource();
        try {
            String key="seckill:"+seckill.getSeckillId();
            byte [] bytes=ProtobufIOUtil.toByteArray(seckill,seckillRuntimeSchema,LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));

            //缓存一小时
            return  jedis.setex(key.getBytes(),60*60,bytes);

        }catch (Exception e){

        }finally {

            jedis.close();
        }
        return  null;

    }
}
