package a5.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import a5.util.PlayerRole;
import org.junit.jupiter.api.Test;

class PenteTest {
    @Test
    void testConstructor() {
        // TODO 1: write at least 1 test case
        Pente game = new Pente();
        assertEquals(game.capturedPairsNo(PlayerRole.FIRST_PLAYER), 0);
        assertEquals(game.capturedPairsNo(PlayerRole.SECOND_PLAYER), 0);
        assertEquals(game.countToWin(), 5);
        assertEquals(game.board().rowSize(), 8);
        assertEquals(game.board().colSize(), 8);

    }

    @Test
    void testCopyConstructor() {
        // test case 1: can a board state be copied to an equal state
        Pente game1 = new Pente();
        game1.makeMove(new Position(2, 2));
        Pente game2 = new Pente(game1);
        assertTrue(game1.stateEqual(game2));

        // TODO 2: write at least 3 test cases

        // test case 2: empty boards
        Pente game3 = new Pente();
        Pente game4 = new Pente();
        assertTrue(game3.stateEqual(game4));

        // test case 3: not equal boards, one empty
        game3.makeMove(new Position(6, 3));
        assertFalse(game3.stateEqual(game4));

        // test case 4: not equal, both not empty
        game4.makeMove(new Position(0,4));
        assertFalse(game3.stateEqual(game4));
    }

    @Test
    void testHashCode() {
        Pente game1 = new Pente();
        Pente game2 = new Pente();
        Pente game3 = new Pente();

        // test case 1: do two equal nonempty board states have the same hash code
        game1.makeMove(new Position(3, 3));
        game2.makeMove(new Position(3, 3));
        assertEquals(game1.hashCode(), game2.hashCode());

        // test case 2: non-equal board states should be very unlikely to have the
        // same hash code.
        game3.makeMove(new Position(0, 0));
        assertNotEquals(game1.hashCode(), game3.hashCode());

        // TODO 3: write at least 3 test cases
        // test case 3: empty boards
        Pente game4 = new Pente();
        Pente game5 = new Pente();
        assertTrue(game5.stateEqual(game4));

        // test case 4: not equal boards, one empty
        game4.makeMove(new Position(6, 3));
        assertFalse(game5.stateEqual(game4));

        // test case 5: not equal, both not empty
        game5.makeMove(new Position(0,4));
        assertFalse(game5.stateEqual(game4));

    }

