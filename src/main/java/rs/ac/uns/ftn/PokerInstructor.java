package rs.ac.uns.ftn;

import com.beust.jcommander.JCommander;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import rs.ac.uns.ftn.handranking.Card;
import rs.ac.uns.ftn.handranking.util.HandRankingException;
import rs.ac.uns.ftn.parser.Parser;

/**
 * Created by Micko on 29-Jun-17.
 */
public class PokerInstructor {
    public static void main(String[] args) throws HandRankingException {
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
        KieSession kSession = kc.newKieSession("preflop-rules");


        kSession.insert(player);

        System.out.println('\n');
        int firedRules = kSession.fireAllRules();


        System.out.println(firedRules);

    }
}
