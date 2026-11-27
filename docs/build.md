# Build & launch: the longer notes

Moved word for word from the `build` context pack (`.atelier/packs.toml`, `[tags.build]`),
which links here, to keep that pack within its 20 lines.

## Core deps

Core deps are only gdx, commons-collections4/-lang3 and
core/jars/parallax-0.1.jar (fileTree); bullet/box2d/ai/ashley/spine/controllers were dropped
(nothing imported them).

## Taking a picture, the offscreen lint

Picture: tools/offscreen-shot.sh tests/renders/rN/x.png 10,25 ./gradlew :desktop:run
--console=plain (menu up by ~8 s once compiled). The "[LWJGL] GLFW_PLATFORM_ERROR ... EGL"
line at start in cage is harmless. tools/offscreen-lint.sh fails a tools/*.sh that runs
java outside cage: wrap it in tools/offscreen.sh (tools/r3/run.sh does).
