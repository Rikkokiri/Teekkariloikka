package graafinenteekkariloikka.view.gameview;

import javax.swing.*;
import java.awt.*;

import graafinenteekkariloikka.model.enums.Kt;

public class CardLabel extends JLabel {

	private static final long serialVersionUID = 4447567343874715527L;

	private ImageIcon YKSI, KAKSI, KOLME, LM;
	
	public CardLabel(){
		
		setVerticalTextPosition(CENTER);
		setHorizontalTextPosition(CENTER);
		
		setPreferredSize(new Dimension(150, 225));
		
		YKSI = new ImageIcon(this.getClass().getResource("/icons/KorttiYksi.png"));
		KAKSI = new ImageIcon(this.getClass().getResource("/icons/KorttiKaksi.png"));
		KOLME = new ImageIcon(this.getClass().getResource("/icons/KorttiKolme.png"));
		LM = new ImageIcon(this.getClass().getResource("/icons/KorttiLautaMuuttuu.png"));
		
	}
	
	public void naytaKortti(Kt kt){
		switch(kt){
			case YKSI:
				setIcon(YKSI); //TODO
				break;
			case KAKSI:
				setIcon(KAKSI);
				break;
			case KOLME:
				setIcon(KOLME);
				break;
			case LM:
				setIcon(LM);
				break;
		}
	}	
}