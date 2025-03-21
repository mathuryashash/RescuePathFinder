import java.awt.Point;
import java.util.*;

public class DFS {
    public static List<Point> findPath(int[][] grid, Point start, Point end) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Map<Point, Point> parentMap = new HashMap<>();
        List<Point> path = new ArrayList<>();

        if (dfsHelper(grid, start, end, visited, parentMap)) {
            return constructPath(parentMap, end);
        }
        return null; // No path found
    }

    private static boolean dfsHelper(int[][] grid, Point current, Point end, boolean[][] visited, Map<Point, Point> parentMap) {
        if (current.equals(end)) return true;

        visited[current.x][current.y] = true;
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        for (int[] dir : directions) {
            int newX = current.x + dir[0];
            int newY = current.y + dir[1];

            if (isValid(grid, newX, newY, visited)) {
                Point next = new Point(newX, newY);
                parentMap.put(next, current);
                if (dfsHelper(grid, next, end, visited, parentMap)) return true;
            }
        }
        return false;
    }

    private static boolean isValid(int[][] grid, int x, int y, boolean[][] visited) {
        return x >= 0 && y >= 0 && x < grid.length && y < grid[0].length &&
                grid[x][y] == 0 && !visited[x][y];
    }

    private static List<Point> constructPath(Map<Point, Point> parentMap, Point end) {
        List<Point> path = new ArrayList<>();
        for (Point at = end; at != null; at = parentMap.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }
}
