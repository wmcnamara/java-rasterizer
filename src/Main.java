import java.awt.*;
import java.awt.image.*;

class Main
{
    static final int windowWidth = 800, windowHeight = 600;

    static Vector3[] vertices = {
            new Vector3(0.5f, -.5f, 1.0f),
            new Vector3(-.5f, -.5f, 1.0f),
            new Vector3(0, 0.5f, 1.0f)
    };

    static public void main(String[] args)
    {
        Rasterizer.Rasterize(vertices, windowWidth, windowHeight);
    }
}


class MainCanvas extends Canvas
{
    MainCanvas(BufferedImage rasterBuf)
    {
        this.rasterBuf = rasterBuf;
    }

    public void paint(Graphics g)
    {
        g.drawImage(rasterBuf, 0, 0, Color.black, null);
    }

    BufferedImage rasterBuf;
}
