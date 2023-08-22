#!/bin/bash

MODULES_DIR="/Users/amendomariestositinjak/Desktop/projects/payment-microservices-demo/payment-microservices-demo"

MODULES=( "wallet-service" "payment-service" )

for MODULE in "${MODULES[@]}"
do
    cd "${MODULES_DIR}/${MODULE}"
    mvn clean package
    docker build -t "${MODULE}" .
done

# shellcheck disable=SC2164
cd /Users/amendomariestositinjak/Desktop/projects/payment-microservices-demo/payment-microservices-demo
docker-compose -f docker-compose.yml up -d