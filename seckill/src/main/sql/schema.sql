--数据库初始化脚本--
--创建数据库
CREATE DATABASE seckill;

use seckill;

--用户表--
CREATE TABLE user(
     user_id  int primary  key  COMMENT  "用户id",
     username VARCHAR(64) NOT NULL  COMMENT "用户名",
     password VARCHAR(64) NOT NULL  COMMENT "密码",
     create_time timestamp  NOT NULL  DEFAULT  CURRENT_TIMESTAMP  COMMENT "创建时间"
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT="用户表"

--秒杀库存表--
CREATE  TABLE seckill(
  seckill_id bigint NOT NULL AUTO_INCREMENT COMMENT "商品库存id",
  name  varchar(120) NOT NULL COMMENT "商品名称",
  number int NOT NULL COMMENT "库存数量",
  start_time timestamp  NOT NULL COMMENT "秒杀开始时间",
  end_time timestamp NOT NULL COMMENT "秒杀结束时间",
  create_time timestamp  NOT NULL  DEFAULT  CURRENT_TIMESTAMP  COMMENT "创建时间",
  PRIMARY key (seckill_id),
  key idx_start_time(start_time),
  key idx_end_time(end_time),
  key idx_create_time(create_time)

)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT="秒杀库存表"

insert  into seckill(name,number ,start_time,end_time)values ("1000元秒杀iPhone10")


--秒杀成功明细表--

CREATE TABLE success_killed(
     seckill_id bigint NOT NULL COMMENT "秒杀商品id",
     user_id int  NOT NULL COMMENT  "用户id",
     state tinyint NOT NULL DEFAULT -1 COMMENT "状态标识： -1:无效  0：成功 1：已付款  2：已发货",
     create_time timestamp  NOT NULL  DEFAULT  CURRENT_TIMESTAMP  COMMENT "创建时间",
     primary  key (seckill_id,user_id),
     key idx_ceate_time(create_time)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT="秒杀成功明细表"

