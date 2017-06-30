package rs.ac.uns.ftn.parser;

import com.beust.jcommander.Parameter;
import rs.ac.uns.ftn.GameState;
import rs.ac.uns.ftn.handranking.Card;
import rs.ac.uns.ftn.parser.converters.ActionConverter;
import rs.ac.uns.ftn.parser.converters.CardConverter;
import rs.ac.uns.ftn.parser.converters.PositionConverter;
import rs.ac.uns.ftn.parser.converters.StateConverter;

import java.util.List;

/**
 * Created by Micko on 29-Jun-17.
 */

public class Parser {

    @Parameter(names = "-c", converter = CardConverter.class)
    private List<Card> cards;

    @Parameter(names = "-s", converter = StateConverter.class)
    private GameState.ROUND state;

    @Parameter(names = "-p", converter = PositionConverter.class)
    private GameState.PLAYER_POSITION position;

    @Parameter(names = "-a", converter = ActionConverter.class)
    private GameState.ACTIONS_BEFORE_PLAYER action;

    @Parameter(names = "-pot")
    private Integer pot;

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public GameState.ROUND getState() {
        return state;
    }

    public void setState(GameState.ROUND state) {
        this.state = state;
    }

    public GameState.PLAYER_POSITION getPosition() {
        return position;
    }

    public void setPosition(GameState.PLAYER_POSITION position) {
        this.position = position;
    }

    public GameState.ACTIONS_BEFORE_PLAYER getAction() {
        return action;
    }

    public void setAction(GameState.ACTIONS_BEFORE_PLAYER action) {
        this.action = action;
    }

    public Integer getPot() {
        return pot;
    }

    public void setPot(Integer pot) {
        this.pot = pot;
    }

    @Override
    public String toString() {
        return "parser.Parser{" +
                "cards='" + cards + '\'' +
                ", state='" + state + '\'' +
                ", position='" + position + '\'' +
                ", action='" + action + '\'' +
                ", pot=" + pot +
                '}';
    }
}