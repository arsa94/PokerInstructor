package rs.ac.uns.ftn.parser.converters;

import com.beust.jcommander.IStringConverter;
import rs.ac.uns.ftn.handranking.Card;

/**
 * Created by Micko on 29-Jun-17.
 */
public class CardConverter implements IStringConverter<Card> {
    public Card convert(String value) {
        Card card = new Card();
        char rank = value.charAt(0);
        char suit = value.charAt(1);
        if (rank == 'a') {
            card.setRank(Card.RANK_ACE);
        }else if (rank == 'k') {
            card.setRank(Card.RANK_KING);
        }else if (rank == 'q') {
            card.setRank(Card.RANK_QUEEN);
        }else if (rank == 'j') {
            card.setRank(Card.RANK_JACK);
        }else if (rank == 't') {
            card.setRank(Card.RANK_10);
        }else if (rank == '9') {
            card.setRank(Card.RANK_9);
        }else if (rank == '8') {
            card.setRank(Card.RANK_8);
        }else if (rank == '7') {
            card.setRank(Card.RANK_7);
        }else if (rank == '6') {
            card.setRank(Card.RANK_6);
        }else if (rank == '5') {
            card.setRank(Card.RANK_5);
        }else if (rank == '4') {
            card.setRank(Card.RANK_4);
        }else if (rank == '3') {
            card.setRank(Card.RANK_3);
        }else if (rank == '2') {
            card.setRank(Card.RANK_2);
        }

        if (suit == 'c') {
            card.setSuit(Card.SUIT_CLUBS);
        }else if (suit == 'd') {
            card.setSuit(Card.SUIT_DIAMONDS);
        }else if (suit == 'h') {
            card.setSuit(Card.SUIT_HEARTS);
        }else if (suit == 's') {
            card.setSuit(Card.SUIT_SPADES);
        }

        return card;
    }
}
