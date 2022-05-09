public class Vector2
{
    public Vector2(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public Vector2()
    {
        this.x = 0;
        this.y = 0;
    }

    public static float dot(Vector2 a, Vector2 b)
    {
        return a.x * b.x + a.y * b.y;
    }

    public static boolean equal(Vector2 a, Vector2 b)
    {
        return (int)a.x == (int)b.x && (int)a.y == (int)b.y;
    }

    public Vector3 toVector3() { return new Vector3(x, y, 1); }
    public float x, y;
}
