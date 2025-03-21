import java.awt.Point;
import java.util.*;

public class BFS {
    public static List<Point> findPath(int[][] grid, Point start, Point end) {
        int rows = grid.length, cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        Map<Point, Point> parentMap = new HashMap<>();
        Queue<Point> queue = new LinkedList<>();

        queue.add(start);
        visited[start.x][start.y] = true;

        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            if (current.equals(end)) {
                return constructPath(parentMap, end);
            }

            for (int[] dir : directions) {
                int newX = current.x + dir[0];
                int newY = current.y + dir[1];

                if (isValid(grid, newX, newY, visited)) {
                    Point next = new Point(newX, newY);
                    queue.add(next);
                    visited[newX][newY] = true;
                    parentMap.put(next, current);
                }
            }
        }
        return null; // No path found
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
