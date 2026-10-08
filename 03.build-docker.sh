echo 'Tien hanh Build Docker cho BarCode'

echo 'Login vao Docker su dung Credential mac dinh tu truoc'
docker login


echo 'Build bar-code, version 1.0'
export MAIN_SERVICE=tdchien1982/spring:barcodes-1.1
docker build . -t $MAIN_SERVICE
docker push $MAIN_SERVICE
cd ..
