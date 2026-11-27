#!/bin/sh
# r21: build the game, then open EditorProbe offscreen and write its PNGs to <outdir>.
#   tools/r21/run.sh tests/renders/r21/before
set -e
cd "$(dirname "$0")/../.."
OUT=$(realpath -m "${1:?usage: run.sh <outdir>}")
CP=$(./gradlew -q --no-configuration-cache -I tools/r21/cp.gradle :desktop:r21Classpath | sed -n 's/^R21CP=//p')
B=$(mktemp -d)
javac -nowarn -d "$B" -cp "$CP" tools/r21/EditorProbe.java
rm -rf "$OUT"; mkdir -p "$OUT"
cd core/assets
../../tools/offscreen.sh java --enable-native-access=ALL-UNNAMED -cp "$B:$CP" EditorProbe "$OUT"
ls "$OUT"
