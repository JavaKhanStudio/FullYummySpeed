# CLAUDE.md

What the game is, how to run it and where the drawings live: see README.md.

The traps of each part of the code (build, menu and flow, tracing, the dog run, drawings,
sound) are in `.atelier/packs.toml`, one `[tags.<slug>]` per part. Read the pack of the part
you touch before you change it. When your change makes a pack wrong, fix the pack in the same
commit. Do not copy pack text here.

- Run with `./gradlew :desktop:run` (or `:desktop:runEditor`). With `ATELIER_AGENT` set, it
  runs offscreen. A new JavaExec task must call `rootProject.offscreen(...)`, or the build stops.
- All game state is static (`GVars_*`). A new static field must be reset in
  `GVars_Game.resetGame()`.
- Do not rename or move `jks.yummy.game.model.DrawingModel`: its class name is inside every
  file in `core/assets/DrawingData/`.
- Identifiers mix French and English (Accueil, Legume, decal, Retour). They are explained in
  the lexicon (`atelier --project fullyummyspeed term list`), not renamed.
