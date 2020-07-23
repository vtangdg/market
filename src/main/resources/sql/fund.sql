CREATE TABLE `Fund` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(20) NOT NULL DEFAULT '' COMMENT '基金代码',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '基金简称',
  `incrY5` decimal(5,2) NOT NULL DEFAULT '0.00' COMMENT '5年涨幅',
  `cxL3` tinyint(4) NOT NULL DEFAULT '0' COMMENT '晨星3年评级',
  `cxL5` tinyint(4) NOT NULL DEFAULT '0' COMMENT '晨星5年评级',
  `cxDay` varchar(20) NOT NULL DEFAULT '' COMMENT '晨星评级日期',
  `heavyStock` varchar(1000) NOT NULL DEFAULT '' COMMENT '前十重仓股票',
  `stockRatio` decimal(5,2) NOT NULL DEFAULT '0.00' COMMENT '前十重仓占净值比(%)',
  `stockDay` varchar(20) NOT NULL DEFAULT '' COMMENT '股票统计日期',
  `queryDay` varchar(20) NOT NULL DEFAULT '' COMMENT '查询数据的日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='基金信息表';