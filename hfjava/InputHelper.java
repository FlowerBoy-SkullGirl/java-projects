import java.io.*;
import java.util.*;
public class InputHelper{
	
	private static final String alphabet = "abcdefg";
	private int gridxy = 7;
	private int gridArea = 49;
	private int[] grid = new int[gridArea];
	private int shipCount = 0;

	public String getUserInput(String prompt)
	{
		String inputLine = null;
		System.out.print(prompt + " ");
		
		try{
			BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
			inputLine = is.readLine();
			if (inputLine.length() == 0)
				return null;
		}catch (IOException e){
			System.out.println("IOException: " + e);
		}
		return inputLine.toLowerCase();
	}
	
	public ArrayList placeShip(int shipSize)
	{
		ArrayList alphaCells = new ArrayList();
		String[] alphacoords = new String[shipSize];
		String temp = null;
		int[] coords = new int[shipSize];
		int attempts = 0;
		boolean found = false;
		int pos = 0;
		
		shipCount++;
		int incr = 1;
		//Place vertically if odd #'d ship
		if ((shipCount % 2) == 1){
			incr = gridxy;
		}
	
		//Search for an empty grid position
		while (!found & attempts++ < 200){
			pos = (int) (Math.random() * gridArea);
			int x = 0;
			found = true;
			while (found && x < shipSize){
				//If location has no value
				if (grid[pos] == 0){
					//Find adjacent positions
					coords[x++] = pos;
					pos += incr;
					if (pos >= gridArea){
						found = false;
					}
					if(x>0 & (pos % gridxy == 0)){
						found = false;
					}
				}else{
					found = false;
				}
			} //inner while
		} //outer while
		
		int x = 0;
		int row = 0;
		int column = 0;
		
		//For each ship pos
		while (x < shipSize) {
			//Mark the mask
			grid[coords[x]] = 1;
			row = (int) (coords[x] / gridxy); //Find how far into  the grid row is
			column = coords[x] % gridxy; //And how deep down
			temp = String.valueOf(alphabet.charAt(column)); //Make a string with both values in char-integer form
			
			alphaCells.add(temp.concat(Integer.toString(row)));
			x++;
		}
		
		return alphaCells;
	}	
}
