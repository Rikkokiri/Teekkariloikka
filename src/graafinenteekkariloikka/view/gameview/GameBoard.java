package graafinenteekkariloikka.view.gameview;

import graafinenteekkariloikka.model.enums.VRTila;
import graafinenteekkariloikka.model.enums.Vari;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JPanel;


public class GameBoard extends JPanel{

	private static final long serialVersionUID = -351311446661992346L;

	private BoardButton[][] ruudut;
	private final int PYSTY = 6;
	private final int VAAKA = 7; 
	private GridLayout grid;	
	
	private ActiveBoardButton[] activebuttons;

	public GameBoard(){
		
		grid = new GridLayout(PYSTY, VAAKA);
		setLayout(grid);
		this.setSize(540, 630);
		ruudut = new BoardButton[PYSTY][VAAKA];
		activebuttons = new ActiveBoardButton[28];
		
		addButtons();
		
	}

	/**
	 * Metodi, jolla asetetaan action listenerit kaikkiin pelilaudan aktiivisiin ruutuihin
	 * @param listener Asetettava action listener
	 */
	public void setGameBoardButtonListeners(ActionListener listener) {
		
		for(int i=0; i<PYSTY; i++){
			for(int j=0; j<VAAKA; j++){
				if(ruudut[i][j] instanceof ActiveBoardButton){
					((ActiveBoardButton)ruudut[i][j]).setActiveBoardButtonListener(listener);
				}
			}
		}	
	}
	
