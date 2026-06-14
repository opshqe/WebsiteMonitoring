package Strategy;

/**
 * In this strategy HTML tags, attributes and extra whitespace are stripped away before
 * comparing. Only the visible text the user would read is checked
 */
public class TextContentStrategy implements Strategy {

    @Override
    public boolean compare(String snapshot1, String snapshot2) {
        String text1 = extractText(snapshot1);
        String text2 = extractText(snapshot2);
        return text1.equals(text2);
    }

    /**
     * Removes all HTML tags and normalises whitespace so that
     * only the human-readable text remains.
     */
    private String extractText(String html) {
        if (html == null || html.isEmpty()) return "";
        //Content is not visible
        String noScript = html.replaceAll("(?si)<(script|style)[^>]*>.*?</\\1>", "");

        String noTags = noScript.replaceAll("<[^>]+>", "");

        return noTags.replaceAll("\\s+", " ").trim();
    }
}

