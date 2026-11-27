# Full Yummy Speed

A children's game made in 2017 with libGDX. The child traces a fruit or a vegetable by
joining its dots in order. When the last dot is joined, the picture is revealed, and each
finished drawing makes a dog run faster across a parallax landscape at the top of the screen.

The title menu offers **Fruit**, **Legume** (vegetables) and **Mix-up!** (both, shuffled).

## Run it

You need JDK 21 or newer; the Gradle wrapper is checked in.

```sh
./gradlew :desktop:run         # the game, a 900x500 window
./gradlew :desktop:runEditor   # the editor, where a drawing's dots are placed
./gradlew :desktop:dist        # a runnable fat jar
```

Both programs run with `core/assets` as their working directory, and every asset path is
relative to it.

When a board agent runs either one (`ATELIER_AGENT` is set), the window opens on a headless
display and the sound is written to `desktop/build/audio/<task>.wav` (`gradle/offscreen.gradle`).
`ATELIER_NO_OFFSCREEN=1` puts them back on the screen.

## Where the drawings live

- `core/assets/DrawingData/fruit/` and `core/assets/DrawingData/legume/`: one file per drawing,
  with no extension. Each file holds the drawing's dots in order and the path of its picture,
  stored as Java-serialized `jks.yummy.game.model.DrawingModel`. You can only read them with
  that class on the classpath.
- `core/assets/DrawingImage/`: the pictures revealed when a drawing is finished.
- `core/Index/jks/index/Index_Drawing.java`: which drawings each menu button plays.
- `tools/r3/run.sh` reads drawings outside the game and draws their dots over their pictures.

The dots were placed in the editor (`StartEditor_Desktop`): right-click adds a dot over the
picture, and **Convert** writes the file into the working directory, then closes.

## Layout

The source is split Eclipse-style: `core/{Mains,Game,Interface,Tools,Index,Editeur,Sounds}`
are each a source root, so `jks.yummy.game` lives in `core/Game/jks/yummy/game/`. The desktop
launchers are in `desktop/src/com/mygdx/game/desktop/`.
