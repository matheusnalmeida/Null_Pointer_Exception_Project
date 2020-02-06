package app.database;

public class DatabaseFactory {

    public static DatabaseMySQL getDatabaseMySQL() {
        return new DatabaseMySQL();
    }
}
