/** Please provide the following information
 * Name(s): Stella Dong
 * NetID(s): ssd74
 * Tell us what you thought about the assignment: nice
 */

/** The goal of this assignment is to familiarize yourself with Java and start
 * to establish good programming practices.
 */

/** Class A1 defines a set of methods to implement. Each method has a comment
 * at the top. These are method specifications (specs) Method specs allow
 * anyone who is reading the program to immediately understand what the method
 * is doing.
 *
 * Each function body has in it a return statement. Without it, the function
 * won't compile. Replace the return statement with code you write to implement
 * the spec.
 */
public class A1 {

    /** Replace the "-1" with the amount of time you spent on A1 in hours.
     *  Example: for 1 hour and 30 min, write that timeSpent = 1.5
     *  Example: for 1 hour, write that timeSpent = 1 or timeSpent = 1.0
     */
    public static double timeSpent = 2.5;

    /** Return the product of the values: 7, 11, and 13. */
    public static int product() {
        return 7*11*13;
    }

    /** In the following order: double x, add 4, divide it by 2, and then
     *  subtract the original x value from the result. */
    public static int theAnsweris2(int x){
        return ((((x*2)+4)/2)-x);
    }

    /** If x is not a three-digit number, return -1;
     * Otherwise return the product of x and the values: 7, 11, and 13.
     */
    public static int magicTrick(int x) {
        int testx = x%100;
        int testx2 = Math.abs(x/100);
        if (testx == x || testx2 >= 10) {
            return -1;
        }
        else {
            return x*7*11*13;
        }
    }

    /** Given some temperature x of water in degrees Celsius,that the
     * melting point of water is 0ºC , and that the boiling point is 100ºC,
     * determine if the water is liquid. Note: in our program water is not
     * a liquid at 0ºC or 100ºC.
     */
    public static boolean isLiquid(int temperature){
        if (temperature < 100 && temperature > 0){
            return true;
        }
        return false;
    }

    /** Given some value x, return 42 if x is equal to 42. If not, return -1.
     */
    public static int theAnsweris42Conditional(int x){
        if (x == 42){
            return 42;
        }
        return -1;
    }

    /** Given two triangle legs a and b of a right triangle, return the hypotenuse.
     *  Requires: a and b must be positive values.
     */
    public static double hypotenuse(double a, double b) {
        return Math.sqrt(a*a + b*b);
    }

    /** Given three triangle side lengths a, b, and c, determine if the triangle can exist.
     */
    public static boolean isTriangle(double a, double b, double c) {
        if (a >= (b+c)){
            return false;
        }
        if (b >= (c+a)){
            return false;
        }
        if (c >= (a+b)){
            return false;
        }
        return true;

    }

    /** Given four operations: ADD, SUBTRACT, MULTIPLY, and DIVIDE and two inputs x and y,
     *  return the result of the operation between x and y.
     *  Requires: opp = DIVIDE and y = 0 cannot be true at the same time.
     */
    public static int calculate(String opp, int x, int y) {
        if (opp.equals("ADD")){
            return x+y;
        }
        if (opp.equals("SUBTRACT")){
            return x-y;
        }
        if (opp.equals("MULTIPLY")){
            return x*y;
        }
        return (int) x / y;

    }

    /** Return the sum of the values in n to m inclusive.
     */
    public static int rangeSum(int n, int m) {
        int sum = 0;
        for (int i = n; i <= m; i++){
            sum += i;
        }
        return sum;
    }

    /** Return the sum of the odd values in n to m inclusive.
     */
    public static int rangeSumOdd(int n, int m) {
        int sum = 0;
        int i = n;
        if (n%2==0){
            i = n+1;
        }
        while (i<=m) {
            sum += i;
            i += 2;
        }
        return sum;
    }

    /** Return whether str is a palindrome.
     */
    public static boolean isPalindrome(String str) {
        String reverseString = "";
        for (int i = str.length()-1; i >= 0; i--){
            reverseString += str.charAt(i);
        }
        if (str.equals(reverseString)) {
            return true;
        }
        return false;
    }

    /** Return a String that return a string that contains the
     *  same characters as str, but with each vowel duplicated.
     */
    public static String duplicateVowels(String str) {
        String duplStr = "";
        char letter = str.charAt(0);
        String vowels = "aeiou";
        for (int i = 0; i < str.length(); i++){
            letter = str.charAt(i);
            duplStr = duplStr + letter;
            if (vowels.indexOf(letter) >= 0) {
                duplStr = duplStr + letter;
            }
        }
        return duplStr;
    }
}
