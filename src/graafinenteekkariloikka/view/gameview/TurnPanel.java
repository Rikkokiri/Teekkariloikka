package graafinenteekkariloikka.view.gameview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


public class TurnPanel extends JLabel {

	private static final long serialVersionUID = -6256053672600144322L;
	
	private JLabel vuorossaLabel;
	private JLabel instructionLabel;
	
	private Font vuorossaFont = new Font("Arial", Font.BOLD, 20);
	private Font instructionsFont = new Font("Arial", Font.BOLD, 15);
	
	private BorderLayout borderL = new BorderLayout();
	
	
	public TurnPanel() {

		setPreferredSize(new Dimension(630, 120)); //ADJUST
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		setOpaque(true);
		setLayout(borderL);
		
	//--- Labels ------
		//vuorossaLabel
		vuorossaLabel = new JLabel();
		vuorossaLabel.setPreferredSize(new Dimension(630, 40));
		vuorossaLabel.setFont(vuorossaFont);
		vuorossaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		vuorossaLabel.setBackground(Color.WHITE);
		vuorossaLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		vuorossaLabel.setOpaque(true);
		vuorossaLabel.setVisible(true);
		this.add(vuorossaLabel, BorderLayout.NORTH);
		
		//instructionLabel
		instructionLabel = new JLabel();
		instructionLabel.setPreferredSize(new Dimension(630, 80));
		instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		instructionLabel.setBackground(new Color(255, 215, 0));
		instructionLabel.setFont(instructionsFont);
		instructionLabel.setOpaque(true);
		instructionLabel.setVisible(true);
		
		this.add(instructionLabel, BorderLayout.SOUTH);
		
	}
	
	public void setTurnText(String nimi){
		vuorossaLabel.setText("Vuorossa pelaaja " + nimi);
	}
	
	public void setInfoText(String s){
		instructionLabel.setText(s);
	}
	
}
