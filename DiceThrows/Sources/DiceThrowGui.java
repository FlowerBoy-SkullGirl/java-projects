import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class DiceThrowGui{

	private JTextArea num_dice_text;
	private JTextArea num_sides_text;
	private JTextArea num_add_text;
	private JTextArea result;
	private JTextArea result_breakdown;
	private JTextArea result_without_low;
	private JButton plus_minus_button;
	private JTextArea result_advantage;

	public boolean negative = false;

	private JFrame frame;

	private Dice die;


	public static void main(String[] args)
	{
		DiceThrowGui gui = new DiceThrowGui();
		gui.go();
	}

	public void go()
	{
		die = new Dice();

		frame = new JFrame("DiceThrows");
		JPanel mainPanel = new JPanel();
		JPanel dicePanel = new JPanel();
		JPanel advantagePanel = new JPanel();
		num_dice_text = new JTextArea(1, 3);
		num_sides_text = new JTextArea(1, 3);
		num_add_text = new JTextArea("", 1, 3);
		result = new JTextArea("Result", 1, 32);
		result_breakdown = new JTextArea("Each die: ", 1, 32);
		result_without_low = new JTextArea("Without lowest roll: ", 1, 32);
		result_advantage = new JTextArea("With (dis)advantage: ", 2, 32);

		plus_minus_button = new JButton("+");
		JButton roll_button = new JButton("Roll!");
		JButton advantage_button = new JButton("Advantage");
		JButton disadvantage_button = new JButton("Disadvantage");

		dicePanel.setLayout(new BoxLayout(dicePanel, BoxLayout.X_AXIS));
		advantagePanel.setLayout(new BoxLayout(advantagePanel, BoxLayout.X_AXIS));


		dicePanel.add(num_dice_text);
		dicePanel.add(new JLabel("d"));
		dicePanel.add(num_sides_text);
		dicePanel.add(plus_minus_button);
		dicePanel.add(num_add_text);
		dicePanel.add(roll_button);

		advantagePanel.add(advantage_button);
		advantagePanel.add(disadvantage_button);

		mainPanel.add(dicePanel);
		mainPanel.add(result);
		mainPanel.add(result_breakdown);
		mainPanel.add(result_without_low);

		mainPanel.add(advantagePanel);

		mainPanel.add(result_advantage);
		
		plus_minus_button.addActionListener(new Plus_minusListener());
		roll_button.addActionListener(new Roll_buttonListener());
		advantage_button.addActionListener(new AdvantageListener());
		disadvantage_button.addActionListener(new DisadvantageListener());

		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		frame.setSize(500, 600);
		frame.setVisible(true);
	}
	
	public class Plus_minusListener implements ActionListener{
		public void actionPerformed(ActionEvent ev)
		{
			if (negative){
				negative = false;
				plus_minus_button.setText("+");
			}
			else if (!negative){
				negative = true;
				plus_minus_button.setText("-");
			}
		}
	}

	public class Roll_buttonListener implements ActionListener{
		public void actionPerformed(ActionEvent ev)
		{
			die.set_nadd(0);

			die.set_ndice(Integer.parseInt(num_dice_text.getText()));
			die.set_nsides(Integer.parseInt(num_sides_text.getText()));
			if (!num_add_text.getText().isEmpty()){
				die.set_nadd(Integer.parseInt(num_add_text.getText()));
			}else{
				die.set_nadd(0);
			}
			if (negative)
				die.neg_mod();

			result.setText(Integer.toString(die.gen_roll()));

			ArrayList<Integer> tempArrl = die.get_arrl();
			String tempStr = "Each die: ";
			int lowest_roll = Integer.parseInt(result.getText());
			for (int i = 0; i < tempArrl.size(); i++){
				tempStr += Integer.toString(tempArrl.get(i));
				if (!(i == tempArrl.size() - 1))
					tempStr += " + ";
				if (tempArrl.get(i) < lowest_roll)
					lowest_roll = tempArrl.get(i);
			}

			result_breakdown.setText(tempStr);

			
			result_without_low.setText("Without lowest roll: " + Integer.toString(Integer.parseInt(result.getText()) - lowest_roll));
		}
	}

	public class AdvantageListener implements ActionListener{
		public void actionPerformed(ActionEvent ev)
		{
			int old_result = Integer.parseInt(result.getText());
			int new_result = die.gen_roll();
			String adv_string = Integer.toString(old_result);

			if (old_result < new_result)
				adv_string = Integer.toString(new_result);

			result_advantage.setText("With advantage: " + Integer.toString(old_result) + "," + Integer.toString(new_result) + "\n" + adv_string);

		}
	}

	public class DisadvantageListener implements ActionListener{
		public void actionPerformed(ActionEvent ev)
		{
			int old_result = Integer.parseInt(result.getText());
			int new_result = die.gen_roll();
			String adv_string = Integer.toString(old_result);

			if (old_result > new_result)
				adv_string = Integer.toString(new_result);

			result_advantage.setText("With disadvantage: " + Integer.toString(old_result) + "," + Integer.toString(new_result) + "\n" + adv_string);

		}
	}
}
