CREATE TABLE `role` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `domain_id` varchar(2) NOT NULL DEFAULT '01' COMMENT '角色所属系统代号',
  `role_key` varchar(30) NOT NULL COMMENT '角色主键，例如：visitor或admin',
  `role_name` varchar(30) NOT NULL COMMENT '角色中文名',
  `created_by` varchar(45) DEFAULT NULL COMMENT '创建人',
  `updated_by` varchar(45) DEFAULT NULL COMMENT '更新人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `data_status` varchar(1) DEFAULT '1' COMMENT '数据状态，0-停用，1-启用',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_key_UNIQUE` (`role_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色信息';

CREATE TABLE `link_user_role` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `role_id` varchar(32) NOT NULL COMMENT '角色id',
  `created_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `updated_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='链接用户和其对应的角色';
