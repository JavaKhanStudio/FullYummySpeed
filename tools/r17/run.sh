#!/bin/sh
# r17: build the game, then play DrawProbe offscreen and write its PNGs to <outdir>.
#   tools/r17/run.sh tests/renders/r17/before
set -e
cd "$(dirname "$0")/../.."
OUT=$(realpath -m "${1:?usage: run.sh <outdir>}")
CP=$(./gradlew -q --no-configuration-cache -I tools/r17/cp.gradle :desktop:r17Classpath | sed -n 's/^R17CP=//p')
B=$(mktemp -d)
javac -nowarn -d "$B" -cp "$CP" tools/r17/DrawProbe.java tools/ProbeWindow.java
rm -rf "$OUT"; mkdir -p "$OUT"
cd core/assets
../../tools/offscreen.sh java --enable-native-access=ALL-UNNAMED -cp "$B:$CP" DrawProbe "$OUT"
ls "$OUT"
