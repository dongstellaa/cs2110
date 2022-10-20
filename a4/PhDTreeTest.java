package a4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

/** @author Amy Huang, Maya Leong */
public class PhDTreeTest {
    private static final Professor prof1 = new Professor("Amy", "Huang");
    private static final Professor prof2 = new Professor("Maya", "Leong");
    private static final Professor prof3 = new Professor("Matthew", "Hui");
    private static final Professor prof4 = new Professor("Arianna", "Curillo");
    private static final Professor prof5 = new Professor("Michelle", "Gao");
    private static final Professor prof6 = new Professor("Isa", "Siu");

    private static PhDTree tree1() {
        return new PhDTree(prof1, 2023);
    }
    private static PhDTree tree2() {
        return new PhDTree(prof4, 2019);
    }

    private static PhDTree tree3() {
        PhDTree t = new PhDTree(prof1, 1950);
        t.insert(prof1, prof2, 1960);
        t.insert(prof2, prof3, 1970);
        return t;
    }


    @Test
    public void constructorTests() {
        assertEquals("Amy Huang", tree1().toString());
        assertEquals("Arianna Curillo", tree2().toString());
    }

    @Test
    public void getterTests() {
        assertEquals(prof1, tree1().prof());
        // we have not inserted anything into the tree yet
        PhDTree t = new PhDTree(prof1, 2000);
        assertEquals(0, t.numAdvisees());
    }

    @Test
    public void insertTests() throws NotFound{
        // add professor 2 as a child of professor 1
        PhDTree t = new PhDTree(prof1, 1950);
        t.insert(prof1, prof2, 1960);
        t.insert(prof2, prof3, 1970);
        assertEquals(1, t.numAdvisees());
        assertEquals(3, t.size());
        assertEquals("Amy Huang[Maya Leong[Matthew Hui]]", t.toString());

        //adding professor to leaves
        t.insert(prof3, prof4, 1965);
        assertEquals(1, t.findTree(prof3).numAdvisees());
        assertEquals(1, t.findTree(prof2).numAdvisees());
        assertEquals(4, t.size());
        assertEquals("Amy Huang[Maya Leong[Matthew Hui[Arianna Curillo]]]", t.toString());

        //adding professor to higher branches
        t.insert(prof2, prof5, 1965);
        assertEquals(1, t.findTree(prof3).numAdvisees());
        assertEquals(2, t.findTree(prof2).numAdvisees());
        assertEquals(5, t.size());
        assertEquals("Amy Huang[Maya Leong[Michelle Gao, Matthew Hui[Arianna Curillo]]]", t.toString());

        //adding professor to root
        t.insert(prof1, prof6, 1980);
        assertEquals(2, t.findTree(prof1).numAdvisees());
        assertEquals(6, t.size());
        String str = "Amy Huang[Maya Leong[Michelle Gao, Matthew Hui[Arianna Curillo]], Isa Siu]";
        assertEquals(str, t.toString());


    }

    @Test
    public void findTreeTests() throws NotFound {
        //given
        PhDTree tree1 = tree1();
        tree1.insert(prof1, prof2, 1950);
        tree1.insert(prof2, prof3, 1960);
        PhDTree tree4 = new PhDTree(prof2, 1950);
        tree4.insert(prof2, prof3, 1980);
        assertEquals(tree4.prof(), tree1.findTree(prof2).prof());
        assertEquals("Maya Leong[Matthew Hui]", tree1.findTree(prof2).toString());

        assertThrows(NotFound.class, () -> tree2().findTree(prof5));
        assertThrows(NotFound.class, () -> tree1.findTree(prof4));
        assertEquals(1, tree1.findTree(prof3).size());

        //finding base (no recursive call)
        assertEquals(tree1, tree1.findTree(prof1));
        assertEquals("Amy Huang[Maya Leong[Matthew Hui]]", tree1.findTree(prof1).toString());

        //finding leaf
        assertEquals("Matthew Hui", tree1.findTree(prof3).toString());
        assertEquals(0, tree1.findTree(prof3).numAdvisees());

        //throwing exception
        assertThrows(NotFound.class, () -> tree1.findTree(prof5));
    }

