package com.example.andreas.model;

import java.util.Objects;

public class Good {
	
	public Good(String designation, double prize, double weight) {
		this.designation = designation;
		this.prize = prize;
		this.weight = weight;
	}

	private String designation;
	
	private double prize;
	
	private double weight;

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public double getPrize() {
		return prize;
	}

	public void setPrize(double prize) {
		this.prize = prize;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public int hashCode() {
		return Objects.hash(designation, prize, weight);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Good other = (Good) obj;
		return Objects.equals(designation, other.designation)
				&& Double.doubleToLongBits(prize) == Double.doubleToLongBits(
						other.prize)
				&& Double.doubleToLongBits(weight) == Double.doubleToLongBits(
						other.weight);
	}

	@Override
	public String toString() {
		return "[designation=" + designation + ", prize=" + prize + ", weight=" 
				+ weight + "]";
	}
}
