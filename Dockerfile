FROM arm64v8/openjdk:17-jdk
COPY ./build/libs/Medi-Check-Backend-0.0.1-SNAPSHOT.jar Medi-Check.jar
ENTRYPOINT ["java", "-jar","-Dspring.profiles.active=main","Medi-Check.jar"]