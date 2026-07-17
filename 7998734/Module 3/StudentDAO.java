import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentDAO {
    private static final String DB_URL = "jdbc:sqlite:school.db";

    public void insertStudent(String name, String grade) {
        String sql = "INSERT INTO students(name, grade) VALUES(?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, grade);
            pstmt.executeUpdate();
            System.out.println("Successfully inserted: " + name);
        } catch (SQLException e) {
            System.out.println("Insertion error: " + e.getMessage());
        }
    }

    public void updateStudentGrade(int id, String newGrade) {
        String sql = "UPDATE students SET grade = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newGrade);
            pstmt.setInt(2, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Successfully updated Student ID " + id + " to Grade " + newGrade);
            } else {
                System.out.println("No student found with ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println("Update error: " + e.getMessage());
        }
    }

    public void displayAllStudents() {
        String sql = "SELECT id, name, grade FROM students";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n--- Current Student Records ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + 
                                   " | Name: " + rs.getString("name") + 
                                   " | Grade: " + rs.getString("grade"));
            }
            System.out.println("--------------------------------\n");
        } catch (SQLException e) {
            System.out.println("Retrieval error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("SQLite JDBC Driver not found.");
            return;
        }

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS students ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "name TEXT NOT NULL, "
                    + "grade TEXT NOT NULL);";
            stmt.execute(createTableSQL);
        } catch (SQLException e) {
            System.out.println("Database initialization error: " + e.getMessage());
        }

        StudentDAO dao = new StudentDAO();

        dao.insertStudent("Charlie Brown", "C");
        dao.insertStudent("Diana Prince", "A");
        
        dao.displayAllStudents();

        dao.updateStudentGrade(1, "B");

        dao.displayAllStudents();
    }
}