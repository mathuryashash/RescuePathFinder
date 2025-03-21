# RescuePathFinder

# Pathfinding Visualizer

## Overview
This is a **Java-based Pathfinding Visualizer** that allows users to visualize different pathfinding algorithms like **A\* (A-Star), Dijkstra, BFS, and DFS**. The application provides an interactive grid where users can set start and end points, generate walls, and visualize the shortest path.

## Features
- **Select Start and End Points**: Click to set start (blue) and end (red) positions.
- **Wall Generation**: Randomly generate obstacles on the grid.
- **Safe Zones**: Right-click to mark a cell as a safe zone.
- **Pathfinding Algorithms**:
  - A* (A-Star)
  - Dijkstra’s Algorithm
  - Breadth-First Search (BFS)
  - Depth-First Search (DFS)
- **Database Integration**: Stores the computed path in an SQL database.
- **Animated Path Visualization**.

## Installation & Setup
### Prerequisites
- **Java (JDK 17+)**
- **IntelliJ IDEA** or any Java IDE
- **MySQL Database (Optional for storing paths)**

### Clone the Repository
```bash
git clone https://github.com/yourusername/Pathfinding-Visualizer.git
cd Pathfinding-Visualizer
```

### Configure Database (Optional)
1. Create a MySQL database named `pathfinder`.
2. Create a table `paths` with the following schema:
   ```sql
   CREATE TABLE paths (
       id INT AUTO_INCREMENT PRIMARY KEY,
       x INT NOT NULL,
       y INT NOT NULL
   );
   ```
3. Update database credentials in `PathfindingVisualizer.java`:
   ```java
   String url = "jdbc:mysql://localhost:3306/pathfinder";
   String user = "root";
   String password = "yourpassword";
   ```

### Run the Application
1. Open the project in **IntelliJ IDEA**.
2. Run the `PathfindingVisualizer` class.

## Usage
1. **Click to set start and end points**.
2. **Right-click to mark safe zones**.
3. **Click "Generate Walls" to create random obstacles**.
4. **Select an algorithm from the dropdown menu**.
5. **Click "Find Path" to visualize the shortest path**.

## Contributing
Feel free to fork this repository and contribute! Submit a pull request with improvements or bug fixes.

