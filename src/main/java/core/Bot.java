package core;

import Viev.*;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;

public class Bot extends TelegramLongPollingBot {
    MyMessage startMessage;
    ArrayList<MyMessage> testScript = new ArrayList<>(7);
    public Bot() {
        //initialize map

        //test scenario
        InlineKeyboardButton startButton = InlineKeyboardButton.builder().text("Приступим")
                .callbackData("START_MAIN").build();
        InlineKeyboardMarkup startKeyboard = InlineKeyboardMarkup.builder()
                .keyboardRow(List.of(startButton)).build();
        startMessage = new MyMessage("Этот бот будет помогать людям чувствовать себя лучше, но пока он только в разработке",
                List.of("AgACAgIAAxkBAAM9Y0v-jJq4k63_faUuYIuuHFhi8nQAAtXBMRvcuGFKBKbfLkftw3cBAAMCAANzAAMqBA"),
                startKeyboard);

        InlineKeyboardButton button1_1 = InlineKeyboardButton.builder().text("Вариант 1")
                .callbackData("2").build();
        InlineKeyboardButton button1_2 = InlineKeyboardButton.builder().text("Вариант 2")
                .callbackData("3").build();
        InlineKeyboardButton button1_3 = InlineKeyboardButton.builder().text("Вариант 3")
                .callbackData("4").build();
        InlineKeyboardMarkup keyboard1 = InlineKeyboardMarkup.builder()
                .keyboardRow(List.of(button1_1, button1_2, button1_3)).build();
        MyMessage message1 = new MyMessage("Здесь будет какой-то текст и фоточка", keyboard1);

        InlineKeyboardButton button2_1 = InlineKeyboardButton.builder().text("Вернутся")
                .callbackData("1").build();
        InlineKeyboardMarkup keyboard2 = InlineKeyboardMarkup.builder()
                .keyboardRow(List.of(button2_1)).build();
        MyMessage message2 = new MyMessage("не туда)", keyboard2);

        MyMessage message3 = new MyMessage("Здесь пока все", keyboard2);

        InlineKeyboardButton button4_1 = InlineKeyboardButton.builder().text("Вариант 1")
                .callbackData("5").build();
        InlineKeyboardButton button4_2 = InlineKeyboardButton.builder().text("Вариант 2")
                .callbackData("6").build();
        InlineKeyboardMarkup keyboard3 = InlineKeyboardMarkup.builder()
                .keyboardRow(List.of(button4_1, button4_2)).build();
        MyMessage message4 = new MyMessage("Здесь еще выбор из 2 вариантов", keyboard3);

        MyMessage message5 = new MyMessage("А здесь конец");

        InlineKeyboardButton button6_1 = InlineKeyboardButton.builder().text("Да!")
                .callbackData("7").build();
        InlineKeyboardMarkup keyboard4 = InlineKeyboardMarkup.builder()
                .keyboardRow(List.of(button6_1)).build();
        MyMessage message6 = new MyMessage("Понравилось?", keyboard4);

        MyMessage message7 = new MyMessage("До новых встреч!");

        testScript.add(message1);
        testScript.add(message2);
        testScript.add(message3);
        testScript.add(message4);
        testScript.add(message5);
        testScript.add(message6);
        testScript.add(message7);
    }

    @Override
    public String getBotUsername() {
        return "buddybodybot";
    }

