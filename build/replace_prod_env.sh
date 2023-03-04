#!/bin/bash

#!/bin/bash

# 1. 开发环境参数配置

DEV_HOST='10.1.65.233'
DEV_DOMAIN='http://ctdev.htymeta.com'
DEV_MYSQL_USERNAME='username: root'
DEV_MYSQL_PASSWORD='password: 123456'
DEV_REDIS_PASSWORD='# password: ybf@2022'

ADMIN_DEV_PORT='port: 8080'
ADMIN_DEV_CONTEXT='context-path: /'

## TEST
DEV_OFFICIAL_APPID='official_appid: wx29331e0cdcf56bec'
DEV_OFFICIAL_SECRET='official_secret: cea61daf6976317b5c6f5fafb9400de8'

## PROD
PROD_OFFICIAL_APPID='official_appid: wxbcba794e0e6361a1'
PROD_OFFICIAL_SECRET='official_secret: 8910ff4f38a756a693e6941e78569001'

# 2. 测试环境参数配置

PROD_HOST='10.1.120.100'
PROD_DOMAIN='https://ct.htymeta.com'
PROD_MYSQL_USERNAME='username: root'
PROD_MYSQL_PASSWORD='password: Yunsd@2023'
PROD_REDIS_PASSWORD='password: 123456'

ADMIN_PROD_PORT='port: 8080'
ADMIN_PROD_CONTEXT='context-path: /admin-api'

# 3. 通用函数

function replace_admin_app_config() {
    ADMIN_YML=$1
    sed -i -E -e "s#^(\s+)$ADMIN_DEV_CONTEXT\$#\1$ADMIN_PROD_CONTEXT#" $ADMIN_YML
    sed -i -E -e "s#^(\s+)$ADMIN_DEV_PORT\$#\1$ADMIN_PROD_PORT#" $ADMIN_YML
}

function replace_app_config() {
    APP_YML=$1
    sed -i -E -e "s#$DEV_HOST#$PROD_HOST#" $APP_YML
    sed -i -E -e "s/^(\s+)$DEV_REDIS_PASSWORD\$/\1$PROD_REDIS_PASSWORD/" $APP_YML
    # diff $(echo $APP_YML | sed 's#resources#classes#') $APP_YML
}

function replace_mysql_config() {
    MYSQL_YML=$1
    sed -i -E -e "s#$DEV_HOST#$PROD_HOST#" $MYSQL_YML
    sed -i -E -e "s#^(\s+)$DEV_MYSQL_USERNAME\$#\1$PROD_MYSQL_USERNAME#" $MYSQL_YML
    sed -i -E -e "s#^(\s+)$DEV_MYSQL_PASSWORD\$#\1$PROD_MYSQL_PASSWORD#" $MYSQL_YML
    echo "========== $MYSQL_YML =========="
    # diff $(echo $MYSQL_YML | sed 's#resources#classes#') $MYSQL_YML
}

function replace_wechat_config() {
    WECHAT_YML=$1
    sed -i -E -e "s#$DEV_DOMAIN#$PROD_DOMAIN#" $WECHAT_YML
    sed -i -E -e "s#^(\s+)$DEV_OFFICIAL_APPID\$#\1$PROD_OFFICIAL_APPID#" $WECHAT_YML
    sed -i -E -e "s#^(\s+)$DEV_OFFICIAL_SECRET\$#\1$PROD_OFFICIAL_SECRET#" $WECHAT_YML
    echo "========== $WECHAT_YML =========="
    # diff $(echo $WECHAT_YML | sed 's#resources#classes#') $WECHAT_YML
}

function replace_mq_config() {
    MQ_YML=$1
    sed -i -E -e "s#$DEV_HOST#$PROD_HOST#" $MQ_YML
    echo "========== $MQ_YML =========="
    # diff $(echo $MQ_YML | sed 's#resources#classes#') $MQ_YML
}

# 4 修改admin服务配置文件
ADMIN_APP_YML='./cloudtimes-admin/target/resources/application.yml'
ADMIN_DRUID_YML='./cloudtimes-admin/target/resources/application-druid.yml'
ADMIN_ROCKETMQ_YML='./cloudtimes-admin/target/resources/application-rocketmq.yml'
ADMIN_WECHAT_YML='./cloudtimes-admin/target/resources/application-partner.yml'
replace_admin_app_config $ADMIN_APP_YML
replace_app_config $ADMIN_APP_YML
replace_mysql_config $ADMIN_DRUID_YML
replace_mq_config $ADMIN_ROCKETMQ_YML
replace_wechat_config $ADMIN_WECHAT_YML

# 5 修改business服务配置文件
BUSINESS_APP_YML='./cloudtimes-business/target/resources/application.yml'
BUSINESS_DRUID_YML='./cloudtimes-business/target/resources/application-druid.yml'
BUSINESS_ROCKETMQ_YML='./cloudtimes-business/target/resources/application-rocketmq.yml'
BUSINESS_WECHAT_YML='./cloudtimes-business/target/resources/application-partner.yml'
replace_app_config    $BUSINESS_APP_YML
replace_mysql_config  $BUSINESS_DRUID_YML
replace_mq_config     $BUSINESS_ROCKETMQ_YML
replace_wechat_config $BUSINESS_WECHAT_YML

# 6 修改socketserver服务配置文件
SOCKETSERVER_APP_YML='./cloudtimes-socketserver/target/resources/application.yml'
SOCKETSERVER_DRUID_YML='./cloudtimes-socketserver/target/resources/application-druid.yml'
SOCKETSERVER_ROCKETMQ_YML='./cloudtimes-socketserver/target/resources/application-rocketmq.yml'
replace_app_config    $SOCKETSERVER_APP_YML
replace_mysql_config  $SOCKETSERVER_DRUID_YML
replace_mq_config     $SOCKETSERVER_ROCKETMQ_YML


# 7 修改detectionserver服务配置文件
DETECTIONSERVER_APP_YML='./cloudtimes-detectionserver/target/resources/application.yml'
DETECTIONSERVER_ROCKETMQ_YML='./cloudtimes-detectionserver/target/resources/application-rocketmq.yml'
replace_app_config    $DETECTIONSERVER_APP_YML
replace_mq_config     $DETECTIONSERVER_ROCKETMQ_YML

exit 0
