FROM openjdk:11
COPY ./target/airtravels-0.0.1-SNAPSHOT.jar /usr/src/jms-201204-sender/
WORKDIR /usr/src/jms-201204-sender/
EXPOSE 8080
CMD ["java", "-jar", "airtravels-0.0.1-SNAPSHOT.jar"]