# r19 proof: removing Input_Desktop and GVars_Game's leftovers changes nothing on screen

tools/r19/run.sh <dir> goes through the menu flow with real clicks sent to Gdx.input's processor:
menu, then for each of Fruit, Legume, Mix-up! the game 60 frames after the click and the menu
30 frames after Retour. That makes seven PNGs. Collections' Random is reseeded before every click,
so the shuffles repeat.

- Control, before vs before (e6359ec): identical.
- After the change: the seven shots match tools/r19/shots.sha256 byte for byte.
- ./gradlew :desktop:classes compiles.
- The probe prints the timer text: 0.01.5 at Retour and 0.01.98 29 frames later, on the menu.
  The old game's timer keeps ticking after Retour (filed as a task).

The sheet is attached to r19 on the board (sheet-before.png).
