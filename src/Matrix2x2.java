/*
    Column major 2x2 matrix class

    Laid out as follows:
    [a11, a21]
    [a12, a22]
*/
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

    /*
        Performs matrix vector multiplication
    */
    public static Vector2 mul(Matrix2x2 lhs, Vector2 rhs)
    {
        Vector2 result = new Vector2(0, 0);

        result.x = lhs.a11 * rhs.x + lhs.a21 * rhs.y;
        result.y = lhs.a12 * rhs.x + lhs.a22 * rhs.y;

        return result;
    }

    /*
        Performs matrix to matrix multiplication
    */
    public static Matrix2x2 mul(Matrix2x2 lhs, Matrix2x2 rhs)
    {
        Matrix2x2 result = new Matrix2x2(0, 0, 0, 0);
        result.a11 = lhs.a11 * rhs.a11 + lhs.a21 * rhs.a12;
        result.a21 = lhs.a11 * rhs.a21 + lhs.a21 * rhs.a22;
        result.a12 = lhs.a12 * rhs.a11 + lhs.a22 * rhs.a12;
        result.a22 = lhs.a12 * rhs.a21 + lhs.a22 * rhs.a22;

        return result;
    }
    /*
        Creates a z axis rotation matrix that rotates an object angle degrees around the origin
    */
    public static Matrix2x2 ZAxisRotationMatrix(float angle)
    {
        float c = (float) Math.cos(angle * (3.14/180.0));
        float s = (float) Math.sin(angle * (3.14f/180.0));

        return new Matrix2x2(c, -s, s, c);
    }

    /*
        Creates a matrix that scales points on the x and y-axis respectively
    */
    public static Matrix2x2 ScaleMatrix(float x, float y)
    {
        return new Matrix2x2(x, 0,0, y);
    }

    float a11, a21, a12, a22;
}
