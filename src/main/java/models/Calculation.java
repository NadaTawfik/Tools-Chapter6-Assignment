package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Calculation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private int number1;
	
	private int number2; 
	
	private String operation;
	
	public int getNumber1() {
		return number1;
	}
	
	
	public int getNumber2() {
		return number2;
	}
	
	
	public String getOperation() {
		return operation;
	}
	
	
	public void setNumber1(int n1) {
		number1 = n1;
	}
	
	
	public void setNumber2(int n2) {
		number2 = n2;
	}
	
	
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	
}
