import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.math.*;

class Main
{
    public static boolean edgeFunction(Vector2 a, Vector2 b, Vector2 c)
    {
        return ((c.x - a.x) * (b.y - a.y) - (c.y - a.y) * (b.x - a.x) >= 0);
    }

    /*
        Checks if a point is inside a triangle defined like tp1, tp2 and tp3
    */
    public static boolean pointInsideTriangle(Vector2 tp1, Vector2 tp2, Vector2 tp3, Vector2 pToTest)
    {
        return edgeFunction(tp1, tp2, pToTest) && edgeFunction(tp2, tp3, pToTest) && edgeFunction(tp3, tp1, pToTest);
    }

    public static Vector2[] NDCToScreenSpace(Vector2[] ndcCoords, int screenWidth, int screenHeight)
    {
        Vector2[] scPts = new Vector2[ndcCoords.length];

        for (int i = 0; i < scPts.length; i++)
        {
            float rx = screenWidth * ((ndcCoords[i].x + 1.0f) / 2.0f);
            float ry = screenHeight * ((ndcCoords[i].y + 1.0f) / 2.0f);

            scPts[i] = new Vector2((int)rx, (int)ry);
        }

        return scPts;
    }

    /*
        Converts screen spaces coordinates to normalized device coordinates
     */
    public static Vector2 ScreenSpaceToNDC(Vector2 screenPnt, int width, int height)
    {
        Vector2 ndc = new Vector2(0, 0);

        ndc.x = ((screenPnt.x / width)  * 2) - 1;
        ndc.y = ((screenPnt.y / height) * 2) - 1;

        return ndc;
    }

    static final int X = 380, Y = 250;
    static BufferedImage I = new BufferedImage(X, Y, BufferedImage.TYPE_INT_RGB);
    static Vector2[] vertices = {
            new Vector2(0.5f, -.5f),
            new Vector2(-.5f, -.5f),
            new Vector2(0, 0.5f)
    };

    static public void main(String[] args)
    {
        Frame f = new Frame("Westons Rasterizer");
        MainCanvas canvas = new MainCanvas();

       // while (true)
        {


            for (int i = 0; i < X; ++i)
                for (int j = 0; j < Y; ++j)
                    I.setRGB(i, j, 0x000000);

            long seconds = System.nanoTime() / 1000000000l;

            float angle = 30;

            float c = (float) Math.cos(angle * (3.14/180));
            float s = (float) Math.sin(angle * (3.14/180));

            Matrix2x2 zAxisRotation = new Matrix2x2
                            (c, -s,
                            s, c);


            for (int i = 0; i < vertices.length; i++) {
                vertices[i] = Matrix2x2.mul(zAxisRotation, vertices[i]);
            }

            Vector2[] screenCoordinates = NDCToScreenSpace(vertices, X, Y);


            for (int i = 0; i < X; ++i)
                for (int j = 0; j < Y; ++j) {
                    if (pointInsideTriangle(vertices[0], vertices[1], vertices[2], ScreenSpaceToNDC(new Vector2(i, j), X, Y))) {
                        I.setRGB(i, j, 0xffffff);
                    }
                }

            f.setSize(new Dimension(X, Y + 22));
            f.setVisible(true);
            f.add("Center", canvas);
            try {
                Thread.sleep(1000);
            }catch(Exception e){}
        }

    }
}
class MainCanvas extends Canvas
{
    public void paint(Graphics g)
    {
        g.drawImage(Main.I, 0, 0, Color.black, null);
        //Dimension s = getSize();
        //g.drawOval(0, 0, s.width, s.height);
    }
}
