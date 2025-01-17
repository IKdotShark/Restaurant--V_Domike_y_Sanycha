#!/bin/bash

echo "Starting backend..."
cd Restaurant
./mvnw spring-boot:run -Dspring-boot.run.arguments=--server.port=8088 &

echo "Starting frontend..."
cd ../frontend/restaurant
npm start
