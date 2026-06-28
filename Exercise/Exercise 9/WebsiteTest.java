import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import model.Website;
import observer.Observer;

public class WebsiteTest {

    // Stub: Observer der protokolliert ob update() aufgerufen wurde
    static class TestObserver implements Observer {
        boolean wasNotified = false;

        @Override
        public void update() {
            wasNotified = true;
        }
    }

    private Website website;

    @BeforeEach
    void setUp() {
        website = new Website("site1", "https://example.com");
    }

    // EC8 – Ein Observer angehängt → wird benachrichtigt
    @Test
    void testEC8_SingleObserverNotified() {
        TestObserver observer = new TestObserver();
        website.attach(observer);
        website.notifyObserver();
        assertTrue(observer.wasNotified);
    }

    // EC9 – Mehrere Observer → alle werden benachrichtigt
    @Test
    void testEC9_MultipleObserversAllNotified() {
        TestObserver o1 = new TestObserver();
        TestObserver o2 = new TestObserver();
        website.attach(o1);
        website.attach(o2);
        website.notifyObserver();
        assertTrue(o1.wasNotified);
        assertTrue(o2.wasNotified);
    }

    // EC10 – Observer entfernt → wird nicht mehr benachrichtigt
    @Test
    void testEC10_DetachedObserverNotNotified() {
        TestObserver observer = new TestObserver();
        website.attach(observer);
        website.detach(observer);
        website.notifyObserver();
        assertFalse(observer.wasNotified);
    }

    // EC11 – Keine Observer vorhanden → kein Fehler
    @Test
    void testEC11_NoObserversNoException() {
        assertDoesNotThrow(() -> website.notifyObserver());
    }
}