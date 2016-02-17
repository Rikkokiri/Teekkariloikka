package graafinenteekkariloikka.model;

import java.io.Serializable;

import graafinenteekkariloikka.model.enums.RTila;
import graafinenteekkariloikka.model.enums.VRTila;

/**
 * @author Pilvi Rajala
 */
public abstract class Ruutu implements Serializable {

	private static final long serialVersionUID = -4747268227300587091L;
	
	protected RTila tila;
	protected Teekkari ruudussa;
	
	public Ruutu(){
		tila = RTila.VAPAA;
		ruudussa = null;
	}
	
	public RTila getTila(){
		return tila;
	}
	
	public void setTila(RTila t){
		tila = t;
		if(t == RTila.VAPAA){
			ruudussa = null;
		}
	}
	
	public Teekkari getRuudussa(){
		return ruudussa;
	}
	
	//Asettaa nappulan ruutuun
	public void setRuudussa(Teekkari t){
		ruudussa = t;
		if(t == null){
			tila = RTila.VAPAA;
		}
		else{
			tila = RTila.VARATTU;
		}
	}
}

/**
 * Turvallinen ruutu, ei voi aueta
 * @author Pilvi
 *
 */
class Turvaruutu extends Ruutu implements Serializable {
	
	private static final long serialVersionUID = 3963302462206379785L;

	public Turvaruutu(){
		super();
	}
}

/**Turvaton ruutu, voi aueta
 * @author Pilvi Rajala
 *
 */
class Vaararuutu extends Ruutu implements Serializable {
	
	private static final long serialVersionUID = 7519224524541960668L;
	
	VRTila kuoppa;
	
	public Vaararuutu(){
		super();
		kuoppa = VRTila.KIINNI;
	}
	
	//Onko ruutu kiinni vai auki?
	public VRTila getKuoppa(){
		return kuoppa;
	}
	
	public void setKuoppa(VRTila t){
		kuoppa = t;
	}
	
	public boolean putoaako(){
		if(tila == RTila.VARATTU && kuoppa == VRTila.AUKI){
			return true;
		}
		else{
			return false;
		}
	}
	
	public Teekkari kukaPutosi(){
		return ruudussa;
	}
	
}
/**Maali
 * Loppujen lopuksi täysin turha luokka
 * @author Pilvi Rajala
 */
class Maaliruutu extends Ruutu implements Serializable {
	
	private static final long serialVersionUID = -7546375987868586235L;

	public Maaliruutu(){
		super();
	}
	
	public String toString(){
		String t;
		if(ruudussa != null){
			t = "MAALI: " + ruudussa.toString() + "\n";
		}
		else{
			t = "MAALI:\n";
		}
		return t;
	}
}