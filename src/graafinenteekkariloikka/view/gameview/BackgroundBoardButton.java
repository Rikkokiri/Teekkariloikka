package graafinenteekkariloikka.view.gameview;

import java.awt.Color;

/**
 * Passiivinen laudan painike
 * Ei sisällä toiminnallisuutta
 * Olemassa vain käytännöllisistä ja esteettisistä syistä
 * @author Pilvi Rajala
 */

public class BackgroundBoardButton extends BoardButton{

	private static final long serialVersionUID = 1079129545337956788L;

	public BackgroundBoardButton(){
		super();
		setBackground(new Color(124, 252, 0));
		setEnabled(false);
	}
}
