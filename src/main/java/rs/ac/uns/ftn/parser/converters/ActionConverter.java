package rs.ac.uns.ftn.parser.converters;

import com.beust.jcommander.IStringConverter;
import rs.ac.uns.ftn.GameState;

/**
 * Created by Micko on 29-Jun-17.
 */
public class ActionConverter implements IStringConverter<GameState.ACTIONS_BEFORE_PLAYER> {

    public GameState.ACTIONS_BEFORE_PLAYER convert(String value) {
        GameState.ACTIONS_BEFORE_PLAYER action = null;
        if(value.equalsIgnoreCase("af")){
            action = GameState.ACTIONS_BEFORE_PLAYER.ALL_FOLD;
        }else if(value.equalsIgnoreCase("oc")){
            action = GameState.ACTIONS_BEFORE_PLAYER.ONE_CALL;
        }else if(value.equalsIgnoreCase("or")){
            action = GameState.ACTIONS_BEFORE_PLAYER.ONE_RAISE;
        }

        return action;
    }
}
