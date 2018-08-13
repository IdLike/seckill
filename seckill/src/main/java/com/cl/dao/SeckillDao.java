package com.cl.dao;

import com.cl.entity.Seckill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SeckillDao {

    /**
     * 减库存
     * @param seckillId  秒杀商品id
     * @param killTime 秒杀时间
     * @return
     */

    int reduceNumber(@Param("seckillId") Long seckillId, @Param("killTime") Date killTime);

    /**
     * 根据id查询秒杀商品
     * @param seckillId
     * @return
     */
    Seckill queryById(Long seckillId);


    /**
     * 查询秒杀商品列表
     * @param offset
     * @param limit
     * @return
     */
    List<Seckill> findAll(@Param("offset")int  offset, @Param("limit") int limit);
}
