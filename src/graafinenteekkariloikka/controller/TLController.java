package graafinenteekkariloikka.controller;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import graafinenteekkariloikka.view.*;
import graafinenteekkariloikka.view.events.BoardButtonEvent;
import graafinenteekkariloikka.view.events.NamesInputEvent;
import graafinenteekkariloikka.view.events.NumberInputEvent;
import graafinenteekkariloikka.view.events.PawnButtonEvent;
import graafinenteekkariloikka.view.listenerinterfaces.BoardButtonListener;
import graafinenteekkariloikka.view.listenerinterfaces.DrawCardListener;
import graafinenteekkariloikka.view.listenerinterfaces.InputNamesListener;
import graafinenteekkariloikka.view.listenerinterfaces.InputNumberListener;
import graafinenteekkariloikka.view.listenerinterfaces.LaunchGameListener;
import graafinenteekkariloikka.view.listenerinterfaces.LoadSaveListener;
import graafinenteekkariloikka.view.listenerinterfaces.NewGameButtonListener;
import graafinenteekkariloikka.view.listenerinterfaces.PawnListener;
import graafinenteekkariloikka.view.listenerinterfaces.QuitGameListener;
import graafinenteekkariloikka.view.listenerinterfaces.SaveGameListener;
import graafinenteekkariloikka.gamestate.GameState;
import graafinenteekkariloikka.gamestate.GameStateManager;
import graafinenteekkariloikka.model.*;
import graafinenteekkariloikka.model.enums.Kt;
import graafinenteekkariloikka.model.enums.VRTila;
import graafinenteekkariloikka.model.enums.Vari;

