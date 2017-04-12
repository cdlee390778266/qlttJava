#
# 2.1.1 账户注册管理表(手机号识别)
# Structure for table "tusacctcnreg"
#
CREATE TABLE `tusacctcnreg` (
  `Fs_ttacct` varchar(16) NOT NULL  COMMENT '推推账号',
  `Fs_CN` varchar(16) NOT NULL  COMMENT '手机号',
  `Ft_regtime` date NOT NULL  COMMENT '注册时间',
  `Ft_updtime` date NOT NULL  COMMENT '更新时间',
  `Fi_status`  integer NOT NULL   COMMENT '账户状态',
   PRIMARY KEY pk_tusacctcnreg(`Fs_ttacct`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账户注册管理表(手机号识别)';

#
# 2.1.2 账户基本信息表
# Structure for table "tusacctbaseinfo"
#
CREATE TABLE `tusacctbaseinfo` (
  `Fs_ttacct` varchar(16) NOT NULL  COMMENT '推推账号',
  `Fs_name` varchar(64)  COMMENT '账户名称',
   PRIMARY KEY pk_tusacctbaseinfo(`Fs_ttacct`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账户基本信息表';


#
# 2.1.3 账户绑定关系表
# Structure for table "tusbindrel"
#
CREATE TABLE `tusbindrel` (
  `Fs_ttacct` varchar(16) NOT NULL  COMMENT '推推账号',
  `Fi_svcchnl` integer  NOT NULL  COMMENT '服务渠道',
  `Fs_bindacct` varchar(64)  NOT NULL  COMMENT '渠道绑定账号',
   PRIMARY KEY pk_tusbindrel(`Fs_ttacct`,`Fi_svcchnl`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账户绑定关系表';
ALTER TABLE `tusbindrel` ADD UNIQUE index_tusbindrel_1(`Fi_svcchnl`,`Fs_bindacct`);

#
# 2.3.1	账户渠道设备信息表
# Structure for table "tuschnldevinfo"
#
CREATE TABLE `tuschnldevinfo` (
  `Fs_ttacct` varchar(16) NOT NULL  COMMENT '推推账号',
  `Fi_svcchnl` integer  NOT NULL  COMMENT '服务渠道',
  `Fs_devno` varchar(64)  NOT NULL  COMMENT '设备号',
  `Fi_devtype` integer  NOT NULL  COMMENT '设备类型',
  `Fi_switch` Integer  NOT NULL  COMMENT '推送开关',
  PRIMARY KEY pk_tuschnldevinfo(`Fs_ttacct`,`Fi_svcchnl`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账户渠道设备信息表';