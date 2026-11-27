// r30 probe: runs the real launcher (StartGame_Desktop.main, 900x500, unchanged) and, from a
// side thread, clicks Fruit once the menu is up, through the same input path r19 uses
// (Gdx.input's processor at the button's screen centre). Nothing else is touched, so a grab
// of its X window by tools/offscreen-shot.sh is what :desktop:run shows after a Fruit click.
// offscreen-shot.sh cannot click and xdotool is not installed: this stands in for the mouse.
// Run: tools/r30/run.sh <outdir>
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.desktop.StartGame_Desktop;

import jks.yummy.interfaces.GVars_Interface;

public class ClickFruit {
    static TextButton find(Group g, String text) {
        for (Actor a : g.getChildren()) {
            if (a instanceof TextButton && text.contentEquals(((TextButton) a).getText())) return (TextButton) a;
            if (a instanceof Group) { TextButton t = find((Group) a, text); if (t != null) return t; }
        }
        return null;
    }

    static void click(String text) {
        TextButton b = find(GVars_Interface.mainInterface.getRoot(), text);
        if (b == null) throw new IllegalStateException("no button " + text);
        Vector2 v = b.localToStageCoordinates(new Vector2(b.getWidth() / 2, b.getHeight() / 2));
        GVars_Interface.mainInterface.stageToScreenCoordinates(v);
        InputProcessor in = Gdx.input.getInputProcessor();
        in.touchDown((int) v.x, (int) v.y, 0, 0);
        in.touchUp((int) v.x, (int) v.y, 0, 0);
        System.out.println("r30 clicked " + text + " at " + (int) v.x + "," + (int) v.y
            + " window " + Gdx.graphics.getWidth() + "x" + Gdx.graphics.getHeight()
            + " backbuffer " + Gdx.graphics.getBackBufferWidth() + "x" + Gdx.graphics.getBackBufferHeight());
    }

    public static void main(String[] a) {
        long delayMs = Long.parseLong(System.getProperty("r30.clickAfterMs", "4000"));
        Thread t = new Thread(() -> {
            try {
                while (Gdx.app == null || GVars_Interface.mainInterface == null) Thread.sleep(100);
                Thread.sleep(delayMs);
                Gdx.app.postRunnable(() -> click("Fruit"));
            } catch (InterruptedException e) { }
        });
        t.setDaemon(true);
        t.start();
        StartGame_Desktop.main(a);
    }
}
