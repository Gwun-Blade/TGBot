package core;

import IO.DBControler;
import IO.XMLReader;
import Viev.*;
import org.checkerframework.checker.units.qual.C;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.sqlite.core.DB;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.xml.transform.Source;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.*;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class Main {
    protected static Bot bot = new Bot();
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
        //Запуск планировщика задачь
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            JobDetail job = newJob(SendMessageJob.class)
                    .withIdentity("SendingMessage")
                    .build();

            SimpleTrigger trigger = newTrigger().withIdentity("it is time")
                    .startNow().withSchedule(simpleSchedule().withIntervalInSeconds(5).repeatForever()).build();

            scheduler.scheduleJob(job, trigger);
            scheduler.start();
        } catch (SchedulerException e) {
            Main.log(Level.WARNING, "Ошибка планировщика задачь " + e.getMessage());
        }
        //теукщие проверки
//        try {
//            DBControler.deleteScenario(0);
//            DBControler.deleteScenario(2);
//            DBControler.deleteScenario(1);
//            DBControler.deleteScenario(3);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        MyBlock block = new MyBlock(1, new MyText("text1"), List.of(), List.of(new MyButton("ended", "3 2")));
//        MyBlock block1 = new MyBlock(2, new MyText("end"),  List.of(),  List.of());
//        Scenario scenario = new Scenario(3, List.of(block, block1), SubTypes.admin);
//        logger.log(Level.INFO, "Созадн тестовый сценарий");
//
//        //проверка бд
//        try {
//            DBControler.CreateDB();
//            DBControler.writeScenario(scenario);
//            System.out.println(DBControler.getMaxId(Tables.Scenaries));
//            Scenario scenario1 = DBControler.getScenario(3);
//            //System.out.println(scenario1);
//        } catch (SQLException e) {
//            log(Level.WARNING, "Ошибка пори запросе сценария по id\n"
//                    + e.getMessage());
//        }
    }

    public static void log(Level level, String text) {
        logger.log(level, text);
    }
    public static Calendar toCalendar(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public static class SendMessageJob implements Job {
        @Override
        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            Main.bot.sendMessage(1144830564l, new MyMessage("Привет"));
            System.out.println("Do something");
        }
    }
}
