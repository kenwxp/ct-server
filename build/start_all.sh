source /etc/profile
. ~/.bash_profile
#!/usr/bin/bash

WORK_DIR=/home/cloudtimes-server

function restart_service() {
  service=$1
  cd $WORK_DIR/$service/
  pwd
  sh run.sh restart
}

# declare an array variable
# declare -a services=("admin" "business" "socketserver" "detectionserver")
declare -a services=("admin" "business" "socketserver")

# 启动服务
for i in "${services[@]}"
do
  restart_service $i
done