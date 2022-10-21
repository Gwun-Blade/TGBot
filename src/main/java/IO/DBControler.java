package IO;

import Viev.MyBlock;
import Viev.Scenario;
import Viev.SubTypes;
import Viev.Tables;
import core.Main;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;

public class DBControler {
    public static Connection connection;
    public static Statement statement;
    public static ResultSet resultSet;

    public static void connect() throws ClassNotFoundException, SQLException {
        connection = null;
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:DataBase.s3db");
        statement = connection.createStatement();
        Main.log(Level.INFO, "Соединение с базой данных выполнено");
    }

    public static void writeScenario(Scenario scenario) throws SQLException {
        statement.execute(scenario.getSQLToWriteIt());
    }

    public static int getMaxId(Tables table) throws SQLException {
        resultSet = statement.executeQuery("SELECT max(id) FROM " + table.toString());
        return resultSet.getInt("id");
    }

    public static Scenario getScenario(int id) throws SQLException {
        resultSet = statement.executeQuery("SELECT id, data, type FROM Scenaries WHERE id = " + id);
        String data = resultSet.getString("data");
        String type = resultSet.getString("type");
        return new Scenario(id, List.of(new MyBlock()), SubTypes.getType(type)); //заглушка обязательно сделать разварачивание сценария
    }
}
