#!/usr/bin/env bash

# Switch to the directory containing the script.
cd $(dirname $(readlink "$0" || echo "$0"))

# Run the app, bulding it if necessary.
JAR="target/scala-2.10/seeping_2.10-0.1-SNAPSHOT-one-jar.jar"
if [ ! -f "$JAR" ]; then
	./sbt one-jar
fi
java -server -jar "$JAR" $*
