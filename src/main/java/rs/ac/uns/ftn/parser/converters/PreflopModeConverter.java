package rs.ac.uns.ftn.parser.converters;

import com.beust.jcommander.IStringConverter;
import rs.ac.uns.ftn.PokerInstructor.PREFLOP_MODE;

/**
 * Created by Arsa on 11-jul-2017..
 */
public class PreflopModeConverter implements IStringConverter<PREFLOP_MODE> {

    public PREFLOP_MODE convert(String value) {
        PREFLOP_MODE mode = null;
        if(value.equalsIgnoreCase("safe")){
            mode = PREFLOP_MODE.SAFE;
        }else if(value.equalsIgnoreCase("tight")){
            mode = PREFLOP_MODE.TIGHT;
        }else if(value.equalsIgnoreCase("moderate")){
            mode = PREFLOP_MODE.MODERATE;
        }else if(value.equalsIgnoreCase("loose")){
            mode =PREFLOP_MODE.LOOSE;
        }

        return mode;
    }
}
