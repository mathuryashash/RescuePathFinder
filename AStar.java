import java.awt.Point;
import java.util.*;

class AStar {
    static class Node implements Comparable<Node> {
        Point point;
        int gCost, hCost;

        Node(Point point, int gCost, int hCost) {
            this.point = point;
            this.gCost = gCost;
            this.hCost = hCost;
        }

        int fCost() {
            return gCost + hCost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.fCost(), o.fCost());
        }
    }

    public static List<Point> findPath(int[][] grid, Point start, Point end) {
        PriorityQueue<Node> openSet = new PriorityQueue<>();
        Map<Point, Point> cameFrom = new HashMap<>();
        Map<Point, Integer> gCost = new HashMap<>();
        Set<Point> closedSet = new HashSet<>();

        openSet.add(new Node(start, 0, heuristic(start, end)));
        gCost.put(start, 0);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (current.point.equals(end)) return reconstructPath(cameFrom, end);

            closedSet.add(current.point);

            for (Point neighbor : getNeighbors(current.point, grid)) {
                if (closedSet.contains(neighbor)) continue;

                int tentativeG = gCost.get(current.point) + 1;
                if (!gCost.containsKey(neighbor) || tentativeG < gCost.get(neighbor)) {
                    cameFrom.put(neighbor, current.point);
                    gCost.put(neighbor, tentativeG);
                    openSet.add(new Node(neighbor, tentativeG, heuristic(neighbor, end)));
                }
            }
        }
        return null;
    }

    private static List<Point> reconstructPath(Map<Point, Point> cameFrom, Point end) {
        List<Point> path = new ArrayList<>();
        for (Point at = end; at != null; at = cameFrom.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }

    private static int heuristic(Point a, Point b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
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
