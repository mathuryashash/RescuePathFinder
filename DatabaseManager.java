import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.Point;

class DatabaseManager {

    private List<Point> retrievePathFromDatabase() {
        String url = "jdbc:mysql://localhost:3306/pathfinder";
        String user = "root";
        String password = "mathur";

        String selectSQL = "SELECT x, y FROM paths ORDER BY id";

        List<Point> path = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL)) {

            while (rs.next()) {
                int x = rs.getInt("x");
                int y = rs.getInt("y");
                path.add(new Point(x, y)); // Add to the path list
            }

            System.out.println("Retrieved path from database: " + path);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return path;
    }
}