    @Override
    public String getBotToken() {
        return "5744157704:AAEbPjPs4vcPzbcgMfUd1koJwf6QgrP5lXY";
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update);
        User user = new User();
        if (update.hasMessage()) {
            Main.log(Level.INFO, "Получено сообщение от: " + update.getMessage().getFrom() +
                    " " + update.getMessage().getFrom().getFirstName());
            user = update.getMessage().getFrom();
            if (update.getMessage().hasText()) {
                Main.log(Level.INFO, "Сообщение содержит текст: " + update.getMessage().getText());
                if (update.getMessage().getText().equalsIgnoreCase("/start")) {
                    replyToButton(user.getId(), "3 1");
                } else {
                    sendMessage(user.getId(), new MyMessage("Заглушка от неизвестного текста"));
                }
            }
            if (update.getMessage().hasPhoto()) {
                Main.log(Level.INFO, "Сообщение содержит фото + " + update.getMessage().getPhoto());
                sendMessage(user.getId(), new MyMessage("Заглушка от фото"));
            }
        } else if (update.hasCallbackQuery()) {
            Main.log(Level.INFO, "Кнопка нажата, нажавший: " + update.getCallbackQuery().getFrom().getId() +
                    " " + update.getCallbackQuery().getFrom().getFirstName());
            Main.log(Level.INFO, "Данные кнопки: + " + update.getCallbackQuery().getId() + " "
                    +  update.getCallbackQuery().getData());
            user = update.getCallbackQuery().getFrom();
            if (update.getCallbackQuery().getData().equalsIgnoreCase("START_MAIN")) {
                sendMessage(user.getId(), testScript.get(0));
            } else {
                replyToButton(user.getId(), update.getCallbackQuery().getData());
            }
            AnswerCallbackQuery close = AnswerCallbackQuery.builder()
                    .callbackQueryId(update.getCallbackQuery().getId()).build();
            try {
                execute(close);
            } catch (Exception e) {
                Main.log(Level.WARNING, e.getMessage());
                e.printStackTrace();
            }
        }
        DataMeneger.writeUser(new MyUser(user.getId(), user.getFirstName(), user.getLastName(), user.getLanguageCode(),
                SubTypes.none, new Date().toString(), 3, false));
    }

    private void replyToButton(Long userId, String buttonData) {
        String[] split = buttonData.split(" ");
        int scenarioId = Integer.parseInt(split[0]);
        int blockId = Integer.parseInt(split[1]);

        try {
            Scenario scenario = DataMeneger.getScenario(scenarioId);
            MyBlock block = scenario.getBlockWithId(blockId);

            sendMessage(userId, block.getMyMessage());
        } catch (SQLException e) {
            Main.log(Level.WARNING, "Вызов сценария с несуществующим Id " + e.getMessage());
        } catch (ScenarioException e) {
            Main.log(Level.WARNING, "Запрос отсутствующего в сценарии блока " + e.getMessage());
        }
    }

    public void sendMessage(Long who, MyMessage message) {
        if (message.hasPhoto()) {
            List<String> photos = message.getPhoto();
            for (String photo :
                    photos) {
                SendPhoto sendPhoto = SendPhoto.builder().chatId(who.toString())
                        .photo(new InputFile(photo)).caption(message.getText())
                        .replyMarkup(message.getKeyboard()).build();
                try {
                    execute(sendPhoto);
                } catch (TelegramApiException ex) {
                    Main.log(Level.WARNING, ex.getMessage());
                    ex.printStackTrace();
                    throw new RuntimeException(ex);
                }
            }
        }
        else if (message.hasText()) {
            SendMessage sendMessage = SendMessage.builder()
                    .chatId(who.toString()).text(message.getText())
                    .replyMarkup(message.getKeyboard()).build();
            try {
                execute(sendMessage);
            } catch (TelegramApiException ex) {
                Main.log(Level.WARNING, ex.getMessage());
                ex.printStackTrace();
                throw new RuntimeException(ex);
            }
        }
    }


    public void sendPhoto(Long who) {
        var plusOne = InlineKeyboardButton.builder()
                .text("plusOne").callbackData("plusOne").build();
        InlineKeyboardMarkup keyboard1 = InlineKeyboardMarkup.builder().keyboardRow(List.of(plusOne)).build();
        SendPhoto sp = SendPhoto.builder().chatId(who.toString())
                .photo(new InputFile("AgACAgIAAxkBAAM9Y0v-jJq4k63_faUuYIuuHFhi8nQAAtXBMRvcuGFKBKbfLkftw3cBAAMCAANzAAMqBA"))
                .caption(null).replyMarkup(null).build();
        try {
            execute(sp);
        } catch (TelegramApiException ex) {
            throw new RuntimeException(ex);
        }
    }
    public void sendText(Long who, String what) {
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString())
                .text(what).build();

        try {
            execute(sm);
        } catch (TelegramApiException ex) {
            throw new RuntimeException(ex);
        }
    }
}
