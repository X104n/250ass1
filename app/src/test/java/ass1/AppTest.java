import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import ass1.App;

public class AppTest {

    @Test
    public void testGetConversionFactor() {
        assertEquals(0.0254, App.getConversionFactor("in"), 0.0001);
        assertEquals(0.3048, App.getConversionFactor("ft"), 0.0001);
        assertEquals(1609.344, App.getConversionFactor("mi"), 0.0001);
        assertEquals(1.0, App.getConversionFactor("m"), 0.0001);
    }
}