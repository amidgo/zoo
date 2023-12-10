DB_USER := amidman
DB_PASSWORD := sadlfjqweoirjjalsdkfn
EXTERNAL_PORT := 21446
INTERNAL_PORT := 3306
DB_NAME := zoo
LOCAL_CONTAINER_NAME := zoo-localdb
LOCAL_DB_URL := mysql://${DB_USER}:${DB_PASSWORD}@tcp(localhost:${EXTERNAL_PORT})/${DB_NAME}

database:
	docker run --name ${LOCAL_CONTAINER_NAME} \
	-v ./.tmp/db:/var/lib/mysql \
	-e MYSQL_USER=${DB_USER} \
	-e MYSQL_ROOT_PASSWORD=${DB_PASSWORD} \
	-e MYSQL_PASSWORD=${DB_PASSWORD} \
	-e MYSQL_DATABASE=${DB_NAME} \
  	-e MYSQL_TCP_PORT:${INTERNAL_PORT} \
	-p ${EXTERNAL_PORT}:${INTERNAL_PORT} \
	-d mysql:8.0-debian \
	--character-set-server=utf8mb4 \
  	--collation-server=utf8mb4_unicode_ci
database-stop:
	docker stop ${LOCAL_CONTAINER_NAME}
	docker rm ${LOCAL_CONTAINER_NAME}
migrate-up:
	migrate -path ./migrations/ -database '${LOCAL_DB_URL}' -verbose up 1
migrate-force:
	migrate -path ./migrations/ -database '${LOCAL_DB_URL}' force 1
migrate-down:
	migrate -path ./migrations/ -database '${LOCAL_DB_URL}' -verbose down 1
build:
	./mvnw clean package
run:
	./mvnw spring-boot:run