#!/bin/bash

echo "Starting Microservices..."

sudo docker start authorization

sudo docker start product

sudo docker start gatewayapi

echo "Done!"