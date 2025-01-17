@echo off
echo Starting backend...
cd Restaurant
start cmd /k mvnw spring-boot:run

echo Starting frontend...
cd ../frontend/restaurant
start cmd /k npm start