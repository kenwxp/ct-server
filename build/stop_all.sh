#!/bin/sh

pids=$( ps -ef | grep java | grep cloudtimes | grep -v grep | awk '{print $2}' )
echo kill $pids
kill -9 $pids