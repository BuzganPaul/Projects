
import java.util.List;
public class Course {
private List<CourseSection> prerequisite;
private String name;
private int minimum;
private int maximum;
Course(String name, List<CourseSection> prerequisite, int minimum, int maximum) {
this.prerequisite = prerequisite;
this.name = name;
this.minimum = minimum;
this.maximum = maximum;
}
public List<CourseSection> getPrerequisite() {
return prerequisite;
}
public void setName(String name) {
this.name = name;
}
int getMaximum() {
return maximum;
}
int getMinimum() {
return minimum;
}
public String getName() {
return name;
}
public void setMaximum(int maximum) {
this.maximum = maximum;
}
public void setMinimum(int minimum) {
this.minimum = minimum;
}
public void setPrerequisite(List<CourseSection> prerequisite) {
this.prerequisite = prerequisite;
}
}