package Strategy;

public class HtmlContentStrategy implements Strategy {

    @Override
    public boolean compare(String snapshot1, String snapshot2) {
        return snapshot1.equals(snapshot2);
    }
}