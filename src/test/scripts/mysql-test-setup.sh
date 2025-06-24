#!/bin/bash
set -e

CONTAINER_REPO=$1
CONTAINER_VERSION=$2
PROJECT_NAME=$3
MYSQL_ENTRYPOINT=$4

CONTAINER_NAME="test-db-${PROJECT_NAME}"
MYSQL_ROOT_PASSWORD="password"
MYSQL_DATABASE="user_management"
MYSQL_PORT=3307

echo "👉 Pulling MySQL image ${CONTAINER_REPO}:${CONTAINER_VERSION}..."
docker pull --platform linux/amd64 "${CONTAINER_REPO}:${CONTAINER_VERSION}"

if [ "$(docker ps -aq -f name="${CONTAINER_NAME}")" ]; then
  echo "🔄 Removing old container ${CONTAINER_NAME}..."
  docker rm -f "${CONTAINER_NAME}"
fi

echo "🚀 Starting MySQL Docker container... with entrypoint scripts from ${MYSQL_ENTRYPOINT}..."
docker run --platform linux/amd64 -d \
  --name "${CONTAINER_NAME}" \
  -e MYSQL_ROOT_PASSWORD=${MYSQL_ROOT_PASSWORD} \
  -e MYSQL_DATABASE=${MYSQL_DATABASE} \
  -p ${MYSQL_PORT}:3306 \
  -v "${MYSQL_ENTRYPOINT}:/docker-entrypoint-initdb.d:ro,Z" \
  "${CONTAINER_REPO}:${CONTAINER_VERSION}"

echo "⏳ Waiting for MySQL to accept connections INSIDE the container..."
until docker exec "${CONTAINER_NAME}" mysqladmin ping -h "127.0.0.1" -uroot -p${MYSQL_ROOT_PASSWORD} --silent 2>/dev/null; do
  echo -n "."
  sleep 2
done
echo ""

echo "⏳ Verifying TCP port ${MYSQL_PORT} is reachable from host..."
until nc -z localhost ${MYSQL_PORT}; do
  echo -n "."
  sleep 2
done
echo ""

echo "✅ MySQL is healthy & reachable on localhost:${MYSQL_PORT}!"