#!/bin/bash

# Script that define variables to use to connect to the DB in command line.
# Reads from `config.properties`. See the README for details.

fn="src/main/java/config.properties"
db_name="tonnerre2zeus_schema"

user=$(grep DB_LOGIN "$fn" | awk -F= '{print $2}')
hostname=$(grep JDBC_URL "$fn" | awk -F/ '{print $3}' | awk -F: '{print $1}')
password=$(grep DB_PASSWORD "$fn" | awk -F= '{print $2}')
