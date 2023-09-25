echo "Stop MySQL+PostreSQL db ..."
echo "Stop+Remove Docker Containers to free resource :)"

docker-compose -f docker-compose-mysql.yml down
docker-compose -f docker-compose-postgres.yml down

echo "Finish stopping 2 DBs (MySQL+PostgreSQL) ..."

