package graafinenteekkariloikka.model;

import java.io.Serializable;

import graafinenteekkariloikka.model.enums.NTila;
import graafinenteekkariloikka.model.enums.Vari;

public class Teekkari implements Serializable {

	private static final long serialVersionUID = 386876204976904161L;

	private final Vari vari; //Nappuloiden väri
	private NTila tila; //sivussa, laudalla tai pudonnut
	private int sijainti; //ruudun indeksi / -1 sivussa / -2 pudonnut laudalta (?)
	private final Pelaaja omistaja;
	private final int index;

	public Teekkari(Pelaaja omistaja, Vari vari, int i){
		this.vari = vari;
		tila = NTila.SIVUSSA;
		sijainti = -1;
		this.omistaja = omistaja;
		index = i;
	}
	
	//Getterit ja setterit
	public NTila getTila(){
		return tila;
	}
	public void setTila(NTila t){
		tila = t;
		//Kun nappulan tilaksi asetetaan pudonnut, voidaan myös sijainniksi myös suoraan merkitä pudonnut (-2)
		if(t == NTila.PUDONNUT){
			setSijainti(-2);
		}
	}
	public Vari getVari(){
		return vari;
	}
	public Pelaaja getOmistaja(){
		return omistaja;
	}
	public int getIndex(){
		return index;
	}
	public int getSijainti(){
		return sijainti;
	}
	public void setSijainti(int s){
		sijainti = s;
	}
	//toString-metodi
	public String toString(){
		return vari + " " + (index+1);
	}
}
