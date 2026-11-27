// r21 probe: opens the draw editor (Main_FYP_DrawEditor) offscreen and clicks it through the
// input processor it installed (the InputMultiplexer: Inputs_Editor first), the way a mouse
// would: frame 30 shot "1-start"; frame 40 a RIGHT click (button 1) at (200,300) window px;
// frame 50 a LEFT click (button 0) at (350,500), which must add nothing; frame 70 shot
// "2-clicked". Prints the dots of Canvas.drawModel after each click.
// Run: tools/r21/run.sh <outdir>
import java.io.File;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.mains.Main_FYP_DrawEditor;

import jks.yummy.editeur.Canvas;
import jks.yummy.game.drawing.Vars.FVars_Drawing;

public class EditorProbe extends ApplicationAdapter {
    final Main_FYP_DrawEditor editor = new Main_FYP_DrawEditor();
    final String out;
    int frame;

    EditorProbe(String out) { this.out = out; }

    @Override public void create() { editor.create(); }

    void shot(String name) {
        Pixmap p = Pixmap.createFromFrameBuffer(0, 0, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight());
        PixmapIO.writePNG(Gdx.files.absolute(out + "/" + name + ".png"), p, 0, true);
        p.dispose();
    }

    @Override public void render() {
        frame++;
        if (frame == 40) {
            boolean h = Gdx.input.getInputProcessor().touchDown(200, 300, 0, 1);
            System.out.println("R21 right click handled=" + h + " dots=" + Canvas.drawModel.drawingList);
        }
        if (frame == 50) {
            boolean h = Gdx.input.getInputProcessor().touchDown(350, 500, 0, 0);
            System.out.println("R21 left click handled=" + h + " dots=" + Canvas.drawModel.drawingList);
        }
        editor.render();
        if (frame == 30) shot("1-start");
        if (frame == 70) { shot("2-clicked"); Gdx.app.exit(); }
    }

    public static void main(String[] a) {
        new File(a[0]).mkdirs();
        Lwjgl3ApplicationConfiguration c = new Lwjgl3ApplicationConfiguration();
        c.setWindowedMode((int) (FVars_Drawing.refSizeWidth * FVars_Drawing.refMulSize),
                          (int) (FVars_Drawing.refSizeHeight * FVars_Drawing.refMulSize));
        new Lwjgl3Application(new EditorProbe(new File(a[0]).getAbsolutePath()), c);
    }
}
