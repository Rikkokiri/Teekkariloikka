package graafinenteekkariloikka.model;

import graafinenteekkariloikka.gamestate.GameState;
import graafinenteekkariloikka.gamestate.GameStateManager;
import graafinenteekkariloikka.model.enums.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class TLModel {

	private Korttipakka pakka;
	private Pelilauta lauta;
	private ArrayList<Pelaaja> pelaajat;
	//Vuorossa oleva pelaaja
	private Pelaaja vuorossa;
	
	public TLModel(){
	
	}
	
//o.o.o.o.o.o PELIN ALOITUS, TALLENNUS JA TALLENNUKSEN LATAUS o.o.o.o.o.o 
	
	//------ ALOITETAAN UUSI PELI -------
	public void aloitaUusiPeli(int pelaajienMaara, ArrayList<String> nimet){
				
		pakka = new Korttipakka();
		lauta = new Pelilauta();
		
		pelaajat = new ArrayList<Pelaaja>();
		
		luoPelaajat(pelaajienMaara, nimet);

		//Ensimm‰inen pelaaja aloittaa pelin
		vuorossa = pelaajat.get(0);
		
	}
	
	//>>> Luo pelaajat -metodi
	/**
	 * Luodaan pyydetty m‰‰r‰ (pelaajienMaara) pelaajia
	 * @param pelaajienMaara Luotavien pelaajien m‰‰r‰
	 * @param nimet ArrayList, johon on tallennettu luotavien pelaajien nimet
	 */
	public void luoPelaajat(int pelaajienMaara, ArrayList<String> nimet){
		
		Vari[] varit = {Vari.SININEN, Vari.KELTAINEN, Vari.PINKKI, Vari.VIOLETTI};
		
		for(int i=0; i<pelaajienMaara; i++){
			//Jos pelaajalle ei ole annettu nime‰ (nimi ""), annetaan nimeksi "Nimetˆn 1", "Nimetˆn 2" jne.
			if(nimet.get(i).equals("")){
				pelaajat.add( new Pelaaja(("Nimetˆn " + (i+1)), varit[i]));
			}
			else{
			pelaajat.add( new Pelaaja(nimet.get(i), varit[i]) );
			}
		}
	}
	
	
	//------ PELIN TALLENNUS ------------
	/**
	 * Metodi pelaajan tallenntamiseen
	 * @return boolean true/false Kertoo, onnistuiko tallennus vai ei.
	 */
	public boolean tallennaPeli(){
		
		File tallennustiedosto = new File("TeekkariloikanTallennus.sav");
		
			try{
			FileOutputStream fos = new FileOutputStream(tallennustiedosto);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(pakka);
			oos.writeObject(lauta);
			oos.writeObject(pelaajat);
			oos.writeObject(vuorossa);
		
			oos.writeObject(GameStateManager.getGamestate());
			
			oos.close();
			fos.close();
			
		} catch (IOException e){
			e.printStackTrace();
			return false;
		} 
		return true;
	}
	
	
	//------ PELIN LATAUS ---------------
	
	@SuppressWarnings("unchecked") //Ongelmakohta: this.pelaajat = (ArrayList<Pelaaja>) ois.readObject();
	public boolean lataaTallennettuPeli(){
		
		try{
			
			FileInputStream fis = new FileInputStream("TeekkariloikanTallennus.sav");
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			this.pakka = (Korttipakka) ois.readObject();
			this.lauta = (Pelilauta) ois.readObject();
			this.pelaajat = (ArrayList<Pelaaja>) ois.readObject(); //TODO
			this.vuorossa = (Pelaaja) ois.readObject();
			
			GameStateManager.setGamestate((GameState) ois.readObject());
			
			ois.close();
			fis.close();
			
		} catch (IOException e){
			return false; //Pelin lataaminen ep‰onnistui, palautetaan false
		} catch (ClassNotFoundException ex){
			return false; //Pelin lataaminen ep‰onnistui, palautetaan false
		}
		return true; //Pelin lataaminen onnistui, palautetaan true
	}
	
	//------ POISTA TALLENNUS -----------------------------
	
	public void poistaTallennus(){
		
		(new File("TeekkariloikanTallennus.sav")).delete();		

	}

	
//o.o.o.o.o.o NOSTA KORTTI o.o.o.o.o.o 	
	/**
	 * Metodi kortin nostamiseen
	 * @return 
	 */
	public Kortti nostaKortti(){
		return pakka.nostaKortti();
	}
	
//o.o.o.o.o.o SIIRTO o.o.o.o.o.o 

	/**
	 * @param siirrettava Teekkari, jota siirretaan
	 * @param aloitus Sen ruudun indeksi, jossa siirrett‰v‰ teekkari aluksi on
	 * @param alustavaSiirto Kortin mukainen siirto
	 * @return voittovuoro Palauttaa tiedon siit‰, oliko kyseess‰ voittava vuoro
	 */
	public boolean siirra(Teekkari siir, int aloitus, int alustavaSiirto){
		
		Teekkari siirrettava = siir;
		
		int aloitusSijainti = aloitus;
		int siirto = alustavaSiirto; //T‰t‰ siirron pituutta muokataan (tarvittaessa)
		//Tuleva sijainti, jos v‰liss‰ ei jouduta hyppim‰‰n toisten nappuloiden yli
		int mahdSijainti = aloitus + siirto;
		
		boolean voittovuoro = false; //Oletus on, ett‰ kyseess‰ ei ole voittava vuoro		
		boolean siirtoKaynnissa = true;
		
	/////////// Siirtoprosessi alkaa t‰st‰ ///////////////////
		while(siirtoKaynnissa){
		
			//Siirtyess‰ ei lasketa ruutuja, joissa on jo nappula.
			int s = siirto;
			int a = aloitus;
			
			while(s>0 && a<28){
				if(lauta.getRuutu(a+1).getTila() == RTila.VARATTU){
					s++;
				}
				if(lauta.getRuutu(a+1) instanceof Maaliruutu){
					siirrettava.setSijainti(a+1);
					lauta.getRuutu(a+1).setRuudussa(siirrettava);
					voittovuoro = true;
					break;
				}
				a++;
				s--;
			}
			
			mahdSijainti = a;
			
			/*
			//Tulee ottaa viel‰ huomioon se mahdollisuus, ett‰ nappula siirret‰‰n maaliruutuun.
			//Maaliin ei tarvitse p‰‰st‰ tasaluvulla.
			if(mahdSijainti >= 27){ 
				
				siirrettava.setSijainti(27);
				lauta.getRuutu(27).setRuudussa(siirrettava);
				
				voittovuoro = true;
				break; //Katkaistaan siirto t‰h‰n
			}
			*/
			
			//Jos ruutu, johon nappula siirrett‰v‰‰n, on avoin vaararuutu (=kuoppa), nappula putoaa kuoppaan
			if(lauta.getRuutu(mahdSijainti) instanceof Vaararuutu){
				if( ((Vaararuutu)lauta.getRuutu(mahdSijainti)).getKuoppa() == VRTila.AUKI){
					siirrettava.setTila(NTila.PUDONNUT); //Metodi asettaa myˆs sijainnin automaattisesti oikein. Ks. Teekkari-luokka
					break;
				}
			}
			
			///////NORMAALI SIIRTO (Eli ei muita nappuloita tai avoimia kuoppia matkalla)////////////
			
			//Jos nappula ei p‰‰se maaliin eik‰ putoa kuoppaan - eli on p‰‰sty t‰nne asti while-loopissa, sit‰ siirret‰‰n nappulaa normaalisti.
			siirrettava.setSijainti(mahdSijainti);
			
			//Annetaan myˆs ruudulle tieto siihen siirtyneest‰ pelaajasta.
			lauta.getRuutu(mahdSijainti).setRuudussa(siirrettava);
			
			siirtoKaynnissa = false;
			
		}
		//BREAK-komento johdattaa t‰nne.
		
		//Vapautetaan viel‰ ruutu, josta l‰hdettiin liikkeelle 
		if(aloitusSijainti>=0){
			lauta.getRuutu(aloitusSijainti).setTila(RTila.VAPAA);
		}
		//Palautetaan tieto siit‰, oliko kyseess‰ voittava vuoro
		
		return voittovuoro; 
	}
	
	
	
//o.o.o.o.o.o LAUTA MUUTTUU o.o.o.o.o.o //
	/**
	 * Metodi, jolla hoidetaan laudan muuttuminen "lauta muuttuu" -kortin noustessa
	 */
	public int laudanMuutos(){
		lauta.arvoTurvattomienRuutujenTilat();
		lauta.lautaMuuttuu();
		int pudonneet = lauta.pudotaNappulat();
		return pudonneet; //Pudonneiden nappuloiden lukum‰‰r‰
	}	
	
	/**
	 * Metodi, joka palauttaa listan ruuduissa olevien nappuloiden v‰reist‰.
	 * Tarvitaan GUIn p‰vitt‰miseen.
	 * @return
	 */
	public Vari[] getListaRuutujenVarit(){
		Vari[] ruutujenvarit = new Vari[28];
		
		for(int i=0; i<28; i++){
			if(lauta.getRuutu(i).getRuudussa() != null){
				ruutujenvarit[i] = lauta.getRuutu(i).getRuudussa().getVari();
			}
			else{
				ruutujenvarit[i] = null;
			}
		}
		return ruutujenvarit;
	}
	
	
	
//o.o.o.o.o.o NAPPULOITA LAUDALLA o.o.o.o.o.o	
	/**
	 * Metodi, jolla tarkistetaan, onko pelaajalle viel‰ nappuloita j‰jell‰.
	 * @param p Pelaaja, jonka nappulatilanne tarkistetaan
	 * @return nappuloitaJajella Palauttaa true, jos nappuloita viel‰ on ja false, jos ei ole
	 */
	public boolean nappuloitaLaudalla(Pelaaja p){
		boolean nappuloitaJaljella = true;
		int nappuloita = 4;
		
		for(int i=0; i<4; i++){
			if(p.getNappula(i).getTila() == NTila.PUDONNUT){
				nappuloita--;
			}
		}
		if(nappuloita == 0){
			nappuloitaJaljella = false;
		}
		
		return nappuloitaJaljella;
	}	
	
//o.o.o.o.o.o VUORON VAIHTO o.o.o.o.o.o
	
	/**Metodi vuoron vaihtamiseksi
	 * @param pelaajat
	 * @param vuorossa Juuri vuorossa ollut pelaaja
	 * @return vuoroon Palauttaa seuraavaksi vuoroon tulevan pelaajan.
	 */
	public void vaihdaVuoroa(){
		//Tarkistetaan, kuka oli juuri vuorossa
		int vuoro = pelaajat.indexOf(vuorossa);
		
		if(vuoro == pelaajat.size()-1){
			vuorossa = pelaajat.get(0);
		}
		else{
			vuorossa = pelaajat.get(vuoro+1);
		}
	}	
			
//############ "APUMETODEITA" #############	
/* 
 * (1) luoPelaajat(int pelaajienMaara, ArrayList<String> nimet))
 * (2) getVuorossa()
 * (3) getLauta()
 * 
 */
		
	/**Metodi, joka palauttaa vuorossa olevan pelaajan
	 */
	public Pelaaja getVuorossa(){
		return vuorossa;
	}
	
	/**
	 * Getteri laudalle
	 * @return lauta Palauttaa peliss‰ k‰ytˆss‰ olevan laudan.
	 */
	public Pelilauta getLauta(){
		return lauta;
	}
	
	/**
	 * Metodi, joka palauttaa pelaajalistan
	 * @return
	 */
	public ArrayList<Pelaaja> getPelaajat(){
		return pelaajat;
	}
	
}
