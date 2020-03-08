
public class Door extends MapSite {
private static int doorCount = 1;
private int doorNo;
private Room room1;
private Room room2;
private boolean open;
Door(Room room1, Room room2) {
doorNo = doorCount;
doorCount++;
this.room1 = room1;
this.room2 = room2;
System.out.println("Created door: " + doorNo + " between " + room1 + " - " + room2);
}
public String toString() {
return "Door " + doorNo;
}
Room otherSideFrom(Room room) {
if (room1 == room)
return room2;
return room1;
}
String isOpen() {
if (open) return "open";
else return "closed";
}
public void enter(Room room) {
if (room2 == room) {
System.out.println("Entered room " + room);
}
}
}