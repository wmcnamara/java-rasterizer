/** Column major 2x2 Matrix Class
 * Laid out as follows:
 * [a11, a21]
 * [a12, a22]
 */
public class Matrix2x2
{
    /** Constructs a 2x2 Matrix, specified in column major format.
     * @param a11 Column 1, row 1
     * @param a21 Column 2 row 1
     * @param a12 Column 1 row 2
     * @param a22 Column 2 row 2
     */
    Matrix2x2(float a11, float a21, float a12, float a22)
    {
        this.a11 = a11;
        this.a22 = a22;
        this.a12 = a12;
        this.a21 = a21;
    } //end Matrix2x2

    /** Performs matrix vector multiplication
     * @param lhs Left hand side of multiplication
     * @param rhs Right hand side of multiplication
     * @return Result of the multiplication operation
     */
    public static Vector2 mul(Matrix2x2 lhs, Vector2 rhs)
    {
        Vector2 result = new Vector2(0, 0);

        result.x = lhs.a11 * rhs.x + lhs.a21 * rhs.y;
        result.y = lhs.a12 * rhs.x + lhs.a22 * rhs.y;

        return result;
    } //end mul

    /** Performs matrix to matrix multiplication
     * @param lhs Left hand side of operation
     * @param rhs Right hand side of operation
     * @return Matrix result of multiplication
     */
    public static Matrix2x2 mul(Matrix2x2 lhs, Matrix2x2 rhs)
    {
        Matrix2x2 result = new Matrix2x2(0, 0, 0, 0);
        result.a11 = lhs.a11 * rhs.a11 + lhs.a21 * rhs.a12;
        result.a21 = lhs.a11 * rhs.a21 + lhs.a21 * rhs.a22;
        result.a12 = lhs.a12 * rhs.a11 + lhs.a22 * rhs.a12;
        result.a22 = lhs.a12 * rhs.a21 + lhs.a22 * rhs.a22;

        return result;
    } //end mul

    /** Creates a z axis rotation matrix that rotates an object angle degrees around the origin
     * @param angle Angle in degrees to rotate around origin
     * @return 2x2 z axis rotation matrix
     */
    public static Matrix2x2 ZAxisRotationMatrix(float angle)
    {
        float c = (float) Math.cos(angle * (3.14/180.0));
        float s = (float) Math.sin(angle * (3.14f/180.0));

        return new Matrix2x2(c, -s, s, c);
    } //end ZAxisRotationMatrix

    /** Creates a matrix that scales points on the x and y-axis respectively
     * @param x X axis scale factor
     * @param y Y axis scale factor
     * @return 2x2 scale matrix
     */
    public static Matrix2x2 ScaleMatrix(float x, float y)
    {
        return new Matrix2x2(x, 0,0, y);
    } //end ScaleMatrix


    /** Creates a matrix that scales points on the x and y-axis respectively
     * @param scale X and Y axis scale factor
     * @return Returns a 2x2 scale matrix
     */
    public static Matrix2x2 ScaleMatrix(Vector2 scale)
    {
        return new Matrix2x2(scale.x, 0,0, scale.y);
    } //end ScaleMatrix

    public float a11, a21, a12, a22;
} //end Matrix2x2