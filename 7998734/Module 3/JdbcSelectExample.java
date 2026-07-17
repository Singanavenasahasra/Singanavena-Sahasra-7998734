import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcSelectExample {
    private static final String DB_URL = "jdbc:sqlite:school.db";

    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("SQLite JDBC Driver not found. Ensure the JAR is in your classpath.");
            return;
        }

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            
            String createTableSQL = "CREATE TABLE IF NOT EXISTS students ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "name TEXT NOT NULL, "
                    + "grade TEXT NOT NULL);";
            stmt.execute(createTableSQL);

            String checkEmptyQuery = "SELECT COUNT(*) FROM students";
            try (ResultSet rs = stmt.executeQuery(checkEmptyQuery)) {
                if (rs.next() && rs.getInt(1) == 0) {
                    stmt.execute("INSERT INTO students (name, grade) VALUES ('Alice Doe', 'A')");
                    stmt.execute("INSERT INTO students (name, grade) VALUES ('Bob Smith', 'B')");
                }
            }

            String selectSQL = "SELECT id, name, grade FROM students";
            try (ResultSet rs = stmt.executeQuery(selectSQL)) {
                System.out.println("ID\t| Name\t\t| Grade");
                System.out.println("-----------------------------------");
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String grade = rs.getString("grade");
                    System.out.println(id + "\t| " + name + "\t| " + grade);
                }
            }

        } catch (SQLException e) {
            System.out.println("Database error occurred: " + e.getMessage());
        }
    }
}