    @Test
    public void sizeTest() {
        PhDTree t = new PhDTree(prof1, 1900);

        //testing size of 1 (just head)
        assertEquals(1, t.size());

        t.insert(prof1, prof2, 1920);
        t.insert(prof2, prof3, 1930);

        //testing adding depth
        assertEquals(3, t.size());

        t.insert(prof2, prof4, 1940);

        //testing not adding depth
        assertEquals(4, t.size());

        //testing both
        t.insert(prof4, prof5, 1950);
        t.insert(prof4, prof6, 1930);

        assertEquals(6, t.size());
    }

    @Test
    public void containsTest() {
        PhDTree t = new PhDTree(prof1, 1900);
        t.insert(prof1, prof2, 1920);
        t.insert(prof2, prof3, 1930);
        assertTrue(t.contains(new Professor("Amy", "Huang")));
        assertFalse(t.contains(prof6));
    }

    @Test
    public void findAcademicLineageTest() throws NotFound {
        //given
        PhDTree t = new PhDTree(prof1, 1900);
        t.insert(prof1, prof2, 1920);
        t.insert(prof2, prof3, 1930);
        List<Professor> lineage1 = new LinkedList<>();
        lineage1.add(prof1);
        lineage1.add(prof2);
        lineage1.add(prof3);
        assertEquals(lineage1, t.findAcademicLineage(prof3));

        PhDTree t2 = new PhDTree(prof1, 1900);
        t2.insert(prof1, prof2, 1920);
        t2.insert(prof2, prof3, 1930);
        t2.insert(prof2, prof4, 1940);
        t2.insert(prof3, prof5, 1940);

        //testing base case
        List<Professor> lineage2 = new LinkedList<>();
        lineage2.add(prof1);
        assertEquals(lineage2, t2.findAcademicLineage(prof1));

        //testing recursive calls
        List<Professor> lineage3 = new LinkedList<>();
        lineage3.add(prof1);
        lineage3.add(prof2);
        lineage3.add(prof3);
        assertEquals(lineage3, t2.findAcademicLineage(prof3));
        lineage3.add(prof5);
        assertEquals(lineage3, t2.findAcademicLineage(prof5));

        List<Professor> lineage4 = new LinkedList<>();
        lineage4.add(prof1);
        lineage4.add(prof2);
        lineage4.add(prof4);
        assertEquals(lineage4, t2.findAcademicLineage(prof4));

        //testing throwing exception (p not in tree)
        assertThrows(NotFound.class, () -> t2.findAcademicLineage(prof6));
    }

    @Test
    public void commonAncestorTest() throws NotFound {
        //given
        PhDTree t = tree3();
        assertEquals(prof2, t.commonAncestor(prof2, prof3));
        assertEquals(prof1, t.commonAncestor(prof1, prof3));
        assertThrows(NotFound.class, () -> t.commonAncestor(prof5, prof3));

        PhDTree t2 = new PhDTree(prof1, 2000);
        Professor prof7 = new Professor("Eva", "Farroha");
        Professor prof8 = new Professor("Stella", "Dong");
        t2.insert(prof1, prof2, 1920);
        t2.insert(prof2, prof3, 1930);
        t2.insert(prof2, prof4, 1940);
        t2.insert(prof4, prof5, 2020);
        t2.insert(prof1, prof6, 2025);
        t2.insert(prof6, prof7, 2030);

        //common ancestor is one layer above
        assertEquals(prof2, t2.commonAncestor(prof3, prof4));

        //professors are at different levels of tree
        assertEquals(prof1, t2.commonAncestor(prof4, prof6));

        //professors are both leaves
        assertEquals(prof1, t2.commonAncestor(prof5, prof7));
        assertEquals(prof2, t2.commonAncestor(prof3, prof5));

        //professors are the same, common ancestor should be itself
        assertEquals(prof5, t2.commonAncestor(prof5, prof5));

        //no common ancestor, throws exception
        assertThrows(NotFound.class, () -> t2.commonAncestor(prof5, prof8));

    }

    @Test
    public void maxDepthTest() {
        //given
        PhDTree t = tree3();
        assertEquals(2, t.maxDepth());

        //our tests
        PhDTree t2 = new PhDTree(prof1, 1900);
        //depth of 0 (no recursive call)
        assertEquals(0, t2.maxDepth());

        //testing adding depth
        t2.insert(prof1, prof2, 1920);
        t2.insert(prof2, prof3, 1930);
        assertEquals(2, t2.maxDepth());

        //not adding depth
        t2.insert(prof2, prof4, 1940);
        assertEquals(2, t2.maxDepth());

        //adding more depth
        t2.insert(prof3, prof5, 1950);
        t2.insert(prof5, prof6, 1930);

        assertEquals(4, t2.maxDepth());
    }

