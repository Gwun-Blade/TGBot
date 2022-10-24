package Viev;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class MyMessage {
    private Long id;
    private String text;
    private List<String> photos;
    private InlineKeyboardMarkup keyboard;

    public Long getId() {
        return id;
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean hasText() {
        return this.text != null && !this.text.isEmpty();
    }

    public List<String> getPhoto() {
        return photos;
    }

    public void setPhoto(List<String> photos) {
        this.photos = photos;
    }

    public boolean hasPhoto() {
        return photos != null && !photos.isEmpty();
    }

    public InlineKeyboardMarkup getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(InlineKeyboardMarkup keyboard) {
        this.keyboard = keyboard;
    }

    public void setKeyboard(List<InlineKeyboardButton> buttonList) {
        keyboard = InlineKeyboardMarkup.builder().keyboardRow(buttonList).build();
    }

    public boolean hasKeyboard() {
        return keyboard != null && !keyboard.getKeyboard().isEmpty();
    }

    public MyMessage (String text, List<String> photos, InlineKeyboardMarkup keyboard) {
        this.text = text;
        this.photos = photos;
        this.keyboard = keyboard;
    }

    public MyMessage (String text, InlineKeyboardMarkup keyboard) {
        this(text, null, keyboard);
    }
    public MyMessage (String text, List<String> photos) {
        this(text, photos, null);
    }
    public MyMessage (String text) {
        this(text, null, null);
    }


}
