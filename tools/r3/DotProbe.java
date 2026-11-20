// r3 probe: loads each core/assets/DrawingData/fruit/<name> exactly as
// DrawingSection_Utils.getDrawing does (ObjectInputStream -> byte[] -> SerializationUtils)
// and draws its dots over its picture the way the game places them: picture stretched to a
// 544x544 square at the canvas origin (y up), dot centre = point + buttonSize/2 (20/2).
// Run: tools/r3/run.sh  -> tests/renders/r3/<name>.png
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import com.badlogic.gdx.math.Vector2;
import jks.yummy.game.model.DrawingModel;

public class DotProbe {
    public static void main(String[] a) throws Exception {
        String assets = a[0], out = a[1];
        new File(out).mkdirs();
        for (int i = 2; i < a.length; i++) {
            String name = a[i];
            DrawingModel m;
            try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(
                    new FileInputStream(assets + "/DrawingData/fruit/" + name)))) {
                m = DrawingModel.readFromPacket((byte[]) in.readObject());
            }
            File pic = new File(assets + "/" + m.imagePath);
            System.out.println(name + ": " + m.drawingList.size() + " points, image=" + m.imagePath
                    + (pic.exists() ? "" : " (MISSING)"));
            int S = 544;
            BufferedImage img = new BufferedImage(S, S, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = img.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(Color.WHITE); g.fillRect(0, 0, S, S);
            if (pic.exists()) g.drawImage(ImageIO.read(pic), 0, 0, S, S, null);
            ArrayList<Vector2> p = m.drawingList;
            g.setStroke(new BasicStroke(2f));
            for (int k = 0; k < p.size(); k++) {
                int x = (int) (p.get(k).x + 10), y = S - (int) (p.get(k).y + 10);
                if (k > 0) {
                    int px = (int) (p.get(k - 1).x + 10), py = S - (int) (p.get(k - 1).y + 10);
                    g.setColor(new Color(255, 0, 0, 180)); g.drawLine(px, py, x, y);
                }
                g.setColor(Color.BLUE); g.fillOval(x - 5, y - 5, 10, 10);
                g.drawString(String.valueOf(k), x + 6, y - 6);
            }
            g.dispose();
            ImageIO.write(img, "png", new File(out + "/" + name + ".png"));
        }
    }
}
