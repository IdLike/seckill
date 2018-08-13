package com.cl.dto;

/**
 * 暴露秒杀地址
 */
public class Exposer {

    //是否开启秒杀
    private  Boolean exposed;
    //md5
    private String md5;

    private  Long seckillId;

    private  Long nowDate;

    private  Long startTime;

    private  Long endTime;

    public Exposer(Boolean exposed, String md5, Long seckillId) {
        this.exposed = exposed;
        this.md5 = md5;
        this.seckillId = seckillId;
    }

    public Exposer(Boolean exposed, Long seckillId,Long nowDate, Long startTime, Long endTime) {
        this.exposed = exposed;
        this.seckillId = seckillId;
        this.nowDate = nowDate;
        this.startTime = startTime;
        this.endTime = endTime;

    }

    public Exposer(Boolean exposed, Long seckillId) {
        this.exposed = exposed;
        this.seckillId = seckillId;
    }

    public Boolean getExposed() {
        return exposed;
    }

    public void setExposed(Boolean exposed) {
        this.exposed = exposed;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public Long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(Long seckillId) {
        this.seckillId = seckillId;
    }

    public Long getNowDate() {
        return nowDate;
    }

    public void setNowDate(Long nowDate) {
        this.nowDate = nowDate;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Exposer{" +
                "exposed=" + exposed +
                ", md5='" + md5 + '\'' +
                ", seckillId=" + seckillId +
                ", nowDate=" + nowDate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
