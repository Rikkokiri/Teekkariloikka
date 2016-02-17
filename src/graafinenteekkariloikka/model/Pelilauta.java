package graafinenteekkariloikka.model;

import graafinenteekkariloikka.model.enums.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/*Pelilaudallani on 27 ruutua + maali (alkuperäisessä 26 + maali).
10 ruuduista on aukevia (vai enemmän?)
 */
public class Pelilauta implements Serializable {

	private static final long serialVersionUID = -2344707400274181112L;
	
	private Ruutu[] ruudut;
	private VRTila[] turvattomienTilat;
	
	public Pelilauta(){
		ruudut = new Ruutu[28];
		luoRuudut();
	}
	
	//Pelilauta on aina samanlainen
	public void luoRuudut(){
		ruudut[0] = new Turvaruutu();
		ruudut[1] = new Turvaruutu();
		ruudut[2] = new Turvaruutu();
		ruudut[3] = new Vaararuutu(); //v
		ruudut[4] = new Turvaruutu();
		ruudut[5] = new Turvaruutu();
		ruudut[6] = new Turvaruutu();
		ruudut[7] = new Vaararuutu(); //v
		ruudut[8] = new Turvaruutu();
		ruudut[9] = new Vaararuutu(); //v
		ruudut[10] = new Turvaruutu();
		ruudut[11] = new Turvaruutu();
		ruudut[12] = new Vaararuutu(); //v
		ruudut[13] = new Turvaruutu();
		ruudut[14] = new Vaararuutu(); //v
		ruudut[15] = new Turvaruutu();
		ruudut[16] = new Turvaruutu();
		ruudut[17] = new Vaararuutu(); //v
		ruudut[18] = new Turvaruutu();
		ruudut[19] = new Vaararuutu(); //V
		ruudut[20] = new Vaararuutu(); //v
		ruudut[21] = new Turvaruutu();
		ruudut[22] = new Vaararuutu(); //v
		ruudut[23] = new Turvaruutu();
		ruudut[24] = new Vaararuutu(); //v
		ruudut[25] = new Turvaruutu(); 
		ruudut[26] = new Vaararuutu(); //v
		
		ruudut[27] = new Maaliruutu();
		
	}
	
	/**
	 * Metodi, joka palauttaa pelilaudan ruudun i
	 * @param i Halutun ruudun indeksi
	 * @return Pelilaudan ruudun indeksissä i
	 */
	public Ruutu getRuutu(int i){
		return ruudut[i];
	}
	
	/**
	 * Metodi, jolla arvotaan 
	 * @return
	 */
	public void arvoTurvattomienRuutujenTilat(){
		
		//Korkeintaan 7/11 epävakaata ruutua voi olla kerralla auki.
		VRTila[][] arvottavatTilat = {
			{VRTila.KIINNI, VRTila.KIINNI, VRTila.KIINNI, VRTila.KIINNI, VRTila.KIINNI, VRTila.KIINNI, VRTila.KIINNI, VRTila.KIINNI, VRTila.KIINNI, VRTila.KIINNI, VRTila.KIINNI},
			{VRTila.AUKI, VRTila.KIINNI, VRTila.KIINNI, VRTila.KIINNI, VRTila.KIINNI, VRTila.KIINNI, VRTila.KIINNI, VRTila.KIINNI, VRTila.KIINNI, VRTila.KIINNI, VRTila.KIINNI},
			{VRTila.AUKI, VRTila.AUKI, VRTila.KIINNI, VRTila.KIINNI, VRTila.KIINNI, VRTila.KIINNI, VRTila.KIINNI, VRTila.KIINNI, VRTila.KIINNI, VRTila.KIINNI, VRTila.KIINNI},
			{VRTila.AUKI, VRTila.AUKI, VRTila.AUKI, VRTila.KIINNI, VRTila.KIINNI, VRTila.KIINNI, VRTila.KIINNI, VRTila.KIINNI, VRTila.KIINNI, VRTila.KIINNI, VRTila.KIINNI},
			{VRTila.AUKI, VRTila.AUKI, VRTila.AUKI, VRTila.AUKI, VRTila.KIINNI, VRTila.KIINNI, VRTila.KIINNI, VRTila.KIINNI, VRTila.KIINNI, VRTila.KIINNI, VRTila.KIINNI},
			{VRTila.AUKI, VRTila.AUKI, VRTila.AUKI, VRTila.AUKI, VRTila.AUKI, VRTila.KIINNI, VRTila.KIINNI, VRTila.KIINNI, VRTila.KIINNI, VRTila.KIINNI, VRTila.KIINNI},
			{VRTila.AUKI, VRTila.AUKI, VRTila.AUKI, VRTila.AUKI, VRTila.AUKI, VRTila.AUKI, VRTila.KIINNI, VRTila.KIINNI, VRTila.KIINNI, VRTila.KIINNI, VRTila.KIINNI},
			{VRTila.AUKI, VRTila.AUKI, VRTila.AUKI, VRTila.AUKI, VRTila.AUKI, VRTila.AUKI, VRTila.AUKI, VRTila.KIINNI, VRTila.KIINNI, VRTila.KIINNI, VRTila.KIINNI},
		};
		
		Random random = new Random();
		int a = random.nextInt(100); //Luku 0-99, 100 lukua
		int index;
		
		System.out.println(a);
		
		//Painotettu todennäköisyys
		if(a <= 2){ //0 aukeaa: 3 % 
			index = 0;
		}else if(a >= 3 && a <= 9){ //1 aukeaa: 7 % 
			index = 1;
		}else if(a >= 10 && a <= 34){ //2 aukeaa: 25 %
			index = 2;
		}else if(a >= 35 && a <= 64){ //3 aukeaa: 30 %
			index = 3;
		}else if(a >= 65 && a <= 84){ //4 aukeaa: 20 %
			index = 4;
		}else if(a >= 85 && a <= 91){ //5 aukeaa: 7 %
			index = 5;
		}else if(a >= 92 && a <= 96){ //6 aukeaa: 5 % 
			index = 6;
		}else{ //7 aukeaa: 3 %
			index = 7;
		}
		System.out.println(index);
			
		turvattomienTilat = arvottavatTilat[index];
		//Sekoitetaan, niin ensimmäiset vaararuudut eivät aina aukea
		Collections.shuffle(Arrays.asList(turvattomienTilat));

	}
	
