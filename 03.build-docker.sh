echo 'Tien hanh Build Docker cho LogService + Su dung RabbitMQ'

echo 'Login vao Docker su dung Credential mac dinh tu truoc'
docker login


echo 'Build log-services-rabbitmq, version 2.0'
export MAIN_SERVICE=tdchien1982/spring:log-services-rabbitmq-2.0
cd log-services-rabbitmq
docker build . -t $MAIN_SERVICE
docker push $MAIN_SERVICE
cd ..
