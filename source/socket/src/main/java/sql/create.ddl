-- mysql -u root -p
-- password

CREATE database socket;

use socket;

-- account union key
-- 用户信息
create table users(
	`ID` bigint(11) not null auto_increment comment 'id',
	`ACCOUNT` varchar(16) NOT NULL comment '账号',
	`NAME` varchar(16) NOT NULL comment '姓名',
	`NIKENAME` varchar(64) comment '昵称',
	`PASSWORD` varchar(16) NOT NULL comment '密码',
	`AVATAR_PIC` varchar(256) comment '头像',
	`SEX` varchar(8) comment '性别',
	`BIRTHDAY` varchar(32) comment '出生年月',
	`EMAIL` varchar(256) comment '邮箱',
	`AGE` bigint(4) comment '年龄',
	`STATIS` varchar(8) comment '状态',
	`SIGNATURE` varchar(256) comment '签名',
	`ACTIVE` varchar(256) comment '是否激活',
	`STATUS_TYPE` varchar(8) comment '状态类型',
	`VALID_FLAG` varchar(8) comment '有效标志',
	`CREATE_TIME` datetime comment '创建时间',
	`UPDATE_TIME` datetime comment '更新时间',
	`LAST_LOGIN_TIME` datetime comment '最后登录时间',
	primary key(`ID`)

) ENGINE=MyISAM default CHARSET=UTF8 auto_increment=1;

-- 登录日志
create table login_log(
	`ID` bigint(11) not null auto_increment comment 'id',
	`ACCOUNT` varchar(16) NOT NULL comment '账号',
	`NAME` varchar(16) NOT NULL comment '姓名',
	`LOGIN_TIME` datetime comment '登录时间',
	`LOGIN_IP` varchar(64) comment '登录IP',
	`LOGIN_ADDRESS` varchar(256) comment '登录地址',
	`CREATE_TIME` datetime comment '创建时间',
	primary key(`ID`)

) ENGINE=MyISAM default CHARSET=UTF8 auto_increment=1;

-- 好友
create table friend_mapper(
	`ID` bigint(11) not null auto_increment comment 'id',
	`ACCOUNT` varchar(16) NOT NULL comment '账号',
	`FRIEND_ACCOUNT` varchar(16) NOT NULL comment '好友账号',
	`FRIEND_REMARK` varchar(16) NOT NULL comment '好友备注',
	`VALID_FLAG` varchar(8) comment '有效标志',
	`UPDATE_TIME` datetime comment '更新时间',
	`CREATE_TIME` datetime comment '创建时间',
	primary key(`ID`)
	
) ENGINE=MyISAM default CHARSET=UTF8 auto_increment=1;

-- 账号分组
create table account_group(
	`ID` bigint(11) not null auto_increment comment 'id',
	`ACCOUNT` varchar(16) NOT NULL comment '账号',
	`GROUP_ACCOUNT` varchar(16) NOT NULL comment '组内账号',
	`ACCOUNT_REMARK` varchar(64) comment '账号备注',
	`GROUP_ID` bigint(11) comment '组名',
	`CREATE_TIME` datetime comment '创建时间',
	primary key(`ID`)

) ENGINE=MyISAM default CHARSET=UTF8 auto_increment=1;

-- 好友分组
create table user_group(
	`ID` bigint(11) not null auto_increment comment 'id',
	`ACCOUNT` varchar(16) NOT NULL comment '账号',
	`GROUP_NAME` varchar(64) comment '组名',
	`ORDER` bigint(4) comment '分组顺序',
	`CREATE_TIME` datetime comment '创建时间',
	primary key(`ID`)

) ENGINE=MyISAM default CHARSET=UTF8 auto_increment=1;

-- 单人聊天室
create table chat_room(
	`ID` bigint(11) not null auto_increment comment 'id',
	`FROM` varchar(16) NOT NULL comment '发起人账号',
	`TO` varchar(16) NOT NULL comment '被发起人账号',
	`CREATE_TIME` datetime comment '创建时间',
	primary key(`ID`)
	
) ENGINE=MyISAM default CHARSET=UTF8 auto_increment=1;

-- 聊天记录
create table chat_message(
	`ID` bigint(11) not null auto_increment comment 'id',
	`ROOM_ID` bigint(11) not null comment '聊天会话ID',
	`FROM` varchar(16) NOT NULL comment '发起人账号',
	`TO` varchar(16) NOT NULL comment '被发起人账号',
	`TEXT` varchar(256) comment '文本内容',
	`AUDIO` varchar(256) comment '语音内容',
	`IMAGE` varchar(256) comment '图文内容',
	`TYPE` varchar(16) comment '聊天类型',
	`IS_READ` varchar(16) comment '是否已读',
	`CREATE_TIME` datetime comment '创建时间',
	primary key(`ID`)
	
) ENGINE=MyISAM default CHARSET=UTF8 auto_increment=1;

