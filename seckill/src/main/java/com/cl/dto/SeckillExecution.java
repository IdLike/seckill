package com.cl.dto;

import com.cl.emnum.SeckillStateEnum;
import com.cl.entity.SuccessSeckilled;

/**
 * 封装秒杀执行后结果
 */
public class SeckillExecution {
  private  Long seckillId;

  //秒杀执行结果的状态
  private int  state;

  private  String stateInfo;

  private SuccessSeckilled successSeckilled;

    public SeckillExecution(Long seckillId, SeckillStateEnum stateEnum, SuccessSeckilled successSeckilled) {
        this.seckillId = seckillId;
        this.state = stateEnum.getState();
        this.stateInfo =stateEnum.getStateInfo();
        this.successSeckilled = successSeckilled;
    }

    public SeckillExecution(Long seckillId, SeckillStateEnum stateEnum) {
        this.seckillId = seckillId;
        this.state = stateEnum.getState();
        this.stateInfo =stateEnum.getStateInfo();
    }

    public Long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(Long seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessSeckilled getSuccessSeckilled() {
        return successSeckilled;
    }

    public void setSuccessSeckilled(SuccessSeckilled successSeckilled) {
        this.successSeckilled = successSeckilled;
    }

    @Override
    public String toString() {
        return "SeckillExecution{" +
                "seckillId=" + seckillId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", successSeckilled=" + successSeckilled +
                '}';
    }
}
