package Viev;

import java.util.ArrayList;
import java.util.List;

public class MyBlock {
   String title;
   MyText text;
   List<MyPhoto> photos;
   List<MyButton> buttons;

   public MyBlock() {
      title = "test";
      text = new MyText("Here will be something");
      photos = new ArrayList<>();
      photos.add(new MyPhoto("photo 1"));
      buttons = new ArrayList<>();
      buttons.add(new MyButton("button 1", "block 2"));
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("<block title=\"" + title + "\">\n");
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
