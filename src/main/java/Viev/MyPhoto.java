package Viev;

public class MyPhoto {
    String photoId;

    public MyPhoto(String id) {
        photoId = id;
    }

    public String getString() {
        return photoId;
    }
    @Override
    public String toString() {
        return "<photo>" + photoId + "</photo>";
    }
}
