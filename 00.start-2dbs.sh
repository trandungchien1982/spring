echo Start MySQL+PostreSQL dbs ...

docker-compose -f docker-compose-mysql.yml up -d
docker-compose -f docker-compose-postgres.yml up -d

echo "Finish start 2 dbs: MySQL + PostgreSQL ..."
