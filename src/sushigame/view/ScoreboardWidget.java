package sushigame.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import sushigame.model.Belt;
import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;
import sushigame.model.Chef;
import sushigame.model.SushiGameModel;



public class ScoreboardWidget extends JPanel implements BeltObserver, ActionListener{

	private SushiGameModel game_model;
	private JLabel display;
	private String refresh;
	
	public ScoreboardWidget(SushiGameModel gm) {
		refresh = "balance";
		game_model = gm;
		game_model.getBelt().registerBeltObserver(this);
		
		JPanel optionPanel = new JPanel();
		JButton balance = new JButton("Sort by Balance (High to Low)");
		JButton weightSold = new JButton("Sort by Weight Sold (High to Low)");
		JButton weightSpoiled = new JButton("Sorth by Weight Spoiled (Low to High)");
		
		
		
		display = new JLabel();
		display.setVerticalAlignment(SwingConstants.TOP);
		setLayout(new BorderLayout());	
		add(display, BorderLayout.CENTER);
		display.setText(makeScoreboardHTML("balance"));
		
		balance.setActionCommand("Balance");
		balance.addActionListener(this);
		weightSold.setActionCommand("weightSold");
		weightSold.addActionListener(this);
		weightSpoiled.setActionCommand("weightSpoiled");
		weightSpoiled.addActionListener(this);

		optionPanel.add(balance);
		optionPanel.add(weightSold);
		optionPanel.add(weightSpoiled);
		
		optionPanel.setLayout(new GridLayout(0, 1));

		add(optionPanel, BorderLayout.NORTH);
	}

	private String makeScoreboardHTML(String s) {
		String sb_html = "<html>";
		sb_html += "<h1>Scoreboard</h1>";

		// Create an array of all chefs and sort by balance.
		Chef[] opponent_chefs= game_model.getOpponentChefs();
		Chef[] chefs = new Chef[opponent_chefs.length+1];
		chefs[0] = game_model.getPlayerChef();
		for (int i=1; i<chefs.length; i++) {
			chefs[i] = opponent_chefs[i-1];
		}
		Arrays.sort(chefs, new HighToLowBalanceComparator());
		Arrays.sort(chefs, new LowToHighWeightComparer());
		Arrays.sort(chefs, new HighToLowWeightComporator());

		
		if (s.equals("balance")) {
		
		for (Chef c : chefs) {
			sb_html += c.getName() + " ($" + Math.round(c.getBalance()*100.0)/100.0 + ") <br>";
		}
		}
		if (s.equals("weightSold")) {
		
		for (Chef c : chefs) {
			sb_html += c.getName() + " weight  (" + Math.round(c.getWeight()*100.0)/100.0 + ") <br>";
		}
		}
		if (s.equals("weightSpoiled")) {
		
		for (Chef c : chefs) {
			sb_html += c.getName() + " spoiled weight  (" + Math.round(c.getSpoilWeight()*100.0)/100.0 + ") <br>";
		}
		}
		return sb_html;
	}

	public void refresh() {
		display.setText(makeScoreboardHTML(refresh));		
	}
	
	@Override
	public void handleBeltEvent(BeltEvent e) {
		if (e.getType() == BeltEvent.EventType.ROTATE) {
			refresh();
		}		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Balance":
			display.setText(makeScoreboardHTML("balance"));
			refresh = "balance";
			break;
		case "weightSold":
			display.setText(makeScoreboardHTML("weightSold"));
			refresh = "weightSold";
			break;
		case "weightSpoiled":
			display.setText(makeScoreboardHTML("weightSpoiled"));
			refresh = "weightSpoiled";
			break;
		}
	}

}
