public class Matrix2x2
{
    //Column major
    Matrix2x2(float a11, float a21, float a12, float a22)
    {
        this.a11 = a11;
        this.a22 = a22;
        this.a12 = a12;
        this.a21 = a21;
    }

    public static Vector2 mul(Matrix2x2 lhs, Vector2 rhs)
    {
        Vector2 result = new Vector2(0, 0);

        result.x = lhs.a11 * rhs.x + lhs.a21 * rhs.y;
        result.y = lhs.a12 * rhs.x + lhs.a22 * rhs.y;

        return result;
    }

    float a11, a21, a12, a22;
}
