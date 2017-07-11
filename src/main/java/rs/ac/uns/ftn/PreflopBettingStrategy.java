package rs.ac.uns.ftn;

import rs.ac.uns.ftn.handranking.Card;
import rs.ac.uns.ftn.handranking.util.HandRanker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

/**
 * Created by Arsa on 11-jul-2017..
 */
public class PreflopBettingStrategy {

    public enum PREFLOP_SETTINGS {TIGHT, MODERATE, LOOSE}
    private enum GROUP {TWO, THREE_OR_FOUR, FIVE_OR_MORE}
    public enum STRATEGIES {MAKE_0, CALL_1, MAKE_1, CALL_2, MAKE_2, MAKE_4}

    private static final List<Pair<Integer, Integer>> Make1_TIGHT = asList(Pair.of(-50,50), Pair.of(-50,50), Pair.of(0, 70));
    private static final List<Pair<Integer, Integer>> Make1_MODERATE = asList(Pair.of(-50,50), Pair.of(50,25), Pair.of(0, 50));
    private static final List<Pair<Integer, Integer>> Make1_LOOSE = asList(Pair.of(-50,50), Pair.of(50,10), Pair.of(0, 30));
    private static final List<List<Pair<Integer, Integer>>> Make_1 = asList(Make1_TIGHT, Make1_MODERATE, Make1_LOOSE);
    private static final List<List<Pair<Integer, Integer>>> Call_1 = Make_1;

    private static final List<Pair<Integer, Integer>> Make2_TIGHT = asList(Pair.of(150,50), Pair.of(200,50), Pair.of(450, 50));
    private static final List<Pair<Integer, Integer>> Make2_MODERATE = asList(Pair.of(50,50), Pair.of(200,25), Pair.of(450, 25));
    private static final List<Pair<Integer, Integer>> Make2_LOOSE = asList(Pair.of(0,0), Pair.of(200,10), Pair.of(450, 10));
    private static final List<List<Pair<Integer, Integer>>> Make_2 = asList(Make2_TIGHT, Make2_MODERATE, Make2_LOOSE);
    private static final List<List<Pair<Integer, Integer>>> Call_2 = Make_2;

    private static final List<Pair<Integer, Integer>> Make4_TIGHT = asList(Pair.of(300, 0), Pair.of(580,0), Pair.of(900, 0));
    private static final List<Pair<Integer, Integer>> Make4_MODERATE = asList(Pair.of(300, 0), Pair.of(580,0), Pair.of(900, 0));
    private static final List<Pair<Integer, Integer>> Make4_LOOSE = asList(Pair.of(300, 0), Pair.of(580,0), Pair.of(900, 0));
    private static final List<List<Pair<Integer, Integer>>> Make_4 = asList(Make4_TIGHT, Make4_MODERATE, Make4_LOOSE);

    private static float getThreshForStrategy(GROUP group, PREFLOP_SETTINGS settings, GameState.PLAYER_POSITION position, int numberOfPlayers, List<List<Pair<Integer, Integer>>> toMap){
        Pair<Integer, Integer> baseAndIncrement = toMap.get(settings.ordinal()).get(group.ordinal());
        float base = baseAndIncrement.getL();
        float increment = baseAndIncrement.getR();
        int playerPositionOrder=2;
        int playersToSmallBlindsTurn;
        if(position == GameState.PLAYER_POSITION.BLINDS){
            playersToSmallBlindsTurn = numberOfPlayers-1;
        }else{
            if(position == GameState.PLAYER_POSITION.EARLY){
                playerPositionOrder = 1;
            }else if (position == GameState.PLAYER_POSITION.MID){
                playerPositionOrder = 2;
            }else if (position == GameState.PLAYER_POSITION.LATE){
                playerPositionOrder = 3;
            }
            playersToSmallBlindsTurn = numberOfPlayers - playerPositionOrder;
        }
        float thresh = base + increment * playersToSmallBlindsTurn;

        return thresh;
    }

    private static Map<STRATEGIES, Float> setThresholds(GROUP group, PREFLOP_SETTINGS settings, GameState.PLAYER_POSITION position, int numberOfPlayers){
        Map<STRATEGIES, Float> thresh = new HashMap<STRATEGIES, Float>();
        thresh.put(STRATEGIES.MAKE_1, getThreshForStrategy(group, settings, position, numberOfPlayers, Make_1));
        thresh.put(STRATEGIES.CALL_1, getThreshForStrategy(group, settings, position, numberOfPlayers, Call_1));
        thresh.put(STRATEGIES.MAKE_2, getThreshForStrategy(group, settings, position, numberOfPlayers, Make_2));
        thresh.put(STRATEGIES.CALL_2, getThreshForStrategy(group, settings, position, numberOfPlayers, Call_2));
        thresh.put(STRATEGIES.MAKE_4, getThreshForStrategy(group, settings, position, numberOfPlayers, Make_4));

        return thresh;
    }

    /* Called the first time we are asked for an action in the pre-flop,
     * the selected strategy dictates the current and subsequent actions */
    public static STRATEGIES getPreflopStrategy(Card c1, Card c2, PREFLOP_SETTINGS settings, GameState.PLAYER_POSITION position, int numberOfPlayers){

        GROUP group;
        float IR;
        Map<STRATEGIES, Float> thresh = new HashMap<STRATEGIES, Float>();

//        if(numberOfPlayers >= 4) // 5 or more players
//            group = GROUP.FIVE_OR_MORE;
//        else if(numberOfPlayers >= 2) // 3-4 players
//            group = GROUP.THREE_OR_FOUR;
//        else
//            group = GROUP.TWO;

        group = GROUP.THREE_OR_FOUR;

        if(group == GROUP.TWO){
            IR = HandRanker.Map_169(c1, c2, HandRanker.IR2);
        }else if(group == GROUP.THREE_OR_FOUR){
            IR = HandRanker.Map_169(c1, c2, HandRanker.IR4);
        }else{
            IR = HandRanker.Map_169(c1, c2, HandRanker.IR7);
        }

        thresh = setThresholds(group, settings, position, numberOfPlayers);

        STRATEGIES strategy;
        if (IR>=thresh.get(STRATEGIES.MAKE_4))
            strategy = STRATEGIES.MAKE_4;
        else if (IR>=thresh.get(STRATEGIES.MAKE_2))
            strategy = STRATEGIES.MAKE_2;
        else if (IR>=thresh.get(STRATEGIES.CALL_2))
            strategy = STRATEGIES.CALL_2;
        else if (IR>=thresh.get(STRATEGIES.MAKE_1))
            strategy = STRATEGIES.MAKE_1;
        else if (IR>=thresh.get(STRATEGIES.CALL_1))
            strategy = STRATEGIES.CALL_1;
        else
            strategy = STRATEGIES.MAKE_0;

        return strategy;
    }

}
