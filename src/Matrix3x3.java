/** Column major 3x3 matrix class
 *  Laid out as follows:
 *     [a11, a21, a31]
 *     [a12, a22, a32]
 *     [a13, a23, a33]
 */
public class Matrix3x3
{
    /** Constructs a 3x3 matrix with floating point elements
     * @param a11 Column 1 row 1
     * @param a21 Column 2 row 1
     * @param a31 Column 3 row 1
     * @param a12 Column 1 row 2
     * @param a22 Column 2 row 2
     * @param a32 Column 3 row 2
     * @param a13 Column 1 row 3
     * @param a23 Column 2 row 3
     * @param a33 Column 3 row 3
     */
    Matrix3x3(float a11, float a21, float a31, float a12, float a22, float a32, float a13, float a23, float a33)
    {
        this.a11 = a11;
        this.a21 = a21;
        this.a31 = a31;

        this.a12 = a12;
        this.a22 = a22;
        this.a32 = a32;

        this.a13 = a13;
        this.a23 = a23;
        this.a33 = a33;
    }

    /** Performs matrix vector multiplication
     * @param lhs Left hand side of multiplication
     * @param rhs Right hand side of multiplication
     * @return Result of the matrix multiplication
     */
    public static Vector3 mul(Matrix3x3 lhs, Vector3 rhs)
    {
        Vector3 result = new Vector3();

        result.x = lhs.a11 * rhs.x + lhs.a21 * rhs.y + lhs.a31 * rhs.z;
        result.y = lhs.a12 * rhs.x + lhs.a22 * rhs.y + lhs.a32 * rhs.z;
        result.z = lhs.a13 * rhs.x + lhs.a23 * rhs.y + lhs.a33 * rhs.z;

        return result;
    }


    /** Performs matrix to matrix multiplication
     * @param lhs Left hand side of multiplication
     * @param rhs Right hand side of multiplication
     * @return Result of the matrix multiplication
     */
    public static Matrix3x3 mul(Matrix3x3 lhs, Matrix3x3 rhs)
    {
        Matrix3x3 result = new Matrix3x3(0, 0, 0, 0, 0, 0, 0, 0, 0);

        result.a11 = lhs.a11 * rhs.a11 + lhs.a21 * rhs.a12 + lhs.a31 * rhs.a13;
        result.a21 = lhs.a11 * rhs.a21 + lhs.a21 * rhs.a22 + lhs.a31 * rhs.a23;
        result.a31 = lhs.a11 * rhs.a31 + lhs.a21 * rhs.a32 + lhs.a31 * rhs.a33;

        result.a12 = lhs.a12 * rhs.a11 + lhs.a22 * rhs.a12 + lhs.a32 * rhs.a13;
        result.a22 = lhs.a12 * rhs.a21 + lhs.a22 * rhs.a22 + lhs.a32 * rhs.a23;
        result.a32 = lhs.a12 * rhs.a31 + lhs.a32 * rhs.a22 + lhs.a32 * rhs.a33;

        result.a13 = lhs.a13 * rhs.a11 + lhs.a23 * rhs.a12 + lhs.a33 * rhs.a13;
        result.a23 = lhs.a13 * rhs.a21 + lhs.a23 * rhs.a22 + lhs.a33 * rhs.a23;
        result.a33 = lhs.a13 * rhs.a31 + lhs.a23 * rhs.a32 + lhs.a33 * rhs.a33;

        return result;
    }

    /** Creates a z axis rotation matrix that rotates an object around the origin
     * @param angle Rotation angle in degrees
     * @return 3x3 rotation matrix
     */
    public static Matrix3x3 ZAxisRotationMatrix(float angle)
    {
        float c = (float) Math.cos(angle * (3.14f / 180.0f));
        float s = (float) Math.sin(angle * (3.14f / 180.0f));

        return new Matrix3x3(c, -s, 0, s, c, 0, 0, 0, 1);
    }

    /** Creates a matrix that scales points on the x y and z axis respectively
     * @param x X axis scale factor
     * @param y Y axis scale factor
     * @param z Z axis scale factor
     * @return 3x3 scale matrix
     */
    public static Matrix3x3 ScaleMatrix(float x, float y, float z)
    {
        return new Matrix3x3(x, 0,0, 0, y, 0, 0, 0, z);
    }

    /** Creates a matrix that scales points on the x y and z axis respectively
     * @param scale 3D vector representing the XYZ scale factor
     * @return 3x3 scale matrix
     */
    public static Matrix3x3 ScaleMatrix(Vector3 scale)
    {
        return new Matrix3x3(scale.x, 0,0, 0, scale.y, 0, 0, 0, scale.z);
    }


    /** Creates a matrix that translates points on the x and y-axis respectively
     * @param x X axis translation
     * @param y Y axis translation
     * @return 3x3 translation matrix
     */
    public static Matrix3x3 TranslationMatrix(float x, float y)
    {
        return new Matrix3x3(1, 0,x, 0, 1, y, 0, 0, 1);
    }

    /** Creates a matrix that translates points on the x and y-axis respectively
     * @param vec 2D vector representing the x and y translation
     * @return 3x3 translation matrix
     */
    public static Matrix3x3 TranslationMatrix(Vector2 vec)
    {
        return new Matrix3x3(1, 0, vec.x, 0, 1, vec.y, 0, 0, 1);
    }

    public float a11, a21, a31, a12, a22, a32, a13, a23, a33;
}
