package core;

import Viev.MyBlock;
import Viev.Scenario;
import Viev.SubTypes;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {
    private static Logger logger;
    static  {
        try {
            InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("log.config");
            LogManager.getLogManager().readConfiguration(inputStream);
            logger = Logger.getLogger(Main.class.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws TelegramApiException {
        //Соеденение с telegram api и инициализация бота
        TelegramBotsApi botApi = new TelegramBotsApi(DefaultBotSession.class);
        Bot bot = new Bot();
        botApi.registerBot(bot);
        log(Level.INFO, "Бот запущен");

        //теукщие проверки
        MyBlock block = new MyBlock();
        Scenario scenario = new Scenario(3, List.of(block), SubTypes.admin);
        logger.log(Level.INFO, "Созадн тестовый сценарий");
        System.out.println(scenario);
    }

    public static void log(Level level, String text) {
        logger.log(level, text);
    }
}
