package rs.ac.uns.ftn;

import com.beust.jcommander.JCommander;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import rs.ac.uns.ftn.handranking.Card;
import rs.ac.uns.ftn.handranking.util.HandRanker;
import rs.ac.uns.ftn.handranking.util.HandRankingException;
import rs.ac.uns.ftn.parser.Parser;
import rs.ac.uns.ftn.exceptions.UndefinedStateException;

import java.util.HashSet;
import java.util.List;

/**
 * Created by Micko on 29-Jun-17.
 */
public class PokerInstructor {
    public static void main(String[] args) throws HandRankingException, UndefinedStateException {

        PokerInstructor pi = new PokerInstructor();
        System.out.println(pi.getResponse(args));

//        boolean exit = false;
//
//        while(!exit){
//            Parser parser = new Parser();
//            Scanner keyboard = new Scanner(System.in);
//            System.out.print(">> ");
//            String cmd = keyboard.nextLine();
//            if(cmd.equals("q")){exit = true; continue;}
//            String[] cmds = cmd.split(" ");
//
//            //-c ah,3h,2h,ks,jc,7s,kc -s t -p b -a af -pot 10000 -no 1
//
//            JCommander.newBuilder()
//                    .addObject(parser)
//                    .build()
//                    .parse(cmds);
//
//            System.out.println(parser);
//
//            Card firstCard = parser.getCards().get(0);
//            Card secondCard = parser.getCards().get(1);
//
//            Deck deck = new Deck();
//            deck.removeCard(firstCard);
//            deck.removeCard(secondCard);
//
//
//            GameState gameState = new GameState();
//            gameState.setPlayerPosition(parser.getPosition());
//            gameState.setActionsBeforePlayer(parser.getAction());
//
//            int len = parser.getCards().size();
//
//            if(len >= 5){
//                HashSet<Card> flop = new HashSet<Card>();
//                flop.add(parser.getCards().get(2));
//                flop.add(parser.getCards().get(3));
//                flop.add(parser.getCards().get(4));
//                deck.removeCard(parser.getCards().get(2));
//                deck.removeCard(parser.getCards().get(3));
//                deck.removeCard(parser.getCards().get(4));
//                gameState.setFlop(flop);
//            }
//
//            if(len >= 6){
//                deck.removeCard(parser.getCards().get(5));
//                gameState.setTurn(parser.getCards().get(5));
//            }
//
//            if(len == 7){
//                deck.removeCard(parser.getCards().get(6));
//                gameState.setRiver(parser.getCards().get(6));
//            }
//
//            List<Card> remainingCards = deck.getCards();
//            List<List<Double>> weightArray = HandRanker.getUniformWeightArray();
//
//            Player player = new Player(firstCard, secondCard, gameState);
//            if(parser.getState() != GameState.ROUND.PREFLOP){
//                player.calculateHandPotential(weightArray, parser.getNumberOfOpponents(), true, remainingCards);
//            }
//
//            System.out.println("HandStrength: " + player.getHandStrength());
//            System.out.println("Positive Potential: " + player.getPositiveHandPotential());
//            System.out.println("Negative Potential: " + player.getNegativeHandPotential());
//            System.out.println("ECH: " + player.getEHS());
//
//            KieServices ks = KieServices.Factory.get();
//            KieContainer kc = ks.getKieClasspathContainer();
//            KieSession kSession;
//            switch(parser.getState()){
//                case PREFLOP:   kSession = kc.newKieSession("preflop-rules");
//                    break;
//                case FLOP:      kSession = kc.newKieSession("flop-rules");
//                    break;
//                case TURN:      kSession = kc.newKieSession("flop-rules");
//                    break;
//                case RIVER:     kSession = kc.newKieSession("flop-rules");
//                    break;
//                default:        throw new UndefinedStateException();
//            }
//
//            kSession.insert(player);
//
//            System.out.println('\n');
//            int firedRules = kSession.fireAllRules();
//            if(firedRules == 0){
//                System.out.println("Bad cards - Fold");
//            }
//
//            System.out.println(firedRules);
//        }
    }


    public String getResponse(String[] cmds) throws UndefinedStateException, HandRankingException {
        Parser parser = new Parser();

        JCommander.newBuilder()
                .addObject(parser)
                .build()
                .parse(cmds);

        System.out.println(parser);

        Card firstCard = parser.getCards().get(0);
        Card secondCard = parser.getCards().get(1);

        Deck deck = new Deck();
        deck.removeCard(firstCard);
        deck.removeCard(secondCard);


        GameState gameState = new GameState();
        gameState.setPlayerPosition(parser.getPosition());
        gameState.setActionsBeforePlayer(parser.getAction());
        gameState.setNrPlayers(parser.getNumberOfOpponents());

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

        Player player = new Player(firstCard, secondCard, gameState, parser.getBet(), parser.getToCall(), parser.getPot());

        if(parser.getState() == GameState.ROUND.PREFLOP){
            player.calculatePreflopStrategy(PreflopBettingStrategy.PREFLOP_SETTINGS.LOOSE);
        }else if(parser.getState() == GameState.ROUND.FLOP){//two card look-ahead on the flop - eddective odds true{
            player.calculateHandPotential(weightArray, parser.getNumberOfOpponents(), true, remainingCards);
        }else if(parser.getState() == GameState.ROUND.TURN){//one card look-ahead on the turn - eddective odds true
            player.calculateHandPotential(weightArray, parser.getNumberOfOpponents(), false, remainingCards);
        }else if(parser.getState() == GameState.ROUND.RIVER){// on river only calculate hand strength - we don't need potential because all cards are on table
            player.calculateHandStrength(weightArray, parser.getNumberOfOpponents(), remainingCards);
        }

        System.out.println("HandStrength: " + player.getHandStrength());
        System.out.println("Positive Potential: " + player.getPositiveHandPotential());
        System.out.println("Negative Potential: " + player.getNegativeHandPotential());
        System.out.println("EHS: " + player.getEHS());
        System.out.println("PositiveEHS: " + player.getPositiveEHS());

        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.getKieClasspathContainer();
        KieSession kSession;
        switch(parser.getState()){
            case PREFLOP:   kSession = kc.newKieSession("preflop-loki-rules");
                break;
            case FLOP:      kSession = kc.newKieSession("flop-rules");
                break;
            case TURN:      kSession = kc.newKieSession("turn-rules");
                break;
            case RIVER:     kSession = kc.newKieSession("river-rules");
                break;
            default:        throw new UndefinedStateException();
        }

        Logger logger = new Logger();
        kSession.insert(player);
        kSession.insert(logger);

        System.out.println('\n');
        int firedRules = kSession.fireAllRules();
        if(firedRules == 0){
            return "Bad cards - Fold";
        }

        logger = (Logger) kSession.getObjects().toArray()[1];

        System.out.println(logger.getText());
        System.out.println(firedRules);
        return logger.getText();
    }
}
