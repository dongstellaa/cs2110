package a2;

/** Test harness for Assignment 2
 */
public class A2Test {
    public static void main(String[] args) {
        System.out.println("testing StudentList methods: \n");
        testEmptyList();
        testAppend();
        testGet();
        testContains();
        testRemove();
        testSizeAndLen();

        System.out.println("\ntesting Student methods: ");
        testStudentConstructor();
        testJoinAndLeave();

        System.out.println("\ntesting Course methods: ");
        testCourseConstructor();
        testGetandEnrollandDropStudents();
    }

    public static void testStudentConstructor() {
        Student s = new Student("Bill", "Nye", 4);
        Student t = new Student("Bill", "Nye", -1);

        System.out.println("s.firstName() = " + s.firstName());
        System.out.println("expected = Bill");

        System.out.println("s.lastName() = " + s.lastName());
        System.out.println("expected = Nye");

        System.out.println("s.fullName() = " + s.fullName());
        System.out.println("expected = Bill Nye");

        System.out.println("s.year() = " + s.year());
        System.out.println("expected = 4");

        System.out.println("s.course() = " + s.course());
        System.out.println("expected = null");

        Course c = new Course(8, 30, "mom's house", "mom", "ur mom");
        s.joinCourse(c);
        System.out.println("after adding: s.course(c) = " + s.course().getName());
        System.out.println("expected = ur mom");

    }


    public static void testJoinAndLeave() {
        Student s = new Student("Bill", "Nye", 4);
        Course c = new Course(8, 30, "mom's house", "mom", "ur mom");
        s.joinCourse(c);
        System.out.println("after adding: s.course(c) = " + s.course().getName());
        System.out.println("expected = ur mom");
        s.leaveCourse();
        System.out.println("after leaving: s.course(c) = " + s.course());
        System.out.println("expected = null");
    }

    public static void testEmptyList() {
        StudentList list = new StudentList();
        Student s = new Student("Bill", "Nye", 4);
        System.out.println("list.size() = " + list.size());
        System.out.println("expected = 0");
        System.out.println("list.contains(s) = " + list.contains(s));
        System.out.println("expected = false");
    }

    public static void testAppend() {
        StudentList list = new StudentList();
        Student s = new Student("Bill", "Nye", 4);
        list.append(s);
        System.out.println("list.size() = " + list.size());
        System.out.println("expected = 1");

        Student t = new Student("Bull", "Nue", 4);
        Student u = new Student("Ball", "Nae", 4);
        list.append(t);
        list.append(u);
        System.out.println("printing out all elements of list: ");
        for (int i = 0; i< list.size(); i++){
            System.out.println(list.get(i));
        }
        System.out.println("expected: \nBill Nye \nBull Nue\nBall Nae");
        System.out.println("list.size() = " + list.size());
        System.out.println("expected = 3");

        Student v = new Student("Boll", "Noe", 4);
        Student w = new Student("Byll", "Nie", 4);
        Student x = new Student("b", "n", 4);
        list.append(v);
        list.append(w);
        list.append(x);
        for (int i = 0; i< list.size(); i++){
            System.out.println(list.get(i));
        }
        System.out.println("expected: \nBill Nye \nBull Nue\nBall Nae\nBoll Noe\nByll Nie\nb n");
        System.out.println("list.size() = " + list.size());
        System.out.println("expected = 6");

    }

    public static void testGet() {
        StudentList list = new StudentList();
        Student s = new Student("Bill", "Nye", 4);
        Student t = new Student("Bull", "Nue", 4);
        Student u = new Student("Ball", "Nae", 4);
        list.append(s);
        list.append(t);

        System.out.println("list.contains(t) = " + list.contains(t));
        System.out.println("expected = true");
        System.out.println("list.contains(u) = " + list.contains(u));
        System.out.println("expected = false");
    }

    public static void testContains() {
        StudentList list = new StudentList();
        Student s = new Student("Bill", "Nye", 4);
        list.append(s);
        System.out.println("list.get(0) = " + list.get(0));
        System.out.println("expected = Bill Nye");
    }

