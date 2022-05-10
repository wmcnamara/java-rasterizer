import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;

public class Rasterizer
{
    public static void Rasterize(Vector3[] vertices, int width, int height)
    {
        if (vertices.length < 3)
            return;

        //Create a frame and rasterizer buffer data
        Frame f = new Frame("Rasterizer");
        BufferedImage rasterBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        MainCanvas canvas = new MainCanvas(rasterBuffer);

        //Fill the background
        Rasterizer.FillBackground(rasterBuffer, 0xffffff);

        //Setup transformations
        float angle = 15;
        Vector3 scale = new Vector3(1.2f, 1.2f, 1.2f);
        Vector2 pos = new Vector2(.1f, 0);


        Matrix3x3 rot = Matrix3x3.ZAxisRotationMatrix(angle);
        Matrix3x3 scaleMat = Matrix3x3.ScaleMatrix(scale);
        Matrix3x3 translate = Matrix3x3.TranslationMatrix(pos);

        //Multiply transformations together
        Matrix3x3 transform = Matrix3x3.mul(translate, Matrix3x3.mul(rot, scaleMat));

        //Perform vertex transformations
        for (int i = 0; i < vertices.length; i++)
        {
            vertices[i] = Matrix3x3.mul(transform, vertices[i]);
        }

        //Draw triangles. Perform triangle contains tests by converting the screen space coordinate to
        //normalized device coordinates and performing an edge test.
        for (int i = 0; i < width; ++i)
        {
            for (int j = 0; j < height; ++j)
            {
                Vector2 NDCPt = CGMath.ScreenSpaceToNDC(new Vector2(i, j), width, height);

                if (CGMath.pointInsideTriangle(vertices[0], vertices[1], vertices[2], NDCPt.toVector3()))
                {
                    rasterBuffer.setRGB(i, j, 0xff0000);
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
