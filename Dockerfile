FROM eclipse-temurin:21-jre-jammy
MAINTAINER Chien Tran <trandungchien1982@gmail.com>

WORKDIR /usr/src/myapp
COPY "./build/libs/barcodes-0.0.1-SNAPSHOT.jar" .

# Run when creating container
CMD java -jar "barcodes-0.0.1-SNAPSHOT.jar"

# We will use port 9091 as default
EXPOSE 9091
