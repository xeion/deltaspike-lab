#!/bin/bash


if [ $# -eq 1 ]; then
  PORT=$1
elif [ ! -z "$ASADMIN_PORT" ]; then
  PORT=$ASADMIN_PORT
else
  echo "Ange en port som 4848 eller 4849 eller 4949"
  exit 1
fi

asadmin --port $PORT undeploy deltaspike
asadmin --port $PORT deploy --name deltaspike --contextroot deltaspike target/deltaspike-1.0.war
