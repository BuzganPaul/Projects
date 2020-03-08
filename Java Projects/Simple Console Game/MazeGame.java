
public class MazeGame {
private Maze createMaze() {
MazeFactory mazeFactory = new MazeFactory();
Maze maze = mazeFactory.makeMaze();
Room room1 = mazeFactory.makeRoom();
Room room2 = mazeFactory.makeRoom();
Door door = mazeFactory.makeDoor(room1, room2);
System.out.println("The door is " + door.isOpen());
System.out.println(door.otherSideFrom(room1).toString());
maze.addRoom(room1);
maze.addRoom(room2);
room1.setSide(Direction.North, mazeFactory.makeWall());
room1.setSide(Direction.East, door);
room1.setSide(Direction.South, mazeFactory.makeWall());
room1.setSide(Direction.West, mazeFactory.makeWall());
room2.setSide(Direction.North, mazeFactory.makeWall());
room2.setSide(Direction.East, mazeFactory.makeWall());
room2.setSide(Direction.South, mazeFactory.makeWall());
room2.setSide(Direction.West, door);
return maze;
}
public static void main(String[] args) {
MazeGame game = new MazeGame();
game.createMaze();
}
}
