FROM openjdk:8u111-jdk-alpine

EXPOSE 8080
VOLUME /tmp
RUN mkdir /customeraccount
ADD CustomerAccount-0.0.1-SNAPSHOT.jar /customeraccount/CustomerAccount.jar
ADD stub2.properties stub2.properties
ADD logging.properties logging.properties
RUN sh -c 'touch /customeraccount/CustomerAccount.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Xmx512M","-jar","/customeraccount/CustomerAccount.jar"]
