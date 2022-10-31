package Viev;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class MyBlock {
   private int id;
   private MyText text;
   private List<MyPhoto> photos;
   private List<MyButton> buttons;

   public MyBlock(int id, MyText text, List<MyPhoto> photos, List<MyButton> buttons) {
      this.id = id;
      this.text = text;
      this.photos = photos;
      this.buttons = buttons;
   }


   public MyMessage getMyMessage() {
      ArrayList<InlineKeyboardButton> but = new ArrayList<>();
      for (MyButton button :
              buttons) {
         but.add(button.getInlineButton());
      }
      InlineKeyboardMarkup keyboardMarkup = InlineKeyboardMarkup.builder().keyboardRow(but).build();
      ArrayList<String> phot = new ArrayList<>();
      for (MyPhoto photo :
              photos) {
         phot.add(photo.getString());
      }
      return new MyMessage(text.getString(), phot, keyboardMarkup);
   }

   public int getId() {
      return id;
   }

   public MyText getText() {
      return text;
   }

   public List<MyPhoto> getPhotos() {
      return photos;
   }

   public List<MyButton> getButtons() {
      return buttons;
   }
   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("<block id=\"" + id + "\">\n");
      if (text != null) {
         builder.append(text.toString() + "\n");
      }
      for (MyPhoto photo:
           photos) {
         builder.append(photo.toString() + "\n");
      }
      for (MyButton button:
              buttons) {
         builder.append(button.toString() + "\n");
      }
      builder.append("</block>");
      return builder.toString();
   }
}
