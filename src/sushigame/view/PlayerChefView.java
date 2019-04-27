package sushigame.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import comp401sushi.AvocadoPortion;
import comp401sushi.CrabPortion;
import comp401sushi.EelPortion;
import comp401sushi.IngredientPortion;
import comp401sushi.Nigiri;
import comp401sushi.Plate;
import comp401sushi.Plate.Color;
import comp401sushi.RedPlate;
import comp401sushi.Roll;
import comp401sushi.Sashimi;
import comp401sushi.Sashimi.SashimiType;
import comp401sushi.SeaweedPortion;
import comp401sushi.ShrimpPortion;
import comp401sushi.Sushi;
import comp401sushi.TunaPortion;
import comp401sushi.YellowtailPortion;
import comp401sushi.Nigiri.NigiriType;

public class PlayerChefView extends JPanel implements ActionListener {

	private List<ChefViewListener> listeners;
	private Sushi kmp_roll;
	private Sushi crab_sashimi;
	private Sushi eel_nigiri;
	private int belt_size;
	
	//SushiType Checker
	private Boolean isSash;
	private Boolean isNigiri;
	private Boolean isRoll;
	
	//ButtonPanel
	private JPanel platePanel;
	private JPanel meatPanel;
	private JPanel ingredientPanel;
	
	//Buttons
	private JButton red_button;
	private JButton green_button;
	private JButton blue_button;
	private JButton gold_button;	
	
	//Sliders + JLabel
	private JSlider price_slider;
	private JLabel priceName;
	private JLabel ingredientSliderLabel;
	private JSlider ingredientSlider;
	
	private JLabel ingredientAmtLabel;
	private JLabel avacadoLabel;
	private JSlider avacadoSlider;
	private double aValue;
	private JLabel crabLabel;
	private JSlider crabSlider;
	private double cValue;
	private JLabel eelLabel;
	private JSlider eelSlider;
	private double eValue;
	private JLabel shrimpLabel;
	private JSlider shrimpSlider;
	private double shValue;
	private JLabel tunaLabel;
	private JSlider tunaSlider;
	private double tValue;
	private JLabel yellowLabel;
	private JSlider yellowSlider;
	private double yValue;
	
	//Meats
	private JButton tuna_button;
	private JButton crab_button;
	private JButton eel_button;
	private JButton shrimp_button;
	private JButton yellow_button;
	
	//Position Slider and Label
	private JLabel positionLabel;
	private JSlider positionSlider;

	//Finish and Next
	private JButton next_button;
	private JButton finish_button;
	
	//Object Creator
	private String whatSush;
	private Color color;
	
	private double goldPrice;
	private int gSliderValue;
	//For Roll, include all the ingredient slider values
	private String meatValue;
	private int positionValue;
	
	//Restart
	private JPanel restart;
	