    @Test
    void makeMove() {
        // test case 1: a simple move
        Pente game = new Pente();
        Position p = new Position(2, 2);
        game.makeMove(p); // a move by the first player
        assertEquals(game.currentPlayer(), PlayerRole.SECOND_PLAYER);
        assertEquals(game.currentTurn(), 2);
        assertFalse(game.hasEnded());
        assertEquals(game.capturedPairsNo(PlayerRole.FIRST_PLAYER), 0);
        assertEquals(game.capturedPairsNo(PlayerRole.SECOND_PLAYER), 0);
        assertEquals(game.board().get(p), PlayerRole.FIRST_PLAYER.boardValue());

        // test case 2: try a capture
        game.makeMove(new Position(2, 3)); // a move by the second player
        game.makeMove(new Position(3, 2)); // a move by the first player
        game.makeMove(new Position(2, 4)); // a move by the second player
        game.makeMove(new Position(2, 5)); // a move by the first player, which should capture the pair [(2, 3), (2, 4)]
        assertEquals(game.capturedPairsNo(PlayerRole.FIRST_PLAYER), 1);
        assertEquals(game.board().get(new Position(2, 3)), 0); // the stone should be removed
        assertEquals(game.board().get(new Position(2, 4)), 0); // the stone should be removed

        // TODO 4: write at least 3 test cases

        // test case 3: captures 2 pairs
        game.makeMove(new Position(2, 1)); //a move by the second player
        game.makeMove(new Position(2, 3)); //a move by the first player
        game.makeMove(new Position(2, 7)); //a move by the second player
        game.makeMove(new Position(2, 6)); //a move by the first player
        game.makeMove(new Position(2, 4)); //a move by the second player, which should capture two pairs: [(2, 2), (2, 3)] and [(2, 5), (2, 6)]
        assertEquals(game.capturedPairsNo(PlayerRole.SECOND_PLAYER), 2);
        assertEquals(game.board().get(new Position(2, 2)), 0); // the stone should be removed
        assertEquals(game.board().get(new Position(2, 3)), 0); // the stone should be removed
        assertEquals(game.board().get(new Position(2, 5)), 0); // the stone should be removed
        assertEquals(game.board().get(new Position(2, 6)), 0); // the stone should be removed

        // test case 4: edge of board
        game.makeMove(new Position(0, 0)); //a move by the first player
        game.makeMove(new Position(0, 7)); //a move by the second player
        game.makeMove(new Position(7, 0)); //a move by the first player
        game.makeMove(new Position(7, 7)); //a move by the second player
        assertNotEquals(game.board().get(new Position(0, 0)), 0); // the stone should be placed
        assertNotEquals(game.board().get(new Position(0, 7)), 0); // the stone should be placed
        assertNotEquals(game.board().get(new Position(7, 0)), 0); // the stone should be placed
        assertNotEquals(game.board().get(new Position(7, 7)), 0); // the stone should be placed

        // test case 5: col+2 is out of index
        Pente game2 = new Pente();
        game2.makeMove(new Position(0, 0)); //a move by the first player
        game2.makeMove(new Position(0, 6)); //a move by the second player
        game2.makeMove(new Position(0, 1)); //a move by the first player
        game2.makeMove(new Position(0, 2)); //a move by the second player
        assertEquals(game2.capturedPairsNo(PlayerRole.FIRST_PLAYER), 0);
        assertEquals(game2.capturedPairsNo(PlayerRole.SECOND_PLAYER), 0);

    }

    @Test
    void capturedPairsNo() {
        // test case 1: are captured pairs registered?
        Pente game = new Pente();
        game.makeMove(new Position(3, 2)); // a move by the first player
        game.makeMove(new Position(3, 3)); // a move by the second player
        game.makeMove(new Position(4, 2)); // a move by the first player
        game.makeMove(new Position(3, 4)); // a move by the second player
        game.makeMove(new Position(3, 5)); // a move by the first player, which should capture the pair [(2, 3), (2, 4)]
        assertEquals(game.capturedPairsNo(PlayerRole.FIRST_PLAYER), 1);

        // TODO 5: write at least 3 test cases

        //test 2: multiple captured pairs are registered
        game.makeMove(new Position(5, 6)); // a move by the second player
        game.makeMove(new Position(3, 3)); // a move by the first player
        game.makeMove(new Position(3, 1)); // a move by the second player
        game.makeMove(new Position(3, 6)); // a move by the first player
        game.makeMove(new Position(3, 7)); // a move by the second player
        game.makeMove(new Position(6, 1)); // a move by the first player
        game.makeMove(new Position(3, 4)); // a move by the second player
        assertEquals(game.capturedPairsNo(PlayerRole.SECOND_PLAYER), 2);

        //test 3: diagonal capture/player messes up
        Pente game2 = new Pente();
        game2.makeMove(new Position(1, 1)); // a move by the first player
        game2.makeMove(new Position(2, 2)); // a move by the second player
        game2.makeMove(new Position(4, 4)); // a move by the first player
        game2.makeMove(new Position(3, 3)); // a move by the second player, falling into player 1 trap
        assertEquals(game2.capturedPairsNo(PlayerRole.FIRST_PLAYER), 1);

    //test 4: num of captures go to correct player
        Pente game3 = new Pente();
        game3.makeMove(new Position(1, 1)); // a move by the first player
        game3.makeMove(new Position(2, 2)); // a move by the second player
        game3.makeMove(new Position(4, 4)); // a move by the first player
        game3.makeMove(new Position(3, 3)); // a move by the second player, falling into player 1 trap
        game3.makeMove(new Position(4, 3)); // a move by the first player
        game3.makeMove(new Position(4, 2)); // a move by the second player
        game3.makeMove(new Position(3, 3)); // a move by the first player
        game3.makeMove(new Position(4, 5)); // a move by the second player
        assertEquals(game3.capturedPairsNo(PlayerRole.SECOND_PLAYER), 1);
        assertEquals(game3.capturedPairsNo(PlayerRole.FIRST_PLAYER), 1);

    }

