#!/bin/bash

echo "Removing Microservices..."

sudo docker rm gatewayapi

sudo docker rm product

sudo docker rm authorization

echo "Done!"