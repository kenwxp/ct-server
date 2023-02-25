#!/usr/bin/bash

WORK_DIR=/home/cloudtimes-server
BACKUP_TIME=$(date "+%F_%H_%M")
declare -a SERVICES=("admin" "business" "socketserver" "detectionserver")

# 创建备份目录
ADMIN_SERVICE="$WORK_DIR/${SERVICES[0]}"
if [ -d "$ADMIN_SERVICE" ]; then
  BACKUP_DIR="$WORK_DIR/backup/$BACKUP_TIME"
  mkdir -p "$BACKUP_DIR"
else
  echo "服务${ADMIN_SERVICE}不存在，无需备份"
  exit 0
fi

# 逐一备份各个服务
for service in "${SERVICES[@]}"
do
  echo mv "$WORK_DIR/$service" "$BACKUP_DIR/"
  mv "$WORK_DIR/$service" "$BACKUP_DIR/"
  mkdir -p "$WORK_DIR/$service"
done

exit 0
