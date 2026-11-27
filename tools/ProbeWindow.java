import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

/**
 * The window size of every probe (tools/r16 RunProbe, r17 DrawProbe, r19 FlowProbe), compiled
 * with it by its run.sh.
 *
 * Inside cage (tools/offscreen.sh sets ATELIER_INSIDE_CAGE=1) the window is forced to cage's
 * output after it is made, and the GL surface keeps its first size: a 900x500 window drew the
 * game, laid out for 1280x696, clipped to its lower-left 900x500 (r26, r28). Made undecorated at
 * the output's size it is never resized, and the shot is the whole game. Elsewhere: 900x500, the
 * size StartGame_Desktop opens. PROBE_WINDOW=launcher keeps 900x500 inside cage too, to see what
 * :desktop:run (StartGame_Desktop) shows there (r28).
 */
final class ProbeWindow {
    private ProbeWindow() {}

    static Lwjgl3ApplicationConfiguration config() {
        Lwjgl3ApplicationConfiguration c = new Lwjgl3ApplicationConfiguration();
        if ("1".equals(System.getenv("ATELIER_INSIDE_CAGE")) && !"launcher".equals(System.getenv("PROBE_WINDOW"))) {
            DisplayMode dm = Lwjgl3ApplicationConfiguration.getDisplayMode();
            c.setDecorated(false);
            c.setWindowedMode(dm.width, dm.height);
        } else c.setWindowedMode(900, 500);
        c.setForegroundFPS(0);
        return c;
    }
}
