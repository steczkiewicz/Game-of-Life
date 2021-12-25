import agh.ics.oop.DNA;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class GenesTest {
    @Test
    public void genesTest() {
        DNA genes1 = new DNA();
        System.out.println(Arrays.toString(genes1.genesArray));
        DNA genes2 = new DNA();
        System.out.println(Arrays.toString(genes2.genesArray));
    }

}
