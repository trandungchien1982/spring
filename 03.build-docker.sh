echo 'Tien hanh Build Docker cho RabbitMQ Retry'

echo 'Login vao Docker su dung Credential mac dinh tu truoc'
docker login


echo 'Build RabbitMQ Retry, version 1.0'
export MAIN_SERVICE=tdchien1982/spring:rabbitmq-retry-1.0
docker build . -t $MAIN_SERVICE
docker push $MAIN_SERVICE
cd ..
