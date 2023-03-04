#!/usr/bin/bash

WORK_DIR=/home/cloudtimes-server

function restart_service() {
  service=$1
  cd $WORK_DIR/$service/
  pwd
  sh run.sh restart
  sleep 10
  sh run.sh status
  exit_status=$?
  if [ $exit_status -eq 1 ]; then
      echo "启动服务 $service 失败 ..."
      exit 0
  else
      echo "启动服务 $service 成功 ..."
  fi
}

# declare an array variable
# declare -a services=("admin" "business" "socketserver" "detectionserver")
declare -a services=("admin" "business")

## now loop through the above array
for i in "${services[@]}"
do
  restart_service $i
done

exit 0