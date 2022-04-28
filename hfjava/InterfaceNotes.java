//An interface is like a completely abstract class that can be used for multiple inheritance
//eg
public interface Pet{
	public abstract void beFriendly();
	public abstract void Play();
}

public class Dog extends Animal implements Pet{
	//constants are static final like so
	public static final int T = 500;
	public void beFriendly()
	{
		//etc
		//Takes the superclass version of the method
		super.eat();
	}
	//Pass constructor arguments to superclasses like so
	public Dog(int x)
	{
		super(x);
	}
	//Or pass to the other class constructor
	public Dog()
	{
		//Calls Dog(int)
		this(5);
	}
}
