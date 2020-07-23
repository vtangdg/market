CREATE TABLE `FundStock` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(20) NOT NULL DEFAULT '' COMMENT '基金代码',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '基金简称',
  `rank` tinyint(4) NOT NULL DEFAULT '0' COMMENT '在所处基金中排名',
  `stockName` varchar(20) NOT NULL DEFAULT '' COMMENT '股票名称',
  `stockValue` varchar(20) NOT NULL DEFAULT '' COMMENT '持股市值',
  `stockNum` varchar(20) NOT NULL DEFAULT '' COMMENT '持股数量',
  `stockChange` varchar(20) NOT NULL DEFAULT '' COMMENT '持股变动',
  `valueRate` decimal(5,2) NOT NULL DEFAULT '0.00' COMMENT '占净值比（%）',
  `totalValueRate` decimal(5,2) NOT NULL DEFAULT '0.00' COMMENT '占总值比（%）',
  `stockValueRate` decimal(5,2) NOT NULL DEFAULT '0.00' COMMENT '占股票投资比（%）',
  `fundId` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '关联fund表id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5351 DEFAULT CHARSET=utf8 COMMENT='基金持仓股票信息表';