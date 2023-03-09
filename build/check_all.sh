#!/usr/bin/bash

WORK_DIR=/home/cloudtimes-server

function check_service() {
  service=$1
  cd $WORK_DIR/$service/
  sh run.sh status
  exit_status=$?
  if [[ $exit_status -eq 1 ]]; then
      echo "启动服务 $service 失败 ..."
      exit 1
  else
      echo "启动服务 $service 成功 ..."
  fi
}

# declare an array variable
declare -a services=("admin" "business" "socketserver" "detectionserver")

# 检查服务启动状态
for i in "${services[@]}"
do
  check_service $i
done

exit 0
