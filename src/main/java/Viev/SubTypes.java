package Viev;

import core.Main;

import java.util.logging.Level;

public enum SubTypes {
    none,
    base,
    curse,
    admin;
    public static SubTypes getType(String str) {
        str = str.toLowerCase();
        switch (str) {
            case "none": return none;
            case "base": return base;
            case "curse": return curse;
            case "admin": return admin;
        }
        Main.log(Level.WARNING, "Неверный тип подписки" + str);
        return none;
    }
}
