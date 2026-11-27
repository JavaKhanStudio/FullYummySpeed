# r20 proof: SpriteModel trim changes no pixel

Probe: tools/r16/run.sh (RunProbe: fixed 1/60 s delta, grape/peach/banana, two wonDrawing()
then run, a jump), checked against tools/r16/shots.sha256.

    tools/r16/run.sh build/r20/before   # HEAD 422004d, before the change
    tools/r16/run.sh build/r20/after    # with the change
    (cd build/r20/<x> && sha256sum -c ../../../tools/r16/shots.sha256)

Both runs: 1-start (dog idle), 2-walk, 3-run (after won drawings), 4-jump-up, 5-jump-top,
6-landed: all six OK. `./gradlew :desktop:classes` compiles (rc 0).