	/**
	 * Metodi, jolla luodaan pelilaudan ruudut ja lisätään ne pelilautaan.
	 */
	public void addButtons(){
		
		//Aktiiviset ruudut
		ruudut[0][0] = new SafeBoardButton(0);
		ruudut[0][1] = new SafeBoardButton(1);
		ruudut[0][2] = new SafeBoardButton(2);
		ruudut[0][3] = new UnsafeBoardButton(3);  //Vaararuutu
		ruudut[0][4] = new SafeBoardButton(4);
		ruudut[0][5] = new SafeBoardButton(5);
		ruudut[0][6] = new SafeBoardButton(6);
		
		ruudut[1][6] = new UnsafeBoardButton(7); //Vaararuutu
		ruudut[2][6] = new SafeBoardButton(8);
		ruudut[3][6] = new UnsafeBoardButton(9); //Vaararuutu
		ruudut[4][6] = new SafeBoardButton(10);
		ruudut[5][6] = new SafeBoardButton(11);
		
		ruudut[5][5] = new UnsafeBoardButton(12); //Vaararuutu
		ruudut[5][4] = new SafeBoardButton(13);
		ruudut[5][3] = new UnsafeBoardButton(14); //Vaararuutu
		ruudut[5][2] = new SafeBoardButton(15);
		ruudut[5][1] = new SafeBoardButton(16);
		ruudut[5][0] = new UnsafeBoardButton(17); //Vaararuutu
		
		ruudut[4][0] = new SafeBoardButton(18);
		ruudut[3][0] = new UnsafeBoardButton(19); //Vaararuutu
		ruudut[2][0] = new UnsafeBoardButton(20); //Vaararuutu
		
		ruudut[2][1] = new SafeBoardButton(21);
		ruudut[2][2] = new UnsafeBoardButton(22);  //Vaararuutu
		ruudut[2][3] = new SafeBoardButton(23);
		ruudut[2][4] = new UnsafeBoardButton(24);  //Vaararuutu
		
		ruudut[3][4] = new SafeBoardButton(25);
		ruudut[3][3] = new UnsafeBoardButton(26); //Vaararuutu
		
		//---------- MAALI -------------------------------------
		ruudut[3][2] = new SafeBoardButton(27);
		//ADJUST
		ruudut[3][2].setText("KANDI");
		ruudut[3][2].setOpaque(true);
		ruudut[3][2].setBackground(new Color(255, 215, 0)); //255, 215, 0 tai 255, 228, 181
		ruudut[3][2].setForeground(Color.black);
				
		//------------------------------------------------------
		
		//Taustana toimivat ruudut, "nurmikkoa" tai vastaavaa
		ruudut[1][0] = new BackgroundBoardButton();
		ruudut[1][1] = new BackgroundBoardButton();
		ruudut[1][2] = new BackgroundBoardButton();
		ruudut[1][3] = new BackgroundBoardButton();
		ruudut[1][4] = new BackgroundBoardButton();
		ruudut[1][5] = new BackgroundBoardButton();
		
		ruudut[2][5] = new BackgroundBoardButton();
		ruudut[3][5] = new BackgroundBoardButton();
		ruudut[4][5] = new BackgroundBoardButton();
		
		ruudut[4][4] = new BackgroundBoardButton();
		ruudut[4][3] = new BackgroundBoardButton();
		ruudut[4][2] = new BackgroundBoardButton();
		ruudut[4][1] = new BackgroundBoardButton();
		
		ruudut[3][1] = new BackgroundBoardButton();
		
		
		for(int i=0; i<PYSTY; i++){
			for(int j=0; j<VAAKA; j++){
				this.add(ruudut[i][j]);
			}
		}
		
		/*Tallennetaan vielä manuaalisesti kaikki aktiiviset laudan painikkeet omaan listaansa
		 * Näin niiden päivittäminen ym. käsittely on helpompaa
		 */
		//Aktiiviset ruudut
		activebuttons[0] = (ActiveBoardButton)ruudut[0][0];
		activebuttons[1] = (ActiveBoardButton)ruudut[0][1];
		activebuttons[2] = (ActiveBoardButton)ruudut[0][2];
		activebuttons[3] = (ActiveBoardButton)ruudut[0][3];  //Vaararuutu
		activebuttons[4] = (ActiveBoardButton)ruudut[0][4];
		activebuttons[5] = (ActiveBoardButton)ruudut[0][5];
		activebuttons[6] = (ActiveBoardButton)ruudut[0][6];
			
		activebuttons[7] = (ActiveBoardButton)ruudut[1][6]; //Vaararuutu
		activebuttons[8] = (ActiveBoardButton)ruudut[2][6];
		activebuttons[9] = (ActiveBoardButton)ruudut[3][6]; //Vaararuutu
		activebuttons[10] = (ActiveBoardButton)ruudut[4][6];
		activebuttons[11] = (ActiveBoardButton)ruudut[5][6];
		
		activebuttons[12] = (ActiveBoardButton)ruudut[5][5]; //Vaararuutu
		activebuttons[13] = (ActiveBoardButton)ruudut[5][4];
		activebuttons[14] = (ActiveBoardButton)ruudut[5][3]; //Vaararuutu
		activebuttons[15] = (ActiveBoardButton)ruudut[5][2];
		activebuttons[16] = (ActiveBoardButton)ruudut[5][1];
		activebuttons[17] = (ActiveBoardButton)ruudut[5][0]; //Vaararuutu
			
		activebuttons[18] = (ActiveBoardButton)ruudut[4][0];
		activebuttons[19] = (ActiveBoardButton)ruudut[3][0]; //Vaararuutu
		activebuttons[20] = (ActiveBoardButton)ruudut[2][0]; //Vaararuutu
			
		activebuttons[21] = (ActiveBoardButton)ruudut[2][1];
		activebuttons[22] = (ActiveBoardButton)ruudut[2][2];  //Vaararuutu
		activebuttons[23] = (ActiveBoardButton)ruudut[2][3];
		activebuttons[24] = (ActiveBoardButton)ruudut[2][4];  //Vaararuutu
				
		activebuttons[25] = (ActiveBoardButton)ruudut[3][4];
		activebuttons[26] = (ActiveBoardButton)ruudut[3][3]; //Vaararuutu
		//Maali
		activebuttons[27] = (ActiveBoardButton)ruudut[3][2];
	}

	/**
	 * Metodi, jolla päivitetään lauta nappulan siirron jälkeen.
	 */
	
	public void updateGameBoardSiirto(Vari[] uudetvarit){
		for(int i=0; i<28; i++){
			if(activebuttons[i] instanceof UnsafeBoardButton){
				if( ((UnsafeBoardButton)activebuttons[i]).getUnsafeButtonState() == VRTila.AUKI){
					//Do nothing
				}
				else{
					activebuttons[i].setVari(uudetvarit[i]);
				}
			}
			else{
				activebuttons[i].setVari(uudetvarit[i]);
			}
		}
	}
	
	/**
	 * Metodi, jolla päivitetään lauta sen jälkeen, kun on nostettu lauta muuttuu -kortti.
	 */
	public void updateGameBoardLautaMuuttuu(VRTila[] unsafeStates){
		
		int k = 0;

		for(int i=0; i<28; i++){
			if(activebuttons[i] instanceof UnsafeBoardButton){
				if(activebuttons[i].getVari() != null && unsafeStates[k] == VRTila.KIINNI){
					//Do nothing
				}
				else{
					((UnsafeBoardButton)activebuttons[i]).setKuoppa(unsafeStates[k]);
				}
				k++;
			}
		}	
	}
}
