#!/bin/bash

source code/get_vars.sh

mysql -u "$user" -h "$hostname" --skip-ssl --password="$password" "$db_name" ||
mysql -u "$user" -h "$hostname" --password="$password" "$db_name"
