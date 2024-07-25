#!/bin/bash

MODULES_DIR="/Users/mariesto/Desktop/java-project/payment-microservices-demo"
MODULES=( "wallet-service" )

# Loop through each module and build & push Docker images
for MODULE in "${MODULES[@]}"; do
    cd "${MODULES_DIR}/${MODULE}" || exit
    mvn clean install
    docker build -t "${MODULE}" .
done

# Navigate back to the project root directory
cd "${MODULES_DIR}" || exit

# Run Docker Compose
docker-compose -f docker-compose.yml up -d
