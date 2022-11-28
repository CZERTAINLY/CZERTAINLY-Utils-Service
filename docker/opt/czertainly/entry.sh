#!/bin/sh

czertainlyHome="/opt/czertainly"
source ${czertainlyHome}/static-functions

log "INFO" "Launching Utils Service"
java -jar ./app.jar

#exec "$@"