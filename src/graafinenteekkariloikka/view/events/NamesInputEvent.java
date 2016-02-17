package graafinenteekkariloikka.view.events;

import java.util.ArrayList;

public class NamesInputEvent {
	
	private ArrayList<String> names;
	
	public NamesInputEvent(ArrayList<String> names){
		this.names = names;
	}
	
	public void setNames(ArrayList<String> names){
		this.names = names;
	}
	
	public ArrayList<String> getNames(){
		return names;
	}
}
