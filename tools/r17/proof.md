# r17 proof: the scoring removal changes nothing on screen

tools/r17/run.sh <dir> plays grape, peach, banana (no shuffle) at a fixed 1/60 s, joins
grape's dots through Section_Drawing.valueSucces (one every 4 frames, the path that called
Utils_Scoring) and writes six PNGs: start, tracing, won, reveal, walking, later.

- Control, before vs before (422004d + r20's SpriteModel edit): identical.
- After the removal: the six shots match tools/r17/shots.sha256 byte for byte;
  stdout says "won at frame 108, speed 100.0" in both (the dog walks after the win).
- ./gradlew :desktop:classes compiles.

The shots are attached to r17 on the board (sheet-before.png, after-*.png).
