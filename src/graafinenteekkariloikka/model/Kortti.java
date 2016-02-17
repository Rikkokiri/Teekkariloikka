package graafinenteekkariloikka.model;

import java.io.Serializable;

import graafinenteekkariloikka.model.enums.Kt;

/**
 * Kortti-luokka
 */
public class Kortti implements Serializable {

	private static final long serialVersionUID = 4465626712792047481L;
	
	private Kt kt; //Kortin tyyppi, enum (yksi, kaksi, kolme, lauta muuttuu)
	
	public Kortti(Kt kt){
		this.kt = kt;
	}
	
	//Palauttaa kortin tyypin
	public Kt getKorttityyppi(){
		return kt;
	}
	
	public String toString(){			//   TURHA ????
		return "Kortti: " + kt;
	}
}