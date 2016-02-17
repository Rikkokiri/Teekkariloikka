package graafinenteekkariloikka.view.events;

public class NumberInputEvent {
	
	private int number;
	
	public NumberInputEvent(int number){
		this.number = number;
	}
	
	public int getNumber(){
		return number;
	}
	
	//Setteri turha?
	public void setNumber(int number){
		this.number = number;
	}
}
