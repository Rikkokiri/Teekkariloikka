package graafinenteekkariloikka.view;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class InfoView extends JPanel {

	private static final long serialVersionUID = 5799963691841316324L;
	
	private JButton launchGameButton;
	private Font buttonFont = new Font("OCR A Extended", Font.BOLD, 22);
	
	private GridBagLayout infoLayout = new GridBagLayout();
	private GridBagConstraints c = new GridBagConstraints();
	
	private Image ohjetausta = new ImageIcon(this.getClass().getResource("/icons/ohjemateriaalisivu.png")).getImage();
	
	public InfoView(){
		
		setLayout(infoLayout);
		setBackground(new Color(153, 217, 234));
		
		launchGameButton = new JButton("Jatka peliin"); //TODO Teksti
		launchGameButton.setPreferredSize(new Dimension(200, 80));
		launchGameButton.setBackground(new Color(252, 205, 20));
		launchGameButton.setBorder(new LineBorder(Color.BLACK, 3));
		launchGameButton.setFont(buttonFont);
		
		c.weightx = 1;
		c.weighty = 1;
		c.insets = new Insets(600, 10, 10, 10);
		add(launchGameButton, c);
		
		
	}
	
	//Override paintComponent
	public void paintComponent( Graphics g ){
		super.paintComponent( g );
		g.drawImage(ohjetausta, 0, 0, null);
		
		launchGameButton.repaint();
		
		g.dispose();
			}
	
	public void setLaunchGameButtonListener(ActionListener listener){
		launchGameButton.addActionListener(listener);
	}
}
