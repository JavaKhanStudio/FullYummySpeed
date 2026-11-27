// r19 probe: walks the menu flow through the real input path, the same way every time, and
// writes PNGs: the menu, then for each of Fruit, Legume, Mix-up! a game 60 frames after the
// click, and the menu 30 frames after its Retour. Clicks go to Gdx.input's processor at the
// button's screen centre. Collections' Random is reseeded before each click (needs
// --add-opens java.base/java.util), so the list shuffles repeat. Delta is fixed at 1/60 s.
// It also prints whether GVars_Game.timer still ticks on the menu after Retour.
// Run: tools/r19/run.sh <outdir>
import java.io.File;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.mains.Main_FYP_Game;

import jks.yummy.game.GVars_Game;
import jks.yummy.interfaces.GVars_Interface;

public class FlowProbe extends ApplicationAdapter {
    final Main_FYP_Game game = new Main_FYP_Game();
    final String out;
    int frame, shots;
    Field delta, collectionsRandom;
    static final String[] MODES = {"Fruit", "Legume", "Mix-up!"};
    // per mode: click at base, game shot at base+60, Retour at base+90, menu shot at base+120
    static final int FIRST = 20, STEP = 150;

    FlowProbe(String out) { this.out = out; }

    @Override public void create() {
        game.create();
        try {
            delta = Gdx.graphics.getClass().getDeclaredField("deltaTime");
            delta.setAccessible(true);
            collectionsRandom = Collections.class.getDeclaredField("r");
            collectionsRandom.setAccessible(true);
        } catch (Exception e) { throw new RuntimeException(e); }
    }

    void shot(String name) {
        Pixmap p = Pixmap.createFromFrameBuffer(0, 0, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight());
        PixmapIO.writePNG(Gdx.files.absolute(out + "/" + (++shots) + "-" + name + ".png"), p, 0, true);
        p.dispose();
    }

    static TextButton find(Group g, String text) {
        for (Actor a : g.getChildren()) {
            if (a instanceof TextButton && text.contentEquals(((TextButton) a).getText())) return (TextButton) a;
            if (a instanceof Group) { TextButton t = find((Group) a, text); if (t != null) return t; }
        }
        return null;
    }

    void click(String text) throws Exception {
        collectionsRandom.set(null, new Random(19));
        TextButton b = find(GVars_Interface.mainInterface.getRoot(), text);
        if (b == null) throw new IllegalStateException("no button " + text);
        Vector2 v = b.localToStageCoordinates(new Vector2(b.getWidth() / 2, b.getHeight() / 2));
        GVars_Interface.mainInterface.stageToScreenCoordinates(v);
        InputProcessor in = Gdx.input.getInputProcessor();
        in.touchDown((int) v.x, (int) v.y, 0, 0);
        in.touchUp((int) v.x, (int) v.y, 0, 0);
    }

    String timerText() { return GVars_Game.timer == null ? "null" : GVars_Game.timer.getText().toString(); }

    @Override public void render() {
        String after = null;
        try {
            delta.setFloat(Gdx.graphics, 1 / 60f);
            frame++;
            int m = (frame - FIRST) / STEP, k = (frame - FIRST) % STEP;
            if (frame >= FIRST && m < MODES.length) {
                if (k == 0) click(MODES[m]);
                if (k == 90) { System.out.println("r19 " + MODES[m] + " timer before Retour: " + timerText()); click("Retour"); }
                if (k == 119) System.out.println("r19 " + MODES[m] + " timer 29 frames after Retour: " + timerText());
                if (k == 60) after = "game-" + MODES[m].replace("!", "");
                if (k == 120) after = "menu-after-" + MODES[m].replace("!", "");
            }
        } catch (Exception e) { throw new RuntimeException(e); }
        game.render();
        if (frame == FIRST - 1) shot("menu");
        if (after != null) shot(after);
        if (frame > FIRST + STEP * MODES.length) Gdx.app.exit();
    }

    public static void main(String[] a) {
        new File(a[0]).mkdirs();
        Lwjgl3ApplicationConfiguration c = ProbeWindow.config();
        new Lwjgl3Application(new FlowProbe(new File(a[0]).getAbsolutePath()), c);
    }
}