    @Test
    public void getAdvisorTest() throws NotFound {
        //given
        PhDTree t = tree3();
        assertEquals(prof2.toString(), t.findAdvisor(prof3).toString());
        assertThrows(NotFound.class, () -> t.findAdvisor(prof1));

        //our tests
        PhDTree t2 = new PhDTree(prof1, 2000);
        t2.insert(prof1, prof2, 2020);
        t2.insert(prof2, prof3, 2030);
        t2.insert(prof2, prof4, 1940);
        t2.insert(prof3, prof5, 1950);

        //p is the root of the tree, expecting NotFound exception
        assertThrows(NotFound.class, () -> t2.findAdvisor(prof1));

        //p is in the first subtree (no recursive call)
        assertEquals(prof1.toString(), t2.findAdvisor(prof2).toString());

        //p is in a lower subtree (recursive call)
        assertEquals(prof3.toString(), t2.findAdvisor(prof5).toString());

        //p is not in the tree
        assertThrows(NotFound.class, () -> t2.findAdvisor(prof6));


    }

    @Test
    public void toStringVerbose() {
        //given
        PhDTree t = tree3();
        String[] lines = t.toStringVerbose().split("\n");
        String[] expected = {
            "Amy Huang - 1950",
            "Maya Leong - 1960",
            "Matthew Hui - 1970"
        };
        Arrays.sort(lines);
        Arrays.sort(expected);
        assertTrue(Arrays.equals(lines, expected));

        //our tests
        PhDTree t2 = new PhDTree(prof1, 2000);

        //just root
        lines = t2.toStringVerbose().split("\n");
        expected = new String[]{
                "Amy Huang - 2000"
        };
        Arrays.sort(lines);
        Arrays.sort(expected);
        assertTrue(Arrays.equals(lines, expected));

        t2.insert(prof1, prof2, 2020);
        t2.insert(prof2, prof3, 2030);

        //single line, no branching off
        lines = t2.toStringVerbose().split("\n");
        expected = new String[]{
                "Amy Huang - 2000",
                "Maya Leong - 2020",
                "Matthew Hui - 2030"
        };
        Arrays.sort(lines);
        Arrays.sort(expected);
        assertTrue(Arrays.equals(lines, expected));

        t2.insert(prof2, prof4, 2040);
        t2.insert(prof4, prof5, 2040);

        //branching off
        lines = t2.toStringVerbose().split("\n");
        expected = new String[]{
                "Amy Huang - 2000",
                "Maya Leong - 2020",
                "Arianna Curillo - 2040",
                "Michelle Gao - 2040",
                "Matthew Hui - 2030"
        };
        Arrays.sort(lines);
        Arrays.sort(expected);
        assertTrue(Arrays.equals(lines, expected));

        t2.insert(prof1, prof6, 2030);

        //adding to root
        lines = t2.toStringVerbose().split("\n");
        expected = new String[]{
                "Amy Huang - 2000",
                "Isa Siu - 2030",
                "Maya Leong - 2020",
                "Arianna Curillo - 2040",
                "Michelle Gao - 2040",
                "Matthew Hui - 2030"
        };
        Arrays.sort(lines);
        Arrays.sort(expected);
        assertTrue(Arrays.equals(lines, expected));

    }

    @Test
    public void numAdviseesTest() throws NotFound{

        PhDTree t = new PhDTree(prof1, 1900);
        PhDTree t2 = new PhDTree(prof6, 2012);

        t.insert(prof1, prof2, 1920);
        t.insert(prof1, prof3, 1930);
        t.insert(prof2, prof4, 1940);
        t.insert(prof1, prof5, 1950);
        t.insert(prof2, prof6, 1930);

        //base layer
        assertEquals(3, t.numAdvisees());
        //not base layer
        assertEquals(2, t.findTree(prof2).numAdvisees());
        //no advisees
        assertEquals(0, t2.numAdvisees());

    }
}
