package rs.ac.uns.ftn.parser.model;

/**
 * Created by Micko on 29-Jun-17.
 */

public class Model {
    public enum State {
        START, FLOP, TURN, RIVER
    }

    public enum Position {
        EARLY, MID, LATE, BLINDS
    }

    public enum Action {
        ALL_FOLD, ONE_CALL, ONE_RAISE
    }
}
