/** 2 dimensional floating point vector class
 *
 */
public class Vector2
{
    /** Constructs a 2D vector from its X Y coordinates
     * @param x X coordinate
     * @param y Y coordinate
     */
    public Vector2(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    /** Constructs an empty 2D vector with zero'd xy components
     *
     */
    public Vector2()
    {
        this.x = 0;
        this.y = 0;
    }

    public static Vector2 sub(Vector2 lhs, Vector2 rhs)
    {
        Vector2 v = new Vector2();

        v.x = lhs.x - rhs.x;
        v.y = lhs.y - rhs.y;

        return v;
    }

    public static Vector2 add(Vector2 lhs, Vector2 rhs)
    {
        Vector2 v = new Vector2();

        v.x = lhs.x + rhs.x;
        v.y = lhs.y + rhs.y;

        return v;
    }

    public static float dot(Vector2 a, Vector2 b)
    {
        return a.x * b.x + a.y * b.y;
    }

    public static boolean equal(Vector2 a, Vector2 b)
    {
        return (int)a.x == (int)b.x && (int)a.y == (int)b.y;
    }

    public Vector3 toVector3() { return new Vector3(x, y, 0); }
    public float x, y;
}