public class TLController implements NewGameButtonListener, InputNumberListener, InputNamesListener, LaunchGameListener,
									DrawCardListener, BoardButtonListener, PawnListener, SaveGameListener, QuitGameListener, LoadSaveListener {

	private TLView view;
	private TLModel model;
	
	private ArrayList<String> playernames;
	private int numberOfPlayers;
	
	private int siirto;
	private Kortti nostettu;
	
	public TLController(TLView view, TLModel model){
		//Controller tiet‰‰ view'n ja modelin olemassaolosta, mutta n‰m‰ eiv‰t controllerista
		this.view = view;
		this.model = model;	
	}
	
	
	//New Game -painiketta painettiin p‰‰valikossa
	@Override
	public void newGamePerformed() {
		view.showPlayersMenu();
	}
	
	@Override
	public void inputNumberPerformed(NumberInputEvent event) {
		numberOfPlayers = event.getNumber();
		view.constructPlayerNamesMenu(numberOfPlayers);
		view.showNamesMenu();
	}

	
	@Override
	public void inputNamesPerformed(NamesInputEvent event) {
		//Otetaan pelaajien nimet talteen
		playernames = event.getNames();
		
		//Avataan n‰kym‰, jossa annetaan pelin ohjeet
		view.showInfoView();	
	}
	
	/*
	 * Info-paneelissa painetaan nappia "Jatka peliin" (launchGameButton)
	 */
	@Override
	public void launchGamePerformed() {
		//
		view.constructGameView(numberOfPlayers);
		
		//Aloitetaan uusi peli
		model.aloitaUusiPeli(numberOfPlayers, playernames);
		
		//Peli alkaa nostovaiheesta
		GameStateManager.setGamestate(GameState.NOSTO);
		view.updateInfo("Nostahan kortti!");
		
		//Avataan pelin‰kym‰
		view.showGameView();
		
		view.updateTurnInfo(model.getVuorossa().getPNimi());
		
	}


	/*
	 * Kortin nosto
	 */
	@Override
	public void drawCardPerformed() {
		
		//Tarkistetaan, onko noston aika
		if(GameStateManager.getGamestate() == GameState.NOSTO){
			view.updateInfo("Nostahan kortti!");
			
			nostettu = model.nostaKortti();
			//P‰ivitet‰‰n n‰kyviin oikeanlainen kortti
			view.updateCardLabel(nostettu.getKorttityyppi());
			
			//Jos nostetaan lauta muuttuu -kortti
			if(nostettu.getKorttityyppi() == Kt.LM){
				GameStateManager.setGamestate(GameState.LAUTAMUUTTUU);
				view.updateInfo("Lauta muuttuu!");

				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				int pudonneet = model.laudanMuutos();
				if(pudonneet > 0){
					view.updateInfo("Taas putosi " + pudonneet + " teekkaria ikuisen teekkarin hautaan.");
				}
				
				//TODO Mekanismi, jolla selvitet‰‰n, mitk‰ nappulat putosivat ja ilmoitetaan tekstin‰ k‰ytt‰j‰lle
				
				// Laudan muututtuaPyydet‰‰n modelilta tieto turvattomien ruutujen uusista tiloista
				VRTila[] turvattomientilat = model.getLauta().getTurvattomienTilat();
								
				view.updateBoardAfterChange(turvattomientilat);
				
				//Vaihdetaan vuorossa olevaa pelaajaa
				vuoronvaihto();
				GameStateManager.setGamestate(GameState.NOSTO);
				//view.updateInfo("Nostahan kortti!");
				
				view.updateTurnInfo(model.getVuorossa().getPNimi());
			}
			
			//Jos nostetaan siirtymisen mahdollista kortti (1, 2 tai 3)
			else{
				GameStateManager.setGamestate(GameState.SIIRTO);
				
				if(nostettu.getKorttityyppi() == Kt.YKSI){
					siirto = 1;
				}
				if(nostettu.getKorttityyppi() == Kt.KAKSI){
					siirto = 2;
				}
				if(nostettu.getKorttityyppi() == Kt.KOLME){
					siirto = 3;
				}
			
				//Kerro pelaajalle, ett‰ h‰nen tulee siirt‰‰ nappulaansa
				view.updateInfo("Laita teekkarisi liikeelle!");
			}
		}
		else{
			//Kerro pelaajalle, ettei viel‰ ole noston aika.
			view.updateInfo("ƒl‰h‰n h‰t‰ile! Ei viel‰ liikuta.");
		}		
	}
	
	
	@Override
	public void pawnPressPerformed(PawnButtonEvent event) {
		
		//Tarkistetaan, onko siirron aika
		if(GameStateManager.getGamestate() == GameState.SIIRTO){
			
			//Tarkistetaanko onko painetussa ruudussa vuorossa olevalle pelaajalle kuuluva nappula
			if(event.getVari() == model.getVuorossa().getVari()){
				
				GameStateManager.setGamestate(GameState.SIIRRETAAN);
				
				Teekkari siirrettava = model.getVuorossa().getNappula(event.getIndex());
				int aloitusruutu = -1; //Nappula on viel‰ sivussa
				
				model.siirra(siirrettava, aloitusruutu, siirto);

				Vari[] uudetvarit = model.getListaRuutujenVarit();
				
				view.updatePawnButton(event.getVari(), event.getIndex());
				
				view.updateBoardAfterMove(uudetvarit);
				
				vuoronvaihto();
				GameStateManager.setGamestate(GameState.NOSTO);
				view.updateInfo("Nostahan kortti!");
				
				view.updateTurnInfo(model.getVuorossa().getPNimi());
				
			}
			else{
				//K‰ske k‰ytt‰j‰n siirt‰‰ omia nappuloitaan.
				view.updateInfo("Pid‰p‰ huoli vain omista teekkareistasi!");
			}
		}
		else{
			//Kerro k‰ytt‰j‰lle, ettei viel‰ ole siirron aika
			view.updateInfo("ƒl‰h‰n h‰t‰ile! Ei viel‰ liikuta.");
		}
	}
	
	
	@Override
	public void boardButtonPressPerformed(BoardButtonEvent event) {
		
		//Tarkistetaan, onko siirron aika
		if(GameStateManager.getGamestate() == GameState.SIIRTO){
			
			//Tarkistetaanko onko painetussa ruudussa vuorossa olevalle pelaajalle kuuluva nappula
			if(model.getVuorossa().getVari() == event.getVari()){ 
				
				Teekkari siirrettava = model.getLauta().getRuutu(event.getIndex()).getRuudussa();
				int aloitusruutu = event.getIndex();
				
				boolean voitto = model.siirra( siirrettava, aloitusruutu, siirto);
				
				//Kun joku pelaajista p‰‰see maaliin.
				if(voitto == true ){
					GameStateManager.setGamestate(GameState.PELIOHI);
					
					model.poistaTallennus();
					
					int n = JOptionPane.showConfirmDialog(null, "Pelaaja " + model.getVuorossa().getPNimi() + " voitto juuri pelin. Haluatko pelata uudelleen?");
					if(n == JOptionPane.YES_NO_OPTION){
						view.showPlayersMenu();
					}
					if(n == JOptionPane.NO_OPTION){
						view.showMainMenu();
					}
					
					
					
				}
				
				Vari[] varit = model.getListaRuutujenVarit();
				
				view.updateBoardAfterMove(varit);
				
				vuoronvaihto();
				GameStateManager.setGamestate(GameState.NOSTO);
				view.updateTurnInfo(model.getVuorossa().getPNimi());
				view.updateInfo("Nostahan kortti!");
				
				
			}
			else{ //Ilmoitetaan pelaajalle, jos kyseess‰ ei ole h‰nen nappulansa.
				view.updateInfo("H‰n ei taida olla sinun teekkarisi.");
				}
		}
		else{
			//Ilmoita, ettei viel‰ ole siirron aika
			view.updateInfo("Viel‰ ei ole etenemisen aika!");
		}
		
	}

	@Override
	public void saveGamePerformed() {
		boolean onnistuiko = model.tallennaPeli();
		
		if(onnistuiko == false){
			JOptionPane.showMessageDialog(null, "Tallennus ep‰onnistui!");
		}
		else{
			JOptionPane.showMessageDialog(null, "Peli tallennettu!");
		}
		view.dispose();
	}


	@Override
	public void quitGamePerformed() {
		int n = JOptionPane.showConfirmDialog(null, "Haluatko varmasti lopettaa pelin tallentamatta?", "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		if(n == JOptionPane.YES_NO_OPTION){
			view.dispose();
		}
		else if(n == JOptionPane.NO_OPTION){
			//Do nothing
		}
	}


	@Override
	public void loadSavePerformed() {
		boolean onnistui = model.lataaTallennettuPeli();
		
		if(onnistui == true){
			ArrayList<Pelaaja> p = model.getPelaajat();
		
			view.constructGameView(p.size());
			
			for(int i=0; i<p.size(); i++){
				for(int j=0; j<4; j++){
					int s = p.get(i).getNappula(j).getSijainti();
					if(s != -1){
						view.updatePawnButton(p.get(i).getVari(), j);
					}
				}
			}
		
			view.updateBoardAfterMove(model.getListaRuutujenVarit());
		
			updateTextsAfterSaveLoad();
		
			if(model.getLauta().getTurvattomienTilat() != null){
			view.updateBoardAfterChange(model.getLauta().getTurvattomienTilat());
			}
			//Avataan pelin‰kym‰
			view.showGameView();
		}
		else if(onnistui == false){
			JOptionPane.showMessageDialog(null, "Tallennusta ei ole olemassa");
		}
	}


	public void vuoronvaihto(){
		int i = 0;
		
		do{
			model.vaihdaVuoroa();
			if(i > 3){
				
			}
			i++;
		}
		while(!model.nappuloitaLaudalla(model.getVuorossa()));
	}
	
	public void kukaanEiVoittanut(){
		
		JOptionPane.showMessageDialog(null, "Kukaan ei selvinnyt edes kandiin asti!");
		GameStateManager.setGamestate(GameState.PELIOHI);
		int n = JOptionPane.showConfirmDialog(null, "Haluatko pelata uudelleen? Jos vaikka nyt joku saisi kandin kasaan!");
		if(n == JOptionPane.YES_NO_OPTION){
			view.showPlayersMenu();
		}
		if(n == JOptionPane.NO_OPTION){
			view.showMainMenu();
		}
		
	}
	
	//---------------------------------------------
	//Apumetodi tallenuksen j‰lkeiseen tekstiruutujen (vuorossaLabel & instructionLabel) p‰ivitt‰miseen ajan tasalle
	public void updateTextsAfterSaveLoad(){
		//P‰ivitet‰‰n ensin vuorossaLabel
		view.updateTurnInfo(model.getVuorossa().getPNimi());
		
		//P‰ivitet‰‰n sitten peliohje
		if(GameStateManager.getGamestate() == GameState.NOSTO){
			view.updateInfo("Nostahan kortti!");
		}
		if(GameStateManager.getGamestate() == GameState.SIIRTO){
			view.updateInfo("Laita teekkarisi liikeelle!");
		}
		if(GameStateManager.getGamestate() == GameState.LAUTAMUUTTUU){
			view.updateInfo("Lauta muuttuu!");
		}
		else{
			//Do nothing
		}
	}
}
