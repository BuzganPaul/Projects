
public class Room extends MapSite {
private int roomNo;
private static int roomCount = 1;
private MapSite _northSide;
private MapSite _southSide;
private MapSite _eastSide;
private MapSite _westSide;
Room() {
roomNo = roomCount;
roomCount++;
System.out.println("Created room " + roomNo);
}
public void enter(Room room) {
System.out.println("You are in room " + roomNo);
}
public String toString() {
return "Room " + roomNo;
}
void setSide(Direction d, MapSite site) {
switch (d) {
case North:
_northSide = site;
break;
case South:
_southSide = site;
break;
case East:
_eastSide = site;
break;
case West:
_westSide = site;
}
System.out.println(d.toString() + " - " + this.toString() + " - " + site.toString());
}
MapSite getSide(Direction d) {
MapSite result = null;
switch (d) {
case North:
result = _northSide;
break;
case South:
result = _southSide;
break;
case East:
result = _eastSide;
break;
case West:
result = _westSide;
break;
}
return result;
}
}