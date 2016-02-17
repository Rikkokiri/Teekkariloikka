package graafinenteekkariloikka.view.gameview;

import java.awt.Color;
import graafinenteekkariloikka.model.enums.VRTila;
import javax.swing.ImageIcon;
import graafinenteekkariloikka.view.gameview.ActiveBoardButton;

public class UnsafeBoardButton extends ActiveBoardButton{

	private static final long serialVersionUID = 1086704622202671813L;

	private ImageIcon Kuoppa;
	private VRTila unsafeButtonState;
	
	public UnsafeBoardButton(int index) {
		super(index);
		unsafeButtonState = VRTila.KIINNI;
		
		//TODO
		Kuoppa = new ImageIcon(this.getClass().getResource("/icons/gradientkuoppa.png"));
		
		setBackground(new Color(255, 216, 151)); //ADJUST 255, 228, 181 tai 245, 222, 179
		
	}

	/**
	 * Metodi ruudun kuopan avaamiseen ja sulkemiseen (ikonin vaihtamiseen).
	 * @param tila
	 */
	public void setKuoppa(VRTila tila){
		if(tila == VRTila.AUKI){
				unsafeButtonState = VRTila.AUKI;
				setIcon(Kuoppa);
		}
		if(tila == VRTila.KIINNI){
			unsafeButtonState = VRTila.KIINNI;
			setIcon(null);
		}
	}
	
	//Kertoo, onko kuoppa auki vai kiinni
	public VRTila getUnsafeButtonState(){
		return unsafeButtonState;
	}
	
}
