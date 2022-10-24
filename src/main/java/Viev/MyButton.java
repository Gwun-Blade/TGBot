package Viev;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public class MyButton {
    private String text;
    private String link;

    public MyButton(String text, String link) {
        this.text = text;
        this.link = link;
    }
    @Override
    public String toString() {
        return "<button id=\"" + link + "\">" + text + "</button>";
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLink() {
        return link;
    }

    public InlineKeyboardButton getInlineButton() {
        return InlineKeyboardButton.builder().text(text).callbackData("" + link).build();
    }

    public void setData(String link) {
        this.link = link;
    }

}
