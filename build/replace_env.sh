#!/bin/bash

PROFILE=$1
ACTIVE_PROFILE_OLD="active: "
ACTIVE_PROFILE_NEW="active: $PROFILE"

echo "ACTIVE_PROFILE: $ACTIVE_PROFILE_NEW"
declare -a services=("admin" "business" "socketserver" "detectionserver")
for service in "${services[@]}"
do
  APP_YML="./cloudtimes-$service/target/resources/application.yml"
  sed -i -E -e "s#^(\s+)$ACTIVE_PROFILE_OLD(.*)\$#\1$ACTIVE_PROFILE_NEW#" $APP_YML
done
