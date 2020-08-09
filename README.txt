
Here is some tips:

Authorization Header -> use only access_key
        - Right -> Authorization:{{access_key}}
        - Wrong -> Authorization:Bearer {{access_key}}

MongoDB Credentials -> 
        - Url connection on application.properties of the microservices
        - Default: mongodb://user:pass@localhost:27017

Autorization -> Port 8001
Product -> Port 8000
GatewayApi -> Port 9000

Some configurations were mocked on utils.ConfigConstants to get a faster development, as:
        - Microservice address
        - Token Master Key
        - Token Expiration Time
        ....

All microservices are pointed to localhost

I had some troubles with network on the containers, so i had to create some .sh to automate the deploy.