import java.util.ArrayList;
public class ShipTest{

	public static void main(String args[])
	{
		Ship test = new Ship();
		
		ArrayList locations = new ArrayList();
		locations.add(1);
		locations.add(2);
		locations.add(3);
 
		test.setCells(locations);

		String userGuess = "2";
		String result = test.checkCell(userGuess);
		String testRet = "failed";

		if (result.equals("hit")){
			testRet = "passed";
		}
		
		System.out.println(testRet);
		System.out.println("New");
	}
}
