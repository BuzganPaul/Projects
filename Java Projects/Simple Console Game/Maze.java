
import java.util.HashSet;
import java.util.Set;
class Maze {
private static Maze mazeInstance = null;
private Set<Room> rooms = new HashSet<Room>();
private Maze() {
}
static Maze instance() {
if (mazeInstance == null)
mazeInstance = new Maze();
return mazeInstance;
}
void addRoom(Room room) {
rooms.add(room);
}
}
