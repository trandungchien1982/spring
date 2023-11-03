FROM openjdk:8u312
MAINTAINER Chien Tran <trandungchien1982@gmail.com>

WORKDIR /usr/src/myapp
COPY "./build/libs/log-services-rabbitmq-0.0.1-SNAPSHOT.jar" .

# Run when creating container
CMD java -jar "log-services-rabbitmq-0.0.1-SNAPSHOT.jar"

# We will use port 8700
EXPOSE 8700
