#!/bin/sh
# r16: build the game, then play RunProbe offscreen and write its PNGs to <outdir>.
#   tools/r16/run.sh tests/renders/r16/before
set -e
cd "$(dirname "$0")/../.."
OUT=$(realpath -m "${1:?usage: run.sh <outdir>}")
CP=$(./gradlew -q --no-configuration-cache -I tools/r16/cp.gradle :desktop:r16Classpath | sed -n 's/^R16CP=//p')
B=$(mktemp -d)
javac -nowarn -d "$B" -cp "$CP" tools/r16/RunProbe.java
rm -rf "$OUT"; mkdir -p "$OUT"
cd core/assets
../../tools/offscreen.sh java --enable-native-access=ALL-UNNAMED -cp "$B:$CP" RunProbe "$OUT"
ls "$OUT"
# The shots at 1280x720 under cage (r26: the window is made at the output size), for a later check:
#   (cd <outdir> && sha256sum -c ../../tools/r16/shots.sha256)   # from tests/renders/r16/<x>
