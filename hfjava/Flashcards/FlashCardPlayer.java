import java.util.*; //arraylist
import java.awt.event.*; //events
import javax.swing.*; //gui
import java.awt.*; //Event listeners
import java.io.*; //File io

public class FlashCardPlayer{
	
	private JTextArea display;
	private JTextArea answer;
	private ArrayList cardList;
	private FlashCard currentCard;
	private Iterator cardIterator;
	private JFrame frame;
	private JButton nextButton;
	private boolean isShowAnswer;

	public static void main(String[] args)
	{
		FlashCardPlayer gui = new FlashCardPlayer();
		gui.go();	
	}

	//Build the gui
	public void go()
	{
		frame = new JFrame("Flash Card Player");
		JPanel mainPanel = new JPanel();
		Font bigFont = new Font("sanserif", Font.BOLD, 24);
		
		display = new JTextArea(10, 50);
		display.setFont(bigFont);
		display.setLineWrap(true);
		display.setWrapStyleWord(true);
		display.setEditable(false);
		
		JScrollPane qScroller = new JScrollPane(display);
		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		nextButton = new JButton("Show Question");
		
		mainPanel.add(qScroller);
		mainPanel.add(nextButton);
		nextButton.addActionListener(new NextCardListener());
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		
		JMenuItem loadMenuItem = new JMenuItem("Load card set");
		
		loadMenuItem.addActionListener(new OpenMenuListener());
		
		fileMenu.add(loadMenuItem);
		
		menuBar.add(fileMenu);
		frame.setJMenuBar(menuBar);
		
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		frame.setSize(400, 500);
		frame.setVisible(true);	
	}

	public class NextCardListener implements ActionListener{
		//Show the answer for the question or show the next question
		public void actionPerformed(ActionEvent ev)
		{
			if (isShowAnswer){
				display.setText(currentCard.getAnswer());
				nextButton.setText("Next Card");
				isShowAnswer = false;
			}else{
				if (cardIterator.hasNext()){
					showNextCard();
				}else{
					display.setText("That was last card");
					nextButton.setEnabled(false);
				}
			}
		}
	}

	public class OpenMenuListener implements ActionListener{
		//Bring up a file dialog
		//let the user navigate and choose card set
		public void actionPerformed(ActionEvent ev)
		{
			JFileChooser fileOpen = new JFileChooser();
			fileOpen.showOpenDialog(frame);
			loadFile(fileOpen.getSelectedFile());
		}
	}
	
	//Build an arraylist of cards from a text file
	//use makeCard() to turn these into flashcards
	private void loadFile(File file)
	{
		cardList = new ArrayList();
		try{
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null){
				makeCard(line);
			}
			reader.close();

		}catch (Exception ex){
			ex.printStackTrace();
		}
		cardIterator = cardList.iterator();
		showNextCard();
	}
	
	//Parse strings into flashcards and add to arraylist
	private void makeCard(String lineToParse)
	{
		StringTokenizer parser = new StringTokenizer(lineToParse, "/");
		if (parser.hasMoreTokens()){
			FlashCard card = new FlashCard(parser.nextToken(), parser.nextToken());
			cardList.add(card);
		}
	}
	
	private void showNextCard()
	{
		currentCard = (FlashCard) cardIterator.next();
		display.setText(currentCard.getQuestion());
		nextButton.setText("Show Answer");
		isShowAnswer = true;
	}
}
