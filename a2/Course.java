package a2;

public class Course {

    /**
     * List of all students enrolled in this Course.
     */
    private StudentList students;
    /**
     * The hour at which lecture for this Course is held (in 24-hour time). 0 <= hour <= 23
     */
    private int hour;
    /**
     * The minutes at which lecture for this Course is held. 0 <= min <= 59 The lectures for this
     * course are at hour:min
     */
    private int min;
    /**
     * The location of lectures at this course (e.g. Statler Hall Room 185) Must be non-empty
     */
    private String location;
    /**
     * The last name of the professor of this course (e.g. Myers, Muhlberger, Gries) Must be
     * non-empty
     */
    private String prof;
    /**
     * The name of this course (e.g. Object-Oriented Programming and Data Structures) Must be
     * non-empty
     */
    private String name;

    /**
     * Constructor: Create new Course with name n, professor last name prof, location loc,<br> and
     * lectures are held at time hour:min. The course has no students. Precondition: n, prof, and
     * loc have at least one character in them, 0 <= hr <= 23, 0 <= min <= 59.
     */
    boolean classInv(){ return min >= 0 && min <=59 && location != null && prof != null && name != null; }

    public Course(int hr, int m, String loc, String p, String n) {
        name = n;
        prof = p;
        location = loc;
        hour = hr;
        min = m;
        students = new StudentList();
    }

    /** Return the name of this course. */
    public String getName() {
        assert classInv();
        return name;
    }

    /**
     * Return the time at which lectures are held for this course in the format hour:min AM/PM using
     * 12-hour time. For example, "11:15 AM", "1:35 PM". Add leading zeros to the minutes if necessary.
     */
    public String getLectureTime() {
        assert classInv();
        String am_pm = "AM";
        int hourUpdate = hour;
        String minUpdate = Integer.toString(min);
        if (hour > 12) {
            am_pm = "PM";
            hourUpdate = hour - 12;
        }
        if (minUpdate.length()<2){
            minUpdate = "0" + minUpdate;
        }
        return Integer.toString(hourUpdate) + ":" + minUpdate + am_pm;
    }

    /**
     * Return the location of lectures in this course.
     */
    public String getLocation() {
        assert classInv();
        return location;
    }

    /**
     * Return the name of the professor in the format "Professor LastName"
     */
    public String getProfessor() {
        assert classInv();
        return "Professor " + prof;
    }

    /**
     * Return the String representation of the list of students enrolled in this course
     */
    public String getStudents() {
        assert classInv();
        return students.toString();

    }

    /**
     * Enroll a new student s to this course. If Student s is already enrolled in a course, they
     * cannot enroll in this course. Return true if the student was successfully enrolled in the
     * course.
     */
    public boolean enrollStudent(Student s) {
        assert classInv() && s.classInv();
        if (students.contains(s) == false){
            students.append(s);
            return true;
        }
        assert classInv();
        return false;

    }

    /**
     * Drop Student s from this course. If Student s is not enrolled in this course, they cannot be
     * dropped from this course. Return true if the student was successfully dropped from the
     * course.
     */
    public boolean dropStudent(Student s) {
        assert classInv() && s.classInv();
        if (students.contains(s)){
            students.remove(s);
            return true;
        }
        assert classInv() && s.classInv();
        return false;
    }

    /**
     * Print the Course information in tabular format
     */
    public void print() {
        System.out.printf("%-40s%-12s%-20s%-40s",
                name, getLectureTime(), prof, location);
    }
}