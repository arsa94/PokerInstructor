package rs.ac.uns.ftn.parser.converters;

import com.beust.jcommander.IStringConverter;
import rs.ac.uns.ftn.parser.model.Model;

/**
 * Created by Micko on 29-Jun-17.
 */
public class ActionConverter implements IStringConverter<Model.Action> {

    public Model.Action convert(String value) {
        Model.Action action = null;
        if(value.equalsIgnoreCase("af")){
            action = Model.Action.ALL_FOLD;
        }else if(value.equalsIgnoreCase("oc")){
            action = Model.Action.ONE_CALL;
        }else if(value.equalsIgnoreCase("or")){
            action = Model.Action.ONE_RAISE;
        }

        return action;
    }
}
