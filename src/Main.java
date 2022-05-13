import java.awt.*;
import java.awt.image.*;


class Main
{
    static final int windowWidth = 800, windowHeight = 600;

    //Vertices and colors for a rectangle
    static Vertex[] vertices = {
            new Vertex(new Vector3(0.5f, -.5f, 1.0f), new Vector3(1f, 0f, 0f)),
            new Vertex(new Vector3(-0.5f, -.5f, 1.0f), new Vector3(0f, 1f, 0f)),
            new Vertex(new Vector3(-.5f, 0.5f, 1.0f), new Vector3(0f, 0f, 1.0f)),

            new Vertex(new Vector3(-0.5f, .5f, 1.0f), new Vector3(1f, 0f, 0f)),
            new Vertex(new Vector3(.5f, .5f, 1.0f), new Vector3(0f, 0f, 1.0f)),
            new Vertex(new Vector3(.5f, -0.5f, 1.0f),  new Vector3(0f, 1f, 0f)),
    };

    static public void main(String[] args)
    {
        //Setup transformations
        float angle = -0;
        Vector3 scale = new Vector3(.7f, .7f, .7f);
        Vector2 pos = new Vector2(0.1f, 0);


        Matrix3x3 rot = Matrix3x3.ZAxisRotationMatrix(angle);
        Matrix3x3 scaleMat = Matrix3x3.ScaleMatrix(scale);
        Matrix3x3 translate = Matrix3x3.TranslationMatrix(pos);

        //Multiply transformations together
        Matrix3x3 transform = Matrix3x3.mul(translate, Matrix3x3.mul(rot, scaleMat));

        //Perform vertex transformations
        for (int i = 0; i < vertices.length; i++)
        {
            vertices[i].position = Matrix3x3.mul(transform, vertices[i].position);
        }

        Rasterizer.Rasterize(vertices, windowWidth, windowHeight, 0xffffff);
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
