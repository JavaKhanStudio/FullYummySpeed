"""r25: move the build pack's picture recipe and core-deps sentence, word for word,
to docs/build.md and link it. Usage: move.py <packs.toml in> <packs.toml out>; prints
the build pack's line count as shown (db.pack_lines: textwrap 92 per source line)."""
import sys, textwrap, tomllib

def pack_lines(c):
    return sum(len(textwrap.wrap(p, 92)) or 1 for p in c.strip().split("\n"))

PICTURE = """Picture: tools/offscreen-shot.sh tests/renders/rN/x.png 10,25 ./gradlew :desktop:run
--console=plain (menu up by ~8 s once compiled). The "[LWJGL] GLFW_PLATFORM_ERROR ... EGL"
line at start in cage is harmless. tools/offscreen-lint.sh fails a tools/*.sh that runs
java outside cage: wrap it in tools/offscreen.sh (tools/r3/run.sh does).
"""
DEPS_OLD = """with --release 17. Core deps are only gdx, commons-collections4/-lang3 and
core/jars/parallax-0.1.jar (fileTree); bullet/box2d/ai/ashley/spine/controllers were dropped
(nothing imported them). gdx 1.14 changed InputProcessor"""
DEPS_NEW = """with --release 17. gdx 1.14 changed InputProcessor"""
DEPS = """Core deps are only gdx, commons-collections4/-lang3 and
core/jars/parallax-0.1.jar (fileTree); bullet/box2d/ai/ashley/spine/controllers were dropped
(nothing imported them).
"""
LINK = "Core deps, taking a picture, the offscreen lint: docs/build.md.\n"

src, out = sys.argv[1], sys.argv[2]
s = open(src).read()
assert s.count(PICTURE) == 1 and s.count(DEPS_OLD) == 1
s = s.replace(PICTURE, LINK).replace(DEPS_OLD, DEPS_NEW)
open(out, "w").write(s)
print(pack_lines(tomllib.loads(s)["tags"]["build"]["context"]))
