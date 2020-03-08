
class MazeFactory {
Maze makeMaze() {
return Maze.instance();
}
Wall makeWall() {
return new Wall();
}
Room makeRoom() {
return new Room();
}
Door makeDoor(Room room1, Room room2) {
return new Door(room1, room2);
}
}