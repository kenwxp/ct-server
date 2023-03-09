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

# 4. 替换active profile
function replace_active_profiles {
  APP_YML=$1
  sed -i -E -e "s#^    (active: .*)\$#    \1,dev#" $APP_YML
}

# 5 修改admin服务配置文件
ADMIN_APP_YML='./cloudtimes-admin/target/resources/application.yml'
replace_admin_app_config $ADMIN_APP_YML
replace_active_profiles $ADMIN_APP_YML

# 6 修改business服务配置文件
BUSINESS_APP_YML='./cloudtimes-business/target/resources/application.yml'
replace_active_profiles $BUSINESS_APP_YML

# 7 修改socketserver服务配置文件
SOCKETSERVER_APP_YML='./cloudtimes-socketserver/target/resources/application.yml'
replace_active_profiles $SOCKETSERVER_APP_YML

# 8 修改detectionserver服务配置文件
DETECTIONSERVER_APP_YML='./cloudtimes-detectionserver/target/resources/application.yml'
replace_active_profiles $DETECTIONSERVER_APP_YML
