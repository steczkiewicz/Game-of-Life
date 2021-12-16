import agh.ics.oop.MapDirection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapDirectionTest {
    @Test
    public void nextTest(){
        MapDirection x = MapDirection.EAST;
        assertEquals(MapDirection.SOUTH,x.next());
        x = x.next();
        assertEquals(MapDirection.WEST,x.next());
        x = x.next();
        assertEquals(MapDirection.NORTH,x.next());
        x = x.next();
        assertEquals(MapDirection.EAST,x.next());
    }
    @Test
    public void previousTest(){
        MapDirection x = MapDirection.EAST;
        assertEquals(MapDirection.NORTH,x.previous());
        x = x.previous();
        assertEquals(MapDirection.WEST,x.previous());
        x = x.previous();
        assertEquals(MapDirection.SOUTH,x.previous());
        x = x.previous();
        assertEquals(MapDirection.EAST,x.previous());
    }
}
