package graafinenteekkariloikka.view.gameview;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class GameBoardBasePanel extends JPanel {

	private static final long serialVersionUID = 2729113284901230299L;
	
	private GameBoard gameBoard;
	private TurnPanel turnPanel;
	
	private GridBagLayout boardPanelLayout = new GridBagLayout();
	private GridBagConstraints c = new GridBagConstraints();
	
	
	public GameBoardBasePanel(){
		
		Dimension size = getPreferredSize();
		size.width = 700;
		setPreferredSize(size);
	
		setBackground(new Color(153, 217, 234)); //TODO Change backgroundcolor?
		
		setLayout(boardPanelLayout);
		
		/////////// PELIVUORON NÄYTTÄVÄN LABELIN LISÄÄMINEN ///////////////////
		turnPanel = new TurnPanel();
		
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.insets = new Insets(10, 30, 10, 10);
		c.weightx = 0.2;
		c.weighty = 0.1;
		c.gridy = 0;
		add(turnPanel, c);
		
		/////// PELILAUDAN LISÄÄMINEN JA ASETTELU
		c.anchor = GridBagConstraints.SOUTHWEST;
		c.weightx = 0.1;
		c.weighty = 0.8;
		c.gridy = 1;
		c.insets = new Insets(10, 30, 30, 10);
		gameBoard = new GameBoard();
		add(gameBoard, c);
		
	}
	
	public void setGameBoardButtonListeners(ActionListener listener){
		gameBoard.setGameBoardButtonListeners(listener);
	}
	
//------ Getterit ---------------	//TODO Tarpeen?
	public GameBoard getGameBoard(){
		return gameBoard;
	}
	
	public TurnPanel getTurnPanel(){
		return turnPanel;
	}

}
