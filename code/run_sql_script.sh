#!/bin/bash

if [[ -z "$1" ]]; then
    echo "Usage:";
    echo "$0 path/to/sql_script.sql"
    exit;
fi

source get_vars.sh

mysql -u "$user" -h "$hostname" --skip-ssl --password="$password" "$db_name" < "$1"
