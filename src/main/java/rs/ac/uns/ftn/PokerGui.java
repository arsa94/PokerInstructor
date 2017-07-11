package rs.ac.uns.ftn;

/**
 * Created by Micko on 02-Jul-17.
 */

import rs.ac.uns.ftn.exceptions.UndefinedStateException;
import rs.ac.uns.ftn.handranking.util.HandRankingException;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PokerGui {
    private JFrame mainFrame;
    private JPanel firstRow;
    private JPanel secondRow;
    private JPanel thirdRow;
    private JComboBox cardRanksList1;
    private JComboBox cardSuitsList1;
    private JComboBox cardRanksList2;
    private JComboBox cardSuitsList2;
    private JComboBox cardRanksList3;
    private JComboBox cardSuitsList3;
    private JComboBox cardRanksList4;
    private JComboBox cardSuitsList4;
    private JComboBox cardRanksList5;
    private JComboBox cardSuitsList5;
    private JComboBox cardRanksList6;
    private JComboBox cardSuitsList6;
    private JComboBox cardRanksList7;
    private JComboBox cardSuitsList7;
    private JComboBox positionsList;
    private JComboBox actionsList;
    private JComboBox noOfPlayersList;
    private JComboBox preflopModeList;
    private JTextField potText;
    private JTextArea response;
    private PokerInstructor pokerInstructor;
    private JTextField betText;
    private JTextField toCallText;

    public PokerGui(){
        prepareGUI();
    }
    public static void main(String[] args){
        PokerGui swingControlDemo = new PokerGui();
        swingControlDemo.showEventDemo();
    }
    private void prepareGUI(){
        mainFrame = new JFrame("Poker Instructor");
        mainFrame.setSize(1250,300);
        mainFrame.setLayout(new GridLayout(3, 1));

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        firstRow = new JPanel();
        firstRow.setLayout(new FlowLayout());
        mainFrame.add(firstRow);

        secondRow = new JPanel();
        secondRow.setLayout(new FlowLayout());
        mainFrame.add(secondRow);

        thirdRow = new JPanel();
        thirdRow.setLayout(new FlowLayout());
        mainFrame.add(thirdRow);

        pokerInstructor = new PokerInstructor();

        mainFrame.setVisible(true);
    }
    private void showEventDemo(){


        String[] cardRanks = new String[] {"","A", "K", "Q", "J", "T", "9", "8", "7", "6", "5", "4", "3", "2"};

        Object[] cardSuits =
                {
                        new ImageIcon(new ImageIcon("src/main/resources/images/null.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)),
                        new ImageIcon(new ImageIcon("src/main/resources/images/hearts.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)),
                        new ImageIcon(new ImageIcon("src/main/resources/images/clubs.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)),
                        new ImageIcon(new ImageIcon("src/main/resources/images/diamonds.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)),
                        new ImageIcon(new ImageIcon("src/main/resources/images/spades.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT))
                };

        String[] preflopModes = new String[]{"safe", "tight", "moderate", "loose"};

        JLabel hand = new JLabel("Hand: ");
        cardRanksList1 = new JComboBox(cardRanks);
        cardRanksList1.setPreferredSize(new Dimension(50,30));
        cardSuitsList1 = new JComboBox( cardSuits);
        cardSuitsList1.setPreferredSize(new Dimension(50,30));

        firstRow.add(hand);
        firstRow.add(cardRanksList1);
        firstRow.add(cardSuitsList1);

        cardRanksList2 = new JComboBox(cardRanks);
        cardRanksList2.setPreferredSize(new Dimension(50,30));
        cardSuitsList2 = new JComboBox( cardSuits );
        cardSuitsList2.setPreferredSize(new Dimension(50,30));
        firstRow.add(cardRanksList2);
        firstRow.add(cardSuitsList2);

        JLabel flop = new JLabel("   Flop: ");
        cardRanksList3 = new JComboBox(cardRanks);
        cardRanksList3.setPreferredSize(new Dimension(50,30));
        cardSuitsList3 = new JComboBox( cardSuits );
        cardSuitsList3.setPreferredSize(new Dimension(50,30));

        firstRow.add(flop);
        firstRow.add(cardRanksList3);
        firstRow.add(cardSuitsList3);

        cardRanksList4 = new JComboBox(cardRanks);
        cardRanksList4.setPreferredSize(new Dimension(50,30));
        cardSuitsList4 = new JComboBox( cardSuits );
        cardSuitsList4.setPreferredSize(new Dimension(50,30));
        firstRow.add(cardRanksList4);
        firstRow.add(cardSuitsList4);

        cardRanksList5 = new JComboBox(cardRanks);
        cardRanksList5.setPreferredSize(new Dimension(50,30));
        cardSuitsList5 = new JComboBox( cardSuits );
        cardSuitsList5.setPreferredSize(new Dimension(50,30));
        firstRow.add(cardRanksList5);
        firstRow.add(cardSuitsList5);

        JLabel turn = new JLabel("   Turn: ");
        cardRanksList6 = new JComboBox(cardRanks);
        cardRanksList6.setPreferredSize(new Dimension(50,30));
        cardSuitsList6 = new JComboBox( cardSuits );
        cardSuitsList6.setPreferredSize(new Dimension(50,30));

        firstRow.add(turn);
        firstRow.add(cardRanksList6);
        firstRow.add(cardSuitsList6);

        JLabel river = new JLabel("   River: ");
        cardRanksList7 = new JComboBox(cardRanks);
        cardRanksList7.setPreferredSize(new Dimension(50,30));
        cardSuitsList7 = new JComboBox( cardSuits );
        cardSuitsList7.setPreferredSize(new Dimension(50,30));

        firstRow.add(river);
        firstRow.add(cardRanksList7);
        firstRow.add(cardSuitsList7);

        JLabel mode = new JLabel("Preflop strategy: ");
        preflopModeList = new JComboBox(preflopModes);
        preflopModeList.setPreferredSize(new Dimension(80,30));
        secondRow.add(mode);
        secondRow.add(preflopModeList);

        String[] positions = new String[] {"Early (1-2)", "Mid(3-5)", "Late(6-7)", "Blinds(8-9)"};
        JLabel position = new JLabel("Position: ");
        positionsList = new JComboBox(positions);

        secondRow.add(position);
        secondRow.add(positionsList);

        String[] actions = new String[] {"All fold", "One call", "One raise", "Reraise"};
        JLabel action = new JLabel("Action: ");
        actionsList = new JComboBox(actions);

        secondRow.add(action);
        secondRow.add(actionsList);

        String[] noOfPlayers = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        JLabel numOfPlayers = new JLabel("Num of Players: ");
        noOfPlayersList = new JComboBox(noOfPlayers);
        noOfPlayersList.setPreferredSize(new Dimension(50, 30));

        secondRow.add(numOfPlayers);
        secondRow.add(noOfPlayersList);

        JLabel pot = new JLabel("Pot: ");
        potText = new JTextField("10000",12);

        secondRow.add(pot);
        secondRow.add(potText);

        JLabel bet = new JLabel("Bet: ");
        betText = new JTextField("200",12);

        secondRow.add(bet);
        secondRow.add(betText);

        JLabel toCall = new JLabel("To Call: ");
        toCallText = new JTextField("1000",12);

        secondRow.add(toCall);
        secondRow.add(toCallText);

        response = new JTextArea(4,50);

        JButton okButton = new JButton("OK");
        JButton refreshButton = new JButton("Refresh");

        okButton.setActionCommand("OK");
        refreshButton.setActionCommand("Refresh");

        okButton.addActionListener(new ButtonClickListener());
        refreshButton.addActionListener(new ButtonClickListener());

        thirdRow.add(response);
        thirdRow.add(okButton);
        thirdRow.add(refreshButton);

        mainFrame.setVisible(true);
    }
    private class ButtonClickListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if( command.equals( "OK" ))  {
                try {
                    getResponse();
                } catch (UndefinedStateException e1) {
                    e1.printStackTrace();
                } catch (HandRankingException e1) {
                    e1.printStackTrace();
                }
            } else if( command.equals( "Refresh" ) )  {
                refreshGui();
            }
        }
    }

    private void refreshGui() {
        cardRanksList1.setSelectedIndex(0);
        cardSuitsList1.setSelectedIndex(0);
        cardRanksList2.setSelectedIndex(0);
        cardSuitsList2.setSelectedIndex(0);
        cardRanksList3.setSelectedIndex(0);
        cardSuitsList3.setSelectedIndex(0);
        cardRanksList4.setSelectedIndex(0);
        cardSuitsList4.setSelectedIndex(0);
        cardRanksList5.setSelectedIndex(0);
        cardSuitsList5.setSelectedIndex(0);
        cardRanksList6.setSelectedIndex(0);
        cardSuitsList6.setSelectedIndex(0);
        cardRanksList7.setSelectedIndex(0);
        cardSuitsList7.setSelectedIndex(0);
        positionsList.setSelectedIndex(0);
        actionsList.setSelectedIndex(0);
        noOfPlayersList.setSelectedIndex(0);
        response.setText("");
    }

    private String cardMaker(String card, int suit){
        String result = "";
        result += card.toLowerCase();
        switch(suit){
            case 1:
                result += "h";
                break;
            case 2:
                result += "c";
                break;
            case 3:
                result += "d";
                break;
            case 4:
                result += "s";
                break;
        }
        return result;
    }

    private String getPosition(int pos){
        switch (pos){
            case 0:
                return "e";
            case 1:
                return "m";
            case 2:
                return "l";
            case 3:
                return "b";
            default:
                return null;
        }
    }

    private String getAction(int pos){
        switch (pos){
            case 0:
                return "af";
            case 1:
                return "oc";
            case 2:
                return "or";
            case 3:
                return "rr";
            default:
                return null;
        }
    }

    private void getResponse() throws UndefinedStateException, HandRankingException {
        String card1 = (String)cardRanksList1.getSelectedItem();
        int suit1 = cardSuitsList1.getSelectedIndex();
        String card2 = (String)cardRanksList2.getSelectedItem();
        int suit2 = cardSuitsList2.getSelectedIndex();
        String card3 = (String)cardRanksList3.getSelectedItem();
        int suit3 = cardSuitsList3.getSelectedIndex();
        String card4 = (String)cardRanksList4.getSelectedItem();
        int suit4 = cardSuitsList4.getSelectedIndex();
        String card5 = (String)cardRanksList5.getSelectedItem();
        int suit5 = cardSuitsList5.getSelectedIndex();
        String card6 = (String)cardRanksList6.getSelectedItem();
        int suit6 = cardSuitsList6.getSelectedIndex();
        String card7 = (String)cardRanksList7.getSelectedItem();
        int suit7 = cardSuitsList7.getSelectedIndex();

        String pfMode = (String)preflopModeList.getSelectedItem();

        int pos = positionsList.getSelectedIndex();

        int act = actionsList.getSelectedIndex();

        int noOponents =  noOfPlayersList.getSelectedIndex()+1;

        String pot = potText.getText();
        String bet = betText.getText();
        String toCall = toCallText.getText();

        //-c ah,ks -s pf -p b -a af -pot 10000 -bet 100 to_call 100 -no 1
        String cards = "";
        String state = "pf";
        cards += cardMaker(card1, suit1)+","+cardMaker(card2, suit2);
        if(!card3.equals("") && !card4.equals("") && !card5.equals("")){
            state = "f";
            cards += ","+cardMaker(card3, suit3)+","+cardMaker(card4, suit4)+","+cardMaker(card5, suit5);
        }
        if(!card6.equals("")){
            state = "t";
            cards += ","+cardMaker(card6, suit6);
        }
        if(!card7.equals("")){
            state = "r";
            cards += ","+cardMaker(card7, suit7);
        }
        String[] cmds = {"-c", cards, "-s", state, "-pfm", pfMode, "-p", getPosition(pos), "-a", getAction(act), "-pot", pot, "-bet", bet, "-to_call", toCall, "-no", String.valueOf(noOponents)};

        for (String cmd : cmds){
            System.out.println(cmd);
        }
        String resp = pokerInstructor.getResponse(cmds);

        response.setText(resp);

    }
}