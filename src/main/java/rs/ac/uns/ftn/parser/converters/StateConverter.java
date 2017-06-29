package rs.ac.uns.ftn.parser.converters;

import com.beust.jcommander.IStringConverter;
import rs.ac.uns.ftn.parser.model.Model;

/**
 * Created by Micko on 29-Jun-17.
 */
public class StateConverter implements IStringConverter<Model.State> {

    public Model.State convert(String value) {
        Model.State state = null;
        if(value.equalsIgnoreCase("s")){
            state = Model.State.START;
        }else if(value.equalsIgnoreCase("f")){
            state = Model.State.FLOP;
        }else if(value.equalsIgnoreCase("t")){
            state = Model.State.TURN;
        }else if(value.equalsIgnoreCase("r")){
            state = Model.State.RIVER;
        }

        return state;
    }
}
