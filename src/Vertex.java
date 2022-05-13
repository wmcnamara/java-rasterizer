public class Vertex
{
    Vertex(Vector3 position, Vector3 color)
    {
        this.position = position;
        this.color = color;
    }

    public Vector3 position; //X, Y position with a 1 homogenous coordinate
    public Vector3 color; //RGB color
}