	public PlayerChefView(int belt_size) {
		positionValue = 0;
		goldPrice = 0;
		gSliderValue = 0;
		
		
		
		this.belt_size = belt_size;
		listeners = new ArrayList<ChefViewListener>();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//SushiType Checker
		isSash = false;
		isNigiri = false;
		isRoll = false;

		//Initial Sushi Creation
		JButton sashimi_button = new JButton("Make Sashimi");
		sashimi_button.setActionCommand("Sashimi");
		sashimi_button.addActionListener(this);
		add(sashimi_button);
		
		JButton nigiri_button = new JButton("Make Nigiri");
		nigiri_button.setActionCommand("Nigiri");
		nigiri_button.addActionListener(this);
		add(nigiri_button);

		JButton roll_button = new JButton("Make Roll");
		roll_button.setActionCommand("Roll");
		roll_button.addActionListener(this);
		add(roll_button); 
		
		restart = new JPanel();
		restart.add(sashimi_button);
		restart.add(nigiri_button);
		restart.add(roll_button);
		
		add(restart);
		
		//Plate Creation
		red_button = new JButton("Red Plate");
		red_button.setActionCommand("RedPlate");
		red_button.addActionListener(this);
		
		green_button = new JButton("Green Plate");
		green_button.setActionCommand("GreenPlate");
		green_button.addActionListener(this);
				
		blue_button = new JButton("Blue Plate");
		blue_button.setActionCommand("BluePlate");
		blue_button.addActionListener(this);

		gold_button = new JButton("Gold Plate");
		gold_button.setActionCommand("GoldPlate");
		gold_button.addActionListener(this);

		//Panel Creation
		platePanel = new JPanel();
		platePanel.add(red_button);
		platePanel.add(green_button);
		platePanel.add(blue_button);
		platePanel.add(gold_button);

		//Sushi Finish and Next
		finish_button = new JButton("~ABRACADABRA~");
		finish_button.setActionCommand("finish");
		finish_button.addActionListener(this);
		
		next_button = new JButton("NEXT");
		next_button.setActionCommand("next");
		next_button.addActionListener(this);
		
		//Meat Creation
		tuna_button = new JButton("tuna");
		tuna_button.setActionCommand("tunaMeat");
		tuna_button.addActionListener(this);
		
		eel_button = new JButton("eel");
		eel_button.setActionCommand("eelMeat");
		eel_button.addActionListener(this);
		
		crab_button = new JButton("crab");
		crab_button.setActionCommand("crabMeat");
		crab_button.addActionListener(this);
		
		shrimp_button = new JButton("shrimp");
		shrimp_button.setActionCommand("shrimpMeat");
		shrimp_button.addActionListener(this);
		
		yellow_button = new JButton("yellowtail");
		yellow_button.setActionCommand("yellowMeat");
		yellow_button.addActionListener(this);
		
		//Ingredient Slider
		 avacadoLabel = new JLabel("Select avacado amount");
		 avacadoSlider = new JSlider(0, 150,0);
			avacadoSlider.setMinorTickSpacing(25);
			avacadoSlider.setMajorTickSpacing(50);
			avacadoSlider.setPaintTicks(true);
			avacadoSlider.setPaintLabels(true);
		 crabLabel = new JLabel("Select crab amount");
		 crabSlider = new JSlider(0,150,0);
		 	crabSlider.setMinorTickSpacing(25);
			crabSlider.setMajorTickSpacing(50);
			crabSlider.setPaintTicks(true);
			crabSlider.setPaintLabels(true);
		 eelLabel = new JLabel("Select eel amount");
		 eelSlider = new JSlider (0,150,0);
		 	eelSlider.setMinorTickSpacing(25);
			eelSlider.setMajorTickSpacing(50);
			eelSlider.setPaintTicks(true);
			eelSlider.setPaintLabels(true);
		 shrimpLabel = new JLabel("Select shrimp amount");
		 shrimpSlider = new JSlider(0,150,0);
		 	shrimpSlider.setMinorTickSpacing(25);
			shrimpSlider.setMajorTickSpacing(50);
			shrimpSlider.setPaintTicks(true);
			shrimpSlider.setPaintLabels(true);
		 tunaLabel = new JLabel("Select tuna amount");
		 tunaSlider = new JSlider(0,150,0);
		 	tunaSlider.setMinorTickSpacing(25);
			tunaSlider.setMajorTickSpacing(50);
			tunaSlider.setPaintTicks(true);
			tunaSlider.setPaintLabels(true);
		 yellowLabel = new JLabel("Select yellowtail amount");
		 yellowSlider = new JSlider(0,150,0);
		 	yellowSlider.setMinorTickSpacing(25);
			yellowSlider.setMajorTickSpacing(50);
			yellowSlider.setPaintTicks(true);
			yellowSlider.setPaintLabels(true);
			
		ingredientAmtLabel = new JLabel ("CHOOSE A VALUE BETWEEN 0.0 -15.0");
		
		ingredientPanel = new JPanel();
		ingredientPanel.add(ingredientAmtLabel);
		ingredientPanel.add(avacadoLabel);
		ingredientPanel.add(avacadoSlider);
		ingredientPanel.add(crabLabel);
		ingredientPanel.add(crabSlider);
		ingredientPanel.add(eelLabel);
		ingredientPanel.add(eelSlider);
		ingredientPanel.add(shrimpLabel);
		ingredientPanel.add(shrimpSlider);
		ingredientPanel.add(tunaLabel);
		ingredientPanel.add(tunaSlider);
		ingredientPanel.add(yellowLabel);
		ingredientPanel.add(yellowSlider);
		
		ingredientPanel.setLayout(new GridLayout(0, 1));
		
		//Meat Panel
		meatPanel = new JPanel();
		meatPanel.add(tuna_button);
		meatPanel.add(eel_button);
		meatPanel.add(shrimp_button);
		meatPanel.add(crab_button);
		meatPanel.add(yellow_button);
		
		//Gold Slider
		priceName= new JLabel("-SELECT A PRICE- 5.00 - 10.00");
		price_slider = new JSlider(500, 1000, 500);
		gSliderValue = 0;
		
		//Ingredients Slider
		ingredientSliderLabel = new JLabel("-SELECT INGREDIENT AMOUNT- 0.0- 1.5 Ounces");
		ingredientSlider = new JSlider(0, 150, 0);
		
		//Plate Position
		positionLabel = new JLabel ("Place on belt");
		positionSlider = new JSlider(0,20);
		positionSlider.setMinorTickSpacing(1);
		positionSlider.setMajorTickSpacing(5);
		positionSlider.setPaintTicks(true);
		positionSlider.setPaintLabels(true);
		
		kmp_roll = new Roll("KMP Roll", new IngredientPortion[] {new EelPortion(1.0), new AvocadoPortion(0.5), new SeaweedPortion(0.2)});
		crab_sashimi = new Sashimi(Sashimi.SashimiType.CRAB);
		eel_nigiri = new Nigiri(Nigiri.NigiriType.EEL);		
		
	}

