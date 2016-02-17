package graafinenteekkariloikka.view;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;


public class MainMenu extends JPanel {
	
	private static final long serialVersionUID = -4716544765010839920L;
	
	//Buttons
	private JButton newGameButton;
	private JButton loadSaveButton;
	private JButton quitButton;
	
	//Font
	Font menuFont = new Font("Arial", Font.BOLD, 24);
	
	//Layout
	private GridBagLayout menuLayout = new GridBagLayout();
	private GridBagConstraints c1 = new GridBagConstraints();
	
	private Image tausta = new ImageIcon(this.getClass().getResource("/icons/MainMenu.png")).getImage();
	
	// <><><> KONSTRUKTORI <><><>
	public MainMenu(){
		
		//Buttons
		newGameButton = new JButton("Uusi peli");
		loadSaveButton = new JButton("Lataa tallennus"); //TODO teksti
		quitButton = new JButton("Lopeta");
		
		//Uusi peli -painike
		newGameButton.setPreferredSize(new Dimension(250, 100)); //TODO Adjust
		newGameButton.setFont(menuFont);
		newGameButton.setContentAreaFilled(false);
		newGameButton.setBorderPainted(false);
		
		//Lopeta-painike
		loadSaveButton.setPreferredSize(new Dimension(250, 100)); //TODO Adjust
		loadSaveButton.setFont(menuFont);
		loadSaveButton.setContentAreaFilled(false);
		loadSaveButton.setBorderPainted(false);
		
		//Lopeta-painike
		quitButton.setPreferredSize(new Dimension(250, 100)); //TODO Adjust
		quitButton.setFont(menuFont);
		quitButton.setContentAreaFilled(false);
		quitButton.setBorderPainted(false);
		
		//PÄÄVALIKON ASETTELU
		this.setLayout(menuLayout);
		c1.insets = new Insets(145, 10, 10, 132);
		c1.gridx = 0;
		c1.gridy = 1;
		this.add(newGameButton, c1);
		c1.insets = new Insets(15, 10, 10, 132);
		c1.gridx = 0;
		c1.gridy = 2;
		this.add(loadSaveButton, c1);
		c1.gridx = 0;
		c1.gridy = 3;
		this.add(quitButton, c1);		
		
	}
	
	//Override paintComponent
		public void paintComponent( Graphics g ){
			super.paintComponent( g );
			g.drawImage(tausta, 0, 0, null);
			newGameButton.repaint();
			loadSaveButton.repaint();
			quitButton.repaint();
			g.dispose();
		}
	
//---- Action Listenerien lisäämien -----------
	
	public void setNewGameButtonListener(ActionListener listener){
		newGameButton.addActionListener(listener);
	}
	
	public void setLoadSaveButtonListener(ActionListener listener){
		loadSaveButton.addActionListener(listener);
	}
	
	public void setQuitButtonListener(ActionListener listener){
		quitButton.addActionListener(listener);
	}
	
}
