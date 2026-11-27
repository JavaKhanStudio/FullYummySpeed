#!/bin/sh
# r30: the real launcher (StartGame_Desktop) under cage, Fruit clicked by tools/r30/ClickFruit,
# its X window grabbed by tools/offscreen-shot.sh at each wait (seconds from launch).
#   tools/r30/run.sh tests/renders/r30 6,14
set -e
cd "$(dirname "$0")/../.."
OUT=$(realpath -m "${1:?usage: run.sh <outdir> [waits]}")
WAITS=${2:-6,14}
CP=$(./gradlew -q --no-configuration-cache -I tools/r19/cp.gradle :desktop:r19Classpath | sed -n 's/^R19CP=//p')
B=$(mktemp -d)
javac -nowarn -d "$B" -cp "$CP" tools/r30/ClickFruit.java
mkdir -p "$OUT"
cd core/assets
../../tools/offscreen-shot.sh "$OUT/launcher.png" "$WAITS" \
  java --enable-native-access=ALL-UNNAMED -cp "$B:$CP" ClickFruit
