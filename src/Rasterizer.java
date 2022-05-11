import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;

/*
    Provides a set of general static functions for the purpose of rasterizing images.
*/
public class Rasterizer
{
    /** Rasterizes a set of vertices with a specific screen size, and creates a display window.
     * @param vertices Set of vertices to rasterize. Should represent triangles, the amount of elements should be a
     * multiple of three. If the vertex count is less than 3 or not an even 3 multiple it will not create a window.
     * @param width Width of the window to draw to
     * @param height Height of the window to draw to
     * @param backGroundColor Hexadecimal color of the screen background to draw over
     */
    public static void Rasterize(Vector3[] vertices, int width, int height, int backGroundColor)
    {
        if (vertices.length % 3 != 0 || vertices.length < 0)
            return;

        //Create a frame and rasterizer buffer data
        Frame f = new Frame("Rasterizer");
        BufferedImage rasterBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        MainCanvas canvas = new MainCanvas(rasterBuffer);

        //Fill the background
        Rasterizer.FillBackground(rasterBuffer, 0xffffff);

        //Draw triangles. Perform triangle contains tests by converting the screen space coordinate to
        //normalized device coordinates and performing an edge test.
        for (int i = 0; i < width; ++i)
        {
            for (int j = 0; j < height; ++j)
            {
                Vector2 NDCPt = CGMath.ScreenSpaceToNDC(new Vector2(i, j), width, height);

                for (int t = 0; t < vertices.length; t += 3)
                {
                    if (CGMath.pointInsideTriangle(vertices[t + 0], vertices[t + 1], vertices[t + 2], NDCPt.toVector3()))
                    {
                        rasterBuffer.setRGB(i, j, 0xff0000);
                    }
                }

            }
        }

        //Display the image
        f.setSize(new Dimension(width, height + 20)); //Add a small offset to adjust for the window bar
        f.setVisible(true);
        f.add("Center", canvas);
    }

    public static void FillBackground(BufferedImage rasterBuffer, int color)
    {
        for (int i = 0; i < rasterBuffer.getWidth(); ++i)
            for (int j = 0; j < rasterBuffer.getHeight(); ++j)
                rasterBuffer.setRGB(i, j, color);
    }
}
