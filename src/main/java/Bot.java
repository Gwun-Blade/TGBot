import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {
    String startMessage = "Этот бот будет помогать людям чувствовать себя лучше, но пока он только в разработке";

    public Bot() {

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
        String text = update.getMessage().getText();
        Long userId = update.getMessage().getFrom().getId();

        System.out.println(userId + ": " + text);

        if (text.equals("/start")) {
            sendText(userId, startMessage);
        } else {
            sendText(userId, text);
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
