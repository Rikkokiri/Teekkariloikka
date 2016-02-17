package graafinenteekkariloikka.view.gameview;

import java.awt.Dimension;

import javax.swing.JButton;

/**
 * Yl�luokka BoardButton
 * Ei ole tarkoitettu k�ytett�v�ksi
 * Mahdollistaa vain passiivisten ja aktiivisten laudan nappuloiden s�il�misen samaan matriisiin
 * @author Pilvi Rajala
 */

public abstract class BoardButton extends JButton{

	private static final long serialVersionUID = 7870312574626261504L;

	public BoardButton(){		
		setPreferredSize(new Dimension(90, 90)); //TODO Adjust
	}
}
