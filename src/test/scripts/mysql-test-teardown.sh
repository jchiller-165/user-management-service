#!/bin/bash
set -e

PROJECT_NAME=$1

CONTAINER_NAME="test-db-${PROJECT_NAME}"

echo "🧹 Stopping and removing container '${CONTAINER_NAME}' ..."
docker stop "${CONTAINER_NAME}" || true
docker rm "${CONTAINER_NAME}" || true
echo "✅ Teardown complete."