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

   public MyBlock(int id, String text, MyPhoto photo, MyButton button) {
      this(id, new MyText(text), List.of(photo), List.of(button));
   }

   public MyBlock(int id, String text, List<MyPhoto> photo, List<MyButton> button) {
      this(id, new MyText(text), photo, button);
   }
   public MyBlock(MyText text, List<MyPhoto> photos, List<MyButton> buttons) {
      this.text = text;
      this.photos = photos;
      this.buttons = buttons;
   }
   public MyBlock(int link, MyText mt) {
      this(link, mt, List.of(), List.of());
   }

   public MyBlock(int link, String mt) {
      this(link, new MyText(mt), List.of(), List.of());
   }
   public MyBlock(int link, List<MyPhoto> photos) {
      this(link, "", photos, List.of());
   }
   public MyBlock(MyText text, List<MyPhoto> photos) {
      this(text, photos, List.of());

   }

   public MyBlock(List<MyPhoto> photos, List<MyButton> buttons) {
      this(new MyText(""), photos, buttons);
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
