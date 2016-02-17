package graafinenteekkariloikka.view.events;

import graafinenteekkariloikka.model.enums.Vari;

/**
 * V‰litt‰‰ painetun BoardButtonin indeksin ja v‰rin
 * @author Pilvi Rajala
 *
 */
public class BoardButtonEvent {
	private int index;
	private Vari vari;
	
	public BoardButtonEvent(int index, Vari vari){
		this.index = index;
		this.vari = vari;
	}
	
	public int getIndex(){
		return index;
	}
	
	//Setteri turha? //REMOVE
	public void setIndex(int index){
		this.index = index;
	}
	
	public Vari getVari(){
		return vari;
	}
	
	//Setteri turha? //REMOVE
	public void setVari(Vari vari){
		this.vari = vari;
	}
}
