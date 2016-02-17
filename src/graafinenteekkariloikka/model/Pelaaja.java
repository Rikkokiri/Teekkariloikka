package graafinenteekkariloikka.model;

import java.io.Serializable;

import graafinenteekkariloikka.model.enums.Vari;

public class Pelaaja implements Serializable {

	private static final long serialVersionUID = -3783265625890617691L;

	private String nimi;
	Teekkari[] nappulat;
	Vari napVari; //nappuloiden väri
	
	public Pelaaja(String nimi, Vari napVari){
		this.nimi = nimi;
		this.napVari = napVari;
		nappulat = new Teekkari[4];
		luoNappulat();
	}
	
	//Getterit
	public String getPNimi(){
		return nimi;
	}
	
	public Vari getVari(){
		return napVari;
	}
	
	public String toString(){
		return nimi + "/" + napVari;
	}
	
	public void luoNappulat(){
		for(int i=0; i<4; i++){
			nappulat[i] = new Teekkari(this, napVari, i);
		}
	}
	public Teekkari getNappula(int i){
		return nappulat[i]; 
	}
}

