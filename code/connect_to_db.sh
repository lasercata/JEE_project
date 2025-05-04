#!/bin/bash

source get_vars.sh

mysql -u "$user" -h "$hostname" --skip-ssl --password="$password" "$db_name"
