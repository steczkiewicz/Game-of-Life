//import agh.ics.oop.*;
//
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//public class AnimalMovingTests {
//    @Test
//    public void directionTest(){
//        Animal dog = new Animal();
//        assertEquals(MapDirection.NORTH, dog.getOrientation());
//        for (int i = 0; i < 15; i++){
//            dog.move(MoveDirection.LEFT);
//        }
//        assertEquals(MapDirection.EAST, dog.getOrientation());
//        dog.move(MoveDirection.RIGHT);
//        dog.move(MoveDirection.RIGHT);
//        dog.move(MoveDirection.LEFT);
//        assertEquals(MapDirection.SOUTH, dog.getOrientation());
//    }
//
//
//    @Test
//    public void positionTest(){
//        Animal dog = new Animal();
//        assertEquals(new Vector2d(2,2), dog.getPosition());
//        dog.move(MoveDirection.FORWARD);
//        dog.move(MoveDirection.FORWARD);
//        dog.move(MoveDirection.RIGHT);
//        dog.move(MoveDirection.FORWARD);
//        assertEquals(new Vector2d(3, 4), dog.getPosition());
//        dog.move(MoveDirection.LEFT);
//        dog.move(MoveDirection.BACKWARD);
//        dog.move(MoveDirection.BACKWARD);
//        dog.move(MoveDirection.BACKWARD);
//        dog.move(MoveDirection.BACKWARD);
//        assertEquals(new Vector2d(3,0), dog.getPosition());
//
//    }
//
//    @Test
//    public void borderTest(){
//        Animal cat = new Animal();
//        for (int i = 0; i < 10; i++){
//            cat.move(MoveDirection.FORWARD);
//        }
//        assertEquals(new Vector2d(2,4), cat.getPosition()); //top border
//        cat.move(MoveDirection.LEFT);
//        for (int i = 0; i < 10; i++){
//            cat.move(MoveDirection.FORWARD);
//        }
//        assertEquals(new Vector2d(0,4), cat.getPosition()); //left border
//        cat.move(MoveDirection.LEFT);
//        for (int i = 0; i < 10; i++){
//            cat.move(MoveDirection.FORWARD);
//        }
//        assertEquals(new Vector2d(0,0), cat.getPosition()); //bottom border
//        cat.move(MoveDirection.LEFT);
//        for (int i = 0; i < 10; i++){
//            cat.move(MoveDirection.FORWARD);
//        }
//        assertEquals(new Vector2d(4,0), cat.getPosition()); //right border
//
//    }
//
//    @Test
//    public void parserTest(){
//        String[] args1 = {"f", "backward", "right", "r", "back", "nothing", "b", "left"};
//        OptionsParser parser = new OptionsParser();
//        ArrayList<MoveDirection> output1 = new ArrayList<MoveDirection>();
//        output1.add(MoveDirection.FORWARD);
//        output1.add(MoveDirection.BACKWARD);
//        output1.add(MoveDirection.RIGHT);
//        output1.add(MoveDirection.RIGHT);
//        output1.add(MoveDirection.BACKWARD);
//        output1.add(MoveDirection.LEFT);
//        assertEquals(output1,parser.parse(args1));
//
//        String[] args2 = {"1", "2", "3", "4", "4", "b"};
//        OptionsParser parser2 = new OptionsParser();
//        ArrayList<MoveDirection> output2 = new ArrayList<MoveDirection>();
//        output2.add(MoveDirection.BACKWARD);
//        assertEquals(output2,parser2.parse(args2));
//
//        String[] args3 = {"f", "r", "b", "l", "forward", "right", "backward", "left"};
//        OptionsParser parser3 = new OptionsParser();
//        ArrayList<MoveDirection> output3 = new ArrayList<MoveDirection>();
//        output3.add(MoveDirection.FORWARD);
//        output3.add(MoveDirection.RIGHT);
//        output3.add(MoveDirection.BACKWARD);
//        output3.add(MoveDirection.LEFT);
//        output3.add(MoveDirection.FORWARD);
//        output3.add(MoveDirection.RIGHT);
//        output3.add(MoveDirection.BACKWARD);
//        output3.add(MoveDirection.LEFT);
//        assertEquals(output3,parser3.parse(args3));
//    }
//}