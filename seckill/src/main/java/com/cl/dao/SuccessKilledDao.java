package com.cl.dao;

import com.cl.entity.SuccessSeckilled;

import org.apache.ibatis.annotations.Param;

public interface SuccessKilledDao {
    /**
     * 插入购买明细，可以过滤重复
     * @param sekillId
     * @param userId
     * @return
     */
    int  insertSuccessKilled(@Param("seckllId") Long seckllId,@Param("userId") int userId);

    /**
     * 根据id查询，并查询出秒杀的商品实体
     * @param seckillId
     * @return
     */
    SuccessSeckilled queryByIdWithSeckill(@Param("seckillId") Long seckillId,@Param("userId") int userId);
}
