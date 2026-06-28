import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import controller.WebsiteMonitor;
import model.Website;
import strategy.Strategy;
import strategy.ContentSizeStrategy;
import strategy.HtmlContentStrategy;

public class WebsiteMonitorTest {

    // Stub: Strategy mit konfigurierbarem Rückgabewert
    static class TestStrategy implements Strategy {
        private final boolean result;

        TestStrategy(boolean result) {
            this.result = result;
        }

        @Override
        public boolean compare(String s1, String s2) {
            return result;
        }
    }

    private WebsiteMonitor monitor;
    private Website website;

    @BeforeEach
    void setUp() {
        monitor = new WebsiteMonitor();
        website = new Website("site1", "https://example.com");
    }

    // EC1 – WebsiteMonitor kann instanziiert werden
    @Test
    void testEC1_Creation() {
        assertNotNull(monitor);
    }

    // EC2 – Default-Strategy (HtmlContentStrategy): gleicher Inhalt → true
    @Test
    void testEC2_DefaultStrategyUnchanged() {
        monitor.addWebsite(website);
        monitor.takeSnapshot(website, "initial content");
        assertTrue(monitor.compareSnapshots(website, "initial content"));
    }

    // EC3 – Strategy kann gewechselt werden
    @Test
    void testEC3_SetStrategy() {
        monitor.setStrategy(new ContentSizeStrategy());
        monitor.addWebsite(website);
        monitor.takeSnapshot(website, "abc");
        assertTrue(monitor.compareSnapshots(website, "xyz")); // gleiche Länge → true
    }

    // EC4 – Erste Prüfung ohne Snapshot → immer true (unchanged)
    @Test
    void testEC4_FirstCheckNoSnapshot() {
        monitor.addWebsite(website);
        assertTrue(monitor.compareSnapshots(website, "some content"));
    }

    // EC5 – addWebsite() fügt Website zur Liste hinzu
    @Test
    void testEC5_AddWebsite() {
        monitor.addWebsite(website);
        assertEquals(1, monitor.getWebsites().size());
    }

    // EC6 – Strategy meldet keine Änderung → compareSnapshots gibt true
    @Test
    void testEC6_NoChangeDetected() {
        monitor.setStrategy(new TestStrategy(true));
        monitor.addWebsite(website);
        monitor.takeSnapshot(website, "snapshot");
        assertTrue(monitor.compareSnapshots(website, "anything"));
    }

    // EC7 – Strategy meldet Änderung → compareSnapshots gibt false
    @Test
    void testEC7_ChangeDetected() {
        monitor.setStrategy(new TestStrategy(false));
        monitor.addWebsite(website);
        monitor.takeSnapshot(website, "old content");
        assertFalse(monitor.compareSnapshots(website, "new content"));
    }
}