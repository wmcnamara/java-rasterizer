import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.math.*;

class Main
{




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

            Matrix2x2 rot = Matrix2x2.ZAxisRotationMatrix(angle);
            Matrix2x2 scale = Matrix2x2.ScaleMatrix(1.5f, 1.5f);

            Matrix2x2 transform = Matrix2x2.mul(rot, scale);

            for (int i = 0; i < vertices.length; i++)
            {
                vertices[i] = Matrix2x2.mul(transform, vertices[i]);
            }

            Vector2[] screenCoordinates = CGMath.NDCToScreenSpace(vertices, X, Y);


            for (int i = 0; i < X; ++i)
            {
                for (int j = 0; j < Y; ++j)
                {
                    Vector2 NDCPt = ScreenSpaceToNDC(new Vector2(i, j), X, Y);
                    if (CGMath.pointInsideTriangle(vertices[0], vertices[1], vertices[2], NDCPt))
                    {
                        I.setRGB(i, j, 0xffffff);
                    }
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
