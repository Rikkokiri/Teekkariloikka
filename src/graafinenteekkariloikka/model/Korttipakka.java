package graafinenteekkariloikka.model;

import graafinenteekkariloikka.model.enums.Kt;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;

/*
Pelin esikuvana toimivassa Kanin loikka -lautapeliss‰ pakassa on 24 korttia.
*/
/**
 * Korttipakka
 * @author Pilvi
 *
 */
public class Korttipakka implements Serializable{

	private static final long serialVersionUID = 1L;
	private int nostoindeksi;
	
	Kortti[] pakka;
	
	/**
	 * Konstruktori
	 * Korttipakan koko on vakio (24).
	 */
	public Korttipakka(){
		pakka = new Kortti[24];
		nostoindeksi = 0;
		luoKortit(); //Lis‰t‰‰n kortit pakkaan
		sekoitaPakka(); //Sekoitetaan pakka
	}
	
	/**
	 * Luodaan jokaista korttityyppi‰ yht‰ monta kappaletta (6). Pakka on siis vakio.
	 */
	public void luoKortit(){
		for(int i=0; i<6; i++){
			pakka[i] = new Kortti(Kt.YKSI);
		}
		for(int i=6; i<12; i++){
			pakka[i] = new Kortti(Kt.KAKSI);
		}
		for(int i=12; i<18; i++){
			pakka[i] = new Kortti(Kt.KOLME);
		}
		for(int i=18; i<24; i++){
			pakka[i] = new Kortti(Kt.LM);
		}
	}
	
	/**
	 * Metodi pakan sekoittamiseen
	 */
	public void sekoitaPakka(){
		Collections.shuffle(Arrays.asList(pakka));
	}
	
	/**
	 * Metodi nostaa pakan seuraavan kortin						-------  TY÷STƒ TƒTƒ!  -------
	 */
	public Kortti nostaKortti(){
		//Jos p‰‰st‰‰n pakan loppuun, sekoitetaan pakka ja aloitetaan alusta. Tehd‰‰n t‰m‰ jo seuraava kierrosta varten.
		if(nostoindeksi >= 23){
			sekoitaPakka();
			nostoindeksi = -1; //Kasvaa seuraavalla rivill‰ nollaan
		}
		nostoindeksi++;
		return pakka[nostoindeksi];
	}
}
