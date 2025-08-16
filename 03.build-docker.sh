echo 'Tien hanh Build Docker cho SendMail'

echo 'Login vao Docker su dung Credential mac dinh tu truoc'
docker login


echo 'Build send-mail, version 1.0'
export MAIN_SERVICE=tdchien1982/spring:send-mail-1.0
cd send-mail
docker build . -t $MAIN_SERVICE
docker push $MAIN_SERVICE
cd ..
