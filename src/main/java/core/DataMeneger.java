package core;

import IO.DBControler;
import Viev.Scenario;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DataMeneger {
    private static HashMap<Integer, Scenario> scenarioMap = new HashMap<>();

    public static Scenario getScenario(int id) throws SQLException {
        if (scenarioMap.containsKey(id)) {
            return scenarioMap.get(id);
        }
        Scenario scenario = DBControler.getScenario(id);
        scenarioMap.put(id, scenario);
        return scenario;
    }
}
