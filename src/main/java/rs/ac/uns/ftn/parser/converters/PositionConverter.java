package rs.ac.uns.ftn.parser.converters;

import com.beust.jcommander.IStringConverter;
import rs.ac.uns.ftn.GameState;

/**
 * Created by Micko on 29-Jun-17.
 */
public class PositionConverter implements IStringConverter<GameState.PLAYER_POSITION> {

    public GameState.PLAYER_POSITION convert(String value) {
        GameState.PLAYER_POSITION position = null;
        if(value.equalsIgnoreCase("e")){
            position = GameState.PLAYER_POSITION.EARLY;
        }else if(value.equalsIgnoreCase("m")){
            position = GameState.PLAYER_POSITION.MID;
        }else if(value.equalsIgnoreCase("l")){
            position = GameState.PLAYER_POSITION.TURN;
        }else if(value.equalsIgnoreCase("b")){
            position = GameState.PLAYER_POSITION.BLINDS;
        }

        return position;
    }
}

