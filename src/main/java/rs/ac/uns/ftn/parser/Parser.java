package rs.ac.uns.ftn.parser;

import com.beust.jcommander.Parameter;
import rs.ac.uns.ftn.handranking.Card;
import rs.ac.uns.ftn.parser.converters.ActionConverter;
import rs.ac.uns.ftn.parser.converters.CardConverter;
import rs.ac.uns.ftn.parser.converters.PositionConverter;
import rs.ac.uns.ftn.parser.converters.StateConverter;
import rs.ac.uns.ftn.parser.model.Model;

import java.util.List;

/**
 * Created by Micko on 29-Jun-17.
 */

public class Parser {

    @Parameter(names = "-c", converter = CardConverter.class)
    private List<Card> cards;

    @Parameter(names = "-s", converter = StateConverter.class)
    private Model.State state;

    @Parameter(names = "-p", converter = PositionConverter.class)
    private Model.Position position;

    @Parameter(names = "-a", converter = ActionConverter.class)
    private Model.Action action;

    @Parameter(names = "-pot")
    private Integer pot;

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public Model.State getState() {
        return state;
    }

    public void setState(Model.State state) {
        this.state = state;
    }

    public Model.Position getPosition() {
        return position;
    }

    public void setPosition(Model.Position position) {
        this.position = position;
    }

    public Model.Action getAction() {
        return action;
    }

    public void setAction(Model.Action action) {
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
