import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import strategy.ContentSizeStrategy;

public class ContentSizeStrategyTest {

    private final ContentSizeStrategy strategy = new ContentSizeStrategy();

    // EC12 – Gleiche Länge → true (unchanged)
    @Test
    void testEC12_SameSize_Unchanged() {
        assertTrue(strategy.compare("hello", "world")); // beide 5 Zeichen
    }

    // EC13 – Größerer Inhalt → false (changed)
    @Test
    void testEC13_LargerContent_Changed() {
        assertFalse(strategy.compare("short", "much longer content"));
    }

    // EC14 – Kleinerer Inhalt → false (changed)
    @Test
    void testEC14_SmallerContent_Changed() {
        assertFalse(strategy.compare("much longer content", "short"));
    }
}
