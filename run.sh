#!/bin/bash

echo "Starting backend..."
cd Restaurant
./mvnw spring-boot:run &

echo "Starting frontend..."
cd ../frontend/restaurant
npm start
