public class Vector3
{

    /** Creates a 3D vector from its X Y and Z coordinates
     * @param x X coordinate
     * @param y Y coordinate
     * @param z Z coordinate
     */
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

    /** Creates a vector3 with zero'd xyz components
     *
     */
    public Vector3()
    {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    /** Performs a dot product operation between two 3D vectors
     * @param a left hand side of operation
     * @param b right hand side of operation
     * @return a scalar value representing the result of the dot product
     */
    public static float dot(Vector3 lhs, Vector3 rhs)
    {
        return lhs.x * rhs.x + lhs.y * rhs.y + lhs.z * rhs.z;
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
