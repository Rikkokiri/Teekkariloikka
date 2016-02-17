package graafinenteekkariloikka.view.gameview;

import java.awt.Color;

//TODO LUOKKAKUVAUS
/**
 *
 * @author Pilvi Rajala
 */
public class SafeBoardButton extends ActiveBoardButton{

	private static final long serialVersionUID = 1L;

	public SafeBoardButton(int index){
		super(index);
		setBackground(new Color(250, 250, 210)); //ADJUST 250, 250, 210
	}
}
