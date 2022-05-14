/** 3 dimensional floating pointer vector class
 */
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
    } //end Vector3

    /** Creates a 3D vector from its X Y coordinates. Z will be set to 0
     * @param x X coordinate
     * @param y Y coordinate
     */
    public Vector3(float x, float y)
    {
        this.x = x;
        this.y = y;
        this.z = 0; //Homogenous coordinate
    } //end Vector3

    /** Creates a vector3 with zero'd xyz components
     */
    public Vector3()
    {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    } //end Vector3 constructor

    /** Performs a dot product operation between two 3D vectors
     * @param lhs left hand side of operation
     * @param rhs right hand side of operation
     * @return a scalar value representing the result of the dot product
     */
    public static float dot(Vector3 lhs, Vector3 rhs)
    {
        return lhs.x * rhs.x + lhs.y * rhs.y + lhs.z * rhs.z;
    } //end dot

    /** Perform scalar multiplication with a vector
     * @param lhs Left hand side of operation
     * @param rhs Right hand side of operation
     * @return Result of multiplications
     */
    public static Vector3 mul(Vector3 lhs, float rhs)
    {
        return new Vector3(lhs.x * rhs, lhs.y * rhs, lhs.z * rhs);
    } //end mul

    /** Performs vector to vector addition
     * @param lhs Left hand side of operation
     * @param rhs Right hand side of operation
     * @return Result of multiplications
     */
    public static Vector3 add(Vector3 lhs, Vector3 rhs)
    {
        return new Vector3(lhs.x + rhs.x, lhs.y + rhs.y, lhs.z + rhs.z);
    } //end add

    /** Performs subtraction of two Vector3's
     * @param lhs Right hand side of operation
     * @param rhs Left hand side of operation
     * @return The result of the component wise subtraction of the vectors
     */
    public static Vector3 sub(Vector3 lhs, Vector3 rhs)
    {
        return new Vector3(lhs.x - rhs.x, lhs.y - rhs.y, lhs.z - rhs.z);
    } //end sub

    /** Performs vector addition for three vectors. Useful for adding colors
     * @param v1 First vector to add
     * @param v2 Second vector to add
     * @param v3 Third vector to add
     * @return Result of multiplications
     */
    public static Vector3 add(Vector3 v1, Vector3 v2, Vector3 v3)
    {
        return new Vector3(v1.x + v2.x + v3.x, v1.y + v2.y + v3.y, v1.z + v2.z + v3.z);
    } //end add

    /** Converts the XYZ components of two float vectors to integers and performs a comparison
     * @param a First vector to compare
     * @param b Second vector to compare
     * @return True if the integer casted components all equal each other
     */
    public static boolean equalInt(Vector3 a, Vector3 b)
    {
        return (int)a.x == (int)b.x && (int)a.y == (int)b.y && (int)a.z == (int)b.z;
    } //end equalInt

    /** Converts a vector3 object to a vector2. Simply removes the Z value and retains the XY.
     * @return Vector2 object with the Vector3 X Y values
     */
    public Vector2 toVector2()
    {
        return new Vector2(x, y);
    } //end toVector2

    public float x, y, z;
} //end Vector3 class
