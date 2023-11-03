echo 'Tien hanh build Apps LogService ...'
cd ./log-services-rabbitmq
chmod +x gradlew
./gradlew build
cd ..
ls -l ./log-services-rabbitmq/build/libs


