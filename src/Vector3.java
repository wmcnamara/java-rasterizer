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
        this.z = 0; //Homogenous coordinate
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

    public static Vector3 mul(Vector3 lhs, float rhs)
    {
        return new Vector3(lhs.x * rhs, lhs.y * rhs, lhs.z * rhs);
    }

    public static Vector3 add(Vector3 lhs, Vector3 rhs)
    {
        return new Vector3(lhs.x + rhs.x, lhs.y + rhs.y, lhs.z + rhs.z);
    }
    public static Vector3 add(Vector3 lhs, Vector3 rhs, Vector3 rrhs)
    {
        return new Vector3(lhs.x + rhs.x + rrhs.x, lhs.y + rhs.y + rrhs.y, lhs.z + rhs.z + rrhs.z);
    }
    public static boolean equal(Vector3 a, Vector3 b)
    {
        return (int)a.x == (int)b.x && (int)a.y == (int)b.y && (int)a.z == (int)b.z;
    }

    public Vector2 toVector2() { return new Vector2(x, y); }
    public float x, y, z;
}
