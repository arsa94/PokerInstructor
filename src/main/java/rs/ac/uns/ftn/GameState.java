/*
 * Copyright (C) 2016 Jellen Vermeir
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package rs.ac.uns.ftn;

import rs.ac.uns.ftn.handranking.Card;
import rs.ac.uns.ftn.handranking.util.HandRankingException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class represents the gameState model
 * THIS IS A STUB !!
 * @author Jellen Vermeir
 */
public class GameState {

    public enum ROUND {PREFLOP, FLOP, TURN, RIVER}
    public enum PLAYER_POSITION {EARLY, MID, LATE, BLINDS}
    public enum ACTIONS_BEFORE_PLAYER {ALL_FOLD, ONE_CALL, ONE_RAISE, RE_RAISE}
    
    private ArrayList<Player> players;
    private Deck remainingCards;
    private int nrPlayers;
    private ROUND currentRound;
    
    private Set<Card> flop;
    private Card turn;
    private Card river;

    private ACTIONS_BEFORE_PLAYER actionsBeforePlayer;
    private PLAYER_POSITION playerPosition;
    
    /**
     * STUB
     * Default constructor. Set fields to 0.
     */
    public GameState(){
        this.remainingCards = null;
        this.players = null;
        this.currentRound = ROUND.PREFLOP;
    }
    
    /**
     * STUB
     * @param nrPlayers
     * @throws HandRankingException 
     */
    public GameState(int nrPlayers) throws HandRankingException{
        
        this.remainingCards = new Deck();
        this.currentRound = ROUND.PREFLOP;
        
        this.players = new ArrayList<Player>();
        for(int i=0; i<nrPlayers; i++){
            Card card1 = remainingCards.drawRandomCard();
            Card card2 = remainingCards.drawRandomCard();
            this.players.add(new Player(card1, card2, this));
        }
    }
    
    /**
     * Return the amount of player in the game.
     * @return The amount of players.
     */
    public int getNrPlayers(){
        return this.nrPlayers;
    }
    
    /**
     * Return a List of active players.
     * @return The Players.
     */
    public List<Player> getPlayers(){
        return this.players;
    }
    
    /**
     * Return the current round of the game.
     * @return The current round.
     */
    public ROUND getCurrentRound(){
        return this.currentRound;
    }
    
    /**
     * Return a Set of Cards representing the flop.
     * @return A set of Cards or null if currentRound < ROUND_FLOP
     */
    public Set<Card> getFlop(){
        return flop;
    }
    
    /**
     * Set the flop
     * @param flop A set of Cards representing the flop.
     */
    public void setFlop(HashSet<Card> flop){
        this.flop = flop;
    }
    
    /**
     * Return the turn Card
     * @return The turn Card or null if currentRound < ROUND_TURN
     */
    public Card getTurn(){
        return turn;
    }
    
    /**
     * Set the turn card.
     * @param turn The turn Card.
     */
    public void setTurn(Card turn){
        this.turn = turn;
    }
    
    /**
     * Return the river card
     * @return The River card or null if currentRound < ROUND_RIVER
     */
    public Card getRiver(){
        return river;
    }
    
    /**
     * Set the river card
     * @param river The river Card
     */
    public void setRiver(Card river){
        this.river = river;
    }

    public ACTIONS_BEFORE_PLAYER getActionsBeforePlayer() {
        return actionsBeforePlayer;
    }

    public void setActionsBeforePlayer(ACTIONS_BEFORE_PLAYER actionsBeforePlayer) {
        this.actionsBeforePlayer = actionsBeforePlayer;
    }

    public PLAYER_POSITION getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(PLAYER_POSITION playerPosition) {
        this.playerPosition = playerPosition;
    }

    /**
     * Return the communitycards as a Set of Cards.
     * @return The communitycards or null if currentRoudn < ROUND_FLOP
     */
    public HashSet<Card> getCommunityCards(){
        if(flop == null)
            return null;
        
        HashSet<Card> communityCards = new HashSet<Card>(flop);
        if(turn != null)
            communityCards.add(turn);
        if(river != null)
            communityCards.add(river);
        
        return communityCards;
    }
}