-- 讨论组
create table discussion_group(
	`ID` bigint(11) not null auto_increment comment 'id',
	`DGROUP_ID` varchar(16) NOT NULL comment '讨论组ID',
	`DGROUP_NAME` varchar(16) NOT NULL comment '讨论组名字',
	`ACCOUNT` varchar(16) NOT NULL comment '创建人',
	`ORDER` bigint(4) comment '讨论组显示顺序',
	`AVATAR` varchar(256) comment '讨论组头像',
	`INTRODUCTION` varchar(256) comment '讨论组介绍',
	`CREATE_TIME` datetime comment '创建时间',
	`UPDATE_TIME` datetime comment '更新时间',
	primary key(`ID`)
	
) ENGINE=MyISAM default CHARSET=UTF8 auto_increment=1;

-- 讨论组成员
create table discussion_group_menber(
	`ID` bigint(11) not null auto_increment comment 'id',
	`DGROUP_ID` varchar(16) NOT NULL comment '群ID',
	`ACCOUNT` varchar(16) NOT NULL comment '成员信息',
	`REMARK` varchar(64) comment '群备注',
	`CREATE_TIME` datetime comment '加入时间',
	primary key(`ID`)
	
) ENGINE=MyISAM default CHARSET=UTF8 auto_increment=1;

-- 用户群
create table friend_group(
	`ID` bigint(11) not null auto_increment comment 'id',
	`FGROUP_ID` varchar(16) NOT NULL comment '群ID',
	`FGROUP_NAME` varchar(16) NOT NULL comment '群名字',
	`ACCOUNT` varchar(16) NOT NULL comment '创建人',
	`ORDER` bigint(4) comment '群显示顺序',
	`AVATAR` varchar(256) comment '群头像',
	`INTRODUCTION` varchar(256) comment '群介绍',
	`CREATE_TIME` datetime comment '创建时间',
	`UPDATE_TIME` datetime comment '更新时间',
	primary key(`ID`)
	
) ENGINE=MyISAM default CHARSET=UTF8 auto_increment=1;

-- 群成员
create table friend_group_member(
	`ID` bigint(11) not null auto_increment comment 'id',
	`FGROUP_ID` varchar(16) NOT NULL comment '群ID',
	`ACCOUNT` varchar(16) NOT NULL comment '成员信息',
	`POSITION` varchar(16) comment '成员职位',
	`REMARK` varchar(64) comment '群备注',
	`CREATE_TIME` datetime comment '加入时间',
	primary key(`ID`)
	
) ENGINE=MyISAM default CHARSET=UTF8 auto_increment=1;

-- 多人聊天室
create table double_chat_room(
	`ID` bigint(11) not null auto_increment comment 'id',
	`GROUP_ID` varchar(16) NOT NULL comment '多人聊天组信息',
	`TYPE` varchar(16) NOT NULL comment '组类别，群 or 讨论组',
	`CREATE_TIME` datetime comment '创建时间',
	primary key(`ID`)
	
) ENGINE=MyISAM default CHARSET=UTF8 auto_increment=1;

-- 多人聊天记录
create table double_chat_message(
	`ID` bigint(11) not null auto_increment comment 'id',
	`ROOM_ID` bigint(11) not null comment '聊天会话ID',
	`FROM` varchar(16) NOT NULL comment '发起人账号',
	`TEXT` varchar(256) comment '文本内容',
	`AUDIO` varchar(256) comment '语音内容',
	`IMAGE` varchar(256) comment '图文内容',
	`TYPE` varchar(16) comment '聊天类型',
	`CREATE_TIME` datetime comment '创建时间',
	primary key(`ID`)
	
) ENGINE=MyISAM default CHARSET=UTF8 auto_increment=1;

-- 账号
create table login_account_no(
	`ID` bigint(11) not null auto_increment comment 'id',
	`NO` bigint(12) not null comment '登录账号',
	`TYPE` varchar(16) comment '账号类型',
	`VALID_FLAG` varchar(8) comment '有效性',
	`CREATE_TIME` datetime comment '创建时间',
	`UPDATE_TIME` datetime comment '修改时间',
	primary key(`ID`)
	
) ENGINE=MyISAM default CHARSET=UTF8 auto_increment=1;