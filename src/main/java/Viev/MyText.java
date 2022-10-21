package Viev;

public class MyText {
    String text;

    public MyText(String text) {
        this.text = text;
    }
    @Override
    public String toString() {
        return "<text>" + text + "</text>";
    }
}
