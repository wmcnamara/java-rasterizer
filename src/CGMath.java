/*
    Class containing various utility functions for computer graphics mathematics
*/
public class CGMath
{
    /*
        Returns true if a point is on the right side of a line defined by a and b
    */
    public static boolean edgeFunction(Vector2 a, Vector2 b, Vector2 c)
    {
        return ((c.x - a.x) * (b.y - a.y) - (c.y - a.y) * (b.x - a.x) >= 0);
    }

    /*
        Checks if a point is inside a triangle defined like tp1, tp2 and tp3
    */
    public static boolean pointInsideTriangle(Vector2 tp1, Vector2 tp2, Vector2 tp3, Vector2 pToTest)
    {
        return edgeFunction(tp1, tp2, pToTest) && edgeFunction(tp2, tp3, pToTest) && edgeFunction(tp3, tp1, pToTest);
    }

    /*
       Checks if a point is inside a triangle defined like tp1, tp2 and tp3
   */
    public static boolean pointInsideTriangle(Vector3 tp1, Vector3 tp2, Vector3 tp3, Vector3 pToTest)
    {
        return edgeFunction(tp1.toVector2(), tp2.toVector2(), pToTest.toVector2()) &&
                edgeFunction(tp2.toVector2(), tp3.toVector2(), pToTest.toVector2()) &&
                edgeFunction(tp3.toVector2(), tp1.toVector2(), pToTest.toVector2());
    }

    /*
        Converts normalized device coordinates to screen space coordinates
    */
    public static Vector2 NDCToScreenSpace(Vector2 ndcCoord, int screenWidth, int screenHeight)
    {
        Vector2 result = new Vector2(0, 0);
        result.x = screenWidth * ((ndcCoord.x + 1.0f) / 2.0f);
        result.y = screenHeight * ((ndcCoord.y + 1.0f) / 2.0f);

        return result;
    }

    /*
        Converts screen spaces coordinates to normalized device coordinates
     */
    public static Vector2 ScreenSpaceToNDC(Vector2 screenPnt, int width, int height)
    {
        Vector2 ndc = new Vector2(0, 0);

        ndc.x = ((screenPnt.x / width)  * 2) - 1;
        ndc.y = ((screenPnt.y / height) * 2) - 1;

        return ndc;
    }

    /*
        Converts screen spaces coordinates to normalized device coordinates
     */
    public static Vector3 ScreenSpaceToNDC(Vector3 screenPnt, int width, int height)
    {
        Vector3 ndc = new Vector3(0, 0);

        ndc.x = ((screenPnt.x / width)  * 2) - 1;
        ndc.y = ((screenPnt.y / height) * 2) - 1;
        ndc.z = 0f;

        return ndc;
    }

    /*
        Converts normalized device coordinates to screen space coordinates
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
}
