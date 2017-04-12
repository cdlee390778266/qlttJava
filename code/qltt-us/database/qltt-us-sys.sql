#
# 应用渠道管理表
# Structure for table "tussysapp"
#
CREATE TABLE `tussysapp` (
  `Fs_appid` varchar(16) NOT NULL  COMMENT '应用接入id',
  `Fs_apptoken` varchar(255) NOT NULL  COMMENT '令牌',
  `Fs_appsecret` varchar(1024) NOT NULL  COMMENT '应用接入公钥',
  `Ft_regtime` date NOT NULL  COMMENT '创建时间',
  `Ft_updtime` date NOT NULL  COMMENT '更新时间',
  `Fi_status`  integer NOT NULL   COMMENT '账户状态',
  PRIMARY KEY pk_tussysapp(`Fs_appid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='应用渠道管理表';

#
# 接口调用日频率表
# Structure for table "Tussyspltdailycall"
#
CREATE TABLE `Tussyspltdailycall` (
  `Fs_appid` varchar(16) NOT NULL  COMMENT '应用接入id',
  `Fs_ptlno` varchar(8) NOT NULL  COMMENT '协议编号',
  `Ft_ptllastcalltime` date NOT NULL  COMMENT '最后调用该协议的时间',
  `Fi_ptlcallnum` integer NOT NULL  COMMENT '协议日调用次数',
  PRIMARY KEY pk_Tussyspltdailycall(`Fs_appid`,`Fs_ptlno`,`Ft_ptllastcalltime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='接口调用日频率表';