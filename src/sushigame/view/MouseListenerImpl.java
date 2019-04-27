package sushigame.view;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import comp401sushi.Plate;
import sushigame.model.Belt;


public class MouseListenerImpl extends JLabel implements MouseListener{
	
	Belt belt;
	int index;
	
	
	public MouseListenerImpl(Belt b, int i) {
		this.belt = b;
		this.index = i;
	}
	

	
	public void mouseClicked(MouseEvent arg0) {
		Plate plate = belt.getPlateAtPosition(index);

		
		JPanel panel = new JPanel();
		panel.setSize(200,500);
		JFrame sushiFrame = new JFrame();
		sushiFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JLabel colorLabel = new JLabel("Color: " + plate.getColor().toString() + " ~ ");
		JLabel sushiLabel = new JLabel("Sushi Type: " + plate.getContents().getName() + " ~ ");
		JLabel chefName = new JLabel("Chef Name: " + plate.getChef().getName() +  " ~ ");
		JLabel plateAge = new JLabel("Plate Age: " + belt.getAgeOfPlateAtPosition(index));
			
		panel.add(colorLabel);
		panel.add(sushiLabel);

		if (plate.getContents().getIngredients().length >= 3) {
			String rollIngredients = new String();
			for(int i=0; i<plate.getContents().getIngredients().length; i++) {
				rollIngredients += (plate.getContents().getIngredients()[i].getAmount() + " " + plate.getContents().getIngredients()[i].getName() + " | ");
			}
			JLabel ingredientsDisplay = new JLabel(rollIngredients + " ~ ");
			panel.add(ingredientsDisplay);
		}
		panel.add(chefName);
		panel.add(plateAge);
		
		
		sushiFrame.setContentPane(panel);
		
		sushiFrame.setSize(500,150);
		sushiFrame.setVisible(true);
	}

	
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
