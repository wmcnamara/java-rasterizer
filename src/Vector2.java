public class Vector2
{
    public Vector2(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public float dot(Vector2 a, Vector2 b)
    {
        return a.x * b.x + a.y * b.y;
    }

    public static boolean equal(Vector2 a, Vector2 b)
    {
        return (int)a.x == (int)b.x && (int)a.y == (int)b.y;
    }

    public float x, y;
}