	/**
	 * Metodi turvattomien ruutujen tilojen uudelleen asettamiseen.
	 */
	public void lautaMuuttuu(){	
		
		//"Nollataan" ensin Vaararuudut ( = suljetaan kaikista kuopat)
		for(int i=0; i<28; i++){
			if(ruudut[i] instanceof Vaararuutu){
				((Vaararuutu)ruudut[i]).setKuoppa(VRTila.KIINNI);
			}
		}
		
		//Asetaan vaararuutujen tilat arvotun ja sekoitun listan mukaisiksi
		int i = 0;
		int j = 0;
		while(j<11 && i<28){
			if(ruudut[i] instanceof Vaararuutu){
				((Vaararuutu)ruudut[i]).setKuoppa(turvattomienTilat[j]);
				j++;
			}
			i++;
		}
	}
	
	/**
	 * Palauttaa pelilaudan turvattomien ruutujen tilat
	 * @return Taulukko pelilaudan turvattomien ruutujen tiloista.
	 */
	public VRTila[] getTurvattomienTilat(){
		return turvattomienTilat;
	}
	
	/**
	 * Metodi nappuloiden pudottamiseen, kun lautaa on muutettu.
	 */
	public int pudotaNappulat(){
		int pudonneet = 0;
		
		for(int i=0; i<27; i++){
			if(ruudut[i] instanceof Vaararuutu){
				if( ((Vaararuutu)ruudut[i]).putoaako() == true ){
					((Vaararuutu)ruudut[i]).kukaPutosi().setTila(NTila.PUDONNUT);
					ruudut[i].setTila(RTila.VAPAA);
					pudonneet++;
				}
			}
		}
		return pudonneet;
	}
	
	/**Metodi laudan tulostamiseen.
	 * Pelin tekstiversiota varten.
	 */
	public void tulostaLauta(){
		for(int i=0; i<27; i++){
			String tyyppi = "Turvallinen";
			if(ruudut[i] instanceof Vaararuutu){
				tyyppi = "Turvaton";
			}
			String auki = "";
			if(ruudut[i] instanceof Vaararuutu && ((Vaararuutu)ruudut[i]).getKuoppa() == VRTila.AUKI){
				auki = " -AUKI-";
			}
			if(ruudut[i].getTila() == RTila.VAPAA){
				System.out.println("Ruutu " + (1+i) + "(" + tyyppi + auki + ")" + ":");
			}
			if(ruudut[i].getTila() == RTila.VARATTU){
				System.out.println("Ruutu " + (1+i) + "(" + tyyppi + auki + ")" +":" + ruudut[i].getRuudussa());
			}
		}
		System.out.println(ruudut[27]);
	}
}
