package a2;
/**
 * Please provide the following information
 *  Name: Stella Dong
 *  NetID: ssd74
 *  Testing Partner Name: Eva Farroha
 *  Testing Partner NetID: edf55
 *  Tell us what you thought about the assignment: bueno, eva tambien
 */

/** A mutable list of {@code Student} objects.
 */
public class StudentList {

    /** class invariant: a list of students, elements can be null*/
    private Student[] list;

    /** class invariant: capacity is an int >= 0, capacity = list.length*/
    private int capacity;

    /** class invariant: size is an int >= 0 and <= capacity, represents the amount of non-null elements in list*/
    private int size;

    /** How long you spent on this assignment */
    public static double timeSpent = 7;

    /** Constructor: A new empty {@code StudentList}. */

    boolean classInv(){
        return capacity >= 0 && size >= 0 && size <= capacity && capacity == list.length;
    }

    public StudentList() {
        list = new Student[5];
        capacity = list.length;
        size = 0;
    }

    /** The number of elements in this StudentList. */
    public int size() {
        assert classInv();
        return size;
    }

    /** The element in the list at position i. Positions start from 0.
     * Requires: 0 <= i < size of StudentList
     */
    public Student get(int i) {
        assert classInv();
        return list[i];
    }

    /** Effect: Add Student s to the end of the list. */
    public void append(Student s) {
        assert classInv() && s.classInv();
        Student[] temp_copy = list;
        if (capacity == size){
            capacity = capacity*2;
            list = new Student[capacity];
            for (int i = 0; i < temp_copy.length; i++){
                list[i] = temp_copy[i];
            }
        }
        list[size] = s;
        size++;
        assert classInv() && s.classInv();
    }

    /** Returns: whether this list contains Student s. */
    public boolean contains(Student s) {
        assert classInv() && s.classInv();
        for (int i = 0; i < list.length; i++){
            if (list[i] == s ){
                return true;
            }
        }
        return false;
    }

    /** Effect: If Student s is in this StudentList, remove Student s from the array and return true.
     * Otherwise return false. Elements other than s remain in the same relative order.
     */
    public boolean remove(Student s) {
        assert classInv() && s.classInv();
        if (this.contains(s)){
            int s_index = this.find(s);
            shift(s_index);
            size--;
            return true;
            }
        return false;
    }

    /** A private helper method that finds the index of Student s in list and returns the index.
     * If s is not in the list, returns -1.
     */
    private int find(Student s){
        for (int i = 0; i < list.length; i++){
            if (list[i].equals(s)){
                return i;
            }
        }
        return -1;
    }

    /** A private helper method that shifts everything in list after index i forward by one index.
     * Doesn't return anything.
     */
    private void shift(int i) {
        int shift = i+1;
        for (int j = i; j < list.length; j++) {
            if (shift < list.length) {
                list[j] = list[shift];
                shift++;
            }
        }
    }

    /** A private helper method that returns the length of list (the capacity).
     */

    public int len() {
        assert classInv();
        return capacity;

    }


    /** The String representation of this StudentList */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(get(i));
        }
        sb.append("]");
        return sb.toString();
    }
}
