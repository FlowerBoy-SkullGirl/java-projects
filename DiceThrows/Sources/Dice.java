import java.util.*;

public class Dice{
	private int num_dice;
	private int num_sides;
	private int num_add;
	private final int MIN_ROLL = 1;
	private ArrayList<Integer> roll_results = new ArrayList<Integer>(); 
	
	Dice()
	{
		set_ndice(0);
		set_nsides(0);
		set_nadd(0);
	}

	public void set_ndice(int nd)
	{
		if (nd > 0)
			num_dice = nd;
	}

	public void set_nsides(int ns)
	{
		if (ns > 0)
			num_sides = ns;
	}

	public void set_nadd(int na)
	{
		num_add = na;
	}
	
	public void neg_mod()
	{
		num_add *= -1;
	}

	public ArrayList<Integer> get_arrl()
	{
		return roll_results;
	}
	public int gen_roll()
	{
		int total = 0;
		int temp = 0;
		if (!roll_results.isEmpty())
			roll_results.clear();
		for (int i = 0; i < num_dice; i++){
			temp = generate_rand_num(MIN_ROLL, num_sides);
			roll_results.add(temp);
			total += temp;
		}
		total += num_add;

		if (total < 1)
			return 1;

		return total;
	}
	
	private static int generate_rand_num(int min, int max)
	{
		return (int)(min + (Math.random() * ((max - min) + 1)));
	}
}
