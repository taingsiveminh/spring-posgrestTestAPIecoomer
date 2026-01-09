#!/bin/sh
# Minimal Maven Wrapper bootstrap (macOS/Linux)

set -eu

BASE_DIR=$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)
WRAPPER_DIR="$BASE_DIR/.mvn/wrapper"
JAR="$WRAPPER_DIR/maven-wrapper.jar"

if [ ! -f "$JAR" ]; then
  echo "Missing $JAR. Download Maven Wrapper jar first." >&2
  exit 1
fi

exec java -classpath "$JAR" -Dmaven.multiModuleProjectDirectory="$BASE_DIR" org.apache.maven.wrapper.MavenWrapperMain "$@"
