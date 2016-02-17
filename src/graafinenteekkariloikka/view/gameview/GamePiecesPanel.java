package graafinenteekkariloikka.view.gameview;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

import graafinenteekkariloikka.model.enums.Kt;
import graafinenteekkariloikka.model.enums.Vari;

/**
 * Paneeli, johon sijoitetaan
 * (1) sivussa olevat nappulat
 * (2) nostettu kortti
 * (3) painikkeet vuoron pelaamiseen
 * @author Pilvi Rajala
 */

public class GamePiecesPanel extends JPanel {

	private static final long serialVersionUID = 1417694759185712134L;

	private JPanel pawnPanel; //Panel for pawns (teekkari)
	private PawnButton[][] pawnButtons;
	private GridLayout grid = new GridLayout();
	
	//Button for drawing a card
	private JButton drawCardButton;
	//Button font
	private Font drawFont = new Font("OCR A Extended", Font.BOLD, 15); //TODO Adjust
	
	//Labels
	private CardLabel cardLabel;
	
	//Layout
	private GridBagLayout piecesLayout = new GridBagLayout();
	private GridBagConstraints c = new GridBagConstraints();
	
	public GamePiecesPanel(){

		Dimension size = getPreferredSize();
		size.width = 500; //TODO adjust
		setPreferredSize(size);
		setBackground(new Color(153, 217, 234)); //TODO Change backgroundcolour
		setLayout(piecesLayout);

	//----- PAINIKEPANEELI -----------------------------
		//Luodaan sivuun tuleva painikepaneeli ja siihen painikkeet

		pawnPanel = new JPanel();
		//pawnPanel.setPreferredSize(new Dimension(320, 320));
		pawnPanel.setBackground(Color.WHITE); //REMOVE
		pawnPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		
		c.anchor = GridBagConstraints.EAST;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 0;
		c.insets = new Insets(30, 10, 10, 30);
		
		add(pawnPanel, c);
		
	//------ PELIKORTTI (Label) --------------------------------------------
		
		c.anchor = GridBagConstraints.SOUTHEAST;

		c.insets = new Insets(10, 10, 10, 30);
		
		cardLabel = new CardLabel();
		add(cardLabel, c);
		
	//------- KORTINNOSTOPAINIKE -----------------------------------
		
		c.anchor = GridBagConstraints.SOUTHEAST;
		c.insets = new Insets(10, 10, 30, 30);
		
		drawCardButton = new JButton("Nosta kortti");
		drawCardButton.setPreferredSize(new Dimension(150, 60));
		drawCardButton.setFont(drawFont);
		drawCardButton.setBackground(new Color(252, 205, 20));
		drawCardButton.setBorder(new LineBorder(Color.BLACK, 3));
		add(drawCardButton, c);
		
	}
	
	/**
	 * TODO Metodikuvaus
	 * @param number
	 */
	public void createPawnPanel(int number){
		
		//Luodaan paneeli ja asetetaan siihen gridlayout
		grid = new GridLayout(number, 4);
		pawnPanel.setLayout(grid);
		pawnPanel.setBackground(Color.white);
		
		pawnButtons = new PawnButton[number][4];
			
		//Luodaan paneeliin tulevat nappulat
		createPawnButtons(number);
		
		for(int i=0; i<number; i++){
			for(int j=0; j<4; j++){
				pawnPanel.add(pawnButtons[i][j]);
			}
		}
		
	}
	
	/**
	 * Metodi, jolla luodaan pelinappulapainikkeet
	 * @param number Kuinka montaa eri väriä olevia nappuloita luodaan
	 */
	public void createPawnButtons(int number){
	
		Vari[] varit = {Vari.SININEN, Vari.KELTAINEN, Vari.PINKKI, Vari.VIOLETTI};
		
		for(int i=0; i<number; i++){
			for(int j=0; j<4; j++){
				pawnButtons[i][j] = new PawnButton(varit[i], j);
			}
		}
		
	}
	
	
	public void showCard(Kt korttityyppi){
		cardLabel.naytaKortti(korttityyppi);
	}
	
	
	public void setDrawCardButtonListener(ActionListener listener){
		drawCardButton.addActionListener(listener);
	}
	
	public void setGamePieceButtonListener(ActionListener listener) {
			for(int i=0; i<pawnButtons.length; i++){
				for(int j=0; j<4; j++){
					pawnButtons[i][j].setPawnButtonListener(listener); //Kutsuu PawnButton-luokan metodia
				}
			}
	}
	
	/**
	 * Getteri pawnPanelille
	 */
	public void updatePawnButton(Vari vari, int index){
		int rivi = 0;
		
		if(vari == Vari.SININEN){
			rivi = 0;
		}
		if(vari == Vari.KELTAINEN){
			rivi = 1;
		}
		if(vari == Vari.PINKKI){
			rivi = 2;
		}
		if(vari == Vari.VIOLETTI){
			rivi = 3;
		}
		pawnButtons[rivi][index].setPawnButtonPressed();
	}
	
}
