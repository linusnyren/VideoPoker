package videopoker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class UserInterface extends JFrame {
	
//	Panels for placing cards and buttons
	private JPanel topPanel;
	private JPanel bottomPanel;
	private JPanel centerPanel;
	
//	JLabels and variabels for playing cards
	private JLabel [] cards;
	private String filepath = "C:\\Users\\ceder\\Documents\\Yrgo filer\\Agila metoder\\Projektarbete\\Playing_Cards_Small\\JPEG\\";
	private Border border = new LineBorder(Color.BLACK, 2);
	
//	Components
	private JButton getNewHand;
	private JButton holdAndGetSecondHand;
	
//	Player
	private Player player;
	private Deck deck;
	
	
	
//	Constructor
	public UserInterface () {
		
		setLayout(new BorderLayout());
		
		player = new Player(100, "John Doe");
		
//		Instatiate panels
		topPanel = new JPanel();
		centerPanel = new JPanel();
		centerPanel.setPreferredSize(new Dimension (600, 200));
		bottomPanel = new JPanel();
		
//		Add panels to JFrame
		add(topPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
		
//		Add cards (JLabels) to centerLabel
		cards = new JLabel [5];

		
		for (int i = 0 ; i < cards.length ; i++ ) {
			cards[i] = new JLabel();
			centerPanel.add(cards[i]);
			cards[i].setIcon(new ImageIcon(getClass().getResource("/2_of_spades.jpg")));
			cards[i].addMouseListener(l);
		}
		
		
//		Add buttons
		getNewHand = new JButton("New hand");
		holdAndGetSecondHand = new JButton("Hold - Get new cards");
		topPanel.add(getNewHand);
		bottomPanel.add(holdAndGetSecondHand);
		
//		Add button listeners
		getNewHand.addActionListener( e -> getNewHand());
		
//		Final settings
		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		
	}
	
//	The listeners "mission" is to note which card the user chooses.
	MouseListener l = new MouseAdapter() {
		
		public void mouseClicked(MouseEvent e) {
			
			for (int i = 0 ; i < cards.length ; i++ ) {
				if (e.getSource() == cards[i]) {
					if (cards[i].getBorder() == border) {
						cards[i].setBorder(null);
					} else {
						cards[i].setBorder(border);
					}
					
				}
			}
			
		}

	};
	
//	Method for getting player
	public void loadPlayer () {
	
		
		
		
		
		
	}
	
	
//	Get new hand
	public void getNewHand() {
		
		deck = new Deck(); //Instantiate new deck

		
		for (int i = 0 ; i < cards.length ; i++ ) {
			Card card = deck.draw();
			player.addCardToHand(card);
			cards[i].setIcon(new ImageIcon(getClass().getResource("/2_of_hearts.jpg")));
		}
		
		for (Card card : player.getHand()) {
			System.out.println(card);
		}
		
	}
	
	
//	Method for saving player
	public void savePlayer() {
		
		
		
		
		
		
		
	}
	
	
//	Make bet
	public void makeBet () {
		
		
		
		
		
		
	}
	
	
	

}
