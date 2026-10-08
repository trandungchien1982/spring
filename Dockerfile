FROM openjdk:21-slim
MAINTAINER Chien Tran <trandungchien1982@gmail.com>

WORKDIR /usr/src/myapp
COPY "./build/libs/send-mail-0.0.1-SNAPSHOT.jar" .

# Run when creating container
CMD java -jar "send-mail-0.0.1-SNAPSHOT.jar"

# We will use port 9090 as default
EXPOSE 9090
