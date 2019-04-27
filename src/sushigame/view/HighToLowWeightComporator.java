package sushigame.view;

import java.util.Comparator;

import sushigame.model.Chef;

public class HighToLowWeightComporator implements Comparator<Chef>{
	public int compare(Chef a, Chef b) {
			
		// We do b - a because we want largest to smallest
		return (int) (Math.round(b.getWeight()*100.0) - 
				Math.round(a.getWeight()*100));
		
		
	}			
}
