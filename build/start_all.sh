#!/usr/bin/bash

WORK_DIR=/home/cloudtimes-server

function restart_service() {
  service=$1
  cd $WORK_DIR/$service/
  pwd
  sh run.sh restart
  sleep 3
  sh run.sh status
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