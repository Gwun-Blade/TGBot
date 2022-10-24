package core;

import IO.DBControler;
import IO.XMLReader;
import Viev.*;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.InputStream;
import java.sql.SQLException;
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
        //соединение с бд
        try {
            DBControler.connect();
        } catch (ClassNotFoundException e) {
            log(Level.WARNING, "Не найден класс org.sqlite.JDBC");
            throw  new RuntimeException(e);
        } catch (SQLException e) {
            log(Level.WARNING, "Ошибка при соединении с базой данных");
            throw  new RuntimeException(e);
        }

        //теукщие проверки
        MyBlock block = new MyBlock(1, "text1", new MyPhoto(""), new MyButton("ended", "3 2"));
        MyBlock block1 = new MyBlock(2, "end");
        Scenario scenario = new Scenario(3, List.of(block, block1), SubTypes.admin);
        logger.log(Level.INFO, "Созадн тестовый сценарий");

        //проверка бд
        try {
            DBControler.CreateDB();
            System.out.println(DBControler.getMaxId(Tables.Scenaries));
            Scenario scenario1 = DBControler.getScenario(3);
            //System.out.println(scenario1);
        } catch (SQLException e) {
            log(Level.WARNING, "Ошибка пори запросе сценария по id\n"
                    + e.getMessage());
        }
    }

    public static void log(Level level, String text) {
        logger.log(level, text);
    }
}
