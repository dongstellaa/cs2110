package a5.logic;

import a5.util.PlayerRole;
import a5.util.GameType;
import a5.util.GameResult;

import java.util.Objects;


/**
 * A Pente game, where players take turns to place stones on board.
 * When consecutive two stones are surrounded by the opponent's stones on two ends,
 * these two stones are removed (captured).
 * A player wins by placing 5 consecutive stones or capturing stones 5 times.
 */
public class Pente extends MNKGame {

    private int numCaptured1;
    private int numCaptured2;

    /**
     * Create an 8-by-8 Pente game.
     */
    public Pente() {
        super(8, 8, 5);
        // TODO 1
        numCaptured1 = 0;
        numCaptured2 = 0;
    }

    /**
     * Creates: a copy of the game state.
     */
    public Pente(Pente game) {
        super(game);
        // TODO 2
        numCaptured1 = game.numCaptured1;
        numCaptured2 = game.numCaptured2;
    }

    @Override
    public boolean makeMove(Position p) {
        // TODO 3
        if (!board().validPos(p)) return false;
        board().place(p, currentPlayer());
        boolean up = true;
        boolean down = true;
        boolean right = true;
        boolean left = true;

        //check edges
        if (p.row() + 3 > 7) down = false;
        if (p.row() - 3 < 0) up = false;
        if (p.col() + 3 > 7) right = false;
        if (p.col() - 3 < 0) left = false;

        //check hor
        if (right) {
            Position a = new Position(p.row(), p.col() + 1);
            Position b = new Position(p.row(), p.col() + 2);
            Position c = new Position(p.row(), p.col() + 3);

            if ((board().get(p) != board().get(a)) && (board().get(a) != 0) && (board().get(a) == board().get(b)) &&
                    (board().get(p) == board().get(c))) {
                board().erase(a);
                board().erase(b);
                if (currentPlayer().equals(PlayerRole.FIRST_PLAYER)) numCaptured1 += 2;
                if (currentPlayer().equals(PlayerRole.SECOND_PLAYER)) numCaptured2 += 2;
            }
            // trap LOL
            else if (p.col()-1>=0){
                Position d = new Position(p.row(), p.col()-1);
                if ((board().get(p)!=board().get(d)) && (board().get(p)==board().get(a)) &&
                        (board().get(d)==board().get(b)) && (board().get(d)!=0)){
                    board().erase(p);
                    board().erase(a);
                    if (currentPlayer().equals(PlayerRole.SECOND_PLAYER)) numCaptured1 += 2;
                    if (currentPlayer().equals(PlayerRole.FIRST_PLAYER)) numCaptured2 += 2;
                }
            }

        }


        if (left){
            Position a = new Position(p.row(), p.col() - 1);
            Position b = new Position(p.row(), p.col() - 2);
            Position c = new Position(p.row(), p.col() - 3);

            if ((board().get(p) != board().get(a)) && (board().get(a) != 0) && (board().get(a) == board().get(b)) &&
                    (board().get(p) == board().get(c))) {
                board().erase(a);
                board().erase(b);
                if (currentPlayer().equals(PlayerRole.FIRST_PLAYER)) numCaptured1 += 2;
                if (currentPlayer().equals(PlayerRole.SECOND_PLAYER)) numCaptured2 += 2;
            }
            // trap LOL
            else if (p.col()+1<=7){
                Position d = new Position(p.row(), p.col()+1);
                if ((board().get(p)!=board().get(d)) && (board().get(p)==board().get(a)) &&
                        (board().get(d)==board().get(b)) && (board().get(d)!=0)){
                    board().erase(p);
                    board().erase(a);
                    if (currentPlayer().equals(PlayerRole.SECOND_PLAYER)) numCaptured1 += 2;
                    if (currentPlayer().equals(PlayerRole.FIRST_PLAYER)) numCaptured2 += 2;
                }
            }

        }

        //check vert
        if (down){
            Position a = new Position(p.row()+1, p.col());
            Position b = new Position(p.row()+2, p.col());
            Position c = new Position(p.row()+3, p.col());

            if ((board().get(p) != board().get(a)) && (board().get(a) != 0) && (board().get(a) == board().get(b)) &&
                    (board().get(p) == board().get(c))) {
                board().erase(a);
                board().erase(b);
                if (currentPlayer().equals(PlayerRole.FIRST_PLAYER)) numCaptured1 += 2;
                if (currentPlayer().equals(PlayerRole.SECOND_PLAYER)) numCaptured2 += 2;
            }
            // trap LOL
            else if (p.row()-1>=0){
                Position d = new Position(p.row(), p.row()-1);
                if ((board().get(p)!=board().get(d)) && (board().get(p)==board().get(a)) &&
                        (board().get(d)==board().get(b)) && (board().get(d)!=0)){
                    board().erase(p);
                    board().erase(a);
                    if (currentPlayer().equals(PlayerRole.SECOND_PLAYER)) numCaptured1 += 2;
                    if (currentPlayer().equals(PlayerRole.FIRST_PLAYER)) numCaptured2 += 2;
                }
            }
        }

        if (up){
            Position a = new Position(p.row()-1, p.col());
            Position b = new Position(p.row()-2, p.col());
            Position c = new Position(p.row()-3, p.col());

            if ((board().get(p) != board().get(a)) && (board().get(a) != 0) && (board().get(a) == board().get(b)) &&
                    (board().get(p) == board().get(c))) {
                board().erase(a);
                board().erase(b);
                if (currentPlayer().equals(PlayerRole.FIRST_PLAYER)) numCaptured1 += 2;
                if (currentPlayer().equals(PlayerRole.SECOND_PLAYER)) numCaptured2 += 2;
            }
            // trap LOL
            else if (p.row()+1<=7){
                Position d = new Position(p.row()+1, p.col());
                if ((board().get(p)!=board().get(d)) && (board().get(p)==board().get(a)) &&
                        (board().get(d)==board().get(b)) && (board().get(d)!=0)){
                    board().erase(p);
                    board().erase(a);
                    if (currentPlayer().equals(PlayerRole.SECOND_PLAYER)) numCaptured1 += 2;
                    if (currentPlayer().equals(PlayerRole.FIRST_PLAYER)) numCaptured2 += 2;
                }
            }
        }

        //check diag
        if (up&&right){
            Position a = new Position(p.row()-1, p.col()+1);
            Position b = new Position(p.row()-2, p.col()+2);
            Position c = new Position(p.row()-3, p.col()+3);

            if ((board().get(p) != board().get(a)) && (board().get(a) != 0) && (board().get(a) == board().get(b)) &&
                    (board().get(p) == board().get(c))) {
                board().erase(a);
                board().erase(b);
                if (currentPlayer().equals(PlayerRole.FIRST_PLAYER)) numCaptured1 += 2;
                if (currentPlayer().equals(PlayerRole.SECOND_PLAYER)) numCaptured2 += 2;
            }
            // trap LOL
            else if ((p.row()+1<=7) && (p.col()-1>=0)) {
                Position d = new Position(p.row() + 1, p.col() - 1);
                if ((board().get(p) != board().get(d)) && (board().get(p) == board().get(a)) &&
                        (board().get(d) == board().get(b)) && (board().get(d) != 0)) {
                    board().erase(p);
                    board().erase(a);
                    if (currentPlayer().equals(PlayerRole.SECOND_PLAYER)) numCaptured1 += 2;
                    if (currentPlayer().equals(PlayerRole.FIRST_PLAYER)) numCaptured2 += 2;
                }
            }
        }

        if (down&&right){
            Position a = new Position(p.row()+1, p.col()+1);
            Position b = new Position(p.row()+2, p.col()+2);
            Position c = new Position(p.row()+3, p.col()+3);

            if ((board().get(p) != board().get(a)) && (board().get(a) != 0) && (board().get(a) == board().get(b)) &&
                    (board().get(p) == board().get(c))) {
                board().erase(a);
                board().erase(b);
                if (currentPlayer().equals(PlayerRole.FIRST_PLAYER)) numCaptured1 += 2;
                if (currentPlayer().equals(PlayerRole.SECOND_PLAYER)) numCaptured2 += 2;
            }
            // trap LOL
            else if ((p.row()-1>=0) && (p.col()-1>=0)){
                Position d = new Position(p.row()-1, p.col()-1);
                if ((board().get(p)!=board().get(d)) && (board().get(p)==board().get(a)) &&
                        (board().get(d)==board().get(b)) && (board().get(d)!=0)){
                    board().erase(p);
                    board().erase(a);
                    if (currentPlayer().equals(PlayerRole.SECOND_PLAYER)) numCaptured1 += 2;
                    if (currentPlayer().equals(PlayerRole.FIRST_PLAYER)) numCaptured2 += 2;
                }
            }
        }

        if (down&&left){
            Position a = new Position(p.row()+1, p.col()-1);
            Position b = new Position(p.row()+2, p.col()-2);
            Position c = new Position(p.row()+3, p.col()-3);

            if ((board().get(p) != board().get(a)) && (board().get(a) != 0) && (board().get(a) == board().get(b)) &&
                    (board().get(p) == board().get(c))) {
                board().erase(a);
                board().erase(b);
                if (currentPlayer().equals(PlayerRole.FIRST_PLAYER)) numCaptured1 += 2;
                if (currentPlayer().equals(PlayerRole.SECOND_PLAYER)) numCaptured2 += 2;
            }
            // trap LOL
            else if ((p.row()-1>=0) && (p.col()+1<=7)){
                Position d = new Position(p.row()-1, p.col()+1);
                if ((board().get(p)!=board().get(d)) && (board().get(p)==board().get(a)) &&
                        (board().get(d)==board().get(b)) && (board().get(d)!=0)){
                    board().erase(p);
                    board().erase(a);
                    if (currentPlayer().equals(PlayerRole.SECOND_PLAYER)) numCaptured1 += 2;
                    if (currentPlayer().equals(PlayerRole.FIRST_PLAYER)) numCaptured2 += 2;
                }
            }
        }

        if (up&&left){
            Position a = new Position(p.row()-1, p.col()-1);
            Position b = new Position(p.row()-2, p.col()-2);
            Position c = new Position(p.row()-3, p.col()-3);

            if ((board().get(p) != board().get(a)) && (board().get(a) != 0) && (board().get(a) == board().get(b)) &&
                    (board().get(p) == board().get(c))) {
                board().erase(a);
                board().erase(b);
                if (currentPlayer().equals(PlayerRole.FIRST_PLAYER)) numCaptured1 += 2;
                if (currentPlayer().equals(PlayerRole.SECOND_PLAYER)) numCaptured2 += 2;
            }
            // trap LOL
            else if ((p.row()+1<=7) && (p.col()+1<=7)){
                Position d = new Position(p.row()+1, p.col()+1);
                if ((board().get(p)!=board().get(d)) && (board().get(p)==board().get(a)) &&
                        (board().get(d)==board().get(b)) && (board().get(d)!=0)){
                    board().erase(p);
                    board().erase(a);
                    if (currentPlayer().equals(PlayerRole.SECOND_PLAYER)) numCaptured1 += 2;
                    if (currentPlayer().equals(PlayerRole.FIRST_PLAYER)) numCaptured2 += 2;
                }
            }
        }

        changePlayer();
        advanceTurn();
        return true;
    }

