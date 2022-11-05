package a5.ai;

import static org.junit.jupiter.api.Assertions.*;

import a5.ai.TranspositionTable.StateInfo;
import a5.logic.Pente;
import a5.logic.Position;
import a5.logic.TicTacToe;
import cms.util.maybe.NoMaybeValue;
import org.junit.jupiter.api.Test;

class TranspositionTableTest {

    @Test
    void testConstructor() {
        // TODO 1: write at least 1 test case
        TranspositionTable<TicTacToe> table1 = new TranspositionTable<>();

        assertEquals(0, table1.size());
    }


    @Test
    void getInfo() throws NoMaybeValue {
        // test case 1: look for a state that is in the table
        TranspositionTable<TicTacToe> table = new TranspositionTable<>();
        TicTacToe state = new TicTacToe();
        table.add(state, 0, GameModel.WIN);

        StateInfo info = table.getInfo(state).get();
        assertEquals(GameModel.WIN, info.value());
        assertEquals(0, info.depth());

        // test case 2: look for a state not in the table
        TicTacToe state2 = state.applyMove(new Position(0, 0));
        assertThrows(NoMaybeValue.class, () -> table.getInfo(state2).get());

        // TODO 2: write at least 3 more test cases

        // test case 3: look for a state in an empty table
        TranspositionTable<TicTacToe> table2 = new TranspositionTable<>();
        assertThrows(NoMaybeValue.class, () -> table2.getInfo(state).get());

        // test case 4: look for a state in a table with multiple values
        TicTacToe state3 = state.applyMove(new Position(1, 1));
        table2.add(state, 0, 69);
        table2.add(state2, 10, 100);
        table2.add(state3, 1, 3);
        StateInfo info1 = table2.getInfo(state2).get();
        assertEquals(100, info1.value());
        assertEquals(10, info1.depth());

        // test case 5: look for a state not in a table with multiple values
        TicTacToe state4 = state.applyMove(new Position(1, 2));
        assertThrows(NoMaybeValue.class, () -> table2.getInfo(state4).get());

        // test case 6: look for a state in an empty table (pente)
        TranspositionTable<PenteModel> table3 = new TranspositionTable<>();
        PenteModel pstate = new PenteModel();
        assertThrows(NoMaybeValue.class, () -> table3.getInfo(pstate).get());

    }

    @Test
    void add() throws NoMaybeValue {
        TranspositionTable<TicTacToe> table = new TranspositionTable<>();

        // test case 1: add a state and check it is in there
        TicTacToe state = new TicTacToe();
        table.add(state, 0, GameModel.WIN);

        StateInfo info = table.getInfo(state).get();
        assertEquals(GameModel.WIN, info.value());
        assertEquals(0, info.depth());

        // TODO 3: write at least 3 more test cases

        // test case 2: overwrite an existing entry and check if it correctly updates
        table.add(state, 1, 4);
        StateInfo info2 = table.getInfo(state).get();
        assertEquals(4, info.value());
        assertEquals(1, info.depth());
        assertEquals(1, table.size());

        // test case 3: try to overwrite existing entry, but new depth < old depth
        table.add(state, 0, 4);
        StateInfo info3 = table.getInfo(state).get();
        assertEquals(4, info.value());
        assertEquals(1, info.depth());

        // test case 4: add 4 states and overwrite existing entry (edge case)
        TicTacToe state2 = state.applyMove(new Position(1, 1));
        TicTacToe state3 = state2.applyMove(new Position(1, 0));
        TicTacToe state4 = state.applyMove(new Position(0, 1));
        TicTacToe state5 = state.applyMove(new Position(2, 2));
        table.add(state2, 2, 100);
        table.add(state3, 20, 69);
        table.add(state4, 5, 5);
        table.add(state5, 12, 2);
        table.add(state, 5, 4);
        assertEquals(4, info.value());
        assertEquals(5, info.depth());
        assertEquals(5, table.size());

        // test case 5: add a state and check if hash table correctly resizes
        TicTacToe state6 = state5.applyMove(new Position(1, 2));
        table.add(state6, 10, 500);
        StateInfo info4 = table.getInfo(state6).get();

        assertEquals(10, info4.depth());
        assertEquals(500, info4.value());
        assertEquals(6, table.size());

        // test case 6: add (pente)
        TranspositionTable<PenteModel> table2 = new TranspositionTable<>();
        PenteModel pstate = new PenteModel();
        table2.add(pstate, 0, GameModel.WIN);
        StateInfo infop = table2.getInfo(pstate).get();
        assertEquals(0, infop.depth());
        assertEquals(GameModel.WIN, infop.value());
    }
}