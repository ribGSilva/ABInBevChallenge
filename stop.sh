#!/bin/bash

echo "Starting Microservices..."

sudo docker stop gatewayapi

sudo docker stop product

sudo docker stop authorization

echo "Done!"