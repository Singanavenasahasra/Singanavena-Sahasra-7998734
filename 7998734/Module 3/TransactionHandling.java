import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionHandling {
    private static final String DB_URL = "jdbc:sqlite:bank.db";

    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("SQLite JDBC Driver not found.");
            return;
        }

        initializeDatabase();

        System.out.println("Balances before transfer:");
        displayBalances();

        transferMoney(1, 2, 200.00);

        System.out.println("Balances after transfer:");
        displayBalances();
    }

    public static void transferMoney(int fromAccountId, int toAccountId, double amount) {
        String debitSQL = "UPDATE accounts SET balance = balance - ? WHERE id = ? AND balance >= ?";
        String creditSQL = "UPDATE accounts SET balance = balance + ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            conn.setAutoCommit(false);

            try (PreparedStatement debitStmt = conn.prepareStatement(debitSQL);
                 PreparedStatement creditStmt = conn.prepareStatement(creditSQL)) {

                debitStmt.setDouble(1, amount);
                debitStmt.setInt(2, fromAccountId);
                debitStmt.setDouble(3, amount);
                int debitRows = debitStmt.executeUpdate();

                if (debitRows == 0) {
                    throw new SQLException("Debit failed. Account ID " + fromAccountId + " may have insufficient funds.");
                }

                creditStmt.setDouble(1, amount);
                creditStmt.setInt(2, toAccountId);
                int creditRows = creditStmt.executeUpdate();

                if (creditRows == 0) {
                    throw new SQLException("Credit failed. Account ID " + toAccountId + " does not exist.");
                }

                conn.commit();
                System.out.println("Transaction committed successfully! Transfer completed.");

            } catch (SQLException e) {
                System.out.println("Transaction encountered an error: " + e.getMessage());
                conn.rollback();
                System.out.println("Transaction successfully rolled back. No changes were saved.");
            }

        } catch (SQLException e) {
            System.out.println("Database connection failure: " + e.getMessage());
        }
    }

    private static void initializeDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            
            stmt.execute("DROP TABLE IF EXISTS accounts");
            
            String createTableSQL = "CREATE TABLE accounts ("
                    + "id INTEGER PRIMARY KEY, "
                    + "holder_name TEXT NOT NULL, "
                    + "balance REAL NOT NULL);";
            stmt.execute(createTableSQL);

            stmt.execute("INSERT INTO accounts (id, holder_name, balance) VALUES (1, 'Alice', 1000.00);");
            stmt.execute("INSERT INTO accounts (id, holder_name, balance) VALUES (2, 'Bob', 500.00);");

        } catch (SQLException e) {
            System.out.println("Database initialization error: " + e.getMessage());
        }
    }

    private static void displayBalances() {
        String sql = "SELECT id, holder_name, balance FROM accounts";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("Account " + rs.getInt("id") + " (" + rs.getString("holder_name") + "): $" + rs.getDouble("balance"));
            }
            System.out.println();
        } catch (SQLException e) {
            System.out.println("Error pulling records: " + e.getMessage());
        }
    }
}