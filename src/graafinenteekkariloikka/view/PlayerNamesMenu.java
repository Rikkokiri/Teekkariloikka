package graafinenteekkariloikka.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class PlayerNamesMenu extends JPanel {

	private static final long serialVersionUID = 8018355829430371371L;

	private GridBagLayout menuLayout = new GridBagLayout();
	private GridBagConstraints c = new GridBagConstraints();
	
	private JLabel ohjeLabel;
	private Font ohjeFont = new Font("OCR A Extended", Font.BOLD, 40);
	
	private JButton inputNamesButton;
	private Font buttonFont = new Font("OCR A Extended", Font.BOLD, 22);
	
	private ArrayList<JLabel> labels;
	private Font nameLabelFont = new Font("OCR A Extended", Font.BOLD, 20);
	private ArrayList<JTextField> fields;
	
	
	public PlayerNamesMenu(){
		
		labels = new ArrayList<JLabel>();
		fields = new ArrayList<JTextField>();
		
		setLayout(menuLayout);
		setBackground(new Color(153, 217, 234));
		
		ohjeLabel = new JLabel("Anna pelaajien nimet");
		ohjeLabel.setFont(ohjeFont);
	
		inputNamesButton = new JButton("Jatka");
		inputNamesButton.setFont(buttonFont);
		inputNamesButton.setPreferredSize(new Dimension(200, 80));
		inputNamesButton.setBackground(new Color(252, 205, 20));
		inputNamesButton.setBorder(new LineBorder(Color.BLACK, 3));
	}

	/**
	 * Metodi tarvittavien Labeleiden luomiseen
	 * @param n Labeleiden lukum‰‰r‰
	 */
	public void createLabels(int n){
		
		String[] varit = {" (sininen) ", " (keltainen) ", " (pinkki) ", " (violetti)"};
		
		for(int i=0; i<n; i++){
			labels.add(new JLabel("Pelaajan " + (i+1) + varit[i] + " nimi:"));
			labels.get(i).setFont(nameLabelFont);
		}
	}
	
	/**
	 * Metodi tarvittavien tekstikenttien luomiseen
	 * @param n Tekstikenttien lukum‰‰r‰
	 */
	public void createTextFields(int n){
		for(int i=0; i<n; i++){
			fields.add(new JTextField(20));
		}
	}
	
	/**
	 * Metodi paneelin/n‰kym‰n kokoamiseen.
	 */
	public void buildPanel(int n){
		
		c.weightx = 1;
		c.weighty = 0.5;
		c.insets = new Insets(10, 10, 10, 10);
		
		
		c.anchor = GridBagConstraints.CENTER;
		c.gridy = 0;
		
		add(ohjeLabel, c);
		
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 10, 10, 300);
		
		int gridx = 0;
		int gridy = 1;
		c.weightx = 0.2;
		
		for(int j=0; j<n; j++){
			c.gridx = gridx;
			c.gridy = gridy;
			
			add(labels.get(j), c);
			gridy++;	
		}
		
		gridy=1; //resetoidaan
		gridx=0;
		c.weightx = 2;
		
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(10, 10, 10, 300);
		
		for(int j=0; j<n; j++){
			c.gridx = gridx;
			c.gridy = gridy;
			
			add(fields.get(j), c);
			gridy++;	
		}
		
		gridy++;
		c.gridy = gridy;
		c.insets = new Insets(10, 10, 10, 10);
		c.anchor = GridBagConstraints.CENTER;
		
		add(inputNamesButton, c);
		
	}
	
	/**
	 * Metodi, joka palauttaa ArrayListin tekstikenttiin syˆteytyist‰ nimist‰.
	 * @return
	 */
	public ArrayList<String> getNameInputs(){
		ArrayList<String> names = new ArrayList<String>();
		
		for(int i=0; i<fields.size(); i++){
			names.add(fields.get(i).getText());
		}
		
		return names;
	}
	
	public void setInputNamesButtonListener(ActionListener listener){
		inputNamesButton.addActionListener(listener);
	}
	
}
