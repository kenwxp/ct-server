drop table if exists ct_store ;
CREATE TABLE ct_store (
                          id int auto_increment NOT NULL COMMENT '序列号',
                          name varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '店铺名称',
                          store_code varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '门店编码',
                          owner_code varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '店主编号',
                          merchant_code varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '商户ID',
                          address varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '详细地址',
                          short_address varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '短地址',
                          region_code varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT ' 地区号',
                          longitude decimal(10,0) NULL COMMENT '经度',
                          latitude decimal(10,0) NULL COMMENT '纬度',
                          photo_url text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '门头照片',
                          area varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '面积',
                          contact_name varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '联系人姓名',
                          contact_phone varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '联系电话',
                          state int NULL COMMENT '运行状态',
                          duty_state int NULL COMMENT '值守状态',
                          create_time datetime NULL COMMENT '新增时间',
                          update_time datetime NULL COMMENT '修改时间',
                          del_flag int NULL COMMENT ' 是否删除',
                          CONSTRAINT `PRIMARY` PRIMARY KEY (id)
)
    ENGINE=InnoDB
DEFAULT CHARSET=utf8mb3
COLLATE=utf8mb3_general_ci
COMMENT='门店表';