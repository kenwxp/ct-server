#!/bin/bash

# 1. 开发环境参数配置

ADMIN_DEV_PORT='port: 8080'
ADMIN_DEV_CONTEXT='context-path: /'

# 2. 测试环境参数配置

ADMIN_TEST_PORT='port: 8080'
ADMIN_TEST_CONTEXT='context-path: /admin-api'

# 3. 通用函数

function replace_admin_app_config() {
    ADMIN_YML=$1
    sed -i -E -e "s#^(\s+)$ADMIN_DEV_CONTEXT\$#\1$ADMIN_TEST_CONTEXT#" $ADMIN_YML
    sed -i -E -e "s#^(\s+)$ADMIN_DEV_PORT\$#\1$ADMIN_TEST_PORT#" $ADMIN_YML
}

# 4 修改admin服务配置文件
ADMIN_APP_YML='./cloudtimes-admin/target/resources/application.yml'
replace_admin_app_config $ADMIN_APP_YML