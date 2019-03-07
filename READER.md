### 考试表
0:id号.
1:考试名称.
2:考试类别编码(编码唯一).
CREATE TABLE KSLB (
    id   VARCHAR (32),
    LBMC VARCHAR (50),
    BM   VARCHAR (5) 
);


### 考试题目表
0:id号.
1:考试表id号.
2:题目名称.
3:题目序号.
4:题目选项id.
CREATE TABLE KSTM (
    id     VARCHAR (32) PRIMARY KEY,
    KSLBID VARCHAR (32),
    KSTMMC VARCHAR (32),
    KSTMXH INTEGER (3),
    TMXXLBID VARCHAR (32) 
);


### 考试题目选项类别
0:id号.
1:选项类别.
2:选项名称.
CREATE TABLE TMXXLB (
    id   VARCHAR (32),
    LBMC VARCHAR (32),
    LB   VARCHAR (2) 
);


### 考试题目选项
0:id号.
1:考试题目选项类别id.
2:选项名称.
3:选项代码.
CREATE TABLE TMXX (
    id       VARCHAR (32) PRIMARY KEY,
    TMXXLBID VARCHAR (32),
    XXMC     VARCHAR (32),
    XXDM     VARCHAR (2) 
);


### 考试结果表
0:id号
1:考试类别编码
2:考试名称
3:考试人登陆编号
4:考试是否完成标记(所有的题)
CREATE TABLE KSJG (
    id     VARCHAR (32) PRIMARY KEY,
    KSLBBM VARCHAR (5),
    KSMC   VARCHAR (32),
    BH     VARCHAR (25),
    SFWC   VARCHAR (1) 
);



### 考试结果内容表
0:id号
1:考试结果表id
2:考题序号
3:考题答案
4:考题名称
``` sql
CREATE TABLE KSJGNR (
    id     VARCHAR (32) PRIMARY KEY,
    KSJGID VARCHAR (32),
    KTXH   INTEGER (3),
    KTDA   VARCHAR (2),
    KTMC   VARCHAR (32) 
);
```
