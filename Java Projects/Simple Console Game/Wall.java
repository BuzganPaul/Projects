
public class Wall extends MapSite {
private int wallNumber;
private static int wallCounter = 1;
Wall() {
wallNumber = wallCounter;
wallCounter++;
System.out.println("Created wall " + wallNumber);
}
public String toString() {
return "Wall " + wallNumber;
}
public void enter(Room room) {
}
}