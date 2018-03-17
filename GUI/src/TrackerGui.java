import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class TrackerGui{

    JPanel coinDisplayPanel;
    JButton addButton;
    JButton testButton;
    JList coinList;
    JButton submitCoinButton;
    Map activeCoins = new HashMap();
    Map coinMap = new HashMap();


    public static void main(String[] args) {
        TrackerGui gui = new TrackerGui();
        gui.go();
    }

    public void go(){
        JFrame mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel leftPanel = new JPanel();
        coinDisplayPanel = new JPanel();

        addButton = new JButton("Add Coin");
        addButton.addActionListener(new AddButtonListener());
        leftPanel.add(addButton);

        testButton = new JButton("Display");
        testButton.addActionListener(new testButtonListener());
        leftPanel.add(testButton);

        mainFrame.getContentPane().add(BorderLayout.WEST, leftPanel);
        mainFrame.getContentPane().add(BorderLayout.EAST, coinDisplayPanel);

        leftPanel.setBackground(Color.darkGray);
        coinDisplayPanel.setBackground(Color.darkGray);

        mainFrame.setSize(350, 600);
        mainFrame.setVisible(true);

        coinMap.put("BITCOIN","BTC");
        coinMap.put("RIPPLE","XRP");
        coinMap.put("NANO","XRB");
        coinMap.put("ETHEREUM","ETH");
        coinMap.put("NEO","NEO");
        coinMap.put("STELLAR","XLM");
    }

    public void addCoinWindow(){

        String [] coinOptions = {"BITCOIN","RIPPLE", "NANO", "ETHEREUM", "NEO", "STELLAR"};
        coinList = new JList(coinOptions);

        submitCoinButton = new JButton("Add Coin");

        JFrame coinFrame = new JFrame();
        JPanel listPanel = new JPanel();
        JPanel buttonPanel = new JPanel();

        coinFrame.getContentPane().add(BorderLayout.NORTH, listPanel);
        coinFrame.getContentPane().add(BorderLayout.SOUTH, buttonPanel);

        JScrollPane scroller = new JScrollPane(coinList);
        listPanel.add(scroller);
        buttonPanel.add(submitCoinButton);
        submitCoinButton.addActionListener(new SubmitCoinListener());
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        coinList.setVisibleRowCount(4);
        coinList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        coinFrame.setSize(300, 200);
        coinFrame.setVisible(true);
    }


    class SubmitCoinListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            String coinName = (String) coinList.getSelectedValue();
            String coinTag = (String) coinMap.get(coinName);

            activeCoins.put(coinName, new Coin(coinName, coinTag));
            System.out.println(activeCoins);
        }
    }

    class testButtonListener implements ActionListener{
        @Override

        public void actionPerformed(ActionEvent e){

            for (Object key : activeCoins.keySet()) {
                String temp = (String) key;
                coinDisplayPanel.add(new JTextArea(temp));
            }
        }
    }

    class AddButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            addCoinWindow();
        }
    }
}
