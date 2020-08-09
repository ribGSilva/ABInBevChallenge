#!/bin/bash

echo "Building microservices..."

sudo docker build -t authorization:1.0 ./ABInBevChallengeAuthorization/

sudo docker run -d --network host --name authorization authorization:1.0

sudo docker stop authorization

sudo docker build -t product:1.0 ./ABInBevChallengeProduct/

sudo docker run -d --network host --name product product:1.0

sudo docker stop product

sudo docker build -t gatewayapi:1.0 ./ABInBevChallengeGatewayAPI/

sudo docker run -d --network host --name gatewayapi gatewayapi:1.0

sudo docker stop product

echo "Done! Now, run the 'start.sh' script to start everything."