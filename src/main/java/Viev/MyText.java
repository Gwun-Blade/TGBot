package Viev;

public class MyText {
    String text;

    public MyText(String text) {
        this.text = text;
    }

    public String getString() {
        return text;
    }
    public boolean isEmpty() {
        return text.isEmpty();
    }
    @Override
    public String toString() {
        return "<text>" + text + "</text>";
    }
}
