import java.awt.Point;
import java.util.*;

class Dijkstra {
    public static List<Point> findPath(int[][] grid, Point start, Point end) {
        Map<Point, Integer> dist = new HashMap<>(); // Declare dist before use
        PriorityQueue<Point> pq = new PriorityQueue<>(Comparator.comparingInt(p -> dist.getOrDefault(p, Integer.MAX_VALUE)));
        Map<Point, Point> cameFrom = new HashMap<>();
        Set<Point> visited = new HashSet<>();

        dist.put(start, 0);
        pq.add(start);

        while (!pq.isEmpty()) {
            Point current = pq.poll();
            if (current.equals(end)) return reconstructPath(cameFrom, end);

            visited.add(current);

            for (Point neighbor : getNeighbors(current, grid)) {
                if (visited.contains(neighbor)) continue;

                int newDist = dist.get(current) + 1; // Using dist safely now
                if (newDist < dist.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                    dist.put(neighbor, newDist);
                    pq.add(neighbor);
                    cameFrom.put(neighbor, current);
                }
            }
        }
        return null; // No path found
    }

    private static List<Point> reconstructPath(Map<Point, Point> cameFrom, Point end) {
        List<Point> path = new ArrayList<>();
        for (Point at = end; at != null; at = cameFrom.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }

    private static List<Point> getNeighbors(Point p, int[][] grid) {
        List<Point> neighbors = new ArrayList<>();
        int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};

        for (int i = 0; i < 4; i++) {
            int nx = p.x + dx[i], ny = p.y + dy[i];
            if (nx >= 0 && ny >= 0 && nx < grid.length && ny < grid[0].length && grid[nx][ny] == 0) {
                neighbors.add(new Point(nx, ny));
            }
        }
        return neighbors;
    }
}
