package com.cl.service;

import com.cl.dto.Exposer;
import com.cl.dto.SeckillExecution;
import com.cl.entity.Seckill;
import com.cl.exception.RepeatKillException;
import com.cl.exception.SeckillColseException;
import com.cl.exception.SeckillException;

import java.util.List;

/**
 * 业务接口：站在“使用者”角度设计接口
 * 方法定义的粒度
 * 参数
 * 返回值类型
 */
public interface SeckillService {

    List<Seckill> getSeckillList();

    Seckill getSeckillById(Long seckillId);

    /**
     * 秒杀开启输出秒杀接口地址
     * 否则输出系统时间和秒杀时间
     * @param seckillId
     */
     Exposer exportSeckillUrl(Long seckillId );

    /**
     * 执行秒杀操作
     * @param seckillId
     * @param userId
     * @param md5
     */
     SeckillExecution excuteSeckill(Long seckillId, Integer userId, String md5)throws
             SeckillException,SeckillColseException,RepeatKillException;
}
