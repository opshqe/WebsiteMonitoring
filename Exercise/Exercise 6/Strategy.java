package Strategy;

public interface Strategy {

    /**
     *
     * snapshot1 the previously stored snapshot
     * snapshot2 the recent fetched content
     * @return true  → no meaningful change detected
     *         false → content has changed
     */
    boolean compare(String snapshot1, String snapshot2);
}