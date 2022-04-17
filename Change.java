import java.util.Scanner;

public class Change
{
	public static void main(String[] args)
	{
		int need_hund = 0;
		int need_fift = 0;
		int need_twen = 0;
		int need_ten = 0;
		int need_five = 0;
		int need_one = 0;
		boolean have_over_twen = true;
		
		Scanner inp = new Scanner(System.in);
		System.out.println("Do you have 50s and 100s? y/n");
		char response = inp.next().charAt(0);
		if (response == 'n')
			have_over_twen = false;
	
		System.out.println("Please enter the dollar amount you need:");
		float amount = inp.nextFloat();
		
		if (have_over_twen){
			while (amount >= 100){
				need_hund++;
				amount -= 100;
			}
			while (amount >= 50){
				need_fift++;
				amount -= 50;
			}
		}
		while (amount >= 20){
			need_twen++;
			amount -= 20;
		}
		while (amount >= 10){
			need_ten++;
			amount -= 10;
		}
		while (amount >= 5){
			need_five++;
			amount -= 5;
		}
		while (amount >= 1){
			need_one++;
			amount -= 1;
		}
		
		if (have_over_twen){
			System.out.println("You need " + need_hund + " hundreds,");
			System.out.println(need_fift + " fifties");
		}else{
			System.out.println("You need: ");
		}
		System.out.println(need_twen + " twenties");
		System.out.println(need_ten + " tens");
		System.out.println(need_five + " fives");
		System.out.println(need_one + " ones");
	}
}
