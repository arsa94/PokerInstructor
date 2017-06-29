package rs.ac.uns.ftn;

import com.beust.jcommander.JCommander;
import rs.ac.uns.ftn.parser.Parser;

/**
 * Created by Micko on 29-Jun-17.
 */
public class PokerInstructor {
    public static void main(String[] args){
        Parser parser = new Parser();
        JCommander.newBuilder()
                .addObject(parser)
                .build()
                .parse(args);

        System.out.println(parser);
    }
}
