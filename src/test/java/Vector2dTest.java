//import agh.ics.oop.MapDirection;
//import agh.ics.oop.Vector2d;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class Vector2dTest {
//    @Test
//    public void equalsTest(){
//        Vector2d x = new Vector2d(1,0);
//        Vector2d y = new Vector2d(2,0);
//        Vector2d z = new Vector2d(1,0);
//        assertEquals(x, z);
//        assertNotEquals(x, y);
//    }
//    @Test
//    public void toStringTest() {
//        Vector2d x = new Vector2d(1,0);
//        Vector2d y = new Vector2d(2,0);
//        Vector2d z = new Vector2d(1,-1);
//        assertEquals("(1,0)",x.toString());
//        assertEquals("(2,0)",y.toString());
//        assertEquals("(1,-1)",z.toString());
//    }
//    @Test
//    public void precedesTest() {
//        Vector2d x = new Vector2d(1,0);
//        Vector2d y = new Vector2d(2,0);
//        Vector2d z = new Vector2d(-1,-1);
//        Vector2d w = new Vector2d(-3,2);
//        assertTrue(x.precedes(y));
//        assertTrue(z.precedes(x));
//        assertFalse(w.precedes(z));
//        assertFalse(y.precedes(w));
//    }
//
//    @Test
//    public void followsTest() {
//        Vector2d x = new Vector2d(1,0);
//        Vector2d y = new Vector2d(2,0);
//        Vector2d z = new Vector2d(-1,-1);
//        Vector2d w = new Vector2d(-3,2);
//        assertFalse(x.follows(y));
//        assertFalse(z.follows(x));
//        assertTrue(y.follows(z));
//        assertTrue(x.follows(z));
//    }
//
//    @Test
//    public void upperRightTest() {
//        Vector2d x = new Vector2d(1,0);
//        Vector2d y = new Vector2d(2,0);
//        Vector2d z = new Vector2d(-1,-1);
//        Vector2d w = new Vector2d(-3,2);
//        assertEquals(new Vector2d(2,0),x.upperRight(y));
//        assertEquals(new Vector2d(1,0),z.upperRight(x));
//        assertEquals(new Vector2d(-1,2),w.upperRight(z));
//        assertEquals(new Vector2d(2,2),y.upperRight(w));
//    }
//    @Test
//    public void lowerLeftTest() {
//        Vector2d x = new Vector2d(1,0);
//        Vector2d y = new Vector2d(2,0);
//        Vector2d z = new Vector2d(-1,-1);
//        Vector2d w = new Vector2d(-3,2);
//        assertEquals(new Vector2d(1,0),x.lowerLeft(y));
//        assertEquals(new Vector2d(-1,-1),z.lowerLeft(x));
//        assertEquals(new Vector2d(-3,-1),w.lowerLeft(z));
//        assertEquals(new Vector2d(-3,0),y.lowerLeft(w));
//    }
//@Test
//    public void addTest() {
//        Vector2d x = new Vector2d(1,0);
//        Vector2d y = new Vector2d(2,0);
//        Vector2d z = new Vector2d(-1,-1);
//        Vector2d w = new Vector2d(-3,2);
//        assertEquals(new Vector2d(3,0),x.add(y));
//        assertEquals(new Vector2d(0,-1),z.add(x));
//        assertEquals(new Vector2d(-4,1),w.add(z));
//        assertEquals(new Vector2d(-1,2),y.add(w));
//    }
//@Test
//    public void subtractTest() {
//        Vector2d x = new Vector2d(1,0);
//        Vector2d y = new Vector2d(2,0);
//        Vector2d z = new Vector2d(-1,-1);
//        Vector2d w = new Vector2d(-3,2);
//        assertEquals(new Vector2d(-1,0),x.subtract(y));
//        assertEquals(new Vector2d(-2,-1),z.subtract(x));
//        assertEquals(new Vector2d(-2,3),w.subtract(z));
//        assertEquals(new Vector2d(5,-2),y.subtract(w));
//    }
//@Test
//    public void oppositeTest() {
//        Vector2d x = new Vector2d(1,0);
//        Vector2d y = new Vector2d(2,0);
//        Vector2d z = new Vector2d(-1,-1);
//        Vector2d w = new Vector2d(-3,2);
//        assertEquals(new Vector2d(-1,0),x.opposite());
//        assertEquals(new Vector2d(1,1),z.opposite());
//        assertEquals(new Vector2d(3,-2),w.opposite());
//        assertEquals(new Vector2d(-2,0),y.opposite());
//    }
//}
