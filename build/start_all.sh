#!/bin/sh

function restart_service() {
  service=$1
  cd ~/cloudtimes/$service/
  pwd
  sh run.sh restart
  sleep 3
  sh run.sh status
}

restart_service admin
restart_service business
restart_service socketserver
restart_service detectionserver
