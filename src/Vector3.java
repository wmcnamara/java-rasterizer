public class Vector3
{
    public Vector3(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Vector3(float x, float y)
    {
        this.x = x;
        this.y = y;
        this.z = 1; //Homogenous coordinate
    }

    public Vector3()
    {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public static float dot(Vector3 a, Vector3 b)
    {
        return a.x * b.x + a.y * b.y + a.z * b.z;
    }

    public static boolean equal(Vector3 a, Vector3 b)
    {
        return (int)a.x == (int)b.x && (int)a.y == (int)b.y && (int)a.z == (int)b.z;
    }

    public Vector2 toVector2() { return new Vector2(x, y); }
    public float x, y, z;
}