    @Test
    void hasEnded() {
        // test case 1: is a board with 5 in a row an ended game?
        Pente game = new Pente();
        assertFalse(game.hasEnded());
        game.makeMove(new Position(1, 1)); // a move by the first player
        game.makeMove(new Position(2, 1)); // a move by the second player
        game.makeMove(new Position(1, 2)); // a move by the first player
        game.makeMove(new Position(2, 2)); // a move by the second player
        game.makeMove(new Position(1, 3)); // a move by the first player
        game.makeMove(new Position(2, 3)); // a move by the second player
        game.makeMove(new Position(1, 4)); // a move by the first player
        game.makeMove(new Position(2, 4)); // a move by the second player
        game.makeMove(new Position(1, 5)); // a move by the first player
        assertTrue(game.hasEnded());

        // TODO 6: write at least 3 test cases

        //test case 2: when a player captures 10 of the other players pieces, does the game end?
        Pente game2 = new Pente();
        assertFalse(game2.hasEnded());
        game2.makeMove(new Position(1, 1)); // a move by the first player
        game2.makeMove(new Position(2, 1)); // a move by the second player
        game2.makeMove(new Position(1, 2)); // a move by the first player
        game2.makeMove(new Position(3, 1)); // a move by the second player
        game2.makeMove(new Position(4, 1)); // a move by the first player, capturing [(2, 1), (3, 1)]
        game2.makeMove(new Position(2, 3)); // a move by the second player
        game2.makeMove(new Position(1, 3)); // a move by the first player
        game2.makeMove(new Position(3, 4)); // a move by the second player
        game2.makeMove(new Position(4, 5)); // a move by the first player, capturing [(2, 3), (3, 4)]
        game2.makeMove(new Position(2, 2)); // a move by the second player
        game2.makeMove(new Position(4, 4)); // a move by the first player
        game2.makeMove(new Position(3, 2)); // a move by the second player
        game2.makeMove(new Position(4, 2)); // a move by the first player, capturing [(2, 2), (3, 2)]
        game2.makeMove(new Position(2, 2)); // a move by the second player
        game2.makeMove(new Position(2, 1)); // a move by the first player
        game2.makeMove(new Position(2, 3)); // a move by the second player
        game2.makeMove(new Position(2, 4)); // a move by the first player, capturing [(2, 2), (2, 3)]
        game2.makeMove(new Position(3, 5)); // a move by the second player
        game2.makeMove(new Position(2, 5)); // a move by the first player
        game2.makeMove(new Position(4, 6)); // a move by the second player
        game2.makeMove(new Position(5, 7)); // a move by the first player, capturing [(3, 5), (4, 6)]
        assertTrue(game2.hasEnded());

        //test case 3: if both 5 in a row and 10 pieces captured, does it end?
        Pente game3 = new Pente();
        assertFalse(game3.hasEnded());
        game3.makeMove(new Position(1, 1)); // a move by the first player
        game3.makeMove(new Position(2, 1)); // a move by the second player
        game3.makeMove(new Position(1, 2)); // a move by the first player
        game3.makeMove(new Position(3, 1)); // a move by the second player
        game3.makeMove(new Position(4, 1)); // a move by the first player, capturing [(2, 1), (3, 1)]
        game3.makeMove(new Position(2, 3)); // a move by the second player
        game3.makeMove(new Position(1, 3)); // a move by the first player
        game3.makeMove(new Position(3, 4)); // a move by the second player
        game3.makeMove(new Position(4, 5)); // a move by the first player, capturing [(2, 3), (3, 4)]
        game3.makeMove(new Position(2, 2)); // a move by the second player
        game3.makeMove(new Position(4, 4)); // a move by the first player
        game3.makeMove(new Position(3, 2)); // a move by the second player
        game3.makeMove(new Position(4, 2)); // a move by the first player, capturing [(2, 2), (3, 2)]
        game3.makeMove(new Position(2, 2)); // a move by the second player
        game3.makeMove(new Position(2, 1)); // a move by the first player
        game3.makeMove(new Position(2, 3)); // a move by the second player
        game3.makeMove(new Position(2, 4)); // a move by the first player, capturing [(2, 2), (2, 3)]
        game3.makeMove(new Position(2, 3)); // a move by the second player
        game3.makeMove(new Position(1, 4)); // a move by the first player
        game3.makeMove(new Position(3, 3)); // a move by the second player
        game3.makeMove(new Position(4, 3)); // a move by the first player, making 5 in a row [(4, 1) to (4, 5), and capturing [(2, 3), (3, 3)]
        assertTrue(game3.hasEnded());

        //test case 4: multiple captures from different players, assert it doesn't end after 10 total pieces captured on the board
        Pente game4 = new Pente();
        assertFalse(game4.hasEnded());
        game4.makeMove(new Position(2, 1)); // a move by the first player
        game4.makeMove(new Position(1, 1)); // a move by the second player
        game4.makeMove(new Position(3, 1)); // a move by the first player
        game4.makeMove(new Position(4, 1)); // a move by the second player, capturing [(2, 1), (3, 1)]
        game4.makeMove(new Position(1, 2)); // a move by the first player
        game4.makeMove(new Position(3, 1)); // a move by the second player
        game4.makeMove(new Position(2, 1)); // a move by the first player
        game4.makeMove(new Position(7, 7)); // a move by the second player
        game4.makeMove(new Position(5, 1)); // a move by the first player, capturing [(3, 1), (4, 1)]
        game4.makeMove(new Position(5, 2)); // a move by the second player
        game4.makeMove(new Position(1, 3)); // a move by the first player
        game4.makeMove(new Position(1, 4)); // a move by the second player, capturing [(1, 2), (1, 3)]
        game4.makeMove(new Position(3, 1)); // a move by the first player
        game4.makeMove(new Position(4, 1)); // a move by the second player, capturing [(2, 1), (3, 1)]
        game4.makeMove(new Position(3, 0)); // a move by the first player
        game4.makeMove(new Position(0, 0)); // a move by the second player
        game4.makeMove(new Position(6, 3)); // a move by the first player, capturing [(4, 1), (5, 2)]
        assertFalse(game4.hasEnded());

        // test case 5: identify a game that hasn't ended with pieces on the board and some captures
        Pente game5 = new Pente();
        game5.makeMove(new Position(1, 1)); // a move by the first player
        game5.makeMove(new Position(2, 1)); // a move by the second player
        game5.makeMove(new Position(1, 2)); // a move by the first player
        game5.makeMove(new Position(3, 1)); // a move by the second player
        game5.makeMove(new Position(4, 1)); // a move by the first player, capturing [(2, 1), (3, 1)]
        assertFalse(game5.hasEnded());

    }

    @Test
    void stateEqual() {
        Pente game1 = new Pente();
        Pente game2 = new Pente();
        Pente game3 = new Pente();

        // test 1: games with equal board states should be stateEqual()
        game1.makeMove(new Position(3, 3));
        game2.makeMove(new Position(3, 3));
        assertTrue(game1.stateEqual(game2));
        assertTrue(game2.stateEqual(game1));

        // test 2: games with unequal board states should not be stateEqual()
        game3.makeMove(new Position(0, 0));
        assertFalse(game1.stateEqual(game3));
        // TODO 7: write at least 3 test cases

        // test case 3: empty boards
        Pente game5 = new Pente();
        Pente game4 = new Pente();
        assertTrue(game5.stateEqual(game4));

        // test case 4: not equal boards, one empty
        game4.makeMove(new Position(6, 3));
        assertFalse(game5.stateEqual(game4));

        // test case 5: not equal, both not empty
        game5.makeMove(new Position(0,4));
        assertFalse(game5.stateEqual(game4));
    }
}