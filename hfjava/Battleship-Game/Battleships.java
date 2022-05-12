import java.util.ArrayList;
import java.util.*;
public class Battleships{

	private ArrayList ships = new ArrayList();
	private InputHelper helper = new InputHelper();
	private int numGuesses = 0;
	
	private void setUpGame()
	{
		//Make ship objects
		Ship target_one = new Ship();
		target_one.setName("Ship 1");
		Ship target_two = new Ship();
		target_two.setName("Ship 2");
		Ship target_three = new Ship();
		target_three.setName("Ship 3");
		
		//Add them to an arraylist
		ships.add(target_one);
		ships.add(target_two);
		ships.add(target_three);
		
		System.out.println("Try sinking every ship!");
		
		//For each space in the arrayList, place the ship on the grid
		for (int i = 0; i < ships.size(); i++){
			ArrayList pos = helper.placeShip(3);
			Ship shipToPlace = (Ship) ships.get(i);
			shipToPlace.setCells(pos);
		}
	}
	
	private void startGame()
	{
		//Until no ships, get user input and check it against Ship objects
		while(!ships.isEmpty()){
			String guess = helper.getUserInput("Enter a coordinate");
			checkGuess(guess);
		}
		finishGame();
	}
	
	private void checkGuess(String guess)
	{
		numGuesses++;
		String result = "miss";
		//For each Ship in ships, check if any have a matching pos to what the user entered
		for (int i = 0; i < ships.size(); i++){
			Ship checking = (Ship) ships.get(i);
			result = checking.checkCell(guess);
			if (result.equals("hit")){
				break;
			}
			if (result.equals("kill")){
				//Remove ships from array that are dead
				ships.remove(checking);
				break;
			}
		}
		System.out.println(result);
	}	

	private void finishGame()
	{
		int Max_good_guesses = 15;
		System.out.println("Game over!");
		if (numGuesses < Max_good_guesses)
			System.out.println("You guess fast!");
	}

	public static void main(String args[])
	{
		Battleships game = new Battleships();
		game.setUpGame();
		game.startGame();
	}
}
