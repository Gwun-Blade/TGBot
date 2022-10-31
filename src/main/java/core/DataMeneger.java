package core;

import IO.DBControler;
import Viev.Scenario;
import Viev.MyUser;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

public class DataMeneger {
    private static HashMap<Integer, Scenario> scenarioMap = new HashMap<>();
    private static HashMap<Long, MyUser> userMap = new HashMap<>();

    static {
        try {
            ArrayList<MyUser> users = DBControler.getAllUsers();
            for (MyUser user:
                 users) {
                userMap.put(user.getId(), user);
            }
        } catch (SQLException e) {
            Main.log(Level.WARNING, "Ошибка при получении списка всех пользователей " + e.getMessage());
        }
    }
    public static Scenario getScenario(int id) throws SQLException {
        if (scenarioMap.containsKey(id)) {
            return scenarioMap.get(id);
        }
        Scenario scenario = DBControler.getScenario(id);
        scenarioMap.put(id, scenario);
        return scenario;
    }
    public static void writeScenario(Scenario scenario) {
        if (!scenarioMap.containsKey(scenario.getId())) {
            try {
                scenarioMap.put(scenario.getId(), scenario);
                DBControler.writeScenario(scenario);
            } catch (SQLException e) {
                Main.log(Level.WARNING, "Ошибка при добавлении сценария " + e.getMessage());
            }
        }
    }
    public static MyUser getUser(Long id) throws SQLException {
        if (userMap.containsKey(id)) {
            return userMap.get(id);
        }
        MyUser myUser = DBControler.getUser(id);
        userMap.put(id, myUser);
        return myUser;
    }
    public static void writeUser(MyUser myUser) {
        if (!userMap.containsKey(myUser.getId())) {
            userMap.put(myUser.getId(), myUser);
            try {
                DBControler.writeUser(myUser);
                Main.log(Level.INFO, "Добавлен новый пользователь");
            } catch (SQLException e) {
                Main.log(Level.WARNING, "Ошибка при добавлении пользователя " + e.getMessage());
            }
        }
    }
    public static HashMap<Integer, Scenario> getScenarioMap() {
        return scenarioMap;
    }

    public static HashMap<Long, MyUser> getUserMap() {
        return userMap;
    }
}
