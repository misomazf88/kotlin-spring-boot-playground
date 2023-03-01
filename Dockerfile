FROM openjdk:8-jre-alpine

RUN mkdir /code
COPY dermo-diagnostic-app/build/libs /code

ENTRYPOINT [ "sh", "-c", "java -jar -Dspring.profiles.active=prod -Duser.timezone=America/Bogota -Dnetworkaddress.cache.ttl=60 -Dnetworkaddress.cache.negative.ttl=10 /code/dermo-diagnostic-app-boot.jar" ]