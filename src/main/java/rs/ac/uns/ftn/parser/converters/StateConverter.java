package rs.ac.uns.ftn.parser.converters;

import com.beust.jcommander.IStringConverter;
import rs.ac.uns.ftn.GameState;

/**
 * Created by Micko on 29-Jun-17.
 */
public class StateConverter implements IStringConverter<GameState.ROUND> {

    public GameState.ROUND convert(String value) {
        GameState.ROUND state = null;
        if(value.equalsIgnoreCase("pf")){
            state = GameState.ROUND.PREFLOP;
        }else if(value.equalsIgnoreCase("f")){
            state = GameState.ROUND.FLOP;
        }else if(value.equalsIgnoreCase("t")){
            state = GameState.ROUND.TURN;
        }else if(value.equalsIgnoreCase("r")){
            state = GameState.ROUND.RIVER;
        }

        return state;
    }
}
