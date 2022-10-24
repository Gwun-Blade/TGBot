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
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public static void connect() throws ClassNotFoundException, SQLException {
        connection = null;
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:src\\main\\resources\\DataBase.s3db");
        statement = connection.createStatement();
        Main.log(Level.INFO, "Соединение с базой данных выполнено");
    }

    public static void CreateDB() throws SQLException {
        statement.execute("CREATE TABLE if not exists [Scenaries] (" +
                "[id] INTEGER  PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "[data] TEXT  NULL," +
                "[type] VARCHAR(50) DEFAULT '''ALL''' NULL" +
                ")");
        System.out.println("Таблица создана или уже существует.");
    }


    public static void writeScenario(Scenario scenario) throws SQLException {
        statement.execute(scenario.getSQLToWriteIt());
    }

    public static int getMaxId(Tables table) throws SQLException {
        resultSet = statement.executeQuery("SELECT max(id) as id FROM " + table.toString());
        return resultSet.getInt("id");
    }


    public static void getAll(Tables table) throws SQLException {
        resultSet = statement.executeQuery("SELECT * FROM " + table.toString());

        while (resultSet.next()) {
            String data1 = resultSet.getString("data");
            String type1 = resultSet.getString("type");
            System.out.println(data1);
            System.out.println(type1);
            System.out.println();
        }
    }
    public static Scenario getScenario(int id) throws SQLException {
        resultSet = statement.executeQuery("SELECT id, data, type FROM scenaries WHERE id = " + id);
        String data = resultSet.getString("data");
        String type = resultSet.getString("type");

        return new Scenario(id, XMLReader.readXML(data), SubTypes.getType(type));
    }
}
