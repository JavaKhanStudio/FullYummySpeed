#!/bin/sh
# r3: overlay each fruit's dots on its picture. Needs gdx + commons-lang3 from the gradle cache.
set -e
cd "$(dirname "$0")/../.."
C=~/.gradle/caches/modules-2/files-2.1
CP="$(ls $C/com.badlogicgames.gdx/gdx/*/*/gdx-1*[0-9].jar | head -1):$(ls $C/org.apache.commons/commons-lang3/*/*/commons-lang3-3*[0-9].jar | tail -1)"
B=$(mktemp -d)
javac -nowarn -d "$B" -cp "$CP" core/Game/jks/yummy/game/model/DrawingModel.java tools/r3/DotProbe.java
java -cp "$B:$CP" DotProbe core/assets tests/renders/r3 ${@:-grape peach}
