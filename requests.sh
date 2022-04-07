#!/usr/bin/env bash
set -x
curl -XPOST  -H 'Content-Type: application/json; charset=utf-8' "http://localhost:8080/api/v1/create-user-entry?first_name=James&last_name=Dean&email=james%40dean.com&phone=00000000001" -d '{}'

sleep 2

curl -H 'Content-Type: application/json; charset=utf-8' "http://localhost:8080/api/v1/find-user-entry?user_id=1" 
