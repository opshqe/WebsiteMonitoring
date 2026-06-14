package Strategy;

public class ContentSizeStrategy implements Strategy {

    @Override
    public boolean compare(String snapshot1, String snapshot2) {
        return snapshot1.length() == snapshot2.length();
    }
}
