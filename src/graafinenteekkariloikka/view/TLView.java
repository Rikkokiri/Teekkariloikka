package graafinenteekkariloikka.view;

import graafinenteekkariloikka.model.enums.*;
import graafinenteekkariloikka.view.events.*;
import graafinenteekkariloikka.view.gameview.*;
import graafinenteekkariloikka.view.listenerinterfaces.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class TLView extends JFrame{

	private static final long serialVersionUID = -5665198394022105754L;
	
	private static final int WIDTH = 1200;
	private static final int HEIGHT = 800;
	
	//---- Panels -------------
	private JPanel basePanel;
	
	private MainMenu mainMenu;
	private PlayersMenu playersMenu;
	private PlayerNamesMenu namesMenu; 
	private InfoView infoView;
	private GameView gameView;
	
	private static final String MAINMENU = "Main Menu View";
	private static final String GAME = "Game View";
	private static final String PNUM = "Number of players menu";
	private static final String NAMES = "Players' names menu";
	private static final String INFO = "Info and rules view";
	
	//--- Action Listeners ---
		//Starting menus
	private NewGameButtonListener newGameButtonListener;
	private InputNumberListener inputNumberListener;
	private InputNamesListener inputNamesListener;
	private LaunchGameListener launchGameListener;
	
		//GameView
	private DrawCardListener drawCardListener;
	private BoardButtonListener boardButtonListener;
	private PawnListener pawnListener;
	
	private SaveGameListener saveGameListener;
	private QuitGameListener quitGameListener;
	private LoadSaveListener loadSaveListener;
	
	//--- Layout -----
	private CardLayout cardL = new CardLayout();
	
	
	// <><><> KONSTRUKTORI <><><>
	public TLView(){
	
	//----- Frame -------------
		setTitle("Teekkariloikka");
		setSize(WIDTH, HEIGHT); //1200 x 800
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Ikkunan kokoa ei voi muuttaa
		setResizable(false);
	
	//----- "basePanel" -------------
		//Luodaan basePanel, "pohjapaneeli", johon eri näkymät sijoitetaan
		basePanel = new JPanel();
		//Asetetaan "pohjapaneelin" layoutiksi CardLayout, jotta liikkuminen pelinäkymän ja valikon välillä onnistuu
		basePanel.setLayout(cardL);
		//Lisätään pohjapaaneli frameen
		add(basePanel);
		
	//----- Creating panels -------------	
		mainMenu = new MainMenu();
		playersMenu = new PlayersMenu();
		namesMenu = new PlayerNamesMenu();
		infoView = new InfoView();
		gameView = new GameView();
		
	
	//----- Adding panels to basePanel
		basePanel.add(mainMenu, MAINMENU);
		basePanel.add(playersMenu, PNUM);
		basePanel.add(namesMenu, NAMES);
		basePanel.add(infoView, INFO);
		basePanel.add(gameView, GAME);
		
		cardL.show(basePanel, MAINMENU);
	
	//----- BUTTONS AND ACTION LISTENERS ---------------
	
	//----- Main menu buttons -------------------
		//New game -button listener
	mainMenu.setNewGameButtonListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(newGameButtonListener != null){
				newGameButtonListener.newGamePerformed();
			}
		}
	});
	
	
	mainMenu.setQuitButtonListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e){
			dispose();
		}
	});
	

	mainMenu.setLoadSaveButtonListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(loadSaveListener != null){
				loadSaveListener.loadSavePerformed();
			}
		}
	});
	
	
	//----- Players number menu ---------------
	
		//Button for sending selected number in players number menu
	playersMenu.setInputNumberButtonListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			if(inputNumberListener != null){
				inputNumberListener.inputNumberPerformed( new NumberInputEvent(playersMenu.getNumber()));
			}
		}
	});
	
		//Cancel -button listener (Siirtää käyttäjän takaisin päävalikkoon)
	playersMenu.setCancelButtonListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			showMainMenu();
		}
	});
	
	//----- Player names menu button ----------
	
	namesMenu.setInputNamesButtonListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			if(inputNamesListener != null){
				inputNamesListener.inputNamesPerformed(new NamesInputEvent(namesMenu.getNameInputs()));
			}
		}
	});
	
	//----- Info view ---------------------------
		//Launch game -button
	infoView.setLaunchGameButtonListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(launchGameListener != null){
				launchGameListener.launchGamePerformed();
			}
		}
	});
	
	//---- Game view ------------
		
		//Draw card -button listener
	gameView.getGamePiecesPanel().setDrawCardButtonListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){

			if(drawCardListener != null){
				drawCardListener.drawCardPerformed();
			}			
		}
	});
	
	
		//ActiveBoardButton listeners
	//TODO Selitä ketjutus
	/*
	 * 
	 */
	gameView.getGameBoardPanel().setGameBoardButtonListeners(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			if(boardButtonListener != null){
				
				Object source = e.getSource();
				if(source instanceof ActiveBoardButton){
					int index = ((ActiveBoardButton) source).getIndex();
					Vari vari = ((ActiveBoardButton) source).getVari();
					
					boardButtonListener.boardButtonPressPerformed(new BoardButtonEvent(index, vari));;
				}
			}
		}
	});
	
	//----- Game view toolbar
	gameView.setSaveGameButtonListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if( saveGameListener != null ){
				saveGameListener.saveGamePerformed();
			}
		}
	});
	
	gameView.setQuitGameButtonListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if( quitGameListener != null ){
				quitGameListener.quitGamePerformed();
			}
		}
	});
	
	
	} //>>>>>>>>>>>> KONSTRUKTORI PÄÄTTYY <<<<<<<<<<<<<<<

	
	
