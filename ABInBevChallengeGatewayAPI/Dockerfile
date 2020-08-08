FROM java:8
EXPOSE 9000
ADD /target/ABInBevChallengeGatewayAPI-1.0-SNAPSHOT.jar gatewayApiServer.jar
ENTRYPOINT ["java","-jar","gatewayApiServer.jar"]