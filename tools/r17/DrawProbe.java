// r17 probe: completes a drawing through Section_Drawing.valueSucces (the path that called the
// dead Utils_Scoring), the same way every time, and writes PNGs at fixed frames, so the trace,
// the reveal and the dog's speed-up can be compared pixel for pixel before and after a change.
// Fixed: delta 1/60 s, the fruit list (grape, peach, banana: no shuffle), one dot every 4 frames.
// Run: tools/r17/run.sh <outdir>
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.mains.Main_FYP_Game;

import jks.yummy.game.GVars_Game;
import jks.yummy.game.drawing.DrawingSection_Utils;
import jks.yummy.game.drawing.Section_Drawing;
import jks.yummy.game.model.DrawingModel;

public class DrawProbe extends ApplicationAdapter {
    final Main_FYP_Game game = new Main_FYP_Game();
    final String out;
    int frame, wonAt = -1;
    Field delta, objective, drawing;
    // shots: by frame, and some relative to the win
    static final int[] SHOT_AT = {30, 60};
    static final String[] SHOT = {"start", "tracing"};
    static final int[] AFTER_WIN = {1, 60, 180, 400};
    static final String[] AFTER = {"won", "reveal", "walking", "later"};

    DrawProbe(String out) { this.out = out; }

    @Override public void create() {
        game.create();
        try {
            delta = Gdx.graphics.getClass().getDeclaredField("deltaTime");
            delta.setAccessible(true);
            objective = Section_Drawing.class.getDeclaredField("currentObjective");
            objective.setAccessible(true);
            drawing = Section_Drawing.class.getDeclaredField("currentDrawing");
            drawing.setAccessible(true);
        } catch (Exception e) { throw new RuntimeException(e); }
    }

    void shot(String name) {
        Pixmap p = Pixmap.createFromFrameBuffer(0, 0, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight());
        PixmapIO.writePNG(Gdx.files.absolute(out + "/" + name + ".png"), p, 0, true);
        p.dispose();
    }

    @Override public void render() {
        try {
            delta.setFloat(Gdx.graphics, 1 / 60f);
            frame++;
            if (frame == 10) {
                ArrayList<DrawingModel> list = new ArrayList<>();
                for (String n : new String[] {"grape", "peach", "banana"})
                    list.add(DrawingSection_Utils.getDrawing("fruit/" + n));
                GVars_Game.initGame(list);
            }
            Section_Drawing d = GVars_Game.section_drawing;
            if (frame >= 20 && wonAt < 0 && frame % 4 == 0) {
                d.setComeFromClick(true);
                int k = objective.getInt(d);
                d.valueSucces(d.buttonList.get(((DrawingModel) drawing.get(d)).drawingList.get(k)));
                if (d.currentDrawIsWon) { wonAt = frame; System.out.println("r17 won at frame " + frame + ", speed " + GVars_Game.currentSpeed); }
            }
        } catch (Exception e) { throw new RuntimeException(e); }
        game.render();
        for (int i = 0; i < SHOT_AT.length; i++)
            if (frame == SHOT_AT[i]) shot((i + 1) + "-" + SHOT[i]);
        for (int i = 0; wonAt > 0 && i < AFTER_WIN.length; i++)
            if (frame == wonAt + AFTER_WIN[i]) shot((i + 3) + "-" + AFTER[i]);
        if (wonAt > 0 && frame > wonAt + AFTER_WIN[AFTER_WIN.length - 1]) {
            System.out.println("r17 speed at end " + GVars_Game.currentSpeed);
            Gdx.app.exit();
        }
        if (frame > 3000) { System.out.println("r17 never won"); Gdx.app.exit(); }
    }

    public static void main(String[] a) {
        new File(a[0]).mkdirs();
        Lwjgl3ApplicationConfiguration c = ProbeWindow.config();
        new Lwjgl3Application(new DrawProbe(new File(a[0]).getAbsolutePath()), c);
    }
}
