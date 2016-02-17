package graafinenteekkariloikka.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PlayersMenu extends JPanel{

	private static final long serialVersionUID = -1272546128207505961L;

	//Label for instructions
	//private JLabel infoLabel;
	
	//Buttons
	private JButton inputNumberButton;
	private JButton cancelButton;
	
	//Radio buttons
	private JRadioButton twoButton;
	private JRadioButton threeButton;
	private JRadioButton fourButton;
	
	//Button group for radio buttons
	private ButtonGroup radioGroup;
	
	//Fonts
	//private Font ohjeFont = new Font("Arial", Font.PLAIN, 30); //TODO
	//private Font menuFont = new Font("Arial", Font.BOLD, 15); //TODO Adjust
	private Font radioFont = new Font("OCR A Extended", Font.BOLD, 25); //TODO
	
	//GridBagLayout
	private GridBagLayout playersMenuLayout = new GridBagLayout();
	private GridBagConstraints c = new GridBagConstraints();
	
	//Background
	private Image tausta = new ImageIcon(this.getClass().getResource("/icons/PlayersMenu.png")).getImage();
	
	public PlayersMenu(){
		
		setLayout(playersMenuLayout);
		
		setBackground(new Color(153, 217, 234));
		
	//OK button
		inputNumberButton = new JButton("OK");
		//inputNumberButton.setFont(menuFont);
		inputNumberButton.setPreferredSize(new Dimension(200, 80));
		inputNumberButton.setContentAreaFilled(false);
		
	//Cancel button
		cancelButton = new JButton("Peruuta");
		//cancelButton.setFont(menuFont);
		cancelButton.setPreferredSize(new Dimension(200, 80));
		cancelButton.setContentAreaFilled(false);
		
	////// Radio buttons /////////////
		//Two button
		twoButton = new JRadioButton("Kaksi");
		twoButton.setFont(radioFont);
		twoButton.setBackground(new Color(153, 217, 234));
		twoButton.setSelected(true);
		
		//Three button
		threeButton = new JRadioButton("Kolme"); 
		threeButton.setFont(radioFont);
		threeButton.setBackground(new Color(153, 217, 234));
		
		//Four button
		fourButton = new JRadioButton("Neljä");
		fourButton.setFont(radioFont);
		fourButton.setBackground(new Color(153, 217, 234));
		
		//Radio buttons to the button group
		radioGroup = new ButtonGroup();
		
		radioGroup.add(twoButton);
		radioGroup.add(threeButton);
		radioGroup.add(fourButton);
	//////////////////////////////////////		
		
	//Ohje //>>>>> KORVATTU TAUSTAAN SISÄLTYVÄLLÄ TEKSTILLÄ! <<<<<<<<
		
		//infoLabel = new JLabel("Valitse pelaajien lukumäärä.");
		//infoLabel.setFont(ohjeFont);
		
	////////////// VALIKON ASETTELU //////////////////////
		
		
		//c.insets = new Insets(10, 100, 50, 10);
		c.gridx = 0;
		c.gridy = 1;

		//c.anchor = GridBagConstraints.CENTER;
		//c.fill = GridBagConstraints.HORIZONTAL;
		
		//add(infoLabel);
		
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.WEST;
		
		c.insets = new Insets(60, 125, 10, 10);
		c.gridy = 2;
		add(twoButton, c);
		c.insets = new Insets(10, 125, 10, 10);
		c.gridy = 3;
		add(threeButton, c);
		c.gridy = 4;
		add(fourButton, c);
		
		c.insets = new Insets(47, 100, 10, 55);
		c.gridy = 5;
		add(inputNumberButton, c);
		
		c.insets = new Insets(10, 100, 10, 55);
		c.gridy = 6;
		add(cancelButton, c);
		
	}
	
	//Override paintComponent
	public void paintComponent( Graphics g ){
		super.paintComponent( g );
		g.drawImage(tausta, 0, 0, null);
		
		twoButton.repaint();
		threeButton.repaint();
		fourButton.repaint();
		
		inputNumberButton.repaint();
		cancelButton.repaint();
		
		g.dispose();
			}
	
	/**
	 * Metodi, joka palauttaa valitun radio buttonin mukaisen arvon
	 * @return
	 */
	public int getNumber(){
		if(twoButton.isSelected()){
			return 2;
		}
		if(threeButton.isSelected()){
			return 3;
		}
		else{
			return 4;
		}
	}
	
	//Methods for setting action listeners
	public void setInputNumberButtonListener(ActionListener listener){
		inputNumberButton.addActionListener(listener);
	}
	
	public void setCancelButtonListener(ActionListener listener){
		cancelButton.addActionListener(listener);
	}
	
}
