public class Registration {
private Student student;
Registration(Student student, CourseSection courseSection) {
this.student = student;
courseSection.requestToRegister(this);
}
public Student getStudent() {
return student;
}
public void setStudent(Student student) {
this.student = student;
}
}