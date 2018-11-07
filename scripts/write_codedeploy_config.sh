#!/bin/bash

set -e

mkdir -p /var/codedeploy/candy-estimator

cat <<EOF >/var/codedeploy/candy-estimator/env.properties
APPLICATION_NAME=$APPLICATION_NAME
DEPLOYMENT_GROUP_NAME=$DEPLOYMENT_GROUP_NAME
DEPLOYMENT_ID=$DEPLOYMENT_ID
EOF
