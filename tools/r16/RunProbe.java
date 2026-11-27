// r16 probe: plays a game the same way every time and writes PNGs at fixed frames, so the
// dog run (Section_Action) can be compared pixel for pixel before and after a change.
// Fixed: delta 1/60 s (Lwjgl3Graphics.deltaTime overwritten each frame), the fruit list
// (grape, peach, banana: no shuffle), the speed (two wonDrawing(): walk, then run), the jump.
// Run: tools/r16/run.sh <outdir>
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.mains.Main_FYP_Game;

import jks.yummy.game.GVars_Game;
import jks.yummy.game.drawing.DrawingSection_Utils;
import jks.yummy.game.model.DrawingModel;
import jks.yummy.game.score.Controler_Score;

public class RunProbe extends ApplicationAdapter {
    final Main_FYP_Game game = new Main_FYP_Game();
    final String out;
    int frame;
    Field delta;
    // frame -> shot name
    static final int[] SHOT_AT = {30, 150, 300, 330, 360, 420};
    static final String[] SHOT = {"start", "walk", "run", "jump-up", "jump-top", "landed"};

    RunProbe(String out) { this.out = out; }

    @Override public void create() {
        game.create();
        try {
            delta = Gdx.graphics.getClass().getDeclaredField("deltaTime");
            delta.setAccessible(true);
        } catch (Exception e) { throw new RuntimeException(e); }
    }

    @Override public void render() {
        try { delta.setFloat(Gdx.graphics, 1 / 60f); } catch (Exception e) { throw new RuntimeException(e); }
        frame++;
        if (frame == 10) {
            ArrayList<DrawingModel> list = new ArrayList<>();
            for (String n : new String[] {"grape", "peach", "banana"})
                list.add(DrawingSection_Utils.getDrawing("fruit/" + n));
            GVars_Game.initGame(list);
        }
        if (frame == 60) Controler_Score.wonDrawing();          // walk
        if (frame == 200) { Controler_Score.wonDrawing(); Controler_Score.wonDrawing(); } // run
        if (frame == 310) GVars_Game.section_action.jump();      // the right click
        game.render();
        for (int i = 0; i < SHOT_AT.length; i++)
            if (frame == SHOT_AT[i]) {
                Pixmap p = Pixmap.createFromFrameBuffer(0, 0, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight());
                PixmapIO.writePNG(Gdx.files.absolute(out + "/" + (i + 1) + "-" + SHOT[i] + ".png"), p, 0, true);
                p.dispose();
            }
        if (frame > SHOT_AT[SHOT_AT.length - 1]) Gdx.app.exit();
    }

    public static void main(String[] a) {
        new File(a[0]).mkdirs();
        Lwjgl3ApplicationConfiguration c = new Lwjgl3ApplicationConfiguration();
        c.setWindowedMode(900, 500);
        c.setForegroundFPS(0);
        new Lwjgl3Application(new RunProbe(new File(a[0]).getAbsolutePath()), c);
    }
}
