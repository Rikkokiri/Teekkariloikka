package graafinenteekkariloikka.view.gameview;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;


public class GameView extends JPanel {

	private static final long serialVersionUID = -1354797133492810767L;

	//Panels and toolbar
		private GameBoardBasePanel gameBoardBase;
		private GamePiecesPanel piecesPanel;
		private JToolBar gameViewToolbar;
		
	//Buttons (toolbar)
		private JButton quitGameButton;
		private JButton saveGameButton;
		
	//private PelikorttiLabel pelikortti; //TODO add
		
	//Layouts
		BorderLayout bordL = new BorderLayout();
		GridBagLayout gameViewGb = new GridBagLayout();
		GridBagConstraints gameViewBag = new GridBagConstraints();
	
	public GameView(){
		//Asetetaan layout (BorderLayout)
		setLayout(bordL);
		
		//Luodaan työkalurivi
		createToolbar();
		
		//Luodaan pohjapaneeli, johon pelilauta tulee
		createGameBoardPanel();
		
		//Luodaan pohjapaneeli, johon pelinappulat yms. tulevat
		createGamePiecesPanel();
		
	}

//---- Metodit pelinäkymän komponenttien luomiseen
	/**
	 * Metodi pelinäkymän työkalurivin luomiseen
	 */
	public void createToolbar(){
		
		//Työkalurivi pelinäkymään
		gameViewToolbar = new JToolBar(JToolBar.HORIZONTAL);
		gameViewToolbar.setFloatable(false);
		
		//setBorder(new EmptyBorder(5, 10, 10, 10)); //TODO adjust
		add(gameViewToolbar, BorderLayout.PAGE_START);
		
		//Buttons
		quitGameButton = new JButton("Lopeta tallentamatta");
		saveGameButton = new JButton("Tallenna ja lopeta");
		
		//Lisätään painikkeita työkaluriville
		gameViewToolbar.add(saveGameButton); //TODO Add functionality!
		gameViewToolbar.add(quitGameButton); //TODO Add functionality!
	
	}	
	
	/**
	 * Metodi pelinäkymän pelilaudan luomiseen
	 */
	public void createGameBoardPanel(){
		//////// Pelilautana toimiva paneeli //////////
		gameBoardBase = new GameBoardBasePanel();
		add(gameBoardBase, BorderLayout.WEST);
	}
	
	/**
	 * Metodi, jolla luodaan GamePiecesPanel, johon tulevat
	 * (a) Sivussa odottavat nappulat 
	 * (b) Nostettu kortti
	 * (c) Painike kortin nostamista varten
	 * @param n //TODO
	 */
	public void createGamePiecesPanel() {
		///////// Paneeli, johon nappulat yms. sijoitetaan //////////
		piecesPanel = new GamePiecesPanel();
		add(piecesPanel, BorderLayout.EAST);
	}
	
//------- Methods for setting action listeners to buttons -----------
	public void setSaveGameButtonListener(ActionListener listener){
		saveGameButton.addActionListener(listener);
	}

	public void setQuitGameButtonListener(ActionListener listener){
		quitGameButton.addActionListener(listener);
	}	
		
	public void setGamePieceButtonListener(ActionListener listener){
		piecesPanel.setGamePieceButtonListener(listener);
	}
	
//--------------- Getterit paneeleille -------------------------
	
	public GamePiecesPanel getGamePiecesPanel(){
		return piecesPanel;
	}
	
	public GameBoardBasePanel getGameBoardPanel(){
		return gameBoardBase;
	}
}
