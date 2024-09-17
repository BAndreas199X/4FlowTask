package com.example.andreas.model;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

public class Container {
	
	private static Container instance = null;
	
	final Stack<Good> containedGoodsStack = new Stack<>();
	private double weightLimit;
	final DecimalFormat df = new DecimalFormat("#.##");
	
	public Container(double i_weightLimit) {
		
		this.weightLimit = i_weightLimit;
		instance = this;
	}
	
	public Container getInstance() {
		return instance;
	}
	
	public String loadGood(Good newGood) {
		
		newGood.setPrize(Double.valueOf(df.format(newGood.getPrize())));
		
		if(newGood.getPrize() <= 0.0 || newGood.getWeight() <= 0.0 || 
				newGood.getDesignation() == null){
			return "The data you provided is not valid. Please make sure to provide"
					+ "a designation and values for weight and prize greater than 0";
		}else if(getTotalWeight() + newGood.getWeight() > weightLimit) {
			return "Good with the data "+newGood+" could not be loaded! The container's "
					+ "capacity is insufficient to accomodate it!";
		}else {
			containedGoodsStack.add(newGood);
			return "Good with the data "+newGood+" loaded successfully!";
		}
	}
	
	public Good unloadGood() {
		
		return containedGoodsStack.pop();
	}
	
	public double getTotalWeight() {
		return containedGoodsStack.stream().mapToDouble(Good::getWeight).sum();
	}
	
	public Good getMaxPrize() {
		
		return containedGoodsStack.stream().max(
				Comparator.comparing(Good::getPrize)).get();
	}

	public List<Good> getAllGoodsInContainer(){
		return containedGoodsStack;
	}
	
	public boolean isContainerEmpty() {
		return containedGoodsStack.isEmpty();
	}
	
	public double getWeightLimit() {
		return this.weightLimit;
	}

	@Override
	public String toString() {
		return "Container [containedGoodsStack=" + containedGoodsStack + ", weightLimit=" + weightLimit + "]";
	}
	
	
}
