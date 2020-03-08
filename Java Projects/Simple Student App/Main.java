public class Main {
private void testCourse() {
Course se = new Course("Software Engineering", null, 2, 4);
CourseSection l1 = new CourseSection("Lab 1", se);
Student student = new Student("George Georgescu");
Student student1 = new Student("Ion Ionescu");
Student student2 = new Student("Omuletul Gopo");
Student student3 = new Student("Radu Radulescu");
Student student4 = new Student("Andrei Andreescu");
System.out.println(l1.getState());
l1.closeRegistration();
System.out.println(l1.getState());
Registration registration = new Registration(student, l1);
System.out.println(l1.getState());
Registration registration1 = new Registration(student1, l1);
System.out.println(l1.getState());
Registration registration2 = new Registration(student2, l1);
System.out.println(l1.getState());
l1.closeRegistration();
System.out.println(l1.getState());
Registration registration3 = new Registration(student3, l1);
System.out.println(l1.getState());
Registration registration4 = new Registration(student4, l1);
System.out.println(l1.getState());
}
public static void main(String args[]) {
Main main = new Main();
main.testCourse();
}
}