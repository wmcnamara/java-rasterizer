/** Class containing various utility functions for computer graphics mathematics
 */
public class CGMath
{
    /** Returns true if a point is on the right side of a line defined by a and b
     * @param a First point defining a line
     * @param b Second point defining a line
     * @param c Point to test
     * @return True if the point is on the right side of the line
     */
    public static boolean edgeFunction(Vector2 a, Vector2 b, Vector2 c)
    {
        return ((c.x - a.x) * (b.y - a.y) - (c.y - a.y) * (b.x - a.x) >= 0);
    }


    /** Checks if a point is inside a triangle defined like tp1, tp2 and tp3
     * @param tp1 The first point defining a triangle
     * @param tp2 The second point defining a triangle
     * @param tp3 The third point defining a triangle
     * @param pToTest The point to test if its inside the triangle
     * @return True if the pToTest point is inside the triangle
     */
    public static boolean pointInsideTriangle(Vector2 tp1, Vector2 tp2, Vector2 tp3, Vector2 pToTest)
    {
        return edgeFunction(tp1, tp2, pToTest) && edgeFunction(tp2, tp3, pToTest) && edgeFunction(tp3, tp1, pToTest);
    }

    /** Checks if a point is inside a triangle defined like tp1, tp2 and tp3
     * @param tp1 The first point defining a triangle
     * @param tp2 The second point defining a triangle
     * @param tp3 The third point defining a triangle
     * @param pToTest The point to test if its inside the triangle
     * @return True if the pToTest point is inside the triangle
     */
    public static boolean pointInsideTriangle(Vector3 tp1, Vector3 tp2, Vector3 tp3, Vector3 pToTest)
    {
        return edgeFunction(tp1.toVector2(), tp2.toVector2(), pToTest.toVector2()) &&
                edgeFunction(tp2.toVector2(), tp3.toVector2(), pToTest.toVector2()) &&
                edgeFunction(tp3.toVector2(), tp1.toVector2(), pToTest.toVector2());
    }

    /** Converts normalized device coordinates to screen space coordinates
     * @param ndcCoord Converts a normalized device coordinate to a screen space coordinate
     * @param screenWidth The width of the screen
     * @param screenHeight The height of the screen
     * @return A screen space coordinate
     */
    public static Vector2 NDCToScreenSpace(Vector2 ndcCoord, int screenWidth, int screenHeight)
    {
        Vector2 result = new Vector2(0, 0);
        result.x = screenWidth * ((ndcCoord.x + 1.0f) / 2.0f);
        result.y = screenHeight * ((ndcCoord.y + 1.0f) / 2.0f);

        return result;
    }

    /** Converts normalized device coordinates to screen space coordinates
     * @param ndcCoord Converts a normalized device coordinate to a screen space coordinate
     * @param screenWidth The width of the screen
     * @param screenHeight The height of the screen
     * @return A screen space coordinate
     */
    public static Vector3 NDCToScreenSpace(Vector3 ndcCoord, int screenWidth, int screenHeight)
    {
        Vector3 result = new Vector3(0, 0);
        result.x = screenWidth * ((ndcCoord.x + 1.0f) / 2.0f);
        result.y = screenHeight * ((ndcCoord.y + 1.0f) / 2.0f);
        result.z = 1.0f;

        return result;
    }

    /** Converts screen spaces coordinates to normalized device coordinates
     * @param screenPnt Screen space coordinate (x, y pixel position)
     * @param width Width of the screen that the screenpoint lies in
     * @param height Height of the screen that the screenpoint lies in
     * @return A coordinate in normalized device coordinate space
     */

    public static Vector2 ScreenSpaceToNDC(Vector2 screenPnt, int width, int height)
    {
        Vector2 ndc = new Vector2(0, 0);

        ndc.x = ((screenPnt.x / width)  * 2) - 1;
        ndc.y = ((screenPnt.y / height) * 2) - 1;

        return ndc;
    }


    /** Converts screen spaces coordinates to normalized device coordinates
     * @param screenPnt Screen space coordinate to convert
     * @param width Width of the screen
     * @param height Height of the screen
     * @return The screen space coordinate in normalized device coordinate
     */
    public static Vector3 ScreenSpaceToNDC(Vector3 screenPnt, int width, int height)
    {
        Vector3 ndc = new Vector3(0, 0);

        ndc.x = ((screenPnt.x / width)  * 2) - 1;
        ndc.y = ((screenPnt.y / height) * 2) - 1;
        ndc.z = 0f;

        return ndc;
    }


    /** Converts normalized device coordinates to screen space coordinates
     * @param ndcCoords Normalized device coordinate to be converted
     * @param screenWidth Width of the screen
     * @param screenHeight Height of the screen
     * @return The normalized device coordinate in screen space
     */
    public static Vector2[] NDCToScreenSpace(Vector2[] ndcCoords, int screenWidth, int screenHeight)
    {
        Vector2[] scPts = new Vector2[ndcCoords.length];

        for (int i = 0; i < scPts.length; i++)
        {
            float rx = screenWidth * ((ndcCoords[i].x + 1.0f) / 2.0f);
            float ry = screenHeight * ((ndcCoords[i].y + 1.0f) / 2.0f);

            scPts[i] = new Vector2((int)rx, (int)ry);
        }

        return scPts;
    }

    /** Computes the barycentric coordinates of a point with respect to a given triangle
     * Solution adapted from: http://realtimecollisiondetection.net/
     * @param a The first point of the triangle
     * @param b The second point of the triangle
     * @param c The third point of the triangle
     * @param p Point to compute barycentric coordinates for
     * @return A Vector3 containing the UVW barycentric coordinates for such a point
     */
    public static Vector3 ComputeBarycentricCoordinates(Vector2 a, Vector2 b, Vector2 c, Vector2 p)
    {
        Vector3 bcCoords = new Vector3();

        Vector2 v0 = Vector2.sub(b, a);
        Vector2 v1 = Vector2.sub(c, a);
        Vector2 v2 = Vector2.sub(p, a);

        float d00 = Vector2.dot(v0, v0);
        float d01 = Vector2.dot(v0, v1);
        float d11 = Vector2.dot(v1, v1);
        float d20 = Vector2.dot(v2, v0);
        float d21 = Vector2.dot(v2, v1);

        float denom = d00 * d11 - d01 * d01;

        bcCoords.x = (d11 * d20 - d01 * d21) / denom;
        bcCoords.y = (d00 * d21 - d01 * d20) / denom;
        bcCoords.z = 1.0f -  bcCoords.x - bcCoords.y;

        return bcCoords;
    }

    /** Converts an RGB value with ranges of 0-1 to an integer hexadecimal value
     * @param color RGB values of the color
     * @return hexadecimal color value
     */
    public static int RGBToHex(Vector3 color)
    {
        int r = Math.round((color.x * 255.0f));
        int g = Math.round((color.y * 255.0f));
        int b = Math.round((color.z * 255.0f));

        return (r << 16) | (g << 8) | b;
    } //end RGBToHex
}
