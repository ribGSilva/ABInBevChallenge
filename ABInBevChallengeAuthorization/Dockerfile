FROM java:8
EXPOSE 8001
ADD /target/ABInBevChallengeAuthorization-1.0-SNAPSHOT.jar authorizationServer.jar
ENTRYPOINT ["java","-jar","authorizationServer.jar"]