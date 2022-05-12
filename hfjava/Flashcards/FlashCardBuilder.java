import java.util.*; //Uhhhhhh OH an arraylist maybe?
import java.awt.event.*; //event stuff
import javax.swing.*; //gui 
import java.awt.*; //Event listeners
import java.io.*; //File IO

public class FlashCardBuilder{
	
	private JTextArea question;
	private JTextArea answer;
	private ArrayList cardList;
	private JFrame frame;
	
	//build a gui
	public void go()
	{
		cardList = new ArrayList();
		frame = new JFrame("FlashCard Builder");
		JPanel mainPanel = new JPanel();
		Font bigFont = new Font("sansserif", Font.BOLD, 24);

		//Add the question and answer text sections
		question = new JTextArea(6, 20);
		question.setLineWrap(true);
		question.setWrapStyleWord(true);
		question.setFont(bigFont);
		
		JScrollPane qScroller = new JScrollPane(question);
		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		answer = new JTextArea(6, 20);
		answer.setLineWrap(true);
		answer.setWrapStyleWord(true);
		answer.setFont(bigFont);
		
		JScrollPane aScroller = new JScrollPane(answer);
		aScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		aScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		//Make a next button
		JButton nextButton = new JButton("Next Card");
		JLabel qLabel = new JLabel("Question:");
		JLabel aLabel = new JLabel("Answer:");
		
		//Build the main panel contents
		mainPanel.add(qLabel);
		mainPanel.add(qScroller);
		mainPanel.add(aLabel);
		mainPanel.add(aScroller);
		mainPanel.add(nextButton);
		nextButton.addActionListener(new NextCardListener());
	
		//File menu bar
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem newMenuItem = new JMenuItem("New");
		JMenuItem saveMenuItem = new JMenuItem("Save");
		
		newMenuItem.addActionListener(new NewMenuListener());
		saveMenuItem.addActionListener(new SaveMenuListener());
		fileMenu.add(newMenuItem);
		fileMenu.add(saveMenuItem);
		menuBar.add(fileMenu);
		
		frame.setJMenuBar(menuBar);
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		frame.setSize(500,600);
		frame.setVisible(true);
	}
	
	//Add card to list and clear text
	//Inner listener class
	public class NextCardListener implements ActionListener{
		public void actionPerformed(ActionEvent ev)
		{
			FlashCard card = new FlashCard(question.getText(), answer.getText());
			cardList.add(card);
			clearCard();
		}
	}
	
	//Inner class
	//Create a file dialog box and save box
	public class SaveMenuListener implements ActionListener{
		public void actionPerformed(ActionEvent ev)
		{
			FlashCard card = new FlashCard(question.getText(), answer.getText());
			cardList.add(card);
			
			JFileChooser fileSave = new JFileChooser();
			fileSave.showSaveDialog(frame);
			saveFile(fileSave.getSelectedFile());
		}
	}
	
	//Inner class
	//Clear out card list and text area
	public class NewMenuListener implements ActionListener{
		public void actionPerformed(ActionEvent ev)
		{
			cardList.clear();
			clearCard();
		}
	}
	
	private void clearCard()
	{
		question.setText("");
		answer.setText("");
		question.requestFocus();
	}
	
	//iterate through list of cards and write each, delimited
	private void saveFile(File file)
	{
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			Iterator cardIterator = cardList.iterator();
			while (cardIterator.hasNext()){
				FlashCard card = (FlashCard) cardIterator.next();
				writer.write(card.getQuestion() + "/"); //delimiter
				writer.write(card.getAnswer() + "\n"); //write a newline for each set
			}
			writer.close();
		}catch (IOException ex){
			System.out.println("Couldn't write the cardList");
			ex.printStackTrace();
		}
	}
}
