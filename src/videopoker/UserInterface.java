package videopoker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class UserInterface extends JFrame {

//	Panels for placing cards and buttons
	private JPanel topPanel;
	private JPanel bottomPanel;
	private JPanel centerPanel;

//	JLabels and variabels for playing cards
	private JLabel[] cards;
	private Border border = new LineBorder(Color.BLACK, 2);

//	Components
	private JButton holdAndGetSecondHand;
	private JButton saveAndQuit;
	private JButton makeBet;
	private JTextField betThis;
	private JLabel playerCredits;
	private JCheckBox theme_music;
	private JCheckBox soundFX;

//	Player, game and deck.
	private Player player;
	private VideoPoker video;
	private Deck deck;

	private int volume = 0;

//	Constructor
	public UserInterface() throws ClassNotFoundException, IOException {

		setLayout(new BorderLayout());

		loadPlayer();
		video = new VideoPoker();

//		Instantiate panels
		topPanel = new JPanel();
		centerPanel = new JPanel();
		centerPanel.setPreferredSize(new Dimension(600, 200));
		bottomPanel = new JPanel();
		theme_music = new JCheckBox("Music Theme");
		soundFX = new JCheckBox("SoundFX");

//		Add panels to JFrame according to BorderLayout. 
		add(topPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);

//		Add cards (JLabels) to centerLabel
		cards = new JLabel[5];

//		Create labels for cards and add listeners	
		for (int i = 0; i < cards.length; i++) {
			cards[i] = new JLabel();
			centerPanel.add(cards[i]);
			cards[i].addMouseListener(l);
		}

//		Instantiate and add components (buttons and fields).
		holdAndGetSecondHand = new JButton("Hold - Get new cards");
		saveAndQuit = new JButton("Save & quit");
		makeBet = new JButton("Make bet");
		betThis = new JTextField(5);
		playerCredits = new JLabel();
		upDateScore(); // Checks and updates the credits

		topPanel.add(playerCredits);
		topPanel.add(betThis);
		topPanel.add(makeBet);
		bottomPanel.add(soundFX);
		bottomPanel.add(holdAndGetSecondHand);
		bottomPanel.add(saveAndQuit);
		bottomPanel.add(theme_music);

		holdAndGetSecondHand.setEnabled(false); // Inactivate get second hand.

//		Add button listeners
		holdAndGetSecondHand.addActionListener(e -> holdAndGetNewCards());
		makeBet.addActionListener(e -> checkBet());
		saveAndQuit.addActionListener(e -> saveAndQuit());
		soundFX.addActionListener(e -> enableSoundFX());
		theme_music.addActionListener(e -> musicTheme());

//		Final settings
		pack();
		setLocationRelativeTo(null); // Centers window. Needs to be called after "pack()" but before "setVisible()".
		setVisible(true);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

//	The listeners "mission" is to note which card the user chooses.
	MouseListener l = new MouseAdapter() {

		public void mouseClicked(MouseEvent e) {

			for (int i = 0; i < cards.length; i++) {
				if (e.getSource() == cards[i] && makeBet.isEnabled() == false) { // Note: The player is only allowed to
																					// mark cards in round one (thereof
																					// the makeBet boolean)
					if (cards[i].getBorder() == border) {
						cards[i].setBorder(null);
					} else {
						cards[i].setBorder(border);
					}

				}
			}

		}

	};

	public void enableSoundFX() {
		if (soundFX.isSelected()) {
			volume = 1;
		} else {
			volume = 0;
		}
	}

//	Method for getting player. If no player is found a new one is instantiated (demo feature).
	public void loadPlayer() throws IOException, ClassNotFoundException {

		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("default_player"));
			player = (Player) ois.readObject();
			ois.close();
		} catch (FileNotFoundException fnf) {
			player = new Player(1000, "John Doe");
		}
	}

//	Get new hand
	public void getNewHand() {

		deck = new Deck(); // Instantiate new deck

		holdAndGetSecondHand.setEnabled(true); // Enable button for second hand.

		for (int i = 0; i < cards.length; i++) {
			Card card = deck.draw(); // Draws new card
			player.addCardToHand(card); // Adds the cards to the player hand
			cards[i].setIcon(card.getFileName()); // Get image for card
			cards[i].setBorder(null); // Resets the border.
		}
	}

//	The method replaces the "unchosen" cards
	public void holdAndGetNewCards() {

		holdAndGetSecondHand.setEnabled(false); // Inactivate get second hand.

		for (int i = 0; i < cards.length; i++) {
			if (cards[i].getBorder() != border) {
				player.removeCardFromHand(i); // Remove the card that the player does not want to keep
				Card card = deck.draw(); // Draws new card
				player.addCardToHand(i, card); // Adds the card to the hand.
				cards[i].setIcon(card.getFileName()); // Get image for card

			}
			cards[i].setBorder(null); // Resets the border.

//			Prepare for next round by activating the right buttons
			betThis.setEnabled(true);
			makeBet.setEnabled(true);

		}

//		Check result, multiply by winnings. Update score when payout is made. 
		int payout = checkResult() * player.getLastbet();
		player.setCredits(player.getCredits() + payout);
		upDateScore();

		player.clearHand();
	}

	
//	Checks the players hand againt the different combination. Returns an int (payout factor).
	public int checkResult() {
		int result = 0;

		try {
			result = video.getHandScore(player.getHand(), volume);
		} catch (Exception e) {
			System.out.println("ERROR!! Couldn´t get score");
		}
		return result;
	}

//	Method for saving player
	public void savePlayer() {

		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("default_player"));
			oos.writeObject(player);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error! couldn´t save player");
		}

	}

//	Make bet
	public void makeBet(int bet) {

		player.setCredits(player.getCredits() - bet);
		player.setLastBet(bet);

	}

//	Check that the user has entered a valid bet and activates buttons to proceed if ok. 
	public void checkBet() {

		int bet = 0;

//		Is the bet numeric?
		try {
			bet = Integer.parseInt(betThis.getText());
		} catch (NumberFormatException nfe) {
			betThis.setText("0");
			JOptionPane.showMessageDialog(null, "Please enter valid bet");
			return;
		}

//		Is the bet within the players limits?
		if (bet > player.getCredits()) {
			betThis.setText("0");
			JOptionPane.showMessageDialog(null, "You're trying to bet more than your holdings");
			return;
		}

//		"Open" the buttons for playing (and stop the player from changing bet).
		betThis.setEnabled(false);
		makeBet.setEnabled(false);

//		Bet ok -> make bet -> updateScore
		makeBet(bet);
		upDateScore();
		getNewHand();

	}

//	Lets the player quit and saves the player. 
	public void saveAndQuit() {
		player.resetLastBet(); // Resets the bets.
		player.clearHand();
		savePlayer();
		System.exit(0); // Exit system.
	}

//	Sets the credits to the current balance. 
	public void upDateScore() {
		playerCredits.setText(String.format("Player credits: %d", player.getCredits()));
	}

	Audio theme = new Audio();

//	Starts music theme
	public void musicTheme() {
		if (theme_music.isSelected()) {
			try {
				theme.playSound("audio/music_theme.wav", 0.2);
				theme_music.setVisible(false);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Couldn´t play music theme");
			}
		}
	}

}
