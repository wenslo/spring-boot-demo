
FROM adoptopenjdk/openjdk8
WORKDIR /root
ADD ./api/src/main/resources /root
ADD ./api/build/libs/api.jar /root
EXPOSE 8080
CMD java -jar api.jar