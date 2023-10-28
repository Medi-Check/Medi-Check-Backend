FROM openjdk:17-oracle
COPY ./build/libs/Medi-Check-Backend-0.0.1-SNAPSHOT.jar Medi-Check.jar
ENTRYPOINT ["java", "-jar", "Medi-Check.jar"]