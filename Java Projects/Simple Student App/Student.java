
import java.util.ArrayList;
import java.util.List;
public class Student {
private String name;
private List<Registration> registrationList;
Student(String name) {
this.name = name;
this.registrationList = new ArrayList<Registration>();
}
public boolean addToSchedule() {
return true;
}
public boolean hasPassedCourse() {
return true;
}
public String getName() {
return name;

}
public void setName(String name) {
this.name = name;
}
public List<Registration> getRegistrationList() {
return registrationList;
}
public void setRegistrationList(List<Registration> registrationList) {
this.registrationList = registrationList;
}
}