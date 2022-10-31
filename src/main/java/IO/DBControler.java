package IO;

import Viev.*;
import core.Main;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
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
        Main.log(Level.INFO, "Таблица создана или уже существует.");
    }


    public static void writeScenario(Scenario scenario) throws SQLException {
        statement.execute(scenario.getSQLToWriteIt());
    }

    public static void writeUser(MyUser myUser) throws SQLException {
        statement.execute("INSERT INTO Users values(" +
                "'" + myUser.getId() + "', " +
                "'" + myUser.getFirstName() + "', " +
                "'" + myUser.getLastName() + "', " +
                "'" + myUser.getLanguageCode() + "', " +
                "'" + myUser.getSubType() + "', " +
                "'" + myUser.getStartDate() + "', " +
                "'" + myUser.getTimeShift() + "', " +
                "'" + myUser.isAdmin() + "')");
    }

    public static MyUser getUser(Long id) throws SQLException {
        resultSet = statement.executeQuery("SELECT * FROM Users " +
                "Where id = " + id);
        MyUser myUser = new MyUser(resultSet.getLong("id"),
                resultSet.getString("firstName"),
                resultSet.getString("lastName"),
                resultSet.getString("languageCode"),
                SubTypes.getType(resultSet.getString("subscribeType")),
                resultSet.getString("startDate"),
                resultSet.getInt("timeShift"),
                resultSet.getBoolean("isAdmin"));
        return myUser;
    }

    public static int getMaxId(Tables table) throws SQLException {
        resultSet = statement.executeQuery("SELECT max(id) as id FROM " + table.toString());
        return resultSet.getInt("id");
    }

    public static ArrayList<MyUser> getAllUsers() throws SQLException {
        resultSet = statement.executeQuery("SELECT * FROM Users");
        ArrayList<MyUser> users = new ArrayList<>();
        while (resultSet.next()) {
            MyUser myUser = new MyUser(resultSet.getLong("id"),
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getString("languageCode"),
                    SubTypes.getType(resultSet.getString("subscribeType")),
                    resultSet.getString("startDate"),
                    resultSet.getInt("timeShift"),
                    resultSet.getBoolean("isAdmin"));
            users.add(myUser);
        }
        return users;
    }

    public static void printAll(Tables table) throws SQLException {
        resultSet = statement.executeQuery("SELECT * FROM " + table.toString());
        if(table == Tables.Scenaries) {
            while (resultSet.next()) {
                String data1 = resultSet.getString("data");
                String type1 = resultSet.getString("type");
                System.out.println(data1);
                System.out.println(type1);
                System.out.println();
            }
        } else if (table == Tables.Users) {

        }
    }

    public static void deleteScenario(int id) throws SQLException {
        statement.execute("DELETE FROM scenaries " +
                "WHERE id = " + id);
    }
    public static Scenario getScenario(int id) throws SQLException {
        resultSet = statement.executeQuery("SELECT id, data, type FROM scenaries WHERE id = " + id);
        String data = resultSet.getString("data");
        String type = resultSet.getString("type");

        if (data == null || data.isEmpty()) throw new SQLException();
        return new Scenario(id, XMLReader.readXML(data), SubTypes.getType(type));
    }


}
