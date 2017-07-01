package rs.ac.uns.ftn;

import com.beust.jcommander.JCommander;
import org.drools.core.process.core.datatype.impl.type.UndefinedDataType;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import rs.ac.uns.ftn.handranking.Card;
import rs.ac.uns.ftn.handranking.util.HandRankingException;
import rs.ac.uns.ftn.parser.Parser;
import rs.ac.uns.ftn.exceptions.UndefinedStateException;

/**
 * Created by Micko on 29-Jun-17.
 */
public class PokerInstructor {
    public static void main(String[] args) throws HandRankingException, UndefinedStateException {
        Parser parser = new Parser();
        JCommander.newBuilder()
                .addObject(parser)
                .build()
                .parse(args);

        System.out.println(parser);

        Card firstCard = parser.getCards().get(0);
        Card secondCard = parser.getCards().get(1);

        GameState gameState = new GameState();
        gameState.setPlayerPosition(parser.getPosition());
        gameState.setActionsBeforePlayer(parser.getAction());
        //gameState.setFlop(flop);

        Player player = new Player(firstCard, secondCard, gameState);

        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.getKieClasspathContainer();
        KieSession kSession;
        switch(parser.getState()){
            case PREFLOP:   kSession = kc.newKieSession("preflop-rules");
                            break;
            case FLOP:      kSession = kc.newKieSession("flop-rules");
                            break;
            case TURN:      kSession = kc.newKieSession("turn-rules");
                            break;
            case RIVER:     kSession = kc.newKieSession("river-rules");
                            break;
            default:        throw new UndefinedStateException();
        }

        kSession.insert(player);

        System.out.println('\n');
        int firedRules = kSession.fireAllRules();
        if(firedRules == 0){
            System.out.println("Bad cards - Fold");
        }

        System.out.println(firedRules);

    }
}
