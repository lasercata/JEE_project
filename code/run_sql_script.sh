#!/bin/bash

if [[ -z "$1" ]]; then
    echo "Usage:";
    echo "$0 path/to/sql_script.sql"
    exit;
fi

source code/get_vars.sh

mysql -u "$user" -h "$hostname" --password="$password" "$db_name" < "$1"
