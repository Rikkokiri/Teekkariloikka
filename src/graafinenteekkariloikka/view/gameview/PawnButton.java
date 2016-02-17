package graafinenteekkariloikka.view.gameview;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import graafinenteekkariloikka.model.enums.Vari;

import javax.swing.*;

public class PawnButton extends JButton {

	private static final long serialVersionUID = -7628095801121724418L;

	private Vari vari;
	private final int index;
	private ImageIcon pawnIcon, silhouetteIcon;
	
	public PawnButton(Vari vari, int index){
		
		this.vari = vari;
		this.index = index;
		
		setPreferredSize(new Dimension(80, 80)); //TODO Adjust
		//FIXME
		setContentAreaFilled(false);
		setBorderPainted(false);
		
		silhouetteIcon = new ImageIcon(this.getClass().getResource("/icons/vaaleasiluetti.png")); //TODO
		
		chooseIcon(vari);
		setIcon(pawnIcon);
	
	}
	
	/**
	 * TODO Metodikuvaus
	 * @param vari
	 */
	public void chooseIcon(Vari vari){
		switch(vari){
			case SININEN:
				pawnIcon = new ImageIcon(this.getClass().getResource("/icons/teekkarisininen.png")); //TODO
				break;
			case KELTAINEN:
				pawnIcon = new ImageIcon(this.getClass().getResource("/icons/teekkarikeltainen.png"));
				break;
			case PINKKI:
				pawnIcon = new ImageIcon(this.getClass().getResource("/icons/teekkaripinkki.png"));
				break;
			case VIOLETTI:
				pawnIcon = new ImageIcon(this.getClass().getResource("/icons/teekkarivioletti.png"));
				break;
		}
	}
	
	//Kun painiketta painetaan, muuttuu sen ulkonäkö
	public void setPawnButtonPressed(){
		setIcon(silhouetteIcon);
	}
	
	//---- Getters --------------
	
	public Vari getColor(){
		return vari;
	}
	
	public int getIndex(){
		return index;
	}
	
	//Action listener
	public void setPawnButtonListener(ActionListener listener){
		this.addActionListener(listener);
	}
	
}
