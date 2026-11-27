#!/bin/sh
# r19: build the game, then play FlowProbe offscreen and write its PNGs to <outdir>.
#   tools/r19/run.sh tests/renders/r19/before
set -e
cd "$(dirname "$0")/../.."
OUT=$(realpath -m "${1:?usage: run.sh <outdir>}")
CP=$(./gradlew -q --no-configuration-cache -I tools/r19/cp.gradle :desktop:r19Classpath | sed -n 's/^R19CP=//p')
B=$(mktemp -d)
javac -nowarn -d "$B" -cp "$CP" tools/r19/FlowProbe.java
rm -rf "$OUT"; mkdir -p "$OUT"
cd core/assets
../../tools/offscreen.sh java --enable-native-access=ALL-UNNAMED --add-opens java.base/java.util=ALL-UNNAMED \
  -cp "$B:$CP" FlowProbe "$OUT"
ls "$OUT"
