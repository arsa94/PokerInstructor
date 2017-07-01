package rs.ac.uns.ftn;

import com.beust.jcommander.JCommander;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import rs.ac.uns.ftn.handranking.Card;
import rs.ac.uns.ftn.handranking.util.HandRanker;
import rs.ac.uns.ftn.handranking.util.HandRankingException;
import rs.ac.uns.ftn.parser.Parser;

import java.util.HashSet;
import java.util.List;

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

        Deck deck = new Deck();
        deck.removeCard(firstCard);
        deck.removeCard(secondCard);


        GameState gameState = new GameState();
        gameState.setPlayerPosition(parser.getPosition());
        gameState.setActionsBeforePlayer(parser.getAction());

        int len = parser.getCards().size();

        if(len >= 5){
            HashSet<Card> flop = new HashSet<Card>();
            flop.add(parser.getCards().get(2));
            flop.add(parser.getCards().get(3));
            flop.add(parser.getCards().get(4));
            deck.removeCard(parser.getCards().get(2));
            deck.removeCard(parser.getCards().get(3));
            deck.removeCard(parser.getCards().get(4));
            gameState.setFlop(flop);
        }

        if(len >= 6){
            deck.removeCard(parser.getCards().get(5));
            gameState.setTurn(parser.getCards().get(5));
        }

        if(len == 7){
            deck.removeCard(parser.getCards().get(6));
            gameState.setRiver(parser.getCards().get(6));
        }

        List<Card> remainingCards = deck.getCards();
        List<List<Double>> weightArray = HandRanker.getUniformWeightArray();

        Player player = new Player(firstCard, secondCard, gameState);
        player.calculateHandPotential(weightArray, parser.getNumberOfOpponents(), true, remainingCards);

        System.out.println("HandStrength: " + player.getHandStrength());
        System.out.println("Positive Potential: " + player.getPositiveHandPotential());
        System.out.println("Negative Potential: " + player.getNegativeHandPotential());
        System.out.println("ECH: " + player.getECH());

        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.getKieClasspathContainer();
        KieSession kSession = null;
        if(parser.getState() == GameState.ROUND.PREFLOP){
            kSession = kc.newKieSession("preflop-rules");
        }else if(parser.getState() == GameState.ROUND.FLOP){
            kSession = kc.newKieSession("flop-rules");
        }else if(parser.getState() == GameState.ROUND.TURN){
            kSession = kc.newKieSession("flop-rules");
        }else if(parser.getState() == GameState.ROUND.RIVER){
            kSession = kc.newKieSession("flop-rules");
        }


        System.out.println(player);

        kSession.insert(player);

        System.out.println('\n');
        int firedRules = kSession.fireAllRules();


        System.out.println(firedRules);

    }
}
