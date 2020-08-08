
Here is some tips:

Authorization Header -> use only access_key
        - Right -> Authorization:{{access_key}}
        - Wrong -> Authorization:Bearer {{access_key}}

MongoDB Credentials -> configure url connection on application.properties of the microservices

Autorization -> Port 8001
Product -> Port 8000
GatewayApi -> Port 9000

Some configurations were mocked on utils.ConfigConstants to get a faster development, as:
        - Microservice address
        - Token Master Key
        - Token Expiration Time
        ....

All microservices are pointed to localhost
