package graafinenteekkariloikka.view.events;

import graafinenteekkariloikka.model.enums.Vari;

/**
 * V‰litt‰‰ painetun painetun PawnButtonin indeksin ja v‰rin
 * @author Pilvi Rajala
 */

public class PawnButtonEvent {

	private int index;
	private Vari vari;
	
	public PawnButtonEvent(int index, Vari vari){
		this.index = index;
		this.vari = vari;
	}

	public int getIndex() { 
		return index;
	}

	public void setIndex(int index) { //Turha? //REMOVE?
		this.index = index;
	}

	public Vari getVari() {
		return vari;
	}

	public void setVari(Vari vari) { //Turha? //REMOVE?
		this.vari = vari;
	}
	
}
