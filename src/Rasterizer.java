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
    public static void Rasterize(Vertex[] vertices, int width, int height, int backGroundColor)
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
                Vector3 pixelPos = new Vector3(i, j, 1);

                for (int t = 0; t < vertices.length; t += 3)
                {
                    Vector3 a = vertices[t + 0].position;
                    Vector3 b = vertices[t + 1].position;
                    Vector3 c = vertices[t + 2].position;

                    Vector3 aScreenSpace = CGMath.NDCToScreenSpace(a, width, height);
                    Vector3 bScreenSpace = CGMath.NDCToScreenSpace(b, width, height);
                    Vector3 cScreenSpace = CGMath.NDCToScreenSpace(c, width, height);

                    Vector3 vColorA = vertices[t + 0].color;
                    Vector3 vColorB = vertices[t + 1].color;
                    Vector3 vColorC = vertices[t + 2].color;

                    Vector3 barycentricCoords = CGMath.ComputeBarycentricCoordinates(
                            aScreenSpace.toVector2(),
                            bScreenSpace.toVector2(),
                            cScreenSpace.toVector2(),
                            pixelPos.toVector2());

                    if (CGMath.pointInsideTriangle(aScreenSpace, bScreenSpace, cScreenSpace, pixelPos))
                    {
                        vColorA = Vector3.mul(vColorA, barycentricCoords.x);
                        vColorB = Vector3.mul(vColorB, barycentricCoords.y);
                        vColorC = Vector3.mul(vColorC, barycentricCoords.z);

                        int pixelColor = CGMath.RGBToHex(Vector3.add(vColorA, vColorB, vColorC));

                        rasterBuffer.setRGB(i, j, pixelColor);
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
