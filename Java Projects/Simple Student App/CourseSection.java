
import java.util.ArrayList;
import java.util.List;
public class CourseSection {
private String name;
private Course course;
private List<Registration> registrationList;
private State state;
private boolean open;
private boolean closedOrCanceled;
CourseSection(String name, Course course) {
this.name = name;
this.course = course;
this.registrationList = new ArrayList<Registration>();
this.closedOrCanceled = false;
this.open = false;
updateState();
}
private void openRegistration() {
this.open = true;
updateState();
}
void requestToRegister(Registration r) {
openRegistration();
registrationList.add(r);
if (this.registrationList.size() > this.course.getMaximum())
this.closedOrCanceled = true;
updateState();
}
public void addToRegistrationList(Registration r) {
this.registrationList.add(r);
}
void closeRegistration() {
this.open = false;
this.closedOrCanceled = true;
updateState();
}
public void cancelRegistration() {
this.closedOrCanceled = true;
updateState();
}
public String getName() {
return name;

}
public void setName(String name) {
this.name = name;
}
public Course getCourse() {
return course;
}
public List<Registration> getRegistrationList() {
return registrationList;
}
public void setRegistrationList(List<Registration> registrationList) {
this.registrationList = registrationList;
}
State getState() {
return state;
}
public void setState(State state) {
this.state = state;
}
public boolean isOpen() {
return open;
}
public void setOpen(boolean open) {
this.open = open;
}
public boolean isClosedOrCanceled() {
return closedOrCanceled;
}
public void setClosedOrCanceled(boolean closedOrCanceled) {
this.closedOrCanceled = closedOrCanceled;
}
private void updateState() {
if (!this.closedOrCanceled && !this.open)
this.state = State.Planned;
else if (this.open && this.registrationList.size() == 0)
this.state = State.Open;
else if (this.open && this.registrationList.size() < this.course.getMinimum())
this.state = State.NotEnoughStudents;
else if (this.open && this.registrationList.size() >= this.course.getMinimum() &&
this.registrationList.size() < this.course.getMaximum())

this.state = State.EnoughStudents;
else if (this.closedOrCanceled && this.registrationList.size() == 0)
this.state = State.Canceled;
else if (this.closedOrCanceled && this.registrationList.size() > 0)
this.state = State.Closed;
}
}