DROP DATABASE IF EXISTS ORDERLOG;
CREATE DATABASE ORDERLOG default charset utf8 COLLATE utf8_general_ci;
USE ORDERLOG;


-- ----------------------------
--  Table structure for `orderin_historylog`
-- ----------------------------
DROP TABLE IF EXISTS `orderin_historylog`;
CREATE TABLE `orderin_historylog` (
  `ORDERID` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '订单号',
  `ORDERTIME` varchar(30) NOT NULL COMMENT '订单时间',
  `ORDERDESCRIPTION` varchar(200) NOT NULL COMMENT '订单描述',
  `PAYTYPE` varchar(3) NOT NULL COMMENT '订单支付类型',
  `ITEMID` varchar(5) NOT NULL COMMENT '订单商品ID',
  `ITEMCNT` varchar(5) NOT NULL COMMENT '订单商品数量',
  `GAMENAME` varchar(20) NOT NULL COMMENT '订单游戏名称',
  `GAMEVERSION` varchar(10) NOT NULL COMMENT '订单游戏版本',
  `STATUS` varchar(10) NOT NULL COMMENT '订单状态',
  `USERID` varchar(5) NOT NULL COMMENT '订单用户',
  PRIMARY KEY (`ORDERID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `union_successorderlog`
-- ----------------------------
DROP TABLE IF EXISTS `union_successorderlog`;
CREATE TABLE `union_successorderlog` (
  `ORDERID` varchar(20) NOT NULL  COMMENT '订单',
  `ORDERTIME` varchar(30) NOT NULL COMMENT '交易类型',
  `SETTLEDATE` varchar(30) NOT NULL COMMENT '清算时间',
  `SELAMT` varchar(12) NOT NULL COMMENT '清算金额',
  `SETLCURRENCY` varchar(5) NOT NULL COMMENT '清算币种',
  `CONVERRATE` varchar(10) NOT NULL COMMENT '清算汇率',
  `CUPSQID` varchar(30) NOT NULL COMMENT 'CUPS交易流水号',
  `CUPSTRACENUM` varchar(30) NOT NULL COMMENT 'CUPS系统跟踪号',
  `CUPSTRACETIME` varchar(30) NOT NULL COMMENT 'CUPS系统跟踪时间',
  `CUPSRESPCODE` varchar(2) NOT NULL COMMENT 'CUPS系统跟踪码',
  `CUPSRESPDESC` varchar(10) NOT NULL COMMENT 'CUPS系统跟踪描述',
  PRIMARY KEY (`ORDERID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;