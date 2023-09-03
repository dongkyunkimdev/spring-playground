#!/bin/bash

echo "Building docker image for discovery-server"
docker build -t discovery-server-image "./discovery-server"

echo "Building docker image for gateway-server"
docker build -t gateway-server-image "./gateway-server"

echo "Building docker image for user-service"
docker build -t user-service-image "./user-service"

echo "Building docker image for product-service"
docker build -t product-service-image "./product-service"

echo "Building docker image for order-service"
docker build -t order-service-image "./order-service"

echo "Building docker image for delivery-service"
docker build -t delivery-service-image "./delivery-service"

echo "All docker images built successfully"
