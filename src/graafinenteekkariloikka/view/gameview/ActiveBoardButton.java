package graafinenteekkariloikka.view.gameview;

import java.awt.event.ActionListener;
import graafinenteekkariloikka.model.enums.Vari;
import javax.swing.ImageIcon;

public class ActiveBoardButton extends BoardButton {
	
	private static final long serialVersionUID = 3537401718935225291L;

	private ImageIcon S, K, P, V;
	
	//Index kertoo monesko ruutu on kyseessä
	private final int index;
	//Ruudussa olevan nappulan väri (Jos ruuduss on nappula)
	Vari vari;
	
	public ActiveBoardButton(int index){
		super();
		this.index = index;
		vari = null;
		setIcon(null);
		
		S = new ImageIcon(this.getClass().getResource("/icons/teekkarisininen.png"));
		K = new ImageIcon(this.getClass().getResource("/icons/teekkarikeltainen.png"));
		P = new ImageIcon(this.getClass().getResource("/icons/teekkaripinkki.png"));
		V = new ImageIcon(this.getClass().getResource("/icons/teekkarivioletti.png"));
		
	}
	
	/**
	 * Metodi action listenerin asettamiseen
	 * @param listener Asetettava action listener
	 */
	public void setActiveBoardButtonListener(ActionListener listener){
		this.addActionListener(listener);
	}
	
	/**
	 * Palauttaa ruudun indeksin. Indeksi on
	 * @return index
	 */
	public int getIndex(){
		return index;
	}
	
	/**
	 * Palauttaa ruudun senhetkisen värin.
	 * Toisin sanoen kertoo, minkä värinen teekkari ruudussa on vai onko ruutu tyhjä (vari = null)
	 * @return vari Ruudun vari
	 */
	public Vari getVari(){
		return vari;
	}

	/**
	 * Metodi ruudussa näkyvän nappulan vaihtamiseen
	 * @param v
	 */
	public void setVari(Vari v){
		vari = v;
		
		if(v == null){
			setIcon(null);
		}
		else{
			switch(v){
				case SININEN:
					setIcon(S);
					break;
				case KELTAINEN:
					setIcon(K);
					break;
				case PINKKI:
					setIcon(P);
					break;
				case VIOLETTI:
					setIcon(V);
					break;
			}
		}
	}
}

