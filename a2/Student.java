package a2;
public class Student {

    private String firstName;
    /** class invariant: firstname is a non-empty String that holds student's first name */

    private String lastName;
    /** class invariant: lastname is a non-empty String that holds student's last name */

    private int year;
    /** class invariant: year is an int > 0 that holds student's year in school, e.g: 1 if Freshman, 2 if Sophomore */

    private Course course;
    /** class invariant: the Course that student is enrolled in, can be null if student isn't enrolled in any course */

    boolean classInv(){
        return firstName != null && lastName != null && year > 0;
    }

    /** Constructor: Create a new Student with first name f, last name l, and year y.
     * This student is not enrolled in any Courses.
     * Requires: f and l have at least one character and y > 0. */

    public Student(String f, String l, int y) {
        firstName = f;
        lastName = l;
        year = y;
    }

    /** The first name of this Student. */
    public String firstName() {
        assert classInv();
        return firstName;
    }

    /** The last name of this Student. */
    public String lastName() {
        assert classInv();
        return lastName;
    }

    /** The first and last name of this Student in the format "First Last". */
    public String fullName() {
        assert classInv();
        return firstName + " " + lastName;
    }

    /** The year in school this Student is in. */
    public int year() {
        assert classInv();
        return year;
    }

    /** The course this student is enrolled in. */
    public Course course() {
        assert classInv();
        return course;
    }

    /** Enroll this Student in Course c.
     * Requires: The student is not enrolled in a course already.*/
    public void joinCourse(Course c) {
        assert c.classInv() && classInv();
        if (course == null){
            course = c;
        }
        assert classInv() && c.classInv();
    }

    /**
     * Drop this Student from their Course. Requires: This student is enrolled in a course already.
     */
    public void leaveCourse() {
        assert classInv();
        if (course != null){
            course = null;
        }
    }

    /** Return the full name of this Student */
    public String toString() {
        return fullName();
    }
}