    public static void testRemove() {
        StudentList list = new StudentList();
        Student s = new Student("Bill", "Nye", 4);
        Student t = new Student("Bull", "Nue", 4);
        Student u = new Student("Ball", "Nae", 4);
        Student v = new Student("Boll", "Noe", 4);

        list.append(s);
        list.append(t);
        list.append(u);

        System.out.println("list.remove(t) = " + list.remove(t));
        System.out.println("expected = true");
        System.out.println("list.remove(v) = " + list.remove(v));
        System.out.println("expected = false");

        System.out.println("printing out all elements of list: ");
        for (int i = 0; i< list.size(); i++){
            System.out.println(list.get(i));
        }
        System.out.println("expected: \nBill Nye \nBall Nae");
        System.out.println("list.size() = " + list.size());
        System.out.println("expected = 2");
    }

    public static void testSizeAndLen(){
        StudentList list = new StudentList();
        Student s = new Student("Bill", "Nye", 4);
        System.out.println("list.size() = " + list.size());
        System.out.println("expected = 0");
        System.out.println("list.len() = " + list.len());
        System.out.println("expected = 5");
        list.append(s);
        System.out.println("list.size() = " + list.size());
        System.out.println("expected = 1");
        System.out.println("list.len() = " + list.len());
        System.out.println("expected = 5");
    }


    public static void testCourseConstructor(){
        Course c = new Course(8, 30, "mom's house", "mom", "ur mom");
        Course a = new Course (3, 26, "slay", "girlboss", "yassss");
        Course newClass = new Course(15, 00, "mom's house", "mom", "ur mom");
        Course urdad = new Course(22,5,"toilet", "doodoo", "poopy");

        System.out.println("c.getLectureTime() = " + c.getLectureTime());
        System.out.println("expected = 8:30AM");

        System.out.println("a.getName() = " + a.getName());
        System.out.println("expected = yassss");

        System.out.println("newClass.getLectureTime() = " + newClass.getLectureTime());
        System.out.println("expected = 3:00PM");

        System.out.println("c.getLocation() = " + c.getLocation());
        System.out.println("expected = mom's house");

        System.out.println("c.getProfessor() = " + c.getProfessor());
        System.out.println("expected = Professor mom");

        System.out.println("newClass.getLectureTime() = " + urdad.getLectureTime());
        System.out.println("expected = 10:05PM");

        System.out.println("urdad.getLocation() = " + urdad.getLocation());
        System.out.println("expected = toilet");

        System.out.println("urdad.getProfessor() = " + urdad.getProfessor());
        System.out.println("expected = Professor doodoo");

        System.out.println("urdad.getName(); = " + urdad.getName());
        System.out.println("expected = poopy");

    }

    public static void testGetandEnrollandDropStudents() {
        Course c = new Course(8, 30, "mom's horuse", "mom", "ur mom");
        Student s = new Student("Bill", "Nye", 4);
        System.out.println("c.enrollStudent(s) = " + c.enrollStudent(s));
        System.out.println("expected = true");
        System.out.println("c.enrollStudent(s) = " + c.enrollStudent(s));
        System.out.println("expected = false");

        Course a = new Course (3, 26, "slay", "girlboss", "yassss");
        Student yucky = new Student("joan", "varous", 2);
        Student eww = new Student("masarrah", "assad", 4);
        Student gross = new Student("sam", "ross", 1);
        a.enrollStudent(yucky);
        a.enrollStudent(eww);
        a.enrollStudent(gross);
        System.out.println("a.getStudents() = " + a.getStudents());
        System.out.println("expected = joan varous, masarrah assad, sam ross");
        System.out.println("a.dropStudent() = " + a.dropStudent(gross));
        System.out.println("expected = true");
        System.out.println("a.getStudents() = " + a.getStudents());
        System.out.println("expected = joan varous, masarrah assad");
        System.out.println("a.dropStudent() = " + a.dropStudent(gross));
        System.out.println("expected = false");

    }
}