	public void registerChefListener(ChefViewListener cl) {
		listeners.add(cl);
	}

	private void makeRedPlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleRedPlateRequest(plate_sushi, plate_position);
		}
	}

	private void makeGreenPlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleGreenPlateRequest(plate_sushi, plate_position);
		}
	}

	private void makeBluePlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleBluePlateRequest(plate_sushi, plate_position);
		}
	}
	
	private void makeGoldPlateRequest(Sushi plate_sushi, int plate_position, double price) {
		for (ChefViewListener l : listeners) {
			l.handleGoldPlateRequest(plate_sushi, plate_position, price);
		}
	}

	public void actionPerformed(ActionEvent e) {
		
		switch (e.getActionCommand()) {
		
		case "Sashimi":
			whatSush = "Sashimi";
			this.removeAll();
			this.repaint();
			
			add(platePanel);
			break;
		case "Nigiri":
			whatSush = "Nigiri";
			this.removeAll();
			this.repaint();
			add(platePanel);
			break;
		case "Roll":
			whatSush = "Roll";
			isRoll = true;
			this.removeAll();
			this.repaint();
			add(platePanel);
			break;
		
		case "RedPlate":
			this.removeAll();
			this.repaint();
			color = Color.RED;
			if (!isRoll) {add(meatPanel);}
			if(isRoll) {
				add(ingredientPanel);
				add(next_button);
			}
			break;
		case "GreenPlate":
			this.removeAll();
			this.repaint();
			color = Color.GREEN;
			if(!isRoll) {add(meatPanel);}
			if(isRoll) {
				add(ingredientPanel);
				add(next_button);
			}
			break;
		case "BluePlate":
			this.removeAll();
			this.repaint();
			color = Color.BLUE;
			if(!isRoll) {add(meatPanel);}
			if(isRoll) {
				add(ingredientPanel);
				add(next_button);
			}
			break;
		case "GoldPlate":
			this.removeAll();
			this.repaint();
			color = Color.GOLD;
			add(priceName);
			add(price_slider);
			price_slider.setMinorTickSpacing(50);
			price_slider.setMajorTickSpacing(100);
			price_slider.setPaintTicks(true);
			price_slider.setPaintLabels(true);


			if(isRoll) {
				add(ingredientPanel);
				gSliderValue = price_slider.getValue();
				goldPrice = gSliderValue/100;
				add(next_button);
			}
			if(!isRoll) {
				add(meatPanel);
				gSliderValue = price_slider.getValue();
				goldPrice = gSliderValue/100;
			}
			
			break;
			
		case "tunaMeat":
			this.removeAll();
			this.repaint();
			meatValue = "tuna";
			add(positionLabel);
			add(positionSlider);
			add(finish_button);
			break;
			
		case "crabMeat":
			this.removeAll();
			this.repaint();
			meatValue = "crab";
			add(positionLabel);
			add(positionSlider);
			add(finish_button);
			break;
			
		case "shrimpMeat":
			this.removeAll();
			this.repaint();
			meatValue = "shrimp";
			add(positionLabel);
			add(positionSlider);
			add(finish_button);
			break;
			
		case "eelMeat":
			this.removeAll();
			this.repaint();
			meatValue = "eel";
			add(positionLabel);
			add(positionSlider);
			add(finish_button);
			break;
			
		case "yellowMeat":
			this.removeAll();
			this.repaint();
			meatValue = "yellow";
			add(positionLabel);
			add(positionSlider);
			add(finish_button);
			break;
			
		case "next":
			this.removeAll();
			this.repaint();
			add(positionLabel);
			add(positionSlider);
			add(finish_button);
			break;
			
		case "finish":
			positionValue = positionSlider.getValue();
			aValue = (double)avacadoSlider.getValue()/100;
			cValue = (double)crabSlider.getValue()/100;
			eValue = (double)eelSlider.getValue()/100;
			shValue = (double)shrimpSlider.getValue()/100;
			tValue = (double)tunaSlider.getValue()/100;
			yValue = (double)yellowSlider.getValue()/100;
			this.removeAll();
			this.repaint();
			add(restart);
			sushiCreator();
			break;
			}
		
	validate();
	}
	
	public void sushiCreator () {
		
		if (whatSush == "Nigiri") {
			Nigiri custNigiri = null;
			if (meatValue == "crab") {
				custNigiri = new Nigiri(NigiriType.CRAB);	
			}
			if (meatValue == "shrimp") {
				custNigiri = new Nigiri(NigiriType.SHRIMP);
			}
			if (meatValue == "yellow") {
				custNigiri = new Nigiri(NigiriType.YELLOWTAIL);
			}
			if(meatValue == "tuna") {
				custNigiri = new Nigiri(NigiriType.TUNA);
			}
			if (meatValue == "eel") {
				custNigiri = new Nigiri(NigiriType.EEL);
			}
			if (color == Color.RED) {
				makeRedPlateRequest(custNigiri, positionValue);
			}
			if (color == Color.BLUE) {
				makeBluePlateRequest(custNigiri, positionValue);
			}
			if (color == Color.GREEN) {
				makeGreenPlateRequest(custNigiri, positionValue);
			}
			if (color == Color.GOLD) {
				makeGoldPlateRequest(custNigiri, positionValue, goldPrice);
			}
		}
		if (whatSush == "Sashimi") {
			Sashimi custSashimi = null;
			if (meatValue == "crab") {
				custSashimi = new Sashimi(SashimiType.CRAB);	
			}
			if (meatValue == "shrimp") {
				custSashimi = new Sashimi(SashimiType.SHRIMP);
			}
			if (meatValue == "yellow") {
				custSashimi = new Sashimi(SashimiType.YELLOWTAIL);
			}
			if(meatValue == "tuna") {
				custSashimi = new Sashimi(SashimiType.TUNA);
			}
			if (meatValue == "eel") {
				custSashimi = new Sashimi(SashimiType.EEL);
			}
			if (color == Color.RED) {
				makeRedPlateRequest(custSashimi, positionValue);
			}
			if (color == Color.BLUE) {
				makeBluePlateRequest(custSashimi, positionValue);
			}
			if (color == Color.GREEN) {
				makeGreenPlateRequest(custSashimi, positionValue);
			}
			if (color == Color.GOLD) {
				makeGoldPlateRequest(custSashimi, positionValue, goldPrice);
			}
		}
		if (whatSush == "Roll") {
			Roll custRoll = null;
			IngredientPortion[] ingredientPortion = new IngredientPortion[6];
			
			ingredientPortion[0] = new AvocadoPortion(aValue);
			ingredientPortion[1] = new EelPortion(eValue);
			ingredientPortion[2] = new TunaPortion(tValue);
			ingredientPortion[3] = new CrabPortion (cValue);
			ingredientPortion[4] = new YellowtailPortion (yValue);
			ingredientPortion[5] = new ShrimpPortion(shValue);
			
			custRoll = new Roll("CUSTOMROLL" , ingredientPortion);
			
			if (color == Color.RED) {
				makeRedPlateRequest(custRoll, positionValue);
			}
			if (color == Color.BLUE) {
				makeBluePlateRequest(custRoll, positionValue);
			}
			if (color == Color.GREEN) {
				makeGreenPlateRequest(custRoll, positionValue);
			}
			if (color == Color.GOLD) {
				makeGoldPlateRequest(custRoll, positionValue, goldPrice);
			}
			isRoll = false;
		}
		
		
		
	}
}