    /**
     * Returns: a new game state representing the state of the game after the current player takes a
     * move {@code p}.
     */
    public Pente applyMove(Position p) {
        Pente newGame = new Pente(this);
        newGame.makeMove(p);
        return newGame;
    }

    /**
     * Returns: the number of captured pairs by {@code playerRole}.
     */
    public int capturedPairsNo(PlayerRole playerRole) {
        // TODO 4
        if (playerRole.equals(PlayerRole.FIRST_PLAYER)) return numCaptured1/2;
        else return numCaptured2/2;
    }

    @Override
    public boolean hasEnded() {
        // TODO 5
        boolean captured = (numCaptured1 >= 10) || (numCaptured2 >=10);
        return super.hasEnded() || captured;
    }

    @Override
    public GameType gameType() {
        return GameType.PENTE;
    }


    @Override
    public String toString() {
        String board = super.toString();
        return board + System.lineSeparator() + "Captured pairs: " +
                "first: " + capturedPairsNo(PlayerRole.FIRST_PLAYER) + ", " +
                "second: " + capturedPairsNo(PlayerRole.SECOND_PLAYER);
    }

    @Override
    public boolean equals(Object o) {
        if (getClass() != o.getClass()) {
            return false;
        }
        Pente p = (Pente) o;
        return stateEqual(p);
    }

    /**
     * Returns: true if the two games have the same state.
     */
    protected boolean stateEqual(Pente p) {
        // TODO 6
        boolean captured = (numCaptured1 == p.numCaptured1) && (numCaptured2 == p.numCaptured2);
        return super.stateEqual(p) && captured;
    }

    @Override
    public int hashCode() {
        // TODO 7
        return Objects.hash(super.hashCode(), numCaptured1, numCaptured2);
    }
}
