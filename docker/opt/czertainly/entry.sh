#!/bin/sh

czertainlyHome="/opt/czertainly"
source ${czertainlyHome}/static-functions

log "INFO" "Launching Utils Service"
java $JAVA_OPTS -jar ./app.jar

#exec "$@"