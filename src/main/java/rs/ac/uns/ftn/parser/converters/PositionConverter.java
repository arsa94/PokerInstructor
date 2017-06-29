package rs.ac.uns.ftn.parser.converters;

import com.beust.jcommander.IStringConverter;
import rs.ac.uns.ftn.parser.model.Model;

/**
 * Created by Micko on 29-Jun-17.
 */
public class PositionConverter implements IStringConverter<Model.Position> {

    public Model.Position convert(String value) {
        Model.Position position = null;
        if(value.equalsIgnoreCase("e")){
            position = Model.Position.EARLY;
        }else if(value.equalsIgnoreCase("m")){
            position = Model.Position.MID;
        }else if(value.equalsIgnoreCase("l")){
            position = Model.Position.LATE;
        }else if(value.equalsIgnoreCase("b")){
            position = Model.Position.BLINDS;
        }

        return position;
    }
}

