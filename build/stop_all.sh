#!/usr/bin/bash

pids=$( ps -ef | grep java | grep cloudtimes | grep -v grep | awk '{print $2}' )
if [[ -n "$pids" ]]; then
  echo kill $pids
  kill -9 $pids
else
  echo "no cloudtimes services is running"
fi

exit 0