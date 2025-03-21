import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;
import java.awt.Point;

class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/pathfinder";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public static void storePath(List<Point> path) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "INSERT INTO paths (path_data) VALUES (?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, path.toString());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
