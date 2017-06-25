
CREATE TABLE `menu` (
  `menu_id`  varchar(128) NOT NULL COMMENT '菜单id'  ,
  `menu_name`  varchar(100) NOT NULL COMMENT '菜单名称' ,
  `menu_link`  varchar(500) NULL COMMENT '菜单链接' ,
  `menu_icon`  varchar(500) NULL COMMENT '菜单对应的图标' ,
  `menu_type`  varchar(50) NULL COMMENT '菜单类型' ,
  `menu_order`  int NULL DEFAULT 0 COMMENT '在同一级别的菜单列表当中的顺序' ,
  `menu_parent_id`  varchar(128) NULL DEFAULT '0' COMMENT '父级菜单id，0为顶级菜单' ,
  `created_by`  varchar(255) NULL COMMENT '创建人' ,
  `updated_by`  varchar(255) NULL COMMENT '更新人' ,
  `created_time`  datetime NULL COMMENT '创建时间' ,
  `updated_time`  datetime NULL COMMENT '修改时间' ,
  `data_status`   varchar(1) DEFAULT NULL COMMENT '数据状态,0-停用,1-启用' ,
  PRIMARY KEY (`menu_id`)
)
;




