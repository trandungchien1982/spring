echo 'Tien hanh build Apps SendMail ...'
cd ./send-mail
chmod +x gradlew
./gradlew build
cd ..
ls -l ./send-mail/build/libs


