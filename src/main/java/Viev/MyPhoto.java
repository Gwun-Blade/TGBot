package Viev;

public class MyPhoto {
    String photoId;

    public MyPhoto(String id) {
        photoId = id;
    }

    @Override
    public String toString() {
        return "<photo>" + photoId + "</photo>";
    }
}
