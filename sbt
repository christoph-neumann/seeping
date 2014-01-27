#!/bin/bash

version="0.13.1"

# Ensure the cache directory for the SBT launcher exists.
sbt_cache="$HOME/.sbt/launch"
test ! -d "$sbt_cache" && mkdir -p "$sbt_cache"

# Download the SBT launcher if it hasn't been downloaded already.
launch_jar="$sbt_cache/sbt-launch-$version.jar"
if [ ! -f $launch_jar ]; then
	echo "Fetching the SBT launcher. Saving as $launch_jar"
	curl -o "$launch_jar" http://repo.typesafe.com/typesafe/ivy-releases/org.scala-sbt/sbt-launch/$version/sbt-launch.jar
fi

# Start SBT
java -Xms512M -Xmx1536M -Xss1M -XX:+CMSClassUnloadingEnabled -XX:MaxPermSize=384M -jar "$launch_jar" "$@"
