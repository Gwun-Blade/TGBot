package Viev;

public class MyButton {
    private String text;
    private String data;

    public MyButton(String text, String data) {
        this.text = text;
        this.data = data;
    }
    @Override
    public String toString() {
        return "<button id=\"" + data + "\">" + text + "</button>";
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
