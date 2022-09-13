/** Test harness for class A1.
 */
class A1Test {

    public static void main(String[] args) {
        testProduct();
        testTheAnswerIs2();
        testMagicTrick();
        testIsLiquid();
        testTheAnswerIs42Conditional();
        testHypotenuse();
        testIsTriangle();
        testCalculate();
        testRangeSum();
        testRangeSumOdd();
        testIsPalindrome();
        testDuplicateVowels();
    }

    static void testProduct() {
        System.out.println("product() = " + A1.product());
        System.out.println("expected = 1001");
    }

    static void testTheAnswerIs2() {
        //test: normal input
        System.out.println("theAnsweris2(17) = " + A1.theAnsweris2(17));
        System.out.println("expected = 2");
    }

    static void testMagicTrick() {
        //test: 3 digit number
        System.out.println("magicTrick(100) = " + A1.magicTrick(100));
        System.out.println("expected = 100100");
        //test: 2 digit number
        System.out.println("magicTrick(99) = " + A1.magicTrick(99));
        System.out.println("expected = -1");
        //test: negative 4 dig
        System.out.println("magicTrick(-1000) = " + A1.magicTrick(-1000));
        System.out.println("expected = -1");
        //test: negative 3 dig
        System.out.println("magicTrick(-200) = " + A1.magicTrick(-200));
        System.out.println("expected = -200200");
    }

    static void testIsLiquid() {
        //test: liquid
        System.out.println("isLiquid(50) = " + A1.isLiquid(50));
        System.out.println("expected = true");
        //test: not liquid
        System.out.println("isLiquid(-20) = " + A1.isLiquid(-20));
        System.out.println("expected = false");
        //test: edge case
        System.out.println("isLiquid(100) = " + A1.isLiquid(100));
        System.out.println("expected = false");
    }

    static void testTheAnswerIs42Conditional() {
        System.out.println("theAnsweris42Conditional(42) = " + A1.theAnsweris42Conditional(42));
        System.out.println("expected = " + 42);
    }

    static void testHypotenuse() {
        //test: ints
        System.out.println("hypotenuse(3, 4) = " + A1.hypotenuse(3, 4));
        System.out.println("expected = 5");
        //test: doubles
        System.out.println("hypotenuse(5.0, 6.0) = " + A1.hypotenuse(5.0, 6.0));
        System.out.println("expected = 7.810");
    }

    static void testIsTriangle() {
        //test: expect true
        System.out.println("isTriangle(3, 4, 5) = " + A1.isTriangle(3, 4, 5));
        System.out.println("expected = true");
        //test: expect false
        System.out.println("isTriangle(3, 4, 8) = " + A1.isTriangle(3, 4, 8));
        System.out.println("expected = false");
        //test: edge case
        System.out.println("isTriangle(10, 2, 8) = " + A1.isTriangle(10, 2, 8));
        System.out.println("expected = false");
    }

    static void testCalculate() {
        //test: add
        System.out.println("calculate(\"ADD\", 2, 3)) = " + A1.calculate("ADD", 2, 3));
        System.out.println("expected = 5");
        //test: divide
        System.out.println("calculate(\"DIVIDE\", 9, 2)) = " + A1.calculate("DIVIDE", 9, 2));
        System.out.println("expected = 4");
        //test: multiply
        System.out.println("calculate(\"MULTIPLY\", 2, 3)) = " + A1.calculate("MULTIPLY", 2, 3));
        System.out.println("expected = 6");
    }

    static void testRangeSum() {
        System.out.println("rangeSum(1, 4) = " + A1.rangeSum(1, 4));
        System.out.println("expected = " + 10);
    }

    static void testRangeSumOdd() {
        //test: start with odd
        System.out.println("rangeSumOdd(1, 4) = " + A1.rangeSumOdd(1, 4));
        System.out.println("expected = " + 4);
        //test: start with even
        System.out.println("rangeSumOdd(2, 7) = " + A1.rangeSumOdd(2, 7));
        System.out.println("expected = " + 15);
    }

    static void testIsPalindrome() {
        //test: one letter
        System.out.println("isPalindrome(\"a\") = " + A1.isPalindrome("a"));
        System.out.println("expected = " + true);
        //test: expect true
        System.out.println("isPalindrome(\"tacocat\") = " + A1.isPalindrome("tacocat"));
        System.out.println("expected = " + true);
        //test: expect false
        System.out.println("isPalindrome(\"cat\") = " + A1.isPalindrome("cat"));
        System.out.println("expected = " + false);
    }

    static void testDuplicateVowels() {
        //test: normal input
        System.out.println("duplicateVowels(\"abracadabra\") = " + A1.duplicateVowels("abracadabra"));
        System.out.println("expected = aabraacaadaabraa");
        //test: one letter
        System.out.println("duplicateVowels(\"a\") = " + A1.duplicateVowels("a"));
        System.out.println("expected = aa");
        //test: no vowels
        System.out.println("duplicateVowels(\"mmmmmmm\") = " + A1.duplicateVowels("mmmmmmmm"));
        System.out.println("expected = mmmmmmm");
    }
}
