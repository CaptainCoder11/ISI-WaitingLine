#!/bin/bash
#
# Deploys the project on the server.
#
# When we deploy it locally, the developers can simply do
# `docker-compose up -d`
# When deploying it on the server, we must make sure to avoid loading
# the `docker-compose.override.yml` file.

docker-compose -f docker-compose.yml up --build -d 