//----- METHODS FOR SETTING SOME LISTENERS OUTSIDE CLASS ----------- //
	
	//New Game -button
	public void setNewGameButtonListener(NewGameButtonListener newGameButtonListener){
		this.newGameButtonListener = newGameButtonListener;
	}
	
	//Input number button (players menu)
	public void setInputNumberListener(InputNumberListener inputNumberListener){
		this.inputNumberListener = inputNumberListener;
	}
		
	//Input names -button (players' names menu)
	public void setInputNamesListener(InputNamesListener inputNamesListener){
		this.inputNamesListener = inputNamesListener;
	}
	
	//Launch game -button (info view)
	public void setLaunchGameButtonListener(LaunchGameListener launchGameButtonListener){
		this.launchGameListener = launchGameButtonListener;
	}
	
	//Draw card -button (PiecesPanel)
	public void setDrawCardButtonListener(DrawCardListener drawCardListener){
		this.drawCardListener = drawCardListener;
	}
	
	//Game board button listeners (GameBoard)
	public void setGameBoardButtonListeners(BoardButtonListener boardButtonListener){
		this.boardButtonListener = boardButtonListener;
	}
	
	//Pieces/pawn button listeners (Pieces Panel)
	public void setPawnListener(PawnListener pawnListener){
		this.pawnListener = pawnListener;
	}
	
	//Save button listener
	public void setSaveGameListener(SaveGameListener saveGameListener){
		this.saveGameListener = saveGameListener;
	}
	
	//Quit button listener
	public void setQuitGameListener(QuitGameListener quitGameListener){
		this.quitGameListener = quitGameListener;
	}	
	
	//Load save listener
	public void setLoadSaveListener(LoadSaveListener loadSaveListener){
		this.loadSaveListener = loadSaveListener;
	}
	
	
//----- METODIT ERI NÄKYMIEN NÄYTTÄMISEEN -----------
	
	public void showPlayersMenu(){
		cardL.show(basePanel, PNUM);
	}
	
	public void showMainMenu(){
		cardL.show(basePanel, MAINMENU);
	}
	
	public void showNamesMenu(){
		cardL.show(basePanel, NAMES);
	}
	
	public void showInfoView(){
		cardL.show(basePanel, INFO);
	}
	
	public void showGameView(){ 
		cardL.show(basePanel, GAME);
	}

//----- METHODS FOR CONSTRUCTING VIEWS ---------

	//CONSTRUCT PLAYER NAMES MENU
	public void constructPlayerNamesMenu(int num){
		namesMenu.createLabels(num);
		namesMenu.createTextFields(num);
		namesMenu.buildPanel(num);
	}
	
	//CONSTRUCT GAME VIEW
	/*
	 * 
	 */
	public void constructGameView(int num){
		
		//Create pawn panel
		gameView.getGamePiecesPanel().createPawnPanel(num);
		
		//Add action listeners to pawn buttons
		gameView.getGamePiecesPanel().setGamePieceButtonListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if( pawnListener != null ){

					Object source = e.getSource();
					
					if(source instanceof PawnButton){
						int index = ((PawnButton) source).getIndex();
						Vari vari = ((PawnButton) source).getColor(); 
					
						pawnListener.pawnPressPerformed(new PawnButtonEvent(index, vari));
					}
				}
			}
		});
	}

//---------- PÄIVITYSMETODIT --------------------------------	
	/**
	 * Metodi CardLabelin päivittämiseen
	 * @param korttityyppi
	 */
	public void updateCardLabel(Kt korttityyppi){
		gameView.getGamePiecesPanel().showCard(korttityyppi);
	}
	
	/**
	 * Metodi laudan päivittämiseen siirron jälkeen
	 */
	public void updateBoardAfterMove(Vari[] uudetvarit){
		gameView.getGameBoardPanel().getGameBoard().updateGameBoardSiirto(uudetvarit);
	}
	
	/**
	 * Metodi laudan päivittämiseen laudan muuttumisen jälkeen
	 */
	public void updateBoardAfterChange(VRTila[] turvattomientilat){
		gameView.getGameBoardPanel().getGameBoard().updateGameBoardLautaMuuttuu(turvattomientilat);
		gameView.revalidate();
		gameView.repaint();
	}
	
	public void updatePawnButton(Vari vari, int index){
		gameView.getGamePiecesPanel().updatePawnButton(vari, index);
	}
	
	public void updateTurnInfo(String nimi){
		gameView.getGameBoardPanel().getTurnPanel().setTurnText(nimi);
	}
	
	public void updateInfo(String s){
		gameView.getGameBoardPanel().getTurnPanel().setInfoText(s);
	}
}
