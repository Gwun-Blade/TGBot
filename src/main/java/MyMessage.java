import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class MyMessage {
    private Long id;
    private String text;
    private String photo;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public boolean hasPhoto() {
        return photo != null;
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

    public MyMessage (String text, String photo, InlineKeyboardMarkup keyboard) {
        this.text = text;
        this.photo = photo;
        this.keyboard = keyboard;
    }

    public MyMessage (String text, InlineKeyboardMarkup keyboard) {
        this(text, null, keyboard);
    }
    public MyMessage (String text, String photo) {
        this(text, photo, null);
    }
    public MyMessage (String text) {
        this(text, null, null);
    }